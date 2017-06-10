import jdk.jfr.events.ExceptionThrownEvent;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;
/*(+)Конструкторы
*(+)transpose
*(+)cut
*(+)Запись в новый текстовый файл
*(-)Выравнивание по правому краю
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
        //Пока что на выходе имеем полный список и список рахбитый на строчки, от одного потом надо будет избавиться
        String massString[];//Промежуточный массив для сплита, можно избавиться
        String s = sb.toString();//Переводим в строку чтобы можно было сплитить(может быть можно сплитить сразу)
        massString = s.split("\n");//Разбиваем на строчки

        String massWord[] = new String[massString.length];//Кажется тоже промежуточный массив,попробуй убрать

        line = new ArrayList[massString.length];
        //Запускаем цикл для каждоый строчки
        for (int i = 0; i < massString.length; i++) {
            line[i] = new ArrayList<String>();
            massWord = massString[i].split("\\s+");
            for (int j = 0; j < massWord.length; j++) {
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
                line[i].add(0, s);
            }

        }
    }

    //Записывает текст в документ
    public void writeTo(String ofile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(ofile));
        for (int i = 0; i < line.length; i++) {

            for (int j = 0; j < line[i].size(); j++) {
                writer.write(line[i].get(j));
                if (line[i].get(j) != " ")
                    writer.write(" ");
            }
            writer.write("\r\n");

        }
        writer.flush();//закрываем потоки i/o, лучше бы все это запихнуть в try/finally
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


        //ПРИМЕРЫ
    public static void main(String[] args) throws IOException {

        //ТЕКСТ БЕРЕТСЯ ИЗ ДОКУМЕНТА

//        TransporatorClass tr = new TransporatorClass("C:\\Users\\danil\\Desktop\\startedfile.txt");
//        System.out.println("Входной файл");
//        tr.getTextofFile("C:\\Users\\danil\\Desktop\\startedfile.txt");
//        tr.cut(1);
//        tr.transpose();
//        tr.right();
//        tr.writeTo("C:\\Users\\danil\\Desktop\\endedfile.txt");
//        System.out.println("Полученный текстовый файл ");
//        tr.getTextofFile("C:\\Users\\danil\\Desktop\\endedfile.txt");


        //   ТЕКСТ ВВОДИТСЯ С КЛАВИАТУРЫ

//        System.out.println("Исходная матрица");
//        TransporatorClass tr2 = new TransporatorClass();
//        tr2.transpose();
//        tr2.cut(1);
//        tr2.right();
//        tr2.writeTo("C:\\Users\\danil\\Desktop\\endedfile.txt");
//        System.out.println("Полученная матрица ");
//        tr2.getTextofFile("C:\\Users\\danil\\Desktop\\endedfile.txt");

    }


}




