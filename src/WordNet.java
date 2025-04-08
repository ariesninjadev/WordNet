// princeton algs4

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;

public class WordNet {

    ArrayList<String[]> synsetsList = new ArrayList<>(); // since synset strings can have multiple words (separated by spaces), we store them as an array
    ArrayList<String> wordList = new ArrayList<>(); // still parsed, but all words are stored as strings individually

    Digraph d; // the digraph representing the hypernyms

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        In synsetFile = new In(synsets);
        In hypernymFile = new In(hypernyms);
        // Read synsets and update lists
        while (synsetFile.hasNextLine()) {
            String line = synsetFile.readLine();
            String[] parts = line.split(",");
            // parts[0] is the synset ID, parts[1] is the synset string
            // parts[2] is the gloss (not used here)
            String[] words = parts[1].split(" ");
            synsetsList.add(words);
            for (String word : words) {
                if (!wordList.contains(word)) {
                    wordList.add(word);
                }
            }
        }
        // Read hypernyms and create the digraph
        int numSynsets = synsetsList.size();
        d = new Digraph(numSynsets);
        while (hypernymFile.hasNextLine()) {
            String line = hypernymFile.readLine();
            String[] parts = line.split(",");
            int synsetID = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                int hypernymID = Integer.parseInt(parts[i]);
                d.addEdge(synsetID, hypernymID);
            }
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return wordList;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        for (String[] synset : synsetsList) {
            for (String w : synset) {
                if (w.equals(word)) {
                    return true;
                }
            }
        }
        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {

    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {

    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}