package it.unicam.pbparser;

public class Main {

    public static void main(String[] args) {
        try {
            if (!args[0].isEmpty()) {
                boolean result = Writer.write(Compressor.compress(Reader.read(args[0])));
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
