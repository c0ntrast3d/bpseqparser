package it.unicam.pbparser.entities;

public class WBSize {

    private short arkOrigin;
    private short arkTail;
    private short arkLength;

    public WBSize(short arkOrigin, short arkTail, short arkLength) {
        this.arkOrigin = arkOrigin;
        this.arkTail = arkTail;
        this.arkLength = arkLength;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d,%d);", this.arkOrigin, this.arkTail, this.arkLength);
    }

    public short getArkTail() {
        return arkTail;
    }

}
