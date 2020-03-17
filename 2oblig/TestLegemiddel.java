class TestLegemiddel{

    public static void main(String[] args){

        Narkotisk testNarkotisk = new Narkotisk("niceShoes", 12.50, 0.5, 2);
        /*
        System.out.println(testNarkotisk.hentId());
        System.out.println(testNarkotisk.hentNavn());
        System.out.println(testNarkotisk.hentPris());
        System.out.println(testNarkotisk.hentVirkestoff());
        testNarkotisk.settNyPris(14.50);
        System.out.println(testNarkotisk.hentPris());
        System.out.println(testNarkotisk.hentNarkotiskStyrke());
        */
        Vanedannende testVanedandende = new Vanedannende("niceDrug", 11.50, 7.5, 17);
        /*
        System.out.println(testVanedandende.hentId());
        System.out.println(testVanedandende.hentNavn());
        System.out.println(testVanedandende.hentPris());
        System.out.println(testVanedandende.hentVirkestoff());
        testVanedandende.settNyPris(77.50);
        System.out.println(testVanedandende.hentPris());
        System.out.println(testVanedandende.hentVanedannendeStyrke());
        */
        Vanlig testVanlig = new Vanlig("badDrug", 99.99, 25.55);
        /*
        System.out.println(testVanlig.hentId());
        System.out.println(testVanlig.hentNavn());
        System.out.println(testVanlig.hentPris());
        System.out.println(testVanlig.hentVirkestoff());
        testVanlig.settNyPris(77.50);
        System.out.println(testVanlig.hentPris());
        */
        System.out.println(testNarkotisk);
        System.out.println(testVanedandende);
        System.out.println(testVanlig);
    }
}
