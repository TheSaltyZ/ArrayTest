/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

/**
 *
 * @author Nick
 */
import java.util.*;

public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Random rand = new Random();

        double[][] people = new double[3][10];         //de begin populatie [drie toestanden] [tien leeftijdscategorieën]        
        int turnsgiven, populatie, turns = 0;                                
        double alfa = 0, beta = 0, gamma = 0;
        
        
        System.out.println("Hoe groot wordt de populatie? ");
        populatie = keyboard.nextInt();
                                                                                          // populatie kan onevenredig verdeeld worden
            for(int j = 0; j < 10; j++) people[0][j] = people[0][j] + (0.1)*populatie;   // beginpopulatie verdelen over 10-S leeftijdscategorieen
                                                                  
                                                                                                                          
        System.out.print("Hoeveel turns wil je draaien? ");
        turnsgiven = keyboard.nextInt();
                
        System.out.print("Wat wordt de kans op besmet worden? (beta)");
        beta = keyboard.nextDouble();

        System.out.print("Wat wordt de kans op resistent worden? (gamma)");
        gamma = keyboard.nextDouble();
        
        System.out.print("Wat wordt de kans op weer vatbaar worden? (alfa)");
        alfa = keyboard.nextDouble();
        
        patientZero(people);
        
       /*System.out.println("CHECK");
        for(int i = 0; i < 3; i++){                                                             //display patient zero matrix
            for(int j = 0; j < 10; j++){
                System.out.println("status: " + (i+1) + "  leeftijdscategorie: " + (j+1));
                System.out.println(people[i][j]);
            }
            
        }  */
        
        
        for(int i = 1; i <= turnsgiven; i++){                                                   // de turns
            turns++;
            interactie(people, beta, gamma, alfa, turns, populatie);

        }
        
        
        
        System.out.println();
        System.out.println();
        System.out.println();
        
        
        System.out.println("Resultaten: ");
           System.out.println();     
        for(int i = 0; i < 3; i++){
             for (int j = 0; j < 10; j++){
                 System.out.println("status: " + (i+1) + "  leeftijdscategorie: " + (j+1));
                 System.out.println("Aantal mensen: " + Math.round(people[i][j]));
                 
                 
                 
             }
        }
        
        
        
        

    }

    public static void patientZero(double[][] peopleIn) {
        Scanner keyboard = new Scanner(System.in);
        Random rand = new Random();

        int PatZero;
        int idx;

        System.out.print("Hoeveel patientzero's wil je? ");
        PatZero = keyboard.nextInt();

        for (int i = 0; i < PatZero; i++) {
            idx = rand.nextInt(10);
            peopleIn[0][idx] = peopleIn[0][idx] - 1;
            peopleIn[1][idx] = peopleIn[1][idx] + 1;

        }
    }

    public static void interactie(double[][] peopleIn, double beta, double gamma, double alfa, int turns, int populatie) {

        double SumInfected = 0, n1, n2, n3, n4;

        for (int i = 0; i < 10; i++) {
            SumInfected = SumInfected + peopleIn[1][i];                                 // som alle geinfecteerden
        }
                                                                                        
        n1 = 1 - Math.pow(beta, 50 * (SumInfected / populatie));                            // kans op besmet worden door interactie I
        n2 = 1 - Math.pow(gamma, 50 * (SumInfected / populatie));                            // kans op resistent worden door interactie I
        n3 = 1 - Math.pow(alfa, 50 * (SumInfected / populatie));
        
        if (Math.random() < n1) {                                                       // eerste random funcie voor besmetten
            for (int i = 0; i < 10; i++) {
                n4 = (0.1 * n1) * peopleIn[0][i]; 
                peopleIn[0][i] = peopleIn[0][i] - n4;                                   // mensen S - mensen naar i
                peopleIn[1][i] = peopleIn[1][i] + n4;                                   // mensen I + mensen naar i

            }
        }
        if (Math.random() < n1) {                                                        // tweede random functie voor resistenten
            for (int i = 0; i < 10; i++) {
                n4 = (0.1 * n2) * peopleIn[1][i];
                peopleIn[1][i] = peopleIn[1][i] - n4;                                    // mensen I - mensen naar R
                peopleIn[2][i] = peopleIn[2][i] + n4;                                    // mensen R + mensen naar R

            }

        }

        if(turns%2 == 0 ){                                                              // om drie beurten
            for (int i = 0; i < 10; i++){
                n4 = (0.1 * n3) * peopleIn[2][i];
                peopleIn[2][i] = peopleIn[2][i] - n4;                                   // mensen R - mensen naar S
                peopleIn[0][i] = peopleIn[0][i] + n4;                                   // mensen S + mensen naar S
                
            }
            
            
        }
        
        
        
        
    }


    

}
