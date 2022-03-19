package proiectia3;
import java.io.*;
import java.util.*;

public class HC_Rucsac extends InstantaRucsac{
    
    public HC_Rucsac(String fisier)throws Exception{
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
        Solutie c = getSolCandidat();
        int itMax = 100;
        int it=0;
        
        while(it<itMax){
            Solutie v = getBestVecin(c);
            if(evaluare(v)>evaluare(c))
                c=v;
            it++;
        }
        System.out.println("Costul obtinut: "+evaluare(c));
        System.out.println("Greutate ocupata: "+evaluareSp(c));
        c.printSol(obiecte);
    }
    
    public Solutie getBestVecin(Solutie s){
        BitSet b = new BitSet(n);
        Solutie v;
        List<BitSet> vecini = new ArrayList<BitSet>(); 
        for(int i=0;i<n;i++){
            b=s.getBitset();
            b.flip(i);
            v = new Solutie(b);
            if(evaluareSp(v)<=capacitate)
                vecini.add(b);
        }
        v = new Solutie(vecini.get(0));
        for(int i=1;i<vecini.size();i++){
            if(evaluare(new Solutie(vecini.get(i)))>evaluare(v))
                v=new Solutie(vecini.get(i));
            }
        return v;
    }
    
}
