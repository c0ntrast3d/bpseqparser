package it.unicam.pbparser.entities;

public class BPair {
    private short index;

    public short getIndex() {
        return index;
    }

    private char baseName;
    private short pair;

    public short getPair() {
        return pair;
    }

    public void setPair(short pair) {
        this.pair = pair;
    }

    public BPair(short index, char baseName, short pair) {
        this.index = index;
        this.baseName = baseName;
        this.pair = pair;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d);", this.index, this.pair);
    }

    public char getBaseName() {
        return baseName;
    }

}
