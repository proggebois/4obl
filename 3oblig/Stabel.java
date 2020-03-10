class Stabel<T> extends Lenkeliste<T>{
    protected Node start;
    protected Node siste;
    protected int stoerrelse;

    public Stabel(){
        this.stoerrelse = 0;
    }

    public void leggPaa(T x){
        leggTil(x);
    }

    public T taAv(){
        return fjern(stoerrelse() -1);
    }
}
