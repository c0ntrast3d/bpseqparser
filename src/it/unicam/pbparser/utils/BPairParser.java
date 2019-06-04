package it.unicam.pbparser.utils;

import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.exceptions.WrongFormatException;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BPairParser {
    private static String regex = "([1-9]\\d*)\\s([ACGUMRWSYKVHDBNacgumrwsykvhdbn])\\s(\\d+)";
    private static Pattern pattern = Pattern.compile(regex);


    public static BPair tryParseBPair(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return new BPair(Short.parseShort(matcher.group(1)), matcher.group(2).charAt(0), Short.parseShort(matcher.group(3)));
        } else throw new WrongFormatException(line);
    }
/*    static BPair tryParseBPair(String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return new BPair(Short.parseShort(matcher.group(1)), matcher.group(2).charAt(0), Short.parseShort(matcher.group(3)));
        } else throw new WrongFormatException(line);
    }*/


    public static Function<String, CompletableFuture<BPair>> parse = (line) -> {
        Matcher matcher = pattern.matcher(line);
        CompletableFuture<BPair> bPairCompletableFuture = new CompletableFuture<>();
        if (matcher.matches()) {
            bPairCompletableFuture.complete(
                    new BPair(
                            Short.parseShort(matcher.group(1)),
                            matcher.group(2).charAt(0),
                            Short.parseShort(matcher.group(3))
                    )
            );
            return bPairCompletableFuture;
        }
        bPairCompletableFuture.completeExceptionally(new WrongFormatException(line));
        return null;
    };

}
