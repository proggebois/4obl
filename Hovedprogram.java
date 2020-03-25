import java.util.Scanner;
import java.io.File;

class Hovedprogram{

    public static void main(String[] args){
        /*Skrive ut en fullstendig oversikt over pasienter, leger, legemidler og resepter
        Side 4 av 7
        (deloppgave E3).
        Opprette og legge til nye elementer i systemet (deloppgave E4).
        Bruke en gitt resept fra listen til en pasient (deloppgave E5).
        Skrive ut forskjellige former for statistikk (deloppgave E6).
        Skrive alle data til fil (deloppgave E8).
        */

        System.out.println("Tast inn filnavn for innlesing av data: \n");
        Scanner inputFil = new Scanner(System.in);
        Legesystem system = new Legesystem(inputFil.nextLine());
        //System.out.println(system);

        system.kjor();



    }
}
