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
        StringBuffer sb = new StringBuffer();//буфер для входного текста
        StringBuffer rez = new StringBuffer();//буфер для обработанного текста

        while (true) {//цикл для вычитывания файла
            String buffer = reader.readLine();
            if (buffer == null) {
                break;
            }
            sb.append(buffer + "\n");//заполняем буфер вычитанным текстом
        }

        for (; ; ) {//начало обработки
            s = sc.nextLine();
            if (s.length() == 0)
                break;

            rez.append(s+"\n");


        }
        writer.write(rez.toString());//пишем в файл обработанный текст
        System.out.println(rez.toString());
        writer.flush();//закрываем потоки i/o, лучше бы все это запихнуть в try/finally
        writer.close();
        reader.close();
    }
}
