package it.unicam.pbparser.exceptions;

public class UnmatchedBasePairException extends RuntimeException {
    public UnmatchedBasePairException(short index, short pair, int wrongPair) {
        super(String.format("Item pair index does not match :: %d is not paired with %d :: found %s", pair, index, wrongPair));
    }
}
