import jdk.jfr.events.ExceptionThrownEvent;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

public class TransporatorClass {

    private int strings = 0;  //Колличество строк
    private int columns = 0;  //Максимальне колличество слов в строке
    private StringBuilder sb; //Для хранения исходного текста
    private StringBuilder tsb;//Для хранения транспонированного текста
    private String[][] tmassWord;//Массив для хранения транспоинрованной матрицы слов
    private String[][] massWord;  //Массив для хранения слов
    private ArrayList<String> wordList;

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
        this.sb = sb;


        String[] massStr;
        String str = sb.toString();
        massStr = str.split("\n");//разбили поток на строчки
        this.strings = massStr.length;

        for (int i = 0; i < massStr.length; i++)
            System.out.println("massStr[" + i + "]=" + massStr[i]);

        for (int i = 0; i < massStr.length; i++) {
            if (columns < massStr[i].split(" ").length)
                columns = massStr[i].split(" ").length;
        }
        System.out.println();
        System.out.println("Максимальное колличество столбцов = " + columns);
        System.out.println("Колличество строчек = " + strings);
        String stringsStr[];
        massWord = new String[strings][columns];
        stringsStr = massStr[0].split(" ");
        for (int i = 0; i < strings; i++) {
            for (int j = 0; j < columns; j++) {


                if (j >= stringsStr.length) {
                    while (j < columns) {
                        massWord[i][j] = "-";
                        j++;
                    }
                } else
                    massWord[i][j] = stringsStr[j];

            }
            if (i != strings - 1)
                stringsStr = massStr[i + 1].split(" ");
        }


        System.out.println("\nВходной файл");
    }


    void clear() {
        sb.delete(0, sb.length());
    }

    void writeTo(String ofile) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(ofile));
        writer.write(sb.toString());
        writer.flush();//закрываем потоки i/o, лучше бы все это запихнуть в try/finally
        writer.close();


    }

    String getString() {
        return sb.toString();
    }


    void transpose() {
        tsb = new StringBuilder();
        tmassWord = new String[columns][strings];
        for (int i = 0; i < columns; i++) {

            for (int j = 0; j < strings; j++) {

                tmassWord[i][j] = massWord[j][i];

                if (tmassWord[i][j] != "-")
                    tsb.append(" " + tmassWord[i][j]);
            }

            tsb.append("\r\n");
        }
        sb = tsb;

    }

    void getMatrix() {
    }


    public static void main(String[] args) throws IOException {
        TransporatorClass tr = new TransporatorClass("C:\\Users\\danil\\Desktop\\startedfile.txt");
        System.out.println(tr.getString());
        System.out.println("Полученная матрица");
        tr.transpose();
        tr.getMatrix();
        tr.writeTo("C:\\Users\\danil\\Desktop\\endedfile.txt");

    }


}




