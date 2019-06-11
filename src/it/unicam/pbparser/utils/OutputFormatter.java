package it.unicam.pbparser.utils;

import java.util.List;

public class OutputFormatter {
    public static <T> String format(List<T> input) {
        StringBuilder sb = new StringBuilder();
        input.forEach(item -> sb.append(item.toString()));
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

}
