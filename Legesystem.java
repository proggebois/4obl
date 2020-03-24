import java.util.Scanner;
import java.io.File;
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
/*
            else if (menyVelger.compareTo("2") == 0){
                brukResept();
            }

            else if (menyVelger.compareTo("3") == 0){
                skrivStatistikk();
            }

            else if (menyVelger.compareTo("4") == 0){
                skrivTilFil();
            }
*/
        }

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
    }

    public void brukResept(){
    }

    public void skrivStatistikk(){
    }

    public void skrivTilFil(){
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
            if (biter[1].compareTo("narkotisk") == 0){
                nyNarkotisk(biter[0], biter[2], biter[3], biter[4]);
            }
            if (biter[1].compareTo("vanedannende") == 0){
                nyVanedannende(biter[0], biter[2], biter[3], biter[4]);
            }
            if (biter[1].compareTo("vanlig") == 0){
                nyVanlig(biter[0], biter[2], biter[3]);
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
            for (String s : reseptBiter){
                System.out.println(s);
            }
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

    public void nyVanlig(String navn, String pris, String virkestoff){
        Legemiddel ny = new Vanlig(navn, Double.parseDouble(pris), Double.parseDouble(virkestoff));
        legemidler.leggTil(ny);
    }

    public void nyNarkotisk(String navn, String pris, String virkestoff, String styrke){
        Legemiddel ny = new Narkotisk(navn, Double.parseDouble(pris), Double.parseDouble(virkestoff), Integer.parseInt(styrke));
        legemidler.leggTil(ny);
    }

    public void nyVanedannende(String navn, String pris, String virkestoff, String styrke){
        Legemiddel ny = new Vanedannende(navn, Double.parseDouble(pris), Double.parseDouble(virkestoff), Integer.parseInt(styrke));
        legemidler.leggTil(ny);
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
