class MilitaerResept extends HvitResept{
    //Rabatt 100%

    public MilitaerResept(Legemiddel refLegemiddel, Lege refLege, Pasient pasient, int reit){
        super(refLegemiddel, refLege, pasient, reit);
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
