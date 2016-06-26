package tk.rynkbit.textToHtml.parser.io;

import tk.rynkbit.textToHtml.parser.TextLexer;
import tk.rynkbit.textToHtml.parser.TextParser;
import tk.rynkbit.textToHtml.parser.TextScanner;

import java.io.*;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Created by michael on 27.06.16.
 */
public class TextController {
    private static File inFile, outFile;
    private static StringBuilder sourceText;
    private static String output;

    private TextController(){
    }

    public static void processFile(String path){
        sourceText = new StringBuilder();
        inFile = new File(path);

        createOutputFile(path);
        readFile();
        compute();
        writeOutputFile(output);

        System.out.println("File processed!");
    }

    private static void writeOutputFile(String output) {
        try(BufferedWriter writer = new BufferedWriter(
                new FileWriter(outFile)
        )) {
            writer.write(output);
        } catch (IOException e) {
            System.out.println("ERROR: Unexpected Input/Output error");
        }
    }

    private static void readFile(){
        try(BufferedReader reader = new BufferedReader(
                new FileReader(inFile)
        )){
            String line = null;
            while((line = reader.readLine()) != null){
                sourceText.append(line);
                sourceText.append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Could not create file " + inFile.getPath());
        } catch (IOException e) {
            System.out.println("ERROR: Unexpected Input/Output error");
        }
    }

    private static void createOutputFile(String path){
        String outPath = path + ".html";

        if(inFile.exists() == false){
            System.out.println("ERROR: Could not find file " + path);
        }

        outFile = new File(outPath);
        try {
            outFile.createNewFile();
        } catch (IOException e) {
            System.out.println("ERROR: Could not create file " + inFile.getPath());
        }
    }

    private static void compute(){
        TextScanner scanner = new TextScanner(sourceText.toString());
        TextLexer lexer = new TextLexer(scanner);
        TextParser parser = new TextParser(lexer);
        output = new String(parser.getOutput());


    }
}
