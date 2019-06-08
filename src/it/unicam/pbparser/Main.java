package it.unicam.pbparser;

public class Main {

    public static void main(String[] args) {
        try {
            if (args[0].isEmpty()) {
                System.out.println("No file/dir supplied");
            } else {
/*                long start = System.currentTimeMillis();
                ReaderOutput output = BpseqReader.read(args[0]);
                System.out.println(String.format("Reading took :: %d ms", System.currentTimeMillis() - start));

                start = System.currentTimeMillis();
                List<BPair> compressed = Compressor.compress(output.getPairs());
                System.out.println(String.format("Compressing took :: %d ms", System.currentTimeMillis() - start));

                start = System.currentTimeMillis();
                Writer.write(output.getHeading(), output.getPrimaryStructure(), compressed);
                System.out.println(String.format("Writing took :: %d ms", System.currentTimeMillis() - start));
                PCGenerator.generate(compressed, output.getPrimaryStructure().length() - 2);*/
                Controller.start(args[0]);
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("Got error :: %s", e.getMessage()));
        } finally {
            System.out.println("FINISHED EXECUTION");
        }
    }
}
