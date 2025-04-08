import edu.princeton.cs.algs4.Digraph;

public class SAP {

    private final Digraph G; // the digraph representing the hypernyms
    private final int V; // number of vertices in the digraph
    private final int E; // number of edges in the digraph

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }
        this.G = new Digraph(G); // create a copy of the graph to avoid modifying the original
        this.V = G.V();
        this.E = G.E();

    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v < 0 || v >= V || w < 0 || w >= V) {
            throw new IllegalArgumentException("Vertex out of bounds");
        }
        if (v == w) {
            return 0; // same vertex
        }
        // Implement BFS or DFS to find the shortest ancestral path
        // Return -1 if no such path exists
        return -1; // placeholder
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {

    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {

    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {

    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}