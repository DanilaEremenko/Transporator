import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*(+)Конструкторы
*(+)transpose
*(+)cut
*(+)Запись в новый текстовый файл
*(+)Выравнивание по правому краю переделано
*/


public class TransporatorClass {

    private String s="";
    private ArrayList<String>[] line;//Список для хранения текста в строках
    private int maxelements = 0;

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
            if (massWord.length > maxelements)
                maxelements = massWord.length;
            for (int j = 0; j < massWord.length; j++) {
                if (!massWord[j].equals(""))
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

    //Выравнивает текст по левому краю
    public void left() {

        int maxlenght = 0;
        for (int i = 1; i < maxelements; i++) {
            for (int j = 0; j < line.length; j++) {
                if (i > line[j].size()) {
                } else if (maxlenght < line[j].get(i - 1).length())
                    maxlenght = line[j].get(i - 1).length();

            }

            for (int j = 0; j < line.length; j++) {
                if (i <= line[j].size()) {
                    while (line[j].get(i - 1).length() < maxlenght)
                        line[j].set(i - 1, line[j].get(i - 1) + " ");
                }

            }
        }

    }

    //Выравнивает текст по правому краю
    public void right() {

        int maxlenght = 0;


        for (int i = 0; i < maxelements; i++) {
            for (int j = 0; j < line.length; j++) {
                while (line[j].size() < maxelements)
                    line[j].add(0, " ");

                if (maxlenght < line[j].get(i).length())
                    maxlenght = line[j].get(i).length();
            }
            for (int j = 0; j < line.length; j++) {

                while (line[j].get(i).length() < maxlenght)
                    line[j].set(i, " " + line[j].get(i));


            }


        }
    }

    //Записывает текст в документ
    public void writeTo(String ofile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(ofile));
        writer.write(this.toString());
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
        s="";
        for (int i = 0; i < line.length; i++) {
            for (int j = 0; j < line[i].size(); j++) {
                s += line[i].get(j);
                if (j != line[i].size() - 1)
                    s += " ";

            }
            if (i != line.length - 1)
                s += "\n";

        }
        return s;
    }
}




