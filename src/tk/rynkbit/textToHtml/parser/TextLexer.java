package tk.rynkbit.textToHtml.parser;

import tk.rynkbit.textToHtml.parser.tree.Token;

import java.util.*;

/**
 * Created by michael on 22.06.16.
 */
public class TextLexer {
    private TextScanner scanner;

    private Map<String, Token.Type> tokenKeys = new Hashtable<>();
    private List<Token> output;


    public TextScanner getScanner() {
        return scanner;
    }

    public TextLexer(TextScanner scanner){
        this.scanner = scanner;
        this.output = new LinkedList<>();

        if(this.scanner == null)
            throw new IllegalArgumentException();


        tokenKeys.put("###", Token.Type.H1);
        tokenKeys.put("---", Token.Type.DIV);
        tokenKeys.put("+++", Token.Type.CODE);
        tokenKeys.put("***", Token.Type.P);
        tokenKeys.put(",,,", Token.Type.H2);

        tokenKeys.put(TextConstants.WHITESPACE, Token.Type.WHITESPACE);
        tokenKeys.put(TextConstants.NEWLINE, Token.Type.NEW_LINE);
        tokenKeys.put(TextConstants.TAB, Token.Type.TAB);
        tokenKeys.put(TextConstants.WHITESPACE_TAB, Token.Type.TAB);

        tokenKeys.put("$ref:", Token.Type.LINK);

        lex();
    }

    private void lex(){
        StringBuilder current = new StringBuilder();
        String tmp;
        StringBuilder tmpBuilder = new StringBuilder();

        while(scanner.hasNext()) {
            current.append(scanner.getNext());
            tmp = null;

            if(scanner.hasNext()){
                tmp = current.toString() + scanner.getNext();
                scanner.getPrevious();

                if(isKey(tmp) == false)
                    tmp = null;
            }
            if(tmp == null) {
                if (isKey(current.toString())) {
                    output.add(getToken(current.toString()));
                    current = new StringBuilder();
                }
                else if(endsWithToken(current.toString(), tmpBuilder) == true){
                    String preTokenText = current.substring(0, current.indexOf(tmpBuilder.toString()));
                    output.add(getToken(preTokenText));
                    output.add(getToken(tmpBuilder.toString()));
                    current = new StringBuilder();
                    tmpBuilder = new StringBuilder();
                }
            }
        }
//        System.out.println("TOKEN\t|\tTYPE");
//        for(Token token : output){
//            System.out.println(token.getToken() + "\t|\t" + token.getType().name());
//        }
    }

    private boolean endsWithToken(String tokenString, StringBuilder tokenKey) {
        for(Map.Entry<String, Token.Type> set : tokenKeys.entrySet()){
            String tmp = null;

            if(tokenString.endsWith(set.getKey())){
                if(scanner.hasNext()){
                    tmp = set.getKey() + scanner.getNext();
                    scanner.getPrevious();

                    if(isKey(tmp) == false)
                        tmp = null;
                }
                if(tmp == null){
                    tokenKey.append(set.getKey());
                    return true;
                }
            }
        }
        return false;
    }

    private Token getToken(String key){
        Token result = new Token(
                key, isKey(key) ? tokenKeys.get(key) : Token.Type.TEXT
        );
        return result;
    }

    private boolean isKey(String possibleKey){
        return tokenKeys.get(possibleKey) != null;
    }

    public List<Token> getOutput() {
        return output;
    }
}
