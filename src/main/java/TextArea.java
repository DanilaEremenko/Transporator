import java.util.Scanner;

public class TextArea {

    //TextArea ta=new TextArea();
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        TextArea ta = new TextArea();
        String s;
        for (; ; ) {
            s = sc.nextLine();
            if (s.length() == 0) {
                break;
            } else
                sb.append(s);
        }
    }


}
