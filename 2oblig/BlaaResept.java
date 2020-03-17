class BlaaResept extends Resept{
    //Rabatt: 75%
    public BlaaResept(Legemiddel refLegemiddel, Lege refLege, int pasientId, int reit){
        super(refLegemiddel, refLege, pasientId, reit);
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
