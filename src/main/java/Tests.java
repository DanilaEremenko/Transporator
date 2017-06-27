import java.io.IOException;

public class Tests {
    public static void main(String[] args) throws IOException {

        //ПРИМЕР ВЫРАВНИВАНИЯ ПО КРАЯМ
        TransporatorClass tr=new TransporatorClass("3.txt");
        tr.left();
        tr.writeTo("4.txt");
        System.out.println(tr.toString().equals
                (       "AA   AAAA AAAA  AAA\n" +
                        "BBBB BBB  BBBBB BBB\n" +
                        "C    CC  \n" +
                        "D    D    D    "));


        tr=new TransporatorClass("4.txt");
        tr.right();
        tr.writeTo("5.txt");
        System.out.println(tr.toString().equals
                ("  AA AAAA  AAAA   AAA\n" +
                 "BBBB  BBB BBBBB   BBB\n" +
                 "              C    CC\n" +
                 "        D     D     D"));

        //ПРИМЕР ТРАНСПОНИРОВАНИЯ



    }
}
