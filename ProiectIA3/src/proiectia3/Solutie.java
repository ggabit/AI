package proiectia3;
import java.util.*;


public class Solutie {
    private BitSet bitset;
    
    public Solutie(BitSet sol){
        bitset=sol;
    }
    
    public BitSet getBitset(){
        return this.bitset;
    }
    
    public void printSol(List<Obiect> obiecte){
        System.out.println("Am adaugat in rucsac: ");
        for(int i=0;i<=bitset.length();i++){
            if(bitset.get(i)) System.out.println("obiectul "+(i+1)+" de valoare: "+obiecte.get(i).getValoare()+" si greutate: "+obiecte.get(i).getGreutate());
        }
    }
}
