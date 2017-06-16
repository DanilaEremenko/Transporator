import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;



public class TransparatorClassLauncher {
    @Option(name = "-a", usage = "Максимальная длина слова")
    private int length;

    @Option(name = "-t", usage = "Обрезать строки, длинее -а")
    private boolean trim;

    @Option(name = "-r", usage = "Выравнивать по правому краю?")
    private boolean isRight;

    @Option(name = "-o", usage = "Имя выходного файла")
    private String outputFileName = "";

    @Argument(usage = "Имя входного файла")
    private String inputFileName = "";

    public static void main(String[] args) {
        new TransparatorClassLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
            return;
        }

        TransporatorClass tr;

        if (inputFileName.equals(""))
            tr = new TransporatorClass();
        else
            tr = new TransporatorClass(inputFileName);

        tr.transpose();

        if (trim)
            tr.cut(length);

        if (isRight)
            tr.right();

        if (!outputFileName.equals("")) {
            try {
                tr.writeTo(outputFileName);
            } catch (IOException e) {
            }

        }
    }

}
