import java.io.IOException;

public class Tests {
    public static void main(String[] args) throws IOException {

        //ПРИМЕЫ ВЫРАВНИВАНИЯ ПО КРАЯМ

        TransporatorClass tr=new TransporatorClass("2.txt");
        /*СОДЕРЖАНИЕ 2.txt
          AA AAAA AAAA AAA
          BBBB BBB BBBBB BBB
          C CC
          D D D*/

        tr.left();
        tr.writeTo("3.txt");
        System.out.println("left      "+tr.toString().equals
                (       "AA   AAAA AAAA  AAA\n" +
                        "BBBB BBB  BBBBB BBB\n" +
                        "C    CC  \n" +
                        "D    D    D    "));




        tr=new TransporatorClass("3.txt");
        /*СОДЕРЖАНИЕ 3.txt
          AA   AAAA AAAA  AAA
          BBBB BBB  BBBBB BBB
          C    CC
          D    D    D    */

        tr.right();
        System.out.println("right     "+tr.toString().equals
                ("  AA AAAA  AAAA   AAA\n" +
                 "BBBB  BBB BBBBB   BBB\n" +
                 "              C    CC\n" +
                 "        D     D     D"));





        //ПРИМЕР ТРАНСПОНИРОВАНИЯ

        tr=new TransporatorClass("1.txt");
        /*СОДЕРЖАНИЕ 1.txt
          AAA AAAA A AA
          BB B
          CCCC CC CCC
          D DDDD DDD DD*/

        tr.transpose();
        tr.left();
        System.out.println("transpose "+tr.toString().equals
                ("AAA  BB   CCCC D\n" +
                 "AAAA B    CC   DDDD\n" +
                 "A    CCC  DDD \n" +
                 "AA   DD  "));



        //ПРИМЕР ОБРЕЗКИ СЛОВ ДО ОПРЕДЕЛННОЙ ДЛИНЫ

        tr=new TransporatorClass("1.txt");
        /*СОДЕРЖАНИЕ 1.txt
          AAA AAAA A AA
          BB B
          CCCC CC CCC
          D DDDD DDD DD*/

        tr.cut(2);
        tr.left();
        System.out.println("cut       "+tr.toString().equals
                ("AA AA A  AA\n" +
                 "BB B \n" +
                 "CC CC CC\n" +
                 "D  DD DD DD"));


    }
}
