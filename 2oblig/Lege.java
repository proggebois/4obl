class Lege{
    String navn;

    public Lege(String navn){
        this.navn = navn;
    }

    @Override
    public String toString(){
        String penStreng = navn + "\n";
        return penStreng;
    }

    public String hentNavn(){
        return navn;
    }
}
