import jdk.jfr.events.ExceptionThrownEvent;

import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class TransporatorClass {




    public static void main(String[] args) {
        //Scanner scan = new Scanner(System.in);
        //String s = scan.nextLine();
        try {

            BufferedReader br=
                    new BufferedReader(new InputStreamReader(System.in,"Cp866"));
            PrintWriter pw=new PrintWriter(
                    new OutputStreamWriter(System.out,"Cp866"),true);
            String s="Это строка с русским текстом";
            System.out.println("System.out puts:"+s);
            int c=0;

            pw.println("Посимвольный ввод");
            while((c=br.read())!=-1)
                pw.println((char)c);
            pw.println("Построчный ввод:");
            do{
                s=br.readLine();
                pw.println(s);

            }while(!s.equals("q"));


        }catch (Exception e){
            System.out.println(e);
        }



    }

}
