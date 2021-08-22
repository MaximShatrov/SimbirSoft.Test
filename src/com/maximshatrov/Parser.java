package com.maximshatrov;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Parser {
    /**
     * Переменная, хранящая url сайта
     */
    private String url;

    Parser(String url) {
        this.url = url;
    }

    /**
     * Парсинг сайта, основанный на библиотеке jsoup
     *
     * @return String содержащий все слова на сайте "url"
     * @throws IOException              исключение, из-за недоступности сайта (UnknownHostException) при вызове Jsoup.connect(url).get();
     * @throws IllegalArgumentException иключение, из-за неправильной записи url в Jsoup.connect(url).get();
     */
    public String parse() throws IOException, IllegalArgumentException {
        Document page = null;
        page = Jsoup.connect(url).get();
        return page.body().text();      //возвращаем только текст из секции <body>
    }
}
