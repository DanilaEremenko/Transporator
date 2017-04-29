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
            int c=0;
            //while((c=br.read())!=-1)
                pw.println((char)c);
//
//            do{
//                s=br.readLine();
//                pw.println(s);
//
//            }while(!s.equals("q"));


        }catch (Exception e){
            System.out.println(e);
        }



    }

}
