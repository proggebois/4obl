class HvitResept extends Resept{

    public HvitResept(Legemiddel refLegemiddel, Lege refLege, Pasient pasient, int reit){
        super(refLegemiddel, refLege, pasient, reit);
    }

    @Override
    public String toString(){
        String penStreng = super.toString();
        penStreng += "Reseptfarge: " + this.farge() + "\n";
        return penStreng;
    }
    @Override
    public String farge(){
        return "hvit";
    }

    @Override
    public double prisAaBetale(){
        return refLegemiddel.hentPris();
    }
}
