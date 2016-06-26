package tk.rynkbit.textToHtml.parser;

import tk.rynkbit.textToHtml.parser.model.Token;
import tk.rynkbit.textToHtml.parser.data.HtmlTemplates;

import java.util.List;
import java.util.Stack;

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
        StringBuilder output = buildHeader();
        List<Token> tokenList = lexer.getOutput();
        Stack<Token> lastTokens = new Stack<>();

        for(int i = 0; i < tokenList.size(); i++){
            Token token = tokenList.get(i);

            if(isTokenSpecial(token)){
                if(lastTokens.empty() == true || lastTokens.peek() == null || (lastTokens.peek() != null &&
                    token.getType() != lastTokens.peek().getType())){

                    if(token.getType() == Token.Type.LINK){
                        try {
                            Token link = tokenList.get(i + 2);
                            Token text = tokenList.get(i + 4);

                            output.append(buildLink(link, text));

                            i+=4;
                        }
                        catch (IndexOutOfBoundsException ex){
                            ex.printStackTrace();
                        }
                    }
                    else{
                        lastTokens.push(token);
                        output.append(buildTag(token));
                    }
                }
                else{
                    Token lastSpecial = lastTokens.pop();
                    output.append(buildTag(lastSpecial, false));
                }
            }
            else{
                output.append(getSpecialContent(token));
            }
        }

        output.append(buildEnd());

        this.output = output.toString();
    }

    private boolean isTokenSpecial(Token token){
        return (token.getType() != Token.Type.TEXT &&
                token.getType() != Token.Type.TAB &&
                token.getType() != Token.Type.WHITESPACE &&
                token.getType() != Token.Type.NEW_LINE);
    }

    private String buildTag(Token token, boolean begin){
        String tag = getSpecialContent(token);
        StringBuilder result = new StringBuilder();

        result.append("<");

        if(begin == false){
            result.append("/");
        }

        result.append(tag);
        result.append(">");

        return result.toString();
    }

    private String buildTag(Token token){
        return buildTag(token, true);
    }

    private String buildLink(Token link, Token text){
        StringBuilder result = new StringBuilder();

        result.append("<a href='http://");
        result.append(link.getToken());
        result.append("'>");
        result.append(text.getToken());
        result.append("</a>");

        return result.toString();
    }

    private StringBuilder buildHeader(){
        StringBuilder header = new StringBuilder();

        header.append(HtmlTemplates.HEADER);

        return header;
    }

    private String buildEnd() {
        return HtmlTemplates.END;
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
                return "&nbsp;";
            case NEW_LINE:
                return "</br>";
            case TAB:
                return "&nbsp;&nbsp;&nbsp;&nbsp;";
            case TEXT:
                return specialToken.getToken();
            case LINK:
                return " ";
        }

        return "";
    }
}
