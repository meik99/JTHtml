package tk.rynkbit.textToHtml.parser;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by michael on 22.06.16.
 */
public final class TextMarks {
    private TextMarks(){}

    private static final Dictionary<String, String> MARKS = new Hashtable<>();

    public static Dictionary<String, String> getMarks()
    {
        if(MARKS.size() <= 0){
            MARKS.put("***", "div");
            MARKS.put("###", "h1");
            MARKS.put(",,,", "h2");
            MARKS.put("+++", "code");
            MARKS.put("---", "p");
        }

        return MARKS;
    }

}
