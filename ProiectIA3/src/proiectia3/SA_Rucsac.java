package proiectia3;
import java.io.*;
import static java.lang.Math.*;
import java.util.*;

public class SA_Rucsac extends InstantaRucsac{ //Simulated Annealing
    
    public SA_Rucsac(String fisier)throws Exception{
      Scanner sc = new Scanner(new BufferedReader(new FileReader(fisier)));
      
      String[] line = sc.nextLine().trim().split(" ");
      this.n = Integer.parseInt(line[0]);
      this.capacitate = Float.parseFloat(line[1]);
      
      while(sc.hasNextLine()) {
         for (int i=0; i<n; i++) {
            line = sc.nextLine().trim().split(" ");
            Obiect o = new Obiect(Float.parseFloat(line[0]),Float.parseFloat(line[1]));
            this.obiecte.add(o);
         }
      }
    }
    
    public void gasesteSolutie(){
        int t=0;
        double T=1000;
        Solutie c = getSolCandidat();
        int itMax = 100;
        int it=0;
        
        while(T>0){
            while(it<itMax){
            Solutie v = getRandVecin(c);
            if(evaluare(v)>evaluare(c))
                c=v;
            else if(Math.random()<P(evaluare(v),evaluare(c),T))
                c=v;
            it++;
            }
            T=g(T,t);
            t++;
            //System.out.println(T);
        }
        System.out.println("Costul obtinut: "+evaluare(c));
        System.out.println("Greutate ocupata: "+evaluareSp(c));
        c.printSol(obiecte);
    }
    
    public double P(double ev, double ec, double T){
        if(ev>ec) return 1;
        return exp(-(ev-ec)/T);
    }
    
    public double g(double T, int t){ //cooling schedule
        //System.out.println(T);
        //return 0.99999*T;
        return T-0.000001;
        //return 1000/(1+exp(pow(10,-5)*(t-5*pow(10,5))));
        
        //return 500*cos(t/16753)+500;
    }
    
    public Solutie getRandVecin(Solutie sol){
        BitSet b;
        Solutie rv;
        Random rand = new Random();
        int i=rand.nextInt(n);
        do{
            b=sol.getBitset();
            b.flip(i);
            rv = new Solutie(b);
        }while(evaluareSp(rv)>capacitate);
        return rv;
    }
}
