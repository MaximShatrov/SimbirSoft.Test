package com.maximshatrov;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private String name;
    private FileWriter writer;
    private String dateFormat = "yyyy.MM.dd_HH-mm-ss";

    Log() {
        //name = "Log_" + new Date().getTime() + ".log";
        name = "Log_" + new SimpleDateFormat(dateFormat).format(new Date()) + ".log";
        //File file = new File("./" + name);
        try {
            writer = new FileWriter(name, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void putToLog(String line) {
        try {
            writer.write(new SimpleDateFormat(dateFormat).format(new Date()) + " " + line + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endLog() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
