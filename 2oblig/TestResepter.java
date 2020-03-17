class TestResepter{

public static void main(String [] args){
    Narkotisk testNarkotisk = new Narkotisk("Paracet", 299.50, 0.5, 2);
    Vanedannende testVanedandende = new Vanedannende("Morfin", 999.50, 7.5, 17);
    Vanlig testVanlig = new Vanlig("Ibux", 100, 25.55);
    Vanlig testPpiller = new Vanlig("Ppiller", 106, 1); //Pris skal bli 0,- etter rabatt
    Vanlig testPStav = new Vanlig("Pstav", 900, 5);


    Lege testLege = new Lege("Boris");
    Spesialist testSpesialist = new Spesialist("Steinar Holden", 54);

    //Resept abstrakt = new Resept(testNarkotisk, testLege, 1, 3);

    HvitResept testHvit = new HvitResept(testNarkotisk, testSpesialist, 1, 3);
    MilitaerResept testMilRes = new MilitaerResept(testVanedandende, testLege, 2, 5);
    BlaaResept testBlaa = new BlaaResept(testVanlig, testLege, 3, 1);
    PResept testPResept = new PResept(testPpiller, testLege, 4);
    PResept testPResept2 = new PResept(testPStav, testLege, 5);

    /* Tester metoder til Resepter
    System.out.println(testHvit.hentId());
    System.out.println(testHvit.hentLegemiddel());
    System.out.println(testHvit.hentLege());
    System.out.println(testHvit.hentPasientId());
    System.out.println(testHvit.hentReit());
    System.out.println(testHvit.bruk());
    System.out.println(testHvit.hentReit());
    System.out.println(testHvit.farge());
    System.out.println(testHvit.prisAaBetale());
    */

    //Tester til subklasse-metoder
    System.out.println(testMilRes.prisAaBetale());
    System.out.println(testBlaa.prisAaBetale());
    System.out.println(testBlaa.farge());


    System.out.println(testPResept.prisAaBetale());
    System.out.println(testPResept2.prisAaBetale());

    System.out.println(testPResept.bruk());
    System.out.println(testPResept.bruk());
    System.out.println(testPResept.bruk());
    //Siste blir false
    System.out.println(testPResept.bruk());

    System.out.println(testHvit);
    System.out.println(testPResept);
    System.out.println(testMilRes);
    System.out.println(testBlaa);

}







}
