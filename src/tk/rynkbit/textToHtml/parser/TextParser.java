package tk.rynkbit.textToHtml.parser;

import sun.net.ftp.FtpDirEntry;
import tk.rynkbit.textToHtml.parser.tree.Token;

import java.util.List;

/**
 * Created by michael on 22.06.16.
 */
public class TextParser {
    private TextLexer lexer;
    private String output;

    public TextParser(TextLexer lexer){
        this.lexer = lexer;
        parse();
    }

    public TextLexer getLexer() {
        return lexer;
    }

    public String getOutput() {
        return output;
    }

    public void parse(){
        StringBuilder output = new StringBuilder();
        List<Token> tokenList = lexer.getOutput();
        Token lastSpecialToken = null;

        for(int i = 0; i < tokenList.size(); i++){

            Token token = tokenList.get(i);
            if(token.getType() == Token.Type.LINK){
                output.append("<a href='");
                output.append(tokenList.get(++i).getToken());
                output.append("'>");
                output.append(tokenList.get(++i).getToken());
                output.append("</a>");
            }
            else if(token.getType() == Token.Type.NEW_LINE){
                if(lastSpecialToken != null){
                    output.append("</br>");
                }
            }
            else if(token.getType() == Token.Type.TAB){
                for(int j = 0; j < 4; j++){
                    output.append("&nbsp;");
                }
            }
            else if(token.getType() != Token.Type.TEXT){
                if(lastSpecialToken != null){
                    output.append("</");
                    output.append(getSpecialContent(lastSpecialToken));
                    lastSpecialToken = null;
                }
                else {
                    lastSpecialToken = token;
                    output.append("<");
                    output.append(getSpecialContent(lastSpecialToken));
                }
                output.append(">");
            }
            else{
                output.append(token.getToken());
                output.append(" ");
            }
        }

        this.output = output.toString();
    }

    private String getSpecialContent(Token specialToken){
        switch (specialToken.getType()){
            case DIV:
                return "div";
            case CODE:
                return "code";
            case H1:
                return "h1";
            case H2:
                return "h2";
            case P:
                return "p";
            case WHITESPACE:
                return " ";
            case TEXT:
                return specialToken.getToken();
            case LINK:
                return " ";
        }

        return "";
    }
}
