import jdk.jfr.events.ExceptionThrownEvent;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

public class TransporatorClass {
    private int strings = 0;  //Колличество строк
    private int columns = 0;  //Максимальне колличество слов в строке
    private StringBuilder[][] massWord;  //Массив для хранения слов


    TransporatorClass(String file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // чтение посимвольно
            int c;
            while ((c = br.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }


        String[] massStr;
        String str = sb.toString();
        massStr = str.split("\n");//разбили поток на строчки
        this.strings = massStr.length;

        for (int i = 0; i < massStr.length; i++) {
            if (columns < massStr[i].split(" ").length)
                columns = massStr[i].split(" ").length;
        }
        System.out.println();
        System.out.println("Максимальное колличество столбцов = " + columns);
        System.out.println("Колличество строчек = " + strings);
        String stringsStr[];
        massWord = new StringBuilder[strings][columns];
        stringsStr = massStr[0].split(" ");
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
                stringsStr = massStr[i + 1].split(" ");
        }


    }

    //Обрезает все слова в тексте до заданного размера
    void cut(int number) {
        for (int i = 0; i < strings; i++)
            for (int j = 0; j < columns; j++) {
                if (massWord[i][j].toString() != "~") {
                    if (massWord[i][j].length() > number)
                        massWord[i][j].delete(number, massWord[i][j].length());

                }
            }

    }

    //Записывает текст в документ
    void writeTo(String ofile) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(ofile));
        for (int i = 0; i < strings; i++) {
            for (int j = 0; j < columns; j++) {
                if (massWord[i][j].toString() == "~") {

                } else {
                    writer.write(massWord[i][j].toString());
                    writer.write(" ");

                }
            }

            writer.write("\r\n");
        }
        writer.flush();//закрываем потоки i/o, лучше бы все это запихнуть в try/finally
        writer.close();


    }

    //Выводит текст документа на консоль по адресу
    void getTextofFile(String file) {
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

    //Транспонирует текст
    void transpose() {
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

    }


    public static void main(String[] args) throws IOException {
        TransporatorClass tr = new TransporatorClass("C:\\Users\\danil\\Desktop\\startedfile.txt");
        System.out.println("Входной файл");
        tr.getTextofFile("C:\\Users\\danil\\Desktop\\startedfile.txt");
        tr.cut(1);
        tr.transpose();
        tr.transpose();
        tr.writeTo("C:\\Users\\danil\\Desktop\\endedfile.txt");
        System.out.println("Полученный файл");
        tr.getTextofFile("C:\\Users\\danil\\Desktop\\endedfile.txt");

    }


}




