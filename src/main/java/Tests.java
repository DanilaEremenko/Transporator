import java.io.IOException;

public class Tests {
    public static void main(String[] args) throws IOException {

        //ТЕСТЫ ДЛЯ TransporatorClass

        System.out.println("TrasporatorClass");
        TransporatorClass tr=new TransporatorClass("TC2.txt");
        /*СОДЕРЖАНИЕ TC2.txt
          AA AAAA AAAA AAA
          BBBB BBB BBBBB BBB
          C CC
          D D D*/

        tr.left();
        tr.writeTo("TC3.txt");
        System.out.println("left      "+tr.toString().equals
                (       "AA   AAAA AAAA  AAA\n" +
                        "BBBB BBB  BBBBB BBB\n" +
                        "C    CC  \n" +
                        "D    D    D    "));




        tr=new TransporatorClass("TC3.txt");
        /*СОДЕРЖАНИЕ TC3.txt
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

        tr=new TransporatorClass("TC1.txt");
        /*СОДЕРЖАНИЕ TC1.txt
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

        tr=new TransporatorClass("TC1.txt");
        /*СОДЕРЖАНИЕ TC1.txt
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





        //ТЕСТЫ ДЛЯ TransporatorClassLauncher

        /*СОДЕРЖАНИЕ TRLin.txt
          AAA AAAA A AA
          BB B
          CCCC CC CCC
          D DDDD DDD DD*/


        //Обрезка слов до длины 3 и выравнивание по правому краю
        System.out.println("TransparatorClassLauncher");
        String[] args1 = new String[7];
        args1[0] = "-a";//Максимальная длина
        args1[1] = "3";
        args1[2] ="-t";//Обрезка
        args1[3] ="-r";//Выравнивание по правому
        args1[4] = "-o";//Выходной файл
        args1[5] = "TRLout1.txt";
        args1[6] = "TRLin.txt";

        TransparatorClassLauncher.main(args1);
        tr=new TransporatorClass("TRLout1.txt");
        tr.right();
        System.out.println("1.test "+
                tr.toString().equals
                        ("AAA  BB CCC   D\n" +
                         "AAA   B  CC DDD\n" +
                         "      A CCC DDD\n" +
                         "         AA  DD"));

        //Обрезка слов до длины 3 и выравнивание по левому краю
        String[] args2 = new String[6];
        args2[0] = "-a";//Максимальная длина
        args2[1] = "3";
        args2[2] ="-t";//Обрезка
        args2[3] = "-o";//Выходной файл
        args2[4] = "TRLout2.txt";
        args2[5] = "TRLin.txt";
        TransparatorClassLauncher.main(args2);
        tr=new TransporatorClass("TRLout2.txt");
        tr.left();
        System.out.println("2.test "+
                tr.toString().equals
                        ("AAA BB  CCC D\n" +
                         "AAA B   CC  DDD\n" +
                         "A   CCC DDD\n" +
                         "AA  DD "));




        //Транспонирование без обрезки
        String[] args3 = new String[3];
        args3[0] = "-o";//Выходной файл
        args3[1] = "TRLout3.txt";
        args3[2] = "TRLin.txt";
        TransparatorClassLauncher.main(args3);

        tr=new TransporatorClass("TRLout3.txt");
        tr.left();
        System.out.println("3.test "+
                tr.toString().equals
                        ("AAA  BB   CCCC D\n" +
                         "AAAA B    CC   DDDD\n" +
                         "A    CCC  DDD \n" +
                         "AA   DD  "));











    }
}
