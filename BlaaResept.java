class BlaaResept extends Resept{
    //Rabatt: 75%
    public BlaaResept(Legemiddel refLegemiddel, Lege refLege, Pasient pasient, int reit){
        super(refLegemiddel, refLege, pasient, reit);
    }

    @Override
    public String toString(){
        String penStreng = super.toString();
        penStreng += "Blaarabatt: 75%\n";
        return penStreng;
    }

    @Override
    public String farge(){
        return "blaa";
    }

    @Override
    public double prisAaBetale(){
        return refLegemiddel.hentPris() * 0.25;
    }
}
