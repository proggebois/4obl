class Spesialist extends Lege implements Godkjenningsfritak{
    int kontrollId;

    public Spesialist(String navn, int kontrollId){
        super(navn);
        this.kontrollId = kontrollId;
    }

    @Override
    public String toString(){
        String penStreng = "Spesialist " + super.toString();
        penStreng += "\nKontrollID: " + kontrollId + "\n";
        return penStreng;
    }

    @Override
    public int hentKontrollId(){
        return kontrollId;
    }
}
