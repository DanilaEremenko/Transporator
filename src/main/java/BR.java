import java.io.*;

public class BR {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\danil\\Desktop\\startedfile.txt"))) {
            // чтение посимвольно
            StringBuilder sb=new StringBuilder();
            int c;
            while ((c = br.read()) != -1) {
                sb.append((char)c);
                System.out.print((char) c);
            }
            System.out.println(sb.toString());
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}