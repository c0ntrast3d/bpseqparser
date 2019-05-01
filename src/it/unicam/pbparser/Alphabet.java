package it.unicam.pbparser;

class Alphabet {

    static boolean isValidBase(String base) {
        String values = "ACGUMRWSYKVHDBN";
        return values.contains(base.toUpperCase());
    }
}
