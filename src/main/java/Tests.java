import java.io.IOException;

public class Tests {
    public static void main(String[] args) throws IOException {

        TransporatorClass tr = new TransporatorClass("1.txt");
        tr.right();
        tr.writeTo("2.txt");


    }
}
