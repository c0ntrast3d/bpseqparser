package it.unicam.pbparser;

public class Main {

    public static void main(String[] args) {
        try {
            if (!args[0].isEmpty()) {
                boolean result = Writer.write(Compressor.compress(Reader.read(args[0])));
                System.out.println(String.format("Execution finished with %s", result ? "success" : "failure"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("Got error :: %s", e.getMessage()));
        }
    }
}
