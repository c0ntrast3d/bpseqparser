package it.unicam.pbparser.entities;

public class BPair {
    private short index;

    public short getIndex() {
        return index;
    }

    private char baseName;
    private short pair;

    public int getPair() {
        return pair;
    }

    public int realIndex() {
        return getPair() - 1;
    }

    public void mark() {
        this.pair = -1;
    }

    public boolean pairNotNull() {
        return this.pair != 0 && this.pair != -1;
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
