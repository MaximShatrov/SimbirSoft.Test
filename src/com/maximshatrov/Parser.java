package com.maximshatrov;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Parser {
    private String url;

    Parser(String url) {
        this.url = url;
    }

    public String parse() throws IOException, IllegalArgumentException {
        Document page = null;
        page = Jsoup.connect(url).get();
        return page.body().text();      //возвращаем только текст из секции <body>
    }
}
