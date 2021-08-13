package com.maximshatrov;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser("https://www.simbirsoft.com/");
        Counter counter = new Counter(parser.parse());
        for (Map.Entry<String, Integer> entry : counter.getResult().entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
