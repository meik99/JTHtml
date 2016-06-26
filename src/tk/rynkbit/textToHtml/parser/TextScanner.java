package tk.rynkbit.textToHtml.parser;

/**
 * Created by michael on 22.06.16.
 */
public class TextScanner {
    private String source;
    private String currentChar;
    private int currentIndex;

    public TextScanner(String source){
        reset(source);
    }

    private void init(String source){
        this.source = source;
        this.currentChar = null;
        this.currentIndex = -1;
    }

    public String getSource() {
        return source;
    }

    public String getCurrentChar() {
        return currentChar;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public String getNext() {
        if(source.length() > currentIndex+1){
            this.currentChar = source.substring(
                    ++this.currentIndex, this.currentIndex+1);
        }
        else{
            this.currentChar = null;
        }

        return this.currentChar;
    }

    public String getPrevious(){
        if(currentIndex > 0)
            currentIndex-=2;
        return this.getNext();
    }

    public boolean hasNext(){
        return this.source.length() > this.currentIndex+1;
    }

    public void reset(){
        this.currentIndex = -1;
        this.currentChar = null;
    }

    public void reset(String text){
        init(text);
    }
}
