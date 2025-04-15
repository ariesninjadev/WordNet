import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {

    private final ArrayList<String[]> synsetsList = new ArrayList<>();
    private final HashMap<String, ArrayList<Integer>> nounToSynsetMap = new HashMap<>();
    private final Digraph digraph;

    // Constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException("Input files cannot be null");
        }

        // Read synsets and populate data structures
        In synsetFile = new In(synsets);
        while (synsetFile.hasNextLine()) {
            String line = synsetFile.readLine();
            String[] parts = line.split(",");
            int synsetID = Integer.parseInt(parts[0]);
            String[] words = parts[1].split(" ");
            synsetsList.add(words);

            for (String word : words) {
                nounToSynsetMap.putIfAbsent(word, new ArrayList<>());
                nounToSynsetMap.get(word).add(synsetID);
            }
        }

        // Read hypernyms and create the digraph
        int numSynsets = synsetsList.size();
        digraph = new Digraph(numSynsets);
        In hypernymFile = new In(hypernyms);
        while (hypernymFile.hasNextLine()) {
            String line = hypernymFile.readLine();
            String[] parts = line.split(",");
            int synsetID = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                int hypernymID = Integer.parseInt(parts[i]);
                digraph.addEdge(synsetID, hypernymID);
            }
        }
    }

    // Returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounToSynsetMap.keySet();
    }

    // Is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word cannot be null");
        }
        return nounToSynsetMap.containsKey(word);
    }

    // Distance between nounA and nounB
    public int distance(String nounA, String nounB) {
        validateNoun(nounA);
        validateNoun(nounB);

        ArrayList<Integer> synsetIDsA = nounToSynsetMap.get(nounA);
        ArrayList<Integer> synsetIDsB = nounToSynsetMap.get(nounB);

        SAP sap = new SAP(digraph);
        return sap.length(synsetIDsA, synsetIDsB);
    }

    // A synset that is the common ancestor of nounA and nounB in a shortest ancestral path
    public String sap(String nounA, String nounB) {
        validateNoun(nounA);
        validateNoun(nounB);

        ArrayList<Integer> synsetIDsA = nounToSynsetMap.get(nounA);
        ArrayList<Integer> synsetIDsB = nounToSynsetMap.get(nounB);

        SAP sap = new SAP(digraph);
        int ancestorID = sap.ancestor(synsetIDsA, synsetIDsB);
        if (ancestorID == -1) {
            return null;
        }
        return String.join(" ", synsetsList.get(ancestorID));
    }

    // Validate if a word is a valid noun
    private void validateNoun(String word) {
        if (word == null || !isNoun(word)) {
            throw new IllegalArgumentException("Word is not a valid WordNet noun");
        }
    }

    // Do unit testing of this class
    public static void main(String[] args) {
        // Unit testing can be added here
    }
}