package com.maximshatrov;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.Map;

public class Main {
    private static Log log = new Log();

    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[]{"https://www.simbirsoft.com/"};
            log.putToLog("Не указаны входные данные! Получаем статистику по странице по-умолчанию: " + args[0]);
            System.err.println("Не указаны входные данные! \nПолучаем статистику по странице по-умолчанию: " + args[0]);
            parseAndPrint(args);
        } else {
            log.putToLog("Получаем статистику по странице: " + args[0]);
            System.out.println("Получаем статистику по странице: " + args[0]);
            parseAndPrint(args);
        }
    }

    private static void parseAndPrint(String[] args) {
        Parser parser = new Parser(args[0]);
        Counter counter = null;
        try {
            counter = new Counter(parser.parse());
            for (Map.Entry<String, Integer> entry : counter.getResult().entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        } catch (UnknownHostException e) {
            printLog("Недоступный URL адрес!", e);
        } catch (IllegalArgumentException | IOException e) {
            printLog("Некорректный URL адрес!", e);
        }
    }

    private static void printLog(String line, Exception e) {
        log.putToLog(line);
        System.err.println(line + " \nПодробности в файле " + log.getName());
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        log.putToLog("printStackTrace \n    " + errors.toString());
        log.endLog();
    }
}
