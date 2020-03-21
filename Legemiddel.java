abstract class Legemiddel{
    protected static int legemiddelIdTeller = 0; //Gir ID til nye instanser

    protected String navn;
    protected int unikLegemiddelId;
    protected double pris;
    protected double virkestoff;

    public Legemiddel(String navn, double pris, double virkestoff){
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;

        unikLegemiddelId = legemiddelIdTeller;
        legemiddelIdTeller++;
    }

    @Override
    public String toString(){
        String penStreng = "Navn: " + navn + "\n";
        penStreng += "ID: " + unikLegemiddelId + "\n";
        penStreng += "Pris: " + pris + "\n";
        penStreng += "Virkestoff: " + virkestoff + "\n";
        return penStreng;
    }

    protected int hentId(){
        return unikLegemiddelId;
    }

    protected String hentNavn(){
        return navn;
    }

    protected double hentPris(){
        return pris;
    }

    protected double hentVirkestoff(){
        return virkestoff;
    }

    protected void settNyPris(double nyPris){
        pris = nyPris;
    }


}
