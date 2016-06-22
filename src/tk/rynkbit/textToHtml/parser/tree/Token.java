package tk.rynkbit.textToHtml.parser.tree;

/**
 * Created by michael on 22.06.16.
 */
public class Token {
    private String token;
    private Type type;

    public Token() {
    }

    public Token(String token, Type type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type{
        WHITESPACE,
        DIV,
        P,
        LINK,
        H1,
        H2,
        CODE,
        TEXT,
        NEW_LINE,
        TAB
    }
}
