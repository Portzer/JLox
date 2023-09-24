import java.util.ArrayList;
import java.util.List;

public class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    Scanner(String source) {
        this.source = source;
    }

    public List<Token> getTokens() {
        while (!isAtEnd()) {
            start = current;
            scanTokens();
        }
        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }

    private void scanTokens() {
        char c = advance();
        switch (c) {
            case '(':addToken(TokenType.LEFT_PAREN);break;
            case ')':addToken(TokenType.RIGHT_PAREN);break;
            case '{':addToken(TokenType.LEFT_BRACE);break;
            case '}':addToken(TokenType.RIGHT_BRACE);break;
            case ',':addToken(TokenType.COMMA);break;
            case '.':addToken(TokenType.DOT);break;
            case '-':addToken(TokenType.MINUS);break;
            case '+':addToken(TokenType.PLUS);break;
            case ';':addToken(TokenType.SEMICOLON);break;
            case '*':addToken(TokenType.STAR);break;
            case '!': addToken(match('=') ? TokenType.BANG_EQUAL : TokenType.BANG);break;
            case '=': addToken(match('=') ? TokenType.EQUAL_EQUAL: TokenType.EQUAL);break;
            case '<': addToken(match('=') ? TokenType.LESS_EQUAL: TokenType.LESS);break;
            case '>': addToken(match('=') ? TokenType.GREATER_EQUAL: TokenType.GREATER);break;
            case ' ':
            case '\r':
            case '\t':
                break;
            case '\n':
                line++;
                break;
            case '/':
                if (match('/')) {
                    while (peek() != '\n' && !isAtEnd()) advance();
                }else {
                    addToken(TokenType.SLASH);
                }
                break;
            case '"':string();break;
            default:
                Lox.error(line, "Unexpect character");
                break;
        }
    }

    private void string() {

    }

    private char peek() {
        if(isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private boolean match(char except) {
        if(isAtEnd()) return false;
        if(source.charAt(current)!=except) return false;
        current++;
        return true;
    }

    private char advance() {
        current++;
        return source.charAt(current - 1);
    }

    private void addToken(TokenType tokenType) {
        addToken(tokenType, null);
    }

    private void addToken(TokenType tokenType, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(tokenType, text, literal, line));
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }
}
