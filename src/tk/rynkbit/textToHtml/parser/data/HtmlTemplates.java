package tk.rynkbit.textToHtml.parser.data;

/**
 * Created by michael on 23.06.16.
 */
public class HtmlTemplates {
    public static final String STYLE = "<link rel='stylesheet'" +
            "href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'/>";
    public static final String BOOTSTRAP = "<script " +
            "src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js'>" +
            "</script>";
    public static final String JQUERY = "<script   src='https://code.jquery.com/jquery-2.2.4.min.js'   " +
            "integrity='sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44='   " +
            "crossorigin='anonymous'></script>";
    public static final String CHARSET = "<meta charset='UTF-8'/>";
    public static final String HEADER = "<html><head>" + CHARSET + STYLE + JQUERY + BOOTSTRAP  + "</head>";
    public static final String END = "</body></html>";
}
