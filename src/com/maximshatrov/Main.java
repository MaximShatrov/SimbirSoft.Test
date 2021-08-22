package com.maximshatrov;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.Map;

public class Main {
    /**
     * Переменная, хранящая лог
     */
    private static Log log = new Log();


    /**
     * Точка входа в программу
     *
     * @param args ссылка на сайт, по которому нелбходимо вывести статистику в формате "https://***" либо "http://***"
     */
    public static void main(String[] args) {
        Main main = new Main();
        if (args.length == 0) {
            args = new String[]{"https://www.simbirsoft.com/"};
            log.putToLog("Не указаны входные данные! Получаем статистику по странице по-умолчанию: " + args[0]);
            System.err.println("Не указаны входные данные! \nПолучаем статистику по странице по-умолчанию: " + args[0]);
            main.parseAndPrint(args);
        } else {
            log.putToLog("Получаем статистику по странице: " + args[0]);
            System.out.println("Получаем статистику по странице: " + args[0]);
            main.parseAndPrint(args);
        }
    }

    private void parseAndPrint(String[] args) {
        Parser parser = new Parser(args[0]);
        Counter counter = null;
        try {
            counter = new Counter(parser.parse());
            for (Map.Entry<String, Integer> entry : counter.getResult().entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        } catch (UnknownHostException e) {
            printExceptionLog("Недоступный URL адрес!", e);
        } catch (IllegalArgumentException | IOException e) {
            printExceptionLog("Некорректный URL адрес!", e);
        }
    }

    private void printExceptionLog(String line, Exception e) {
        log.putToLog(line);
        System.err.println(line + " \nПодробности в файле " + log.getName());
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        log.putToLog("printStackTrace: \n    " + errors.toString());
        log.endLog();
    }
}
