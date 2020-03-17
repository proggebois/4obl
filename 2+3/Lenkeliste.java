class Lenkeliste<T> implements Liste<T>{
    protected Node start;
    protected Node siste;
    protected int stoerrelse;

    public Lenkeliste(){
        stoerrelse = 0;
    }

    public String toString(){
        Node gjeldene = start;
        String retur = "Start " + start + "siste " + siste +"stoerrelse " +stoerrelse +"\n";
        for (int i = 0; i < stoerrelse; i++){
            retur += gjeldene + "\n";
            gjeldene = gjeldene.hentNeste();
        }
        return retur;
    }

    public int stoerrelse(){
        return stoerrelse;
    }

    public void leggTil(T x){
        Node ny = new Node(x);
        if (stoerrelse == 0){
            start = ny;
            siste = ny;
        }
        else{
            siste.settNeste(ny);
            siste = ny;
        }
        stoerrelse++;
    }

    public T fjern(){
        if (stoerrelse == 0){
            throw new UgyldigListeIndeks(-1);
        }
        T retur = start.hentData();
        start = start.hentNeste();
        stoerrelse--;
        return retur;
    }

    public void leggTil(int pos, T x){
        if (pos < 0 || pos > stoerrelse){
            throw new UgyldigListeIndeks(pos);
        }
        else{
            Node ny = new Node(x);

            if (pos == 0){
                ny.settNeste(start);
                start = ny;
                if (stoerrelse == 0){
                    siste = ny;
                }
            }

            else {
                Node nyPluss1 = start;
                Node nyMinus1 = null;
                for (int i = 0; i < pos; i++){
                    nyMinus1 = nyPluss1;
                    nyPluss1 = nyPluss1.hentNeste();
                }
                ny.settNeste(nyPluss1);
                nyMinus1.settNeste(ny);
            }
            stoerrelse++;
            }
    }


    public void sett(int pos, T x){
        if (pos < 0 || pos >= stoerrelse || stoerrelse == 0){
            throw new UgyldigListeIndeks(pos);
        }
        Node gjeldene = start;
        for(int i = 0; i < pos; i++){
            gjeldene = gjeldene.hentNeste();
        }
        gjeldene.settData(x);
    }

    public T fjern(int pos){
        if (pos < 0 || pos >= stoerrelse || stoerrelse == 0){
            throw new UgyldigListeIndeks(pos);
        }
        Node retur = start;
        Node foranRetur = null;

        for (int i = 0; i < pos; i++){
            foranRetur = retur;
            retur = retur.hentNeste();
        }

        if (retur == siste){
            siste = foranRetur;
            if (foranRetur != null){
                foranRetur.settNeste(null);
            }
        }
        else {
            foranRetur.settNeste(retur.hentNeste());
        }
        stoerrelse--;
        return retur.hentData();
    }

    public T hent(int pos){
        if (pos < 0 || pos >= stoerrelse){
            throw new UgyldigListeIndeks(pos);
        }
        Node retur = start;
        for (int i = 0; i < pos; i++){
            retur = retur.hentNeste();
        }
        return retur.hentData();

    }



    class Node  {
        protected T data;
        protected Node neste;

        public Node(T data){
            this.data = data;
        }

        public String toString(){
            return "" + hentData();
        }

        public Node hentNeste(){
            return neste;
        }
        public void settNeste(Node node){
            neste = node;
        }

        public T hentData(){
            return data;
        }

        public void settData(T nyData){
            data = nyData;
        }
    }

}
