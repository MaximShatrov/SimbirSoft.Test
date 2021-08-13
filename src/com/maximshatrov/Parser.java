package com.maximshatrov;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Parser {
    private String url;

    Parser(String url) {
        this.url = url;
    }

    public String parse() {
        Document page = null;
        try {
            page = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.err.println("Неправильный URL адрес!");
        }
        return page.body().text();      //возвращаем только текст из секции <body>
    }
}
