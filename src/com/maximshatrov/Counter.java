package com.maximshatrov;

import java.util.*;

public class Counter {
    private List<String> textList;
    private Map<String, Integer> uniqEntryHashMap;

    Counter(String rawText) {
        textList = new ArrayList<String>(Arrays.asList(rawText
                .replaceAll("([0-9]-[0-9])", "")                             // "-" в номерах
                .replaceAll("\\p{Digit}", "")                                // цифры
                .replaceAll("(^\\.*)", "")                                   // точка в начале
                .replaceAll("(\\.*$)", "")                                   // точка в конце
                .replaceAll("(^\\,*)", "")                                   // запятая в начале
                .replaceAll("(\\,*$)", "")                                   // запятая в конце
                .replaceAll("([«^»%&',)(\\]\"\\[ /!:;=?$\\x22]+)", " ")      // различные знаки препинания
                .toUpperCase().split(" ")));

    }

    public Map<String, Integer> getResult() {
        Iterator<String> iter = textList.iterator();
        while (iter.hasNext()) {
            if (iter.next().length() <= 3) {            //отсеиваем прелоги и местоимения короче 4 символов
                iter.remove();
            }
        }
        uniqEntryHashMap = new LinkedHashMap<>();       //linked, чтобы сохранить порядок первого вхождения слова в исходном тексте
        for (String word : textList) {
            Integer entryCount = uniqEntryHashMap.get(word);
            if (entryCount == null) {
                entryCount = 0;
            }
            uniqEntryHashMap.put(word, entryCount + 1);
        }
        return uniqEntryHashMap;
    }
}
