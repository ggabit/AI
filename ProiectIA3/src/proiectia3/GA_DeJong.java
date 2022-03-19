package proiectia3;

import static java.lang.Math.pow;
import java.util.*;

public class GA_DeJong {
    private int n; //
    private double s,d; // intervalul
    private int dimPop;
    
    public GA_DeJong(int n, double s, double d, int dimPop){
        this.n=n;
        this.s=s;
        this.d=d;
        this.dimPop=dimPop;
    }
    
    public void gasesteSolutie(){
        int t=0;
        int nrGen=500;
        double ri = 0.25;
        double rm = 0.01;
        ArrayList<SolutieDJ> pop=getPopInit(); // initializare populatie
        SolutieDJ bestI=pop.get(0);
        
        while(t<nrGen){
            t++;
            double fit[] = new double[dimPop];
            double p[] = new double[dimPop];
            double S=0;
            for(int i=0;i<dimPop;i++){
                fit[i]=fitness(pop.get(i)); // fitness-ul fiecarui individ
                S+=fit[i];                     // suma fitness-urilor
            }
            for(int i=0;i<dimPop;i++){
                p[i]=fit[i]/S;
            }
            pop=selectie(p,pop);
            
            // operatori
            // mutatie
            pop=mutatie(pop,rm);
            
            // incrucisare
            ArrayList<SolutieDJ> urmasi = new ArrayList<SolutieDJ>();
            while(urmasi.size()<dimPop){
                SolutieDJ C1=pop.get(0);
                SolutieDJ C2=pop.get(0);
                for(int i=0;i<dimPop;i++){
                    if(Math.random()<ri){
                        C1=pop.get(i);
                        //pop.remove(i);
                        break;
                    }
                }
                for(int i=0;i<dimPop;i++){
                    if(Math.random()<ri){
                        C2=pop.get(i);
                        //pop.remove(i);
                        break;
                    }
                }
                ArrayList<SolutieDJ> copii=incrucisare(C1,C2);
                urmasi.add(copii.get(0));
                urmasi.add(copii.get(1));
            }
            pop=urmasi;
            // evaluare
            if(fitness(getBestIndivid(pop))<fitness(bestI))
                bestI=getBestIndivid(pop);
        }
        System.out.println("Minimul f obtinut : "+fitness(bestI));
        System.out.println("Solutia x : ("+decodificare(bestI)[0]+", "+decodificare(bestI)[1]+")");
    }
    
    public SolutieDJ getBestIndivid(ArrayList<SolutieDJ> pop){
        SolutieDJ C = pop.get(0);
        for(int i=1;i<dimPop;i++){
            if(fitness(C)<fitness(pop.get(i)))
                C=pop.get(i);
        }
        return C;
    }
    
    public ArrayList<SolutieDJ> incrucisare(SolutieDJ C1, SolutieDJ C2){
        Random rand = new Random();
        int k= rand.nextInt(n);
        BitSet copiiBs[] = new BitSet[2]; 
        copiiBs[0]=C1.getBitset(); // initializez cu o valoare oarecare
        copiiBs[1]=C1.getBitset();
        for(int i=0;i<n;i++){
            if(i<k){
                copiiBs[0].set(i, C1.getBitset().get(i));
                copiiBs[1].set(i, C2.getBitset().get(i));
            }
            else{
                copiiBs[0].set(i, C2.getBitset().get(i));
                copiiBs[1].set(i, C1.getBitset().get(i));
            }
        }
        SolutieDJ O1 = new SolutieDJ(copiiBs[0]);
        SolutieDJ O2 = new SolutieDJ(copiiBs[1]);
        ArrayList<SolutieDJ> copii=new ArrayList<SolutieDJ>();
        copii.add(O1);
        copii.add(O2);
        return copii;
    }
    
    public ArrayList<SolutieDJ> mutatie(ArrayList<SolutieDJ> pop, double rm){
        for(int i=0;i<pop.size();i++){
            if(Math.random()<rm){           //decid daca fac mutatia individului
                Random rand = new Random();
                int k= rand.nextInt(n);     // aleg gena
                pop.get(i).getBitset().flip(k);
            }
        }
        return pop;
    }
    
    public ArrayList<SolutieDJ> selectie(double p[], ArrayList<SolutieDJ> pop){
        ArrayList<SolutieDJ> selectati=new ArrayList<SolutieDJ>();
        double q[] = new double[dimPop];
        for(int i=0;i<dimPop;i++){  // pt fiecare individ
            q[i]=0;
            for(int j=0;j<=i;j++){
                q[i]+=p[j];
            }
        }
        int k=0;
        while(k<dimPop){
            double r=Math.random();
            for(int i=0;i<dimPop;i++){
                if(r>q[i] && r<q[i+1])
                    selectati.add(pop.get(i));
            }
            k++;
        }
        //System.out.println(selectati.size());
        return selectati;
    }
    
    public ArrayList<SolutieDJ> getPopInit(){
        ArrayList<SolutieDJ> pop = new ArrayList<SolutieDJ>();
        for(int i=0;i<dimPop;i++){
            pop.add(getCromRand());
        }
        return pop;
    }
    
    public SolutieDJ getCromRand(){
        BitSet x1 = new BitSet(n);
        BitSet x2 = new BitSet(n);
        BitSet x = new BitSet(2*n);
        for(int i=0;i<n;i++){
            if (Math.random()<0.5){
                x1.set(i);
                x.set(i);
            }
            else{
                x1.set(i,false);
                x.set(i,false);
            }

            if (Math.random()<0.5){
                x2.set(i);
                x.set(i+n);
            }
            else{
                x2.set(i,false);
                x.set(i+n,false);
            }
        }
        SolutieDJ sol = new SolutieDJ(x);
        return sol;
    }
    
    public double fitness(SolutieDJ s){
        double valoare=0;
        valoare = pow(decodificare(s)[0],2) + pow(decodificare(s)[1],2);
        return valoare;
    }
    
    public  double[] decodificare(SolutieDJ sol){
        double[] x = {0,0};
        int k=n-1;
        for(int i=0;i<n;i++){
            if(sol.getBitset().get(i))
                x[0] += pow(2,k);
            k--;
        }
        k=2*n-1;
        for(int i=n;i<n*2;i++){
            if(sol.getBitset().get(i))
                x[1] += pow(2,k);
            k--;
        }
        x[0] = s + x[0]*(d-s)/(pow(2,n)-1);
        x[1] = s + x[1]*(d-s)/(pow(2,n)-1);
        return x;
    }
}
