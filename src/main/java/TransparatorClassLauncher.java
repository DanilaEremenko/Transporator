import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;


public class TransparatorClassLauncher {
    @Option(name = "-a", usage = "Максимальная длина слова")
    private int length=0;

    @Option(name = "-t", usage = "Обрезать строки, длинее -а")
    private boolean trim;

    @Option(name = "-r", usage = "Выравнивать по правому краю?")
    private boolean isRight;

    @Option(name = "-o", usage = "Имя выходного файла")
    private String outputFileName = "";

    @Argument(usage = "Имя входного файла")
    private String inputFileName = "";

    public static void main(String[] args) throws IOException {
        new TransparatorClassLauncher().launch(args);
    }



    private void launch(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
            return;
        }

        TransporatorClass tr;

        tr = new TransporatorClass(inputFileName);

        tr.transpose();

        if (trim&&length!=0)
            tr.cut(length);

        if (isRight)
            tr.right(length);
        else
            tr.left(length);

        if (!outputFileName.equals(""))
            tr.writeTo(outputFileName);
        else
            tr.writeTo();
    }

}
