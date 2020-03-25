import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

class Legesystem{

    Lenkeliste<Pasient> pasienter;
    Lenkeliste<Legemiddel> legemidler;
    Lenkeliste<Lege> leger;
    Lenkeliste<Resept> resepter;

    public Legesystem(String filnavn){
        pasienter = new Lenkeliste<Pasient>();
        legemidler = new Lenkeliste<Legemiddel>();
        leger = new Lenkeliste<Lege>();
        resepter = new Lenkeliste<Resept>();
        lesFrafil(filnavn);
    }

    public void kjor(){

        String menyVelger = "0";
        while (!(menyVelger.compareTo("5") == 0)){
            skrivHovedmeny();
            Scanner input = new Scanner (System.in);
            menyVelger = input.nextLine();

            if (menyVelger.compareTo("0") == 0){
                System.out.println(this);
            }

            else if (menyVelger.compareTo("1") == 0){
                opprettNy();
            }

            else if (menyVelger.compareTo("2") == 0){
                brukResept();
            }

            else if (menyVelger.compareTo("3") == 0){
                skrivStatistikk();
            }
/*
            else if (menyVelger.compareTo("4") == 0){
                skrivTilFilMeny();
            }
*/
        }

    }

    public void skrivTilFilMeny(){
        Scanner scannerSkrivTilFil = new Scanner(System.in);

        System.out.println("\n\n - Meny for aa skrive til fil - \n\n");
        System.out.println("Denne funksjonen skriver all data til en txt fil.")
        System.out.println("Tast 'ja' for aa fortsett: ")

        String filnavn = scannerSkrivTilFil.nextLine();

        if (filnavn.toLowerCase().compareTo("ja") == 0){
            System.out.println("Tast inn onsket filnavn, format 'filnavn.txt' \n");
            filnavn = scannerSkrivTilFil.nextLine();
            skrivTilFil(filnavn);
        }
    }

    public void skrivTilFil(String filnavn){
        File nyFil = null;
        try {
            nyFil = new File(filnavn);
            if (nyFil.createNewFile()){
                System.out.println("\nOpprettet en ny fil, filnavn: " + filnavn + "\n");
            }
            else {
                System.out.println("Filen finnes fra foer.");
            }
        } catch(IOException e) {
            System.out.println("Det skjedde en feil.");
        }

        //Gjoere ting med fil.
    }

    public void skrivHovedmeny(){
        String hovedMeny = " - Legesystem hovedmeny - \n";
        hovedMeny += "0 - Skriv ut oversikt over pasienter, leger, legemidler og resepter. \n";
        hovedMeny += "1 - Opprette og legge til nye elementer i systemet.\n";
        hovedMeny += "2 - Bruk en gitt resept fra listen til en pasient.\n";
        hovedMeny += "3 - Skriv ut forskjellige former for statistikk.\n";
        hovedMeny += "4 - Skriv alle data til fil.\n";
        hovedMeny += "5 - Tast 5 for å avslutte.\n";
        System.out.println(hovedMeny);
    }

    public void opprettNy(){
        System.out.println("Opprett nytt element");
        String alternativer = "0 - Tast 0 for å opprette ny lege\n";
        alternativer += "1 - Tast 1 for å opprette ny pasient\n";
        alternativer += "2 - Tast 2 for å opprette ny resept\n";
        alternativer += "3 - Tast 3 for å opprette nytt legemiddel\n";
        System.out.println(alternativer);
        Scanner opprettNyVelger = new Scanner(System.in);
        String velger = opprettNyVelger.nextLine();

        if (velger.compareTo("0") == 0){
            System.out.println("Tast inn info på ny Lege\n");
            System.out.println("Format: 'navn,kontrollID' , kontrollID = 0 hvis vanlig lege\n");
            String legeInfo = opprettNyVelger.nextLine();//vet ikke om jeg trenger ny scanner her
            String[] legeBiter = legeInfo.split(",");

            try {
                this.nyLege(legeBiter[0],legeBiter[1]);
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Riktig format for lege er 'navn,kontrollID'.");
            } catch(NumberFormatException e){
                System.out.println("KontrollIDen kunne ikke parces til int.");
            }
        }

        if (velger.compareTo("1") == 0){
            System.out.println("Tast inn info på ny pasient\n");
            System.out.println("Format: 'navn,fnr'.\n");
            String pasientInfo = opprettNyVelger.nextLine();//trenger kanskje ny scanner
            String[] pasientBiter = pasientInfo.split(",");

            try {
                this.nyPasient(pasientBiter[0],pasientBiter[1]);
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Riktig format for pasient er 'navn,fnr'.");
            }
        }

        if (velger.compareTo("2") == 0){
            System.out.println("Tast inn info på ny resept\n");
            System.out.println("Format: 'legemiddelNummer,legeNavn,pasientID,type,[reit]'.\n");
            String reseptInfo = opprettNyVelger.nextLine();//trenger kanskje ny Scanner
            String[] reseptBiter = reseptInfo.split(",");
            if (reseptBiter.length == 5){
                nyResept(reseptBiter[3], reseptBiter[0], reseptBiter[1], reseptBiter[2], reseptBiter[4]);
            }
            else if (reseptBiter.length == 4){
                nyResept(reseptBiter[0], reseptBiter[1], reseptBiter[2]);
            }
            else {
                System.out.println("Feil format.\n Riktig format er: 'legemiddelNummer,legeNavn,pasientID,type,[reit]'.");
            }
        }

        if (velger.compareTo("3") == 0){
            System.out.println("Skriv inn info på nytt legemiddel\n");
            System.out.println("Format: 'navn,type,pris,virkestoff,[styrke]'.");
            String legemiddelInfo = opprettNyVelger.nextLine();
            String[] legemiddelBiter = legemiddelInfo.split(",");
            if (legemiddelBiter.length == 4){
                nyLegemiddel(legemiddelBiter[0], legemiddelBiter[2], legemiddelBiter[3]);
            }
            else if (legemiddelBiter.length == 5){
                nyLegemiddel(legemiddelBiter[0], legemiddelBiter[1], legemiddelBiter[2], legemiddelBiter[3], legemiddelBiter[4]);
            }
            else {
                System.out.println("Format ikke godkjent.");
            }
        }
    }

    public void brukResept(){
        Scanner reseptMenyVelger = new Scanner(System.in);
        System.out.println("Hvilken pasient vil du se resepter for?");
        for (Pasient pasient : pasienter){
            System.out.println("" + pasient.hentId() + ": " + pasient.hentNavn() + " (" + pasient.hentFnr() + ")\n");
        }

        boolean godkjent = false;
        int index = 0;
        String valg = "hei";
        Pasient gjeldende = null;

        while (godkjent == false){
            valg = reseptMenyVelger.nextLine();
            try {
                index = Integer.parseInt(valg);
                gjeldende = pasienter.hent(index);
                godkjent = true;//Denne linjen kjører ikke hvis vi faar exception!
            } catch(NumberFormatException e) {
                System.out.println("Du skulle taste inn tallet bruh");
            } catch (UgyldigListeIndeks e){
                System.out.println("Ugyldig index...");
            }
        }

        if (gjeldende.hentResepter().stoerrelse() == 0){
            System.out.println("Pasienten har ingen resepter.");
        }
        else {
            System.out.println("Valgt pasient: \n" + gjeldende);
            System.out.println("Hvilken resept vil du bruke?");
            int teller = 0;
            for (Resept r : gjeldende.hentResepter()){
                System.out.println(""+ teller + ": " + r.hentLegemiddel().hentNavn() + " (" + r.hentReit() + ")\n");
                teller++;
            }

            Resept gjeldendeResept = null;
            godkjent = false;
            while (godkjent == false){
                System.out.println("vedder paa at denne kjorer");
                valg = reseptMenyVelger.nextLine();
                try {
                    index = Integer.parseInt(valg);
                    gjeldendeResept = gjeldende.hentResepter().hent(index);
                    godkjent = true;
                } catch(NumberFormatException e) {
                    System.out.println("Du skulle taste inn et tall.");
                } catch(UgyldigListeIndeks e){
                    System.out.println("Ugyldig index til reseptlisten.");
                }
            }
            boolean resultat = gjeldendeResept.bruk();
            if (resultat == false){
                System.out.println("Kunne ikke bruke resept paa " + gjeldendeResept.hentLegemiddel().hentNavn() + " (ingen gjenvaerende reit).");
            }
            else {
                System.out.println("Bruk resept paa " + gjeldendeResept.hentLegemiddel().hentNavn() + " var vellykket. Gjenvaerende reit: " + gjeldendeResept.hentReit());
            }
        }
    }

    public void skrivStatistikk(){
        //Her kommer en undermeny for aa velge type statistikk
        System.out.println("\n\n - Meny for utskrift av statistikk - ");
        String alternativer = "0 - Tast 0 for totalt antall utskrevne resepter paa vanedannende legemidler\n";
        alternativer += "1 - Tast 1 for totalt antall utskrevne resepter paa narkotiske legemidler\n";
        alternativer += "2 - Tast 2 for antall resepter paa narkotiske legemidler per lege\n";
        alternativer += "3 - Tast 3 for antall resepter paa narkotiske legemidler per pasient\n";
        alternativer += "Alt annet sender deg tilbake til hovedmenyen";
        System.out.println(alternativer);
        Scanner nyVelger = new Scanner(System.in);
        String velger = nyVelger.nextLine();

        //Skriver ut totalt antall utskrevne resepter på vanedannende
        if (velger.compareTo("0") == 0){
            int vaneTeller = 0;
            for (Resept r : this.resepter){
                if (r.hentLegemiddel() instanceof Vanedannende){
                    vaneTeller++;
                }
            }
            System.out.println("\n\nTotalt antall resepter paa vanedannende legemidler: " + vaneTeller + "\n\n");
        }

        //Skriver ut totalt antall utskrevne resepter på narkotiske
        if (velger.compareTo("1") == 0){
            int narkoTeller = 0;
            for (Resept r : this.resepter){
                if (r.hentLegemiddel() instanceof Narkotisk){
                    narkoTeller++;
                }
            }
            System.out.println("\n\nTotalt antall resepter paa narkotiske legemidler: " + narkoTeller + "\n\n");
        }

        //Per lege, utskrevne resepter paa narkotisk:
        if (velger.compareTo("2") == 0){
            SortertLenkeliste<Lege> sorterteLeger = new SortertLenkeliste<Lege>();
            for (Lege lege : leger){
                sorterteLeger.leggTil(lege);
            }
            int narkotiskTeller = 0;
            for (Lege lege : sorterteLeger){
                narkotiskTeller = 0;
                for (Resept r : lege.hentUtskrevedeResepter()){
                    if (r.hentLegemiddel() instanceof Narkotisk){
                        narkotiskTeller++;
                    }
                }
                if (narkotiskTeller > 0){
                    System.out.println("\n\nLege: " + lege.hentNavn() + "\nAntall utskrevne: " + narkotiskTeller + "\n\n");
                }
            }
        }

        //Per pasient, utskrevne resepter paa narkotisk
        if (velger.compareTo("3") == 0){
            int narkotiskTeller = 0;
            for (Pasient p : this.pasienter){
                narkotiskTeller = 0;
                for (Resept r : p.hentResepter()){
                    if (r.hentLegemiddel() instanceof Narkotisk){
                        narkotiskTeller++;
                    }
                }
                if (narkotiskTeller > 0){
                    System.out.println("\n\nPasient: " + p.hentNavn() + "\nAntall utskrevne: " + narkotiskTeller + "\n\n");
                }
            }
        }


    }


    public String toString(){
        String penStreng = "All data: Pasienter - Legemidler - Leger - Resepter\n";
        penStreng += "Pasienter:\n";

        for (Pasient p : pasienter){
            penStreng += p + "\n";
        }

        penStreng += "\nLegemidler:\n";
        for (Legemiddel legemiddel : legemidler){
            penStreng += legemiddel + "\n";
        }

        penStreng += "\nLeger:\n";
        SortertLenkeliste<Lege> sorterteLeger = new SortertLenkeliste<Lege>();
        for (Lege lege : leger){
            sorterteLeger.leggTil(lege);
        }
        for (Lege lege : sorterteLeger){
            penStreng += lege + "\n";
        }

        penStreng += "\nResepter:\n";
        for (Resept resept : resepter){
            penStreng += resept;
        }
        return penStreng;
    }


    //Apner fil, lager nye objekter og legger de i sine respektive lister v/ bruk av 4 while-lokker
    public void lesFrafil(String filnavn){
        Scanner fil = null;//denne er viktig, uten den har vi ikke fil-variabelen etter try/catch
        try {
            fil = new Scanner(new File(filnavn));
        } catch(Exception e) {
            System.out.println("Finner ikke fil");
        }

        String linje = fil.nextLine();//Legger "# Pasienter (navn, fnr)" i linje
        linje = fil.nextLine();//Legger forste pasient i linje

        //Pasientlokke:
        while(linje.charAt(0) != '#'){
            String[] biter = linje.split(",");
            nyPasient(biter[0], biter[1]);
            linje = fil.nextLine();
        }

        //Legemiddellokke:
        linje = fil.nextLine();//Legger forste legemiddel i linje
        while(linje.charAt(0) != '#'){
            String[] biter = linje.split(",");
            if (biter.length == 4){
                nyLegemiddel(biter[0], biter[2], biter[3]);
            }
            else if (biter.length == 5){
                nyLegemiddel(biter[0], biter[1], biter[2], biter[3], biter[4]);
            }
            linje = fil.nextLine();
        }

        //Legelokke:
        linje = fil.nextLine(); //forste lege i linje
        while(linje.charAt(0) != '#'){
            String[] biter = linje.split(",");
            String navn = biter[0];
            String knr = biter[1];
            nyLege(navn, knr);
            linje = fil.nextLine();
        }

        //Reseptlokke:
        while(fil.hasNext()){
            linje = fil.nextLine();
            String[] reseptBiter = linje.split(",");
            if (reseptBiter.length == 5){
                nyResept(reseptBiter[3], reseptBiter[0], reseptBiter[1], reseptBiter[2], reseptBiter[4]);
            }
            if (reseptBiter.length == 4){
                nyResept(reseptBiter[0], reseptBiter[1], reseptBiter[2]);
            }
        }
    }




    public void nyPasient(String navn, String fnr){
        Pasient ny = new Pasient(navn, fnr);
        pasienter.leggTil(ny);
    }

    public void nyLege(String navn, String knr){
        int kontrollID = Integer.parseInt(knr);
        if (kontrollID == 0){
            Lege ny = new Lege(navn);
            leger.leggTil(ny);
        }
        else {
            Lege ny = new Spesialist(navn, kontrollID);
            leger.leggTil(ny);
        }
    }

    public void nyLegemiddel(String navn, String pris, String virkestoff){
        Legemiddel ny = new Vanlig(navn, Double.parseDouble(pris), Double.parseDouble(virkestoff));
        legemidler.leggTil(ny);
    }

    public void nyLegemiddel(String navn, String type, String pris, String virkestoff, String styrke){
        if (type.compareTo("narkotisk") == 0){
            Legemiddel ny = new Narkotisk(navn, Double.parseDouble(pris), Double.parseDouble(virkestoff), Integer.parseInt(styrke));
            legemidler.leggTil(ny);
        }
        else if (type.compareTo("vanedannende") == 0){
            Legemiddel ny = new Vanedannende(navn, Double.parseDouble(pris), Double.parseDouble(virkestoff), Integer.parseInt(styrke));
            legemidler.leggTil(ny);
        }
        else {
            System.out.println("Noe galt med type-variablen.");
        }
    }

    public void nyResept(String legemiddelNummer, String legeNavn, String pasientID){
        Resept ny = null;
        int nyLegemiddelNummer = 0;
        int nyPasientID = 0;
        try {
            nyLegemiddelNummer = Integer.parseInt(legemiddelNummer);
            nyPasientID = Integer.parseInt(pasientID);
        } catch(NumberFormatException e) {
            System.out.println("Feil i format, NumberFormatException.");
        }
        Lege utskrivendeLege = null;
        for (Lege lege : leger){
            if (lege.hentNavn().compareTo(legeNavn) == 0){
                utskrivendeLege = lege;
            }
        }
        try {
            ny = utskrivendeLege.skrivPResept(legemidler.hent(nyLegemiddelNummer), pasienter.hent(nyPasientID));
        } catch(Exception e) {
            System.out.println(e);
        }
        resepter.leggTil(ny);
        pasienter.hent(nyPasientID).nyResept(ny);
    }

    public void nyResept(String type, String legemiddelNummer, String legeNavn, String pasientID, String reit){
        Resept ny = null;
        int nyLegemiddelNummer = 0;
        int nyPasientID = 0;
        int nyReit = 0;
        try {
            nyLegemiddelNummer = Integer.parseInt(legemiddelNummer);
            nyPasientID = Integer.parseInt(pasientID);
            nyReit = Integer.parseInt(reit);
        } catch(NumberFormatException e) {
            System.out.println("Feil i format, NumberFormatException.");
        }
        Lege utskrivendeLege = null;
        for (Lege lege : leger){
            if (lege.hentNavn().compareTo(legeNavn) == 0){
                utskrivendeLege = lege;
            }
        }

        if (type.compareTo("hvit") == 0){
            try {
                ny = utskrivendeLege.skrivHvitResept(legemidler.hent(nyLegemiddelNummer), pasienter.hent(nyPasientID), nyReit);
            } catch(Exception e) {
                System.out.println(e);
            }

        }

        if (type.compareTo("blaa") == 0){
            try {
                ny = utskrivendeLege.skrivBlaaResept(legemidler.hent(nyLegemiddelNummer), pasienter.hent(nyPasientID), nyReit);
            } catch(Exception e) {
                System.out.println(e);
            }
        }

        if (type.compareTo("millitaer") == 0){
            try {
                ny = utskrivendeLege.skrivMilitaerResept(legemidler.hent(nyLegemiddelNummer), pasienter.hent(nyPasientID), nyReit);
            } catch(Exception e) {
                System.out.println(e);
            }
        }

        resepter.leggTil(ny);
        pasienter.hent(nyPasientID).nyResept(ny);
    }


}
