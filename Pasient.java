class Pasient{
    protected static int pasientIdTeller = 0;

    protected String navn;
    protected String fnr;
    protected int unikPasientId;
    protected Stabel<Resept> resepter;

    public Pasient(String navn, String fnr){
        this.navn = navn;
        this.fnr = fnr;
        this.unikPasientId = pasientIdTeller;
        pasientIdTeller++;

        this.resepter = new Stabel<Resept>();
    }

    public int hentId(){
        return unikPasientId;
    }

    public void nyResept(Resept resept){
        resepter.leggTil(resept);
    }

    @Override
    public String toString(){
        String penStreng = "PasientID: " + unikPasientId + "\n";
        penStreng += "Navn: " + navn + ", fnr: " + fnr + "\n";
        return penStreng;
    }


}
