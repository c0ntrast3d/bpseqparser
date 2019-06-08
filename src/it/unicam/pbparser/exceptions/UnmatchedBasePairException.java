package it.unicam.pbparser.exceptions;

public class UnmatchedBasePairException extends RuntimeException {
    public UnmatchedBasePairException(short index, short pair, int wrongPair, String fileName) {
        super(String.format("File %s. Error :: Item pair index does not match :: %d is not paired with %d :: found %s",fileName, pair, index, wrongPair));
    }
}
