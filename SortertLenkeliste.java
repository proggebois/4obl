class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{

    @Override
    public void leggTil(T x){
        Node ny = new Node(x);

        if (stoerrelse() == 0){
            super.leggTil(x);
        }


        else if (ny.hentData().compareTo(start.hentData()) < 0){
            super.leggTil(0, x);
        }

        else if (ny.hentData().compareTo(siste.hentData()) > 0){
            super.leggTil(stoerrelse(), x);
            siste = ny;
        }

        else{
            int index = 0;
            Node gjeldene = start;
            while (ny.hentData().compareTo(gjeldene.hentData()) > 0){
                    index++;
                    gjeldene = gjeldene.hentNeste();
            }
            super.leggTil(index, x);
        }

    }

    @Override
    public T fjern(){
        return super.fjern(stoerrelse() -1);
    }

    @Override
    public void sett(int pos, T x){
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggTil(int pos, T x){
        throw new UnsupportedOperationException();
    }




}
