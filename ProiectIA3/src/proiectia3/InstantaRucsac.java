
package proiectia3;

import java.util.*;

abstract class InstantaRucsac {
    int n; // nr obiecte existente
    double capacitate;  // capacitate rucsac
    List<Obiect> obiecte = new ArrayList<Obiect>();  // obiectele existente
    
    public double evaluare(Solutie s){
        double cost=0;
        for(int i=0;i<n;i++){
            if(s.getBitset().get(i))
                cost+=obiecte.get(i).getValoare();
        }
        return cost;
    }
    
    public double evaluareSp(Solutie s){
        double spatiu=0;
        for(int i=0;i<n;i++){
            if(s.getBitset().get(i))
                spatiu+=obiecte.get(i).getGreutate();
        }
        return spatiu;
    }
    
    public Solutie getSolCandidat(){
        BitSet bitset = new BitSet(n);
        Solutie sol;
        do{
            for(int i=0;i<n;i++){
            if (Math.random()<0.5)
                bitset.set(i);
            else bitset.set(i,false);
        }
        sol = new Solutie(bitset);
        }while(evaluareSp(sol)>capacitate);
        return sol;
    }
    
    public List<BitSet> getVecini(Solutie s){
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
        return vecini;
    }
    
    abstract void gasesteSolutie();
}
