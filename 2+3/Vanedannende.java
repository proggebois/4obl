class Vanedannende extends Legemiddel{
    int styrke;

    public Vanedannende(String navn, double pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    @Override
    public String toString(){
        String penStreng = super.toString();
        penStreng += "Vanedannende styrke: " + styrke + "\n";
        return penStreng;
    }

    public int hentVanedannendeStyrke(){
        return styrke;
    }

}
