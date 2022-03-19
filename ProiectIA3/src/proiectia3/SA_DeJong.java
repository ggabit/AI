package proiectia3;

import static java.lang.Math.exp;
import static java.lang.Math.pow;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class SA_DeJong {
    private int n; //
    private double s,d; // intervalul
            
    public SA_DeJong(int n, double s, double d){
        this.n=n;
        this.s=s;
        this.d=d;
    }
    
    public void gasesteSolutie(){
        SolutieDJ c = getSolCandidat();
        int itMax = 100;
        int it=0;
        int t=0;
        double T=1000;
        
        while(T>0){
            while(it<itMax){
            SolutieDJ v = getRandVecin(c);
            if(evaluare(v)<evaluare(c))
                c=v;
            else if(Math.random()<P(evaluare(v),evaluare(c),T))
                c=v;
            it++;
            }
            T=g(T,t);
            t++;
            //System.out.println(T);
        }
        System.out.println("Minimul f obtinut : "+evaluare(c));
        System.out.println("Solutia x : ("+decodificare(c)[0]+", "+decodificare(c)[1]+")");
    }
    
    public double P(double ev, double ec, double T){
        if(ev<ec) return 1;
        return exp(-(ev-ec)/T);
    }
    
    public double g(double T, int t){ //cooling schedule
        //System.out.println(T);
        //return 0.99999*T;
        return T-0.000001;
        //return 1000/(1+exp(pow(10,-5)*(t-5*pow(10,5))));
        
        //return 500*cos(t/16753)+500;
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
    
    public SolutieDJ getRandVecin(SolutieDJ sol){
        BitSet b;
        Random rand = new Random();
        int i=rand.nextInt(2*n-1);
        b=sol.getBitset();
        b.flip(i);
        SolutieDJ rv = new SolutieDJ(b);
        return rv;
    }
}
