public class Outcast {

    private final WordNet wordNet;

    public Outcast(WordNet wordNet) {
        if (wordNet == null) {
            throw new IllegalArgumentException("wordNet is null");
        }
        this.wordNet = wordNet;
    }

    public String outcast(String[] nouns) {
        if (nouns == null || nouns.length < 2) {
            throw new IllegalArgumentException("nouns array needs at least two nouns");
        }

        int maxDist = -1;
        String outcast = null;

        for (String nounA : nouns) {
            int distanceSum = 0;
            for (String nounB : nouns) {
                if (!nounA.equals(nounB)) {
                    distanceSum += wordNet.distance(nounA, nounB);
                }
            }
            if (distanceSum > maxDist) {
                maxDist = distanceSum;
                outcast = nounA;
            }
        }

        return outcast;
    }

    public static void main(String[] args) {
        WordNet wordNet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordNet);
        for (int t = 2; t < args.length; t++) {
            String[] nouns = args[t].split(",");
            System.out.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}