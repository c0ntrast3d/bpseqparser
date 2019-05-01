package it.unicam.pbparser;

import it.unicam.pbparser.entities.BPair;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            if (!args[0].isEmpty()) {
                List<BPair> input = Reader.read(args[0]);
                List<BPair> compressed = Compressor.compress(input);
                long start = System.currentTimeMillis();
                boolean result = Writer.write(compressed);
                long finish = System.currentTimeMillis();
                System.out.println(String.format("Execution took :: %d ms", finish - start));
                //boolean result = Writer.write(Compressor.compress(Reader.read(args[0])));
                System.out.println(String.format("Operation %s", result ? "success" : "failure"));
            } else {
                System.out.println("No file supplied or too many arguments!");
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("Got error :: %s", e.getMessage()));
        } finally {
            System.out.println("FINISHED EXECUTION");
        }
    }
}
