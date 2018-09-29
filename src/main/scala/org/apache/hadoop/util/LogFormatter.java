package org.apache.hadoop.util;

import java.util.logging.*;
import java.io.*;
import java.text.*;
import java.util.Date;

public class LogFormatter extends Formatter {
    private static final String FORMAT = "yyMMdd HHmmss";
    private static final String NEWLINE = System.getProperty("line.separator");

    private final Date date = new Date();
    private final SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);

    private static boolean loggedServere = false;

    private static boolean showTime = true;
    private static boolean showThreadIDs = false;

    static {
        Handler[] handlers = LogFormatter.getLogger("").getHandlers();
        for (int i = 0; i < handlers.length; i++){
            handlers[i].setFormatter(new LogFormatter());
            handlers[i].setLevel(Level.FINEST);
        }
    }

    public static Logger getLogger(String name){
        return Logger.getLogger(name);
    }

    public static void showTime(boolean showTime){
        LogFormatter.showTime = showTime;
    }

    public static void setShowThreadIDs(boolean showThreadIDs){
        LogFormatter.showThreadIDs = showThreadIDs;
    }
    @Override
    public String format(LogRecord record) {
        StringBuffer buffer = new StringBuffer();

        if(showTime){
            date.setTime(record.getMillis());
            formatter.format(date, buffer, new FieldPosition(0));
        }
        return null;
    }
}
