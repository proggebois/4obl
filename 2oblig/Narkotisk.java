class Narkotisk extends Legemiddel{
    int styrke;

    public Narkotisk(String navn, double pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    @Override
    public String toString(){
        String penStreng = super.toString();
        penStreng += "Narkotisk styrke: " + styrke + "\n";
        return penStreng;
    }

    public int hentNarkotiskStyrke(){
        return styrke;
    }




}
