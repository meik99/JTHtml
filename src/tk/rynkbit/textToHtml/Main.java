package tk.rynkbit.textToHtml;

import tk.rynkbit.textToHtml.parser.TextLexer;
import tk.rynkbit.textToHtml.parser.TextParser;
import tk.rynkbit.textToHtml.parser.TextScanner;
import tk.rynkbit.textToHtml.parser.tree.Token;

import java.io.*;

/**
 * Created by michael on 22.06.16.
 */
public class Main {
    private static int EXIT_CODE = 1;

    public static void main(String[] args){
        if(args.length < 1)
            System.exit(EXIT_CODE++);

        File inFile = null;
        File outFile = null;
        boolean createSuccessfull;
        StringBuilder sourceText = new StringBuilder();

        try{
            inFile = new File(args[0]);
            outFile = new File(inFile.getPath() + ".html");
        }catch (Exception ex){
            System.exit(EXIT_CODE++);
        }

        if(inFile == null || inFile.isDirectory() == true)
            System.exit(EXIT_CODE++);

        try {
            createSuccessfull = outFile.createNewFile();
        } catch (IOException e) {
            System.exit(EXIT_CODE++);
        }

        try(BufferedReader reader = new BufferedReader(
                new FileReader(inFile)
        )){
            String line = null;
            while((line = reader.readLine()) != null){
                sourceText.append(line);
                sourceText.append("\n");
            }
        } catch (IOException e) {
            System.exit(EXIT_CODE++);
        }

        TextScanner scanner = new TextScanner(sourceText.toString());
        TextLexer lexer = new TextLexer(scanner);
        TextParser parser = new TextParser(lexer);

        System.out.println("Content\tType");
        for(Token t : lexer.getOutput()){
            System.out.println(t.getToken() + "\t" + t.getType().name());
        }
        System.out.println();
        System.out.println("###PARSER###");
        System.out.println(parser.getOutput());
        System.out.println("###PARSER END###");

        try(BufferedWriter writer = new BufferedWriter(
                new FileWriter(outFile)
        )) {
            writer.write("<html><head><meta charset='UTF-8'/></head><body>");
            writer.write(parser.getOutput());
            writer.write("</body></html>");
            writer.flush();
        } catch (IOException e) {
            System.exit(EXIT_CODE++);
        }
    }
}
