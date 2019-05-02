package it.unicam.pbparser.entities;

import java.util.List;

public class ReaderOutput {
    private String heading = null;

    public String getHeading() {
        return heading;
    }

    public String getPrimaryStructure() {
        return primaryStructure;
    }

    public List<BPair> getPairs() {
        return pairs;
    }

    private String primaryStructure = null;
    private List<BPair> pairs = null;

    public ReaderOutput(String heading, String primaryStructure, List<BPair> pairs) {
        this.heading = heading;
        this.primaryStructure = primaryStructure;
        this.pairs = pairs;
    }

}
