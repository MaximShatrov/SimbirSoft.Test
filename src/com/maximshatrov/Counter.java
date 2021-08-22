package com.maximshatrov;

import java.util.*;

public class Counter {
    /**
     * List<String> образованный из String rawText при создании объекта Counter, с удаленными знаками препинания и цифрами
     */
    private List<String> textList;
    /**
     * Map<String, Integer> уникальных слов из textlist
     */
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

    /**
     * Создание Map из уникальных слов, длиннее 3 символов
     *
     * @return Map<String , Integer>, где String key это уникальное слово, а int value - колличество повторений этого слова в String rawText
     */
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
