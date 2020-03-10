class MilitaerResept extends HvitResept{
    //Rabatt 100%

    public MilitaerResept(Legemiddel refLegemiddel, Lege refLege, int pasientId, int reit){
        super(refLegemiddel, refLege, pasientId, reit);
    }

    @Override
    public String toString(){
        String penStreng = super.toString();
        penStreng += "Militaerrabatt: 100%\n";
        return penStreng;
    }
    @Override
    public double prisAaBetale(){
        return 0;
    }
}
