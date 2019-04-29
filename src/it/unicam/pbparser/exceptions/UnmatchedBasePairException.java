package it.unicam.pbparser.exceptions;

public class UnmatchedBasePairException extends RuntimeException {
    public UnmatchedBasePairException() {
        super("Item pair index does not match");
    }
}
