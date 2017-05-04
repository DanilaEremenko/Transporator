import java.io.*;
import java.util.Scanner;

/**
 * @author Drygba
 *         Date: 23.01.13
 */
public class FREE {
    public static void main(String[] arg) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\danil\\Desktop\\startedfile.txt")); //входной файл
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\danil\\Desktop\\endedfile.txt")); //выходной файл
        Scanner sc = new Scanner(System.in);
        String s;
        int j = 0;
        StringBuffer sb = new StringBuffer();//буфер для входного текста
        StringBuffer rez = new StringBuffer();//буфер для обработанного текста
        StringBuffer[][] massString = new StringBuffer[3][3];
        while (true) {//цикл для вычитывания файла
            String str = reader.readLine();
            if (str == null)
                break;


            sb.append(str + "\n");//заполняем буфер вычитанным текстом

            writer.write(sb.toString());
            System.out.println(sb.toString());


//        for (; ; ) {//начало обработки
//            s = sc.nextLine();
//            if (s.length() == 0)
//                break;
//
//            rez.append(s+"\n");
//
//
//        }
//        writer.write(rez.toString());//пишем в файл обработанный текст
            System.out.println(rez.toString());
            writer.flush();//закрываем потоки i/o, лучше бы все это запихнуть в try/finally
            writer.close();
            reader.close();
        }
    }
}
