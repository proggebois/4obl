class Lege implements Comparable<Lege>{
    protected static int legeIdTeller = 0;

    String navn;
    Lenkeliste<Resept> utskrevedeResepter;
    int unikLegeId;

    public Lege(String navn){
        this.navn = navn;
        utskrevedeResepter = new Lenkeliste<Resept>();
        this.unikLegeId = legeIdTeller;
        legeIdTeller++;
    }

    @Override
    public String toString(){
        String penStreng = navn + "\n";
        penStreng += "ID: " + unikLegeId + "\n";
        return penStreng;
    }

    //Denne metoden gjør at vi kan bruke compareTo paa leger
    //den kaller bare compareTo paa navn-strengene, siden de skal sorteres alfabetisk etter navn
    public int compareTo(Lege annen){
        return navn.compareTo(annen.hentNavn());
    }

    public String hentNavn(){
        return navn;
    }

    public Lenkeliste hentUtskrevedeResepter(){
        return utskrevedeResepter;
    }


    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit)throws UlovligUtskrift{
        //Fanger om utskrift av narkotisk uten tillatelse
        if (legemiddel instanceof Narkotisk){
            if (!(this instanceof Spesialist)){//Dette er kanskje feil syntax?
                throw new UlovligUtskrift(this, legemiddel);
            }
        }
        //Lager ny resept, legger til i listen, returnerer
        HvitResept ny = new HvitResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(ny);
        return ny;
    }

    public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        //Fanger om utskrift av narkotisk uten tillatelse
        if (legemiddel instanceof Narkotisk){
            if (!(this instanceof Spesialist)){//Dette er kanskje feil syntax?
                throw new UlovligUtskrift(this, legemiddel);
            }
        }
        //Lager ny resept, legger til i listen, returnerer
        MilitaerResept ny = new MilitaerResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(ny);
        return ny;
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient)throws UlovligUtskrift{
        //Fanger om utskrift av narkotisk uten tillatelse
        if (legemiddel instanceof Narkotisk){
            if (!(this instanceof Spesialist)){//Dette er kanskje feil syntax?
                throw new UlovligUtskrift(this, legemiddel);
            }
        }
        //Lager ny resept, legger til i listen, returnerer
        PResept ny = new PResept(legemiddel, this, pasient);
        utskrevedeResepter.leggTil(ny);
        return ny;
    }

    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit)throws UlovligUtskrift{
        //Fanger om utskrift av narkotisk uten tillatelse
        if (legemiddel instanceof Narkotisk){
            if (!(this instanceof Spesialist)){//Dette er kanskje feil syntax?
                throw new UlovligUtskrift(this, legemiddel);
            }
        }
        //Lager ny resept, legger til i listen, returnerer
        BlaaResept ny = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(ny);
        return ny;
    }


}
