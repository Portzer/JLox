import javax.xml.stream.FactoryConfigurationError;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class Lox {

    private static final Interpreter INTERPRETER = new Interpreter();
    static boolean hadError = false;
    static boolean hadRunTimeEError = false;

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.out.println(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        for (; ; ) {
            System.out.print("> ");
            String line = reader.readLine();
            if(line==null) break;
            run(line);
            hadError = false;
        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if(hadError) System.exit(65);
        if(hadRunTimeEError) System.exit(70);
    }

    private static void run(String source) throws IOException {
        String path = "D:\\CS\\Code\\Lox\\JLox\\resource\\demo1.text";
        Path path1 = Paths.get(path);
        byte[] bytes = Files.readAllBytes(path1);
        String s = new String(bytes);
        Scanner scanner = new Scanner(s);
        List<Token> tokens = scanner.getTokens();
        Parser parser = new Parser(tokens);
        List<Stmt> stmts = parser.parse();
        if(hadError) return;
        INTERPRETER.interpret(stmts);
    }

    public static void error(int line, String message) {
        report(line, "", message);
    }

    public static void error(Token token, String message) {
        if (token.type == TokenType.EOF) {
            report(token.line, " at end", message);
        } else {
            report(token.line, " at '" + token.lexeme + "'", message);
        }
    }

    private static void report(int line, String where, String message) {
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }

    public static void runtimeError(RunTimeError error) {
        System.err.println(error.getMessage() + "\n[line " + error.token.line + "]");
        hadRunTimeEError = true;
    }
}
