package it.unicam.pbparser;

class Alphabet {
    private static String values = "ACGUMRWSYKVHDBN";

    static boolean isValidBase(String base) {
        return values.contains(base.toUpperCase());
    }
}
