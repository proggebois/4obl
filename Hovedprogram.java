class Hovedprogram{
/*
Opprette minimum en instans av hver eneste klasse og la disse inneholde
nødvendige referanser til andre objekter.
- Skrive ut relevant informasjon om hvert enkelt objekt. (Her vil det lønne seg
å ha overskrevet toString() metoden i alle klassene du har skrevet).
*/
    public static void main(String[] args){
        Narkotisk testNarkotisk = new Narkotisk("Paracet", 299.50, 0.5, 2);
        Vanedannende testVanedandende = new Vanedannende("Morfin", 999.50, 7.5, 17);
        Vanlig testVanlig = new Vanlig("Ibux", 100, 25.55);
        Vanlig testPpiller = new Vanlig("Ppiller", 106, 1);

        Lege testLege = new Lege("Boris");
        Spesialist testSpesialist = new Spesialist("Steinar Holden", 54);

        /*Info saa langt
        System.out.println(testNarkotisk);
        System.out.println(testVanedandende);
        System.out.println(testVanlig);
        System.out.println(testPpiller);
        System.out.println(testLege);
        System.out.println(testSpesialist);
        */

        HvitResept testHvit = new HvitResept(testNarkotisk, testSpesialist, 1, 3);
        MilitaerResept testMilRes = new MilitaerResept(testVanedandende, testLege, 2, 5);
        BlaaResept testBlaa = new BlaaResept(testVanlig, testLege, 3, 1);
        PResept testPResept = new PResept(testPpiller, testLege, 4);

        System.out.println(testHvit);
        System.out.println(testMilRes);
        System.out.println(testBlaa);
        System.out.println(testPResept);


    }

}
