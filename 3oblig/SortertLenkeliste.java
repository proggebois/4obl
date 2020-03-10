class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{

    @Override
    public void leggTil(T x){
        Node ny = new Node(x);
        Node gjeldene = start;
        Node forrige = start;

        if (stoerrelse() == 0){
            super.leggTil(x);
        }

        else if (x.compareTo(start.hentData()) < 0){
            ny.settNeste(start);
            start = ny;
            stoerrelse++;
        }

        else if (x.compareTo(siste.hentData()) > 0){
            siste.settNeste(ny);
            siste = ny;
            stoerrelse++;
        }

        else{
            while (x.compareTo(gjeldene.hentData()) > 0){
                    forrige = gjeldene;
                    gjeldene = gjeldene.hentNeste();
            }
            ny.settNeste(gjeldene);
            forrige.settNeste(ny);
            stoerrelse++;
        }

    }




}
