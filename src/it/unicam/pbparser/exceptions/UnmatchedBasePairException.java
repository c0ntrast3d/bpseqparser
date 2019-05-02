package it.unicam.pbparser.exceptions;

public class UnmatchedBasePairException extends RuntimeException {
    public UnmatchedBasePairException(short index, short pair) {
        super(String.format("Item pair index does not match :: %d is not paired with %d", pair, index));
    }
}
