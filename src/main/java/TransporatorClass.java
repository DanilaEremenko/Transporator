import jdk.jfr.events.ExceptionThrownEvent;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

public class TransporatorClass {
    private int strings = 0;  //Колличество строк
    private int columns = 0;  //Максимальне колличество слов в строке
    private StringBuilder[][] massWord;  //Массив для хранения слов
    private StringBuilder[] massStr;//Массив хранящий текст разбитый построчно

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

        String[] massStr;
        String str = sb.toString();
        massStr = str.split("\n");//разбили поток на строчки

        this.massStr = new StringBuilder[massStr.length];
        for (int i = 0; i < massStr.length; i++)
            this.massStr[i] = new StringBuilder(massStr[i]);

        this.strings = massStr.length;

        for (int i = 0; i < massStr.length; i++) {
            if (columns < massStr[i].split("\\s+").length)
                columns = massStr[i].split("\\s+").length;
        }
        String stringsStr[];
        massWord = new StringBuilder[strings][columns];
        stringsStr = massStr[0].split("\\s+");
        for (int i = 0; i < strings; i++) {
            for (int j = 0; j < columns; j++) {


                if (j >= stringsStr.length) {
                    while (j < columns) {
                        massWord[i][j] = new StringBuilder("~");
                        j++;
                    }
                } else
                    massWord[i][j] = new StringBuilder(stringsStr[j]);

            }
            if (i != strings - 1)
                stringsStr = massStr[i + 1].split("\\s+");
        }


    }

    private void constructor(){

    }

    //Транспонирует текст
    public void transpose() {
        StringBuilder[][] tmassWord = new StringBuilder[columns][strings];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < strings; j++) {

                tmassWord[i][j] = massWord[j][i];

            }
        }
        int c = columns;
        columns = strings;
        strings = c;
        massWord = new StringBuilder[strings][columns];
        massWord = tmassWord;
        massStr = new StringBuilder[strings];
        for (int i = 0; i < strings; i++) {
            massStr[i] = new StringBuilder();
            for (int j = 0; j < columns; j++) {
                if (j != 0)
                    massStr[i].append(" " + massWord[i][j]);
                else
                    massStr[i].append(massWord[i][j]);
            }
            massStr[i].append("\r\n");
        }
        delWaste();

    }

    //Обрезает все слова в тексте до заданного размера
    public void cut(int number) {
        for (int i = 0; i < strings; i++) {
            massStr[i] = new StringBuilder();
            for (int j = 0; j < columns; j++) {
                if (massWord[i][j].toString() != "~") {
                    if (massWord[i][j].length() > number)
                        massWord[i][j].delete(number, massWord[i][j].length());

                    if (j == 0)
                        massStr[i].append(massWord[i][j].toString());
                    else
                        massStr[i].append(" " + massWord[i][j].toString());

                }

            }
            massStr[i].append("\r\n");
        }


    }

    //Выравнивает текст по правому краю
    public void right()  {
        int maxsize = 0;
        for (int i = 0; i < strings; i++) {
            for (int j = 0; j < massStr[i].length(); j++) {
                if (massStr[i].charAt(j) == '~') {
                    massStr[i].delete(j - 1, j + 1);
                    j--;
                }
            }
            if (maxsize < massStr[i].length())
                maxsize = massStr[i].length();
        }

        for (int i = 0; i < strings; i++) {
            while (massStr[i].length() < maxsize) {
                massStr[i].reverse().append(" ").reverse();
            }
        }

    }

    //Записывает текст в документ
    public void writeTo(String ofile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(ofile));
        for (int i = 0; i < strings; i++)
            writer.write(massStr[i].toString());
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

    private void delWaste() {
        for (int i = 0; i < strings; i++) {
            for (int j = 0; j < massStr[i].length(); j++) {
                if (massStr[i].charAt(j) == '~') {
                    if (j != 0) {
                        massStr[i].delete(j - 1, j + 1);
                        j--;
                    } else {
                        massStr[i].delete(j, j + 1);
                    }
                }
            }
        }
    }


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

        System.out.println("Исходная матрица");
        TransporatorClass tr2 = new TransporatorClass();
        tr2.cut(1);
        tr2.right();
        tr2.transpose();
        tr2.writeTo("C:\\Users\\danil\\Desktop\\endedfile.txt");
        System.out.println("Полученная матрица ");
        tr2.getTextofFile("C:\\Users\\danil\\Desktop\\endedfile.txt");

    }


}




