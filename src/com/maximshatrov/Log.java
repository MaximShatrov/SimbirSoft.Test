package com.maximshatrov;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    /**
     * String хранящий имя лога
     */
    private String name;
    /**
     * FileWriter используемый для работы с файлом
     */
    private FileWriter writer;
    /**
     * Формат даты, используемый в имени лога и во временных метках событий внутри лога
     */
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

    /**
     * Геттер имени файла
     *
     * @return String имя файла
     */
    public String getName() {
        return name;
    }

    /**
     * Добавление строки в файл лога
     *
     * @param line String, помещаемая в конец файла лога
     */
    public void putToLog(String line) {
        try {
            writer.write(new SimpleDateFormat(dateFormat).format(new Date()) + " " + line + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Завершение записи в лог
     */
    public void endLog() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
