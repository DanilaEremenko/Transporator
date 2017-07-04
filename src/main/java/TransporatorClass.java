import sun.invoke.empty.Empty;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class TransporatorClass {

    private String s = "";
    private ArrayList<String>[] line;//Список для хранения текста в строках
    private int maxelements = 0;//Кол-во столбцов в самый длинной строчке

    public TransporatorClass(String file) throws IOException {

        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(Paths.get(file));
        while (sc.hasNextLine())
            sb.append(sc.nextLine() + "\n");


        sc.close();
        makeArrayWord(sb);


    }

    public TransporatorClass() {
        StringBuilder sb = new StringBuilder();
        sb = empty(sb);
        makeArrayWord(sb);

    }


    //Если текстовый файл пустой
    private StringBuilder empty(StringBuilder sb) {
        System.out.println("Введите текст");
        sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        String s;
        for (; ; ) {
            s = sc.nextLine();
            if (s.length() == 0) {
                break;
            } else
                sb.append(s + "\n");
        }
        return sb;
    }

    private void makeArrayWord(StringBuilder sb) {
        String massString[];
        massString = sb.toString().split("\n");//Разбиваем на строчки

        String massWord[];

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
        ArrayList[] temporaryline = new ArrayList[maxelements];
        for (int i = 0; i < maxelements; i++) {
            temporaryline[i] = new ArrayList<String>();
            for (int j = 0; j < line.length; j++) {
                if (i >= line[j].size())
                    j++;
                else
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

    //Выравнивание текста по левому краю по заданию
    public void left(int maxlenght) {
        for (int i = 1; i < maxelements; i++)
            for (int j = 0; j < line.length; j++) {
                if (i <= line[j].size()) {
                    while (line[j].get(i - 1).length() < maxlenght)
                        line[j].set(i - 1, line[j].get(i - 1) + " ");
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

    //Выравнивание текста по правому краю по заданию
    public void right(int maxlenght) {
        for (int i = 0; i < maxelements; i++)
            for (int j = 0; j < line.length; j++) {
                if (i >= line[j].size())
                    j++;
                else {
                    while (line[j].get(i).length() < maxlenght)
                        line[j].set(i, " " + line[j].get(i));
                }
            }


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

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(line, that.line);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(line);
    }

    @Override
    public String toString() {
        s = "";
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





