import java.util.Scanner;

public class TextArea {

    //TextArea ta=new TextArea();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextArea ta=new TextArea();
        String s;
        for(;;) {
            s = sc.nextLine();
            //ta=new TextArea("вффыв");
            if(s.length()==0)
                break;
        }
    }


}
