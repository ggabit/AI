package proiectia3;

import static java.lang.Math.pow;
import java.util.*;

public class HC_DeJong {
    private int n; // nr biti xi
    private double s,d; // intervalul
            
    public HC_DeJong(int n, double s, double d){
        this.n=n;
        this.s=s;
        this.d=d;
    }
    
    public void gasesteSolutie(){
        SolutieDJ c = getSolCandidat();
        int itMax = 100;
        int it=0;
        
        while(it<itMax){
            SolutieDJ v = getBestVecin(c);
            if(evaluare(v)<evaluare(c))
                c=v;
            it++;
        }
        System.out.println("Minimul f obtinut : "+evaluare(c));
        System.out.println("Solutia x : ("+decodificare(c)[0]+", "+decodificare(c)[1]+")");
    }
    
    public double evaluare(SolutieDJ s){
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
    
    public SolutieDJ getSolCandidat(){
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
    
    public List<BitSet> getVecini(SolutieDJ sol){
        BitSet b = new BitSet(2*n);
        List<BitSet> vecini = new ArrayList<BitSet>(); 
        for(int i=0;i<n*2;i++){
            b=sol.getBitset();
            b.flip(i);
            vecini.add(b);
        }
        return vecini;
    }
    
    public SolutieDJ getBestVecin(List<BitSet> vecini){
        SolutieDJ bv = new SolutieDJ(vecini.get(0));
        for(int i=1;i<vecini.size();i++){
            if(evaluare(new SolutieDJ(vecini.get(i)))<evaluare(bv))
                bv=new SolutieDJ(vecini.get(i));
            }
        return bv;
    }
    
    public SolutieDJ getBestVecin(SolutieDJ s){
        BitSet b = new BitSet(n);
        SolutieDJ v;
        List<BitSet> vecini = new ArrayList<BitSet>(); 
        for(int i=0;i<n;i++){
            b=s.getBitset();
            b.flip(i);
            v = new SolutieDJ(b);
            vecini.add(b);
        }
        v = new SolutieDJ(vecini.get(0));
        for(int i=1;i<vecini.size();i++){
            if(evaluare(new SolutieDJ(vecini.get(i)))>evaluare(v))
                v=new SolutieDJ(vecini.get(i));
            }
        return v;
    }
}
