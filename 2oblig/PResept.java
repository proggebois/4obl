class PResept extends HvitResept{
    //Rabatt: Flat -108

    public PResept(Legemiddel refLegemiddel, Lege refLege, int pasientId){
        super(refLegemiddel, refLege, pasientId, 3);
    }

    @Override
    public String toString(){
        String penStreng = super.toString();
        penStreng += "PRabatt: -108,- \n";
        return penStreng;
    }

    @Override
    public double prisAaBetale(){
        double pris = refLegemiddel.hentPris() - 108;
        if (pris > 0){
            return pris;
        }
        else{
            return 0;
        }
    }
}
