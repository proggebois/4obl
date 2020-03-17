abstract class Resept{
    protected static int reseptTeller = 0; //Gir unik ID til resepter

    Legemiddel refLegemiddel;
    Lege refLege;
    int pasientId;
    int reit;
    int id;

    public Resept(Legemiddel refLegemiddel, Lege refLege, int pasientId, int reit){
        this.refLegemiddel = refLegemiddel;
        this.refLege = refLege;
        this.pasientId = pasientId;
        this. reit = reit;

        id = reseptTeller;
        reseptTeller++;
    }

    @Override
    public String toString(){
        String penStreng = " - Utskrift av resept - \n";
        penStreng += "Utskrevet til pasientID: " + pasientId + "\n";
        penStreng += "Utskrevet av: " + refLege + "\n";
        penStreng += "Reit: " + reit + "\n";
        penStreng += "ReseptID: " + id + "\n";
        return penStreng;
    }

    public int hentId(){
        return id;
    }

    public Legemiddel hentLegemiddel(){
        return refLegemiddel;
    }

    public Lege hentLege(){
        return refLege;
    }

    public int hentPasientId(){
        return pasientId;
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
