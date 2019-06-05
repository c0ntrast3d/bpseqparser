package it.unicam.pbparser.entities;

import java.util.List;

public class ReaderOutput {
    private String heading;
    private String primaryStructure;
    private List<BPair> pairs;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public String getHeading() {
        return heading;
    }

    public String getPrimaryStructure() {
        return primaryStructure;
    }

    public List<BPair> getPairs() {
        return pairs;
    }


    public ReaderOutput(String fileName, String heading, String primaryStructure, List<BPair> pairs) {
        this.fileName = fileName;
        this.heading = heading;
        this.primaryStructure = primaryStructure;
        this.pairs = pairs;
    }

}
