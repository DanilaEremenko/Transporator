import sun.invoke.empty.Empty;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class TransporatorClass {


    private ArrayList<String> line = new ArrayList<>();//Список для хранения текста в строках
    private int maxelements = 0;//Кол-во столбцов в самый длинной строчке


    public TransporatorClass(String file) throws IOException {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(Paths.get(file));
        sb = empty(sb, sc);
        makeArrayWord(sb);


    }

    public TransporatorClass() {
        System.out.println("Введите текст");
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        sb = empty(sb, sc);
        makeArrayWord(sb);

    }


    //Если текстовый файл пустой
    private StringBuilder empty(StringBuilder sb, Scanner sc) {
        int a = 0;
        while (sc.hasNext()) {
            sb.append(sc.nextLine());
            if (a == sb.length())
                break;
            sb.append("\n");
            a = sb.length();
        }

        return sb;
    }

    private void makeArrayWord(StringBuilder sb) {
        String massString[];
        massString = sb.toString().split("\n");//Разбиваем на строчки


        String massWord[];

        //Запускаем цикл для каждоый строчки
        for (int i = 0; i < massString.length; i++) {
            massWord = massString[i].split("\\s+");
            if (massWord.length > maxelements)
                maxelements = massWord.length;
            for (int j = 0; j < massWord.length; j++) {
                if (!massWord[j].equals(""))
                    line.add(massWord[j]);
            }

            line.add("\n");
        }


    }

    //Транспонирует текст
    public void transpose() {
        //Ниже получаем транспонированную матрицу разбитую на строчки
        ArrayList<String> temporaryline = new ArrayList();
        int k = 0;//Когда был предыдущий n, чтобы проверить что не залезаем в предыдущую строчку
        for (int i = 0; i < maxelements; i++) {
            for (int j = 0; j < line.size(); j++) {
                if (line.get(j).equals("\n")) {
                    if ((i + k) < j)
                        temporaryline.add(line.get(i + k));
                    k = j + 1;
                }


            }
            temporaryline.add("\n");
            k = 0;


        }

        line = temporaryline;
    }

    //Обрезает все слова в тексте до заданного размера
    public void cut(int number) {
        for (int i=0;i<line.size();i++)
            if (line.get(i).length() > number && !line.get(i).equals("\n"))
                line.set(i,line.get(i).substring(0, number));


    }

    //Выравнивание текста по левому краю по заданию
    public void left(int maxlenght) {
        for (int i = 0; i < line.size(); i++)
            if (!line.get(i).equals("\n"))
                while (line.get(i).length() < maxlenght)
                    line.set(i, line.get(i) + " ");

    }


    //Выравнивание текста по правому краю по заданию
    public void right(int maxlenght) {
        for (int i = 0; i < line.size(); i++)
            if (!line.get(i).equals("\n"))
                while (line.get(i).length() < maxlenght)
                    line.set(i, " " + line.get(i));


    }

    //Записывает текст в документ
    public void writeTo(String ofile) {
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(ofile));
            writer.write(this.toString());
            writer.flush();//закрываем потоки i/o
            writer.close();
        } catch (IOException e) {
            System.out.println(this.toString());
        }
    }


    public void writeTo() {
        System.out.println(this.toString());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransporatorClass that = (TransporatorClass) o;

        if (maxelements != that.maxelements) return false;
        return line != null ? line.equals(that.line) : that.line == null;
    }

    @Override
    public int hashCode() {
        int result = line != null ? line.hashCode() : 0;
        result = 31 * result + maxelements;
        return result;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < line.size() - 1; i++)
            if (!line.get(i).equals("\n") && !line.get(i + 1).equals("\n"))
                s += line.get(i) + " ";
            else
                s += line.get(i);

        return s;


    }
}

//Выравнивает текст по левому краю
//    public void left() {
//
//        int maxlenght = 0;
//        for (int i = 1; i < maxelements; i++) {
//            for (int j = 0; j < line.length; j++) {
//                if (i > line[j].size()) {
//                } else if (maxlenght < line[j].get(i - 1).length())
//                    maxlenght = line[j].get(i - 1).length();
//
//            }
//            for (int j = 0; j < line.length; j++) {
//                if (i <= line[j].size()) {
//                    while (line[j].get(i - 1).length() < maxlenght)
//                        line[j].set(i - 1, line[j].get(i - 1) + " ");
//                }
//
//            }
//        }
//
//    }
//


//    //Выравнивает текст по правому краю
//    public void right() {
//
//        int maxlenght = 0;
//
//
//        for (int i = 0; i < maxelements; i++) {
//            for (int j = 0; j < line.length; j++) {
//                while (line[j].size() < maxelements)
//                    line[j].add(0, " ");
//
//                if (maxlenght < line[j].get(i).length())
//                    maxlenght = line[j].get(i).length();
//            }
//            for (int j = 0; j < line.length; j++) {
//
//                while (line[j].get(i).length() < maxlenght)
//                    line[j].set(i, " " + line[j].get(i));
//
//
//            }
//
//
//        }
//    }
//
