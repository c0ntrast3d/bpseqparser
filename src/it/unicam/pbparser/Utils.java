package it.unicam.pbparser;

import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.exceptions.WrongFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Utils {
    private static String regex = "([1-9]\\d*)\\s([ACGUMRWSYKVHDBNacgumrwsykvhdbn])\\s(\\d+)";
    private static Pattern pattern = Pattern.compile(regex);

    static BPair tryParseBPair(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return new BPair(Short.parseShort(matcher.group(1)), matcher.group(2).charAt(0), Short.parseShort(matcher.group(3)));
        } else throw new WrongFormatException(line);
    }
}
