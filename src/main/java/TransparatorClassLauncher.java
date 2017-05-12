import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class TransparatorClassLauncher {
    @Option(name = "-a", usage = "Максимальная длина строки")
    private int length = 10;

    @Option(name = "-t", usage = "Обрезать строки, длинее -а")
    private boolean trim;

    @Option(name = "-r", usage = "Выравнивать по правому краю?")
    private boolean isRight;

    @Option(name = "-o", usage = "Имя выходного файла")
    private String outputFileName;

    @Argument(usage = "Имя входного файла")
    private String inputFileName;

    public TransparatorClassLauncher() {
    }

    public static void main(String[] args) {
        TransparatorClassLauncher transposeLauncher = new TransparatorClassLauncher();
        CmdLineParser parser = new CmdLineParser(transposeLauncher);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
            return;
        }
    }

}