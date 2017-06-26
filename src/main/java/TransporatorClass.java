import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*(+)Конструкторы
*(+)transpose
*(+)cut
*(+)Запись в новый текстовый файл
*(+)Выравнивание по правому краю
*/


public class TransporatorClass {

    private ArrayList<String>[] line;//Список для хранения текста в строках

    TransporatorClass(String file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // чтение посимвольно
            int c;
            while ((c = br.read()) != -1) {
                sb.append((char) c);
            }
            br.close();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        makeArrayWord(sb);

    }

    TransporatorClass() {
        System.out.println("Введите текст");
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        String s;
        for (; ; ) {
            s = sc.nextLine();
            if (s.length() == 0) {
                break;
            } else
                sb.append(s + "\r\n");
        }
        makeArrayWord(sb);

    }

    private void makeArrayWord(StringBuilder sb) {
        String massString[];
        massString = sb.toString().split("\n");//Разбиваем на строчки

        String massWord[] = new String[massString.length];//

        line = new ArrayList[massString.length];
        //Запускаем цикл для каждоый строчки
        for (int i = 0; i < massString.length; i++) {
            line[i] = new ArrayList<String>();
            massWord = massString[i].split("\\s+");
            for (int j = 0; j < massWord.length; j++) {
                if (massWord[j] != "")
                    line[i].add(massWord[j]);
            }

        }
    }

    //Транспонирует текст
    public void transpose() {
        //Ниже получаем транспонированную матрицу разбитую на строчки
        ArrayList[] temporaryline = new ArrayList[line.length];
        for (int i = 0; i < line.length; i++) {
            temporaryline[i] = new ArrayList<String>();
            for (int j = 0; j < line.length; j++) {
                if (i < line[j].size())
                    temporaryline[i].add(line[j].get(i));

            }

        }

        line = temporaryline;
    }

    //Обрезает все слова в тексте до заданного размера
    public void cut(int number) {
        for (int i = 0; i < line.length; i++)
            for (int j = 0; j < line[i].size(); j++) {
                if (line[i].get(j).length() > number)
                    line[i].set(j, line[i].get(j).substring(0, number));

            }

    }

    //Выравнивает текст по правому краю
    public void right() {
        int maxlenght = 0;
        int linelength[] = new int[line.length];
        for (int i = 0; i < line.length; i++) {
            linelength[i] = 0;
            for (int j = 0; j < line[i].size(); j++) {
                linelength[i] += line[i].get(j).length();
                if (j != line[i].size() - 1)
                    linelength[i]++;
            }
            if (linelength[i] > maxlenght)
                maxlenght = linelength[i];
        }

        String s = " ";
        for (int i = 0; i < line.length; i++) {
            while (linelength[i] < maxlenght) {
                linelength[i]++;
                line[i].set(0, s + line[i].get(0));
            }

        }
    }

    //Записывает текст в документ
    public void writeTo(String ofile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(ofile));
        for (int i = 0; i < line.length; i++) {
            for (int j = 0; j < line[i].size(); j++) {
                writer.write(line[i].get(j));
                writer.write(" ");
            }
            writer.write("\r\n");

        }
        writer.flush();//закрываем потоки i/o
        writer.close();
    }

    public void clear(String ofile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(ofile));
        writer.write("");
        writer.flush();//закрываем потоки i/o
        writer.close();
    }

    //Выводит текст документа на консоль по адресу
    public void getTextofFile(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int c;
            while ((c = br.read()) != -1) {
                System.out.print((char) c);
            }
            br.close();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransporatorClass that = (TransporatorClass) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(line, that.line);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(line);
    }

    @Override
    public String toString() {
        return "TransporatorClass{" +
                "line=" + Arrays.toString(line) +
                '}';
    }
}




