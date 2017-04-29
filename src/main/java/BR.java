import java.io.*;

public class BR {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\danil\\Desktop\\file.txt"))) {
            // чтение посимвольно
            int c;
            while ((c = br.read()) != -1) {

                System.out.print((char) c);
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}