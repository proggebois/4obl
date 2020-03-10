abstract class Legemiddel{
    protected static int teller = 0; //Gir ID til nye instanser

    protected String navn;
    protected int id;
    protected double pris;
    protected double virkestoff;

    public Legemiddel(String navn, double pris, double virkestoff){
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;

        id = teller;
        teller++;
    }

    @Override
    public String toString(){
        String penStreng = "Navn: " + navn + "\n";
        penStreng += "ID: " + id + "\n";
        penStreng += "Pris: " + pris + "\n";
        penStreng += "Virkestoff: " + virkestoff + "\n";
        return penStreng;
    }

    protected int hentId(){
        return id;
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
