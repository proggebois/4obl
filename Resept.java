abstract class Resept{
    protected static int reseptIdTeller = 0; //Gir unik ID til resepter

    Legemiddel refLegemiddel;
    Lege refLege;
    Pasient pasient;
    int reit;
    int unikReseptId;

    public Resept(Legemiddel refLegemiddel, Lege refLege, Pasient pasient, int reit){
        this.refLegemiddel = refLegemiddel;
        this.refLege = refLege;
        this.pasient = pasient;
        this.reit = reit;

        this.unikReseptId = reseptIdTeller;
        reseptIdTeller++;
    }

    @Override
    public String toString(){
        String penStreng = " - Utskrift av resept - \n";
        penStreng += "Utskrevet til pasient: " + pasient + "\n"; //pasientObjektet eller ID?? Sjekk senere
        penStreng += "Utskrevet av: " + refLege + "\n";
        penStreng += "Reit: " + reit + "\n";
        penStreng += "ReseptID: " + unikReseptId + "\n";
        return penStreng;
    }

    public int hentId(){
        return unikReseptId;
    }

    public Legemiddel hentLegemiddel(){
        return refLegemiddel;
    }

    public Lege hentLege(){
        return refLege;
    }

    public int hentPasientId(){
        return pasient.hentId();
    }

    public int hentReit(){
        return reit;
    }

    public boolean bruk(){
        if (reit > 0){
            reit--;
            return true;
        }
        else{
            return false;
        }
    }

    abstract public String farge();

    abstract public double prisAaBetale();

}
