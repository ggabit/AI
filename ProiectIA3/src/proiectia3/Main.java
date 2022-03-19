
package proiectia3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {

    public static void main(String[] args)throws Exception{
        // TODO code application logic here
        //file: f7_l-d_kp_7_50
        /*
        HC_Rucsac I = new HC_Rucsac("D:\\IA\\Lab\\Lab7\\low-dimensional\\f10_l-d_kp_20_879");
        I.gasesteSolutie();
        SA_Rucsac I = new SA_Rucsac("D:\\IA\\Lab\\Lab7\\low-dimensional\\f10_l-d_kp_20_879");
        I.gasesteSolutie();*/

        String file="f10_l-d_kp_20_879.txt";
        Scanner sc = new Scanner(new BufferedReader(new FileReader("D:\\IA\\Lab\\Lab7\\low-dimensional-optimum\\"+file)));
        int cost = Integer.parseInt(sc.nextLine());

        Boolean ok=true;
        while(ok){
            System.out.println("Alegeti algoritmul dorit: ");
            System.out.println("1 - Hill Climbing");
            System.out.println("2 - Simulated Annealing");
            System.out.println("3 - HC f");
            System.out.println("4 - SA f");
            System.out.println("5 - GA f");
            System.out.println("6 - STOP");

            Scanner console = new Scanner(System.in);
            int x = console.nextInt();

            switch (x) {
              case 1:
                //HillClimbing
                System.out.println("Hill Climbing");
                System.out.println("Cost optim: "+cost);
                InstantaRucsac I1 = new HC_Rucsac("D:\\IA\\Lab\\Lab7\\low-dimensional\\"+file);
                I1.gasesteSolutie();
                break;
              case 2:
                //SA
                System.out.println("Simulated Annealing");
                System.out.println("Cost optim: "+cost);
                InstantaRucsac I2 = new SA_Rucsac("D:\\IA\\Lab\\Lab7\\low-dimensional\\"+file);
                I2.gasesteSolutie();
                break;
              case 3:
                //HillClimbing f
                System.out.println("Hill Climbing pt functia f");
                HC_DeJong I3 = new HC_DeJong(24,-5.12,5.12);
                I3.gasesteSolutie();
                break;
              case 4:
                //Simulated Annealing f
                System.out.println("Simulated Annealing pt functia f");
                SA_DeJong I4 = new SA_DeJong(24,-5.12,5.12);
                I4.gasesteSolutie();
                break;
              case 5:
                //GA f
                System.out.println("Simulated Annealing pt functia f");
                GA_DeJong I5 = new GA_DeJong(24,-5.12,5.12,50); // dimPop=50
                I5.gasesteSolutie();
                break;
              case 6:
                ok=false;
                break;
              default:
                System.out.println("Numar invalid. ");
            }
        }
    }

}
