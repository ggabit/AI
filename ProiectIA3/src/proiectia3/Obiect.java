package proiectia3;

public class Obiect {
    private double greutate;
    private double valoare;
    
    public Obiect(double v, double g){
        greutate=g;
        valoare=v;
    }
    
    public double getValoare(){
        return valoare;
    }
    
    public double getGreutate(){
        return greutate;
    }
}
