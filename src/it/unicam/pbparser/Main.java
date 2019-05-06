package it.unicam.pbparser;

import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.entities.ReaderOutput;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            if (args[0].isEmpty()) {
                System.out.println("No file supplied or too many arguments!");
            } else {
                long start = System.currentTimeMillis();
                ReaderOutput output = Reader.read(args[0]);
                System.out.println(String.format("Reading took :: %d ms", System.currentTimeMillis() - start));

                start = System.currentTimeMillis();
                List<BPair> compressed = Compressor.compress(output.getPairs());
                System.out.println(String.format("Compressing took :: %d ms", System.currentTimeMillis() - start));

                start = System.currentTimeMillis();
                Writer.write(output.getHeading(), output.getPrimaryStructure(), compressed);
                System.out.println(String.format("Writing took :: %d ms", System.currentTimeMillis() - start));
                PCGenerator.generate(compressed, output.getPrimaryStructure().length() - 2);
                //boolean result = Writer.write(Compressor.compress(Reader.read(args[0])));
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("Got error :: %s", e.getMessage()));
        } finally {
            System.out.println("FINISHED EXECUTION");
        }
    }
}
