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
            if (biter[1] == "narkotisk"){
                nyNarkotisk(biter[0], biter[2], biter[3], biter[4]);
            }
            if (biter[1] == "vanedannende"){
                nyVanedannende(biter[0], biter[2], biter[3], biter[4]);
            }
            if (biter[1] == "vanlig"){
                nyVanlig(biter[0], biter[2], biter[3]);
            }
            linje = fil.nextLine();
        }

        //Legelokke:
        linje = fil.nextLine(); //forste lege i linje
        while(linje.charAt(0) != '#'){
            String[] biter = linje.split(",");
            nyLege(biter[0], biter[1]);
            linje = fil.nextLine();
        }

        //Reseptlokke:
        linje = fil.nextLine();
        while(fil.hasNext()){
            Resept ny = null;
            String[] biter = linje.split(",");
            String type = biter[3];
            String legeNavn = biter[1];
            int legemiddelNummer = Integer.parseInt(biter[0]);
            int pasientID = Integer.parseInt(biter[2]);
            Lege utskrivendeLege = null;
            for (Lege l : leger){
                if (l.hentNavn() == legeNavn){
                    utskrivendeLege = l;
                }
            }

            if (type == "hvit"){
                int reit = Integer.parseInt(biter[4]);
                try {
                    ny = utskrivendeLege.skrivHvitResept(legemidler.hent(legemiddelNummer), pasienter.hent(pasientID), reit);
                } catch(Exception e) {
                    System.out.println(e);
                }

            }

            if (type == "blaa"){
                int reit = Integer.parseInt(biter[4]);
                try {
                ny = utskrivendeLege.skrivBlaaResept(legemidler.hent(legemiddelNummer), pasienter.hent(pasientID), reit);
                } catch(Exception e) {
                    System.out.println(e);
                }
            }

            if (type == "millitaer"){
                int reit = Integer.parseInt(biter[4]);
                try {
                    ny = utskrivendeLege.skrivMilitaerResept(legemidler.hent(legemiddelNummer), pasienter.hent(pasientID), reit);
                } catch(Exception e) {
                    System.out.println(e);
                }
            }

            if (type == "p"){
                try {
                    ny = utskrivendeLege.skrivPResept(legemidler.hent(legemiddelNummer), pasienter.hent(pasientID));
                } catch(Exception e) {
                    System.out.println(e);
                }
            }

            resepter.leggTil(ny);
            pasienter.hent(pasientID).nyResept(ny);
            linje = fil.nextLine();
        }
    }




    public void nyPasient(String navn, String fnr){
        Pasient ny = new Pasient(navn, fnr);
        pasienter.leggTil(ny);
    }

    public void nyLege(String navn, String knr){
        if (knr == "0"){
            Lege ny = new Lege(navn);
            leger.leggTil(ny);
        }
        else {
            Lege ny = new Spesialist(navn, Integer.parseInt(knr));
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






}
