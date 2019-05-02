package it.unicam.pbparser.exceptions;

public class WrongFormatException extends RuntimeException{
    public WrongFormatException(String line) {
        super(String.format("Failed to parse line %s", line));
    }
}
