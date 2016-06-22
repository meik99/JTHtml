package tk.rynkbit.textToHtml.parser;

import tk.rynkbit.textToHtml.parser.tree.Token;

import java.util.*;

/**
 * Created by michael on 22.06.16.
 */
public class TextLexer {
    private TextScanner scanner;

    private Dictionary<String, Token.Type> threeCharToken = new Hashtable<>();
    private List<Token> output;


    public TextScanner getScanner() {
        return scanner;
    }

    public TextLexer(TextScanner scanner){
        this.scanner = scanner;
        this.output = new LinkedList<>();

        if(this.scanner == null)
            throw new IllegalArgumentException();

        threeCharToken.put("---", Token.Type.DIV);
        threeCharToken.put("###", Token.Type.H1);
        threeCharToken.put("+++", Token.Type.CODE);
        threeCharToken.put("***", Token.Type.P);
        threeCharToken.put("$ref:", Token.Type.LINK);
        threeCharToken.put(" ", Token.Type.WHITESPACE);
        threeCharToken.put("\n", Token.Type.WHITESPACE);
        threeCharToken.put("\t", Token.Type.WHITESPACE);
        threeCharToken.put(",,,", Token.Type.H2);

        lex();
    }

    private void lex(){
        StringBuilder currentToken = new StringBuilder();
        while (scanner.hasNext()){
            scanner.getNext();

            if(scanner.getCurrentChar().equals(" ") ||
                scanner.getCurrentChar().equals("%n") ||
                scanner.getCurrentChar().equals("\t") ||
                scanner.getCurrentChar().equals(System.lineSeparator())){

                Token.Type currentType = threeCharToken.get(currentToken.toString());
                if(currentType != null) {
                    output.add(
                            new Token(currentToken.toString(), currentType)
                    );
                    currentToken = new StringBuilder();
                }
                else{
                    output.add(
                            new Token(currentToken.toString(), Token.Type.TEXT)
                    );
                    currentToken = new StringBuilder();
                }

                if(scanner.getCurrentChar().equals(System.lineSeparator())){
                    output.add(
                            new Token("", Token.Type.NEW_LINE)
                    );
                }
                else if(scanner.getCurrentChar().equals("\t")){
                    output.add(
                            new Token("", Token.Type.TAB)
                    );
                }
            }
            else{
                currentToken.append(scanner.getCurrentChar());
            }
        }
    }

    public List<Token> getOutput() {
        return output;
    }
}
