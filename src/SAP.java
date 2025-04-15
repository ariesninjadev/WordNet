import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

public class SAP {

    private final Digraph G;

    // Constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        }
        this.G = new Digraph(G); // Create a copy of the graph to avoid modifying the original
    }

    // Length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return bfs(v, w)[0]; // Return the length
    }

    // A common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return bfs(v, w)[1]; // Return the ancestor
    }

    // Length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        validateVertices(v);
        validateVertices(w);
        return bfs(v, w)[0]; // Return the length
    }

    // A common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        validateVertices(v);
        validateVertices(w);
        return bfs(v, w)[1]; // Return the ancestor
    }

    // Helper method to perform BFS and find shortest ancestral path
    private int[] bfs(int v, int w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);

        int minDistance = Integer.MAX_VALUE;
        int ancestor = -1;

        for (int i = 0; i < G.V(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int distance = bfsV.distTo(i) + bfsW.distTo(i);
                if (distance < minDistance) {
                    minDistance = distance;
                    ancestor = i;
                }
            }
        }

        if (minDistance == Integer.MAX_VALUE) {
            return new int[]{-1, -1}; // No path found
        }
        return new int[]{minDistance, ancestor};
    }

    // Overloaded helper method for BFS with iterables
    private int[] bfs(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);

        int minDistance = Integer.MAX_VALUE;
        int ancestor = -1;

        for (int i = 0; i < G.V(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int distance = bfsV.distTo(i) + bfsW.distTo(i);
                if (distance < minDistance) {
                    minDistance = distance;
                    ancestor = i;
                }
            }
        }

        if (minDistance == Integer.MAX_VALUE) {
            return new int[]{-1, -1}; // No path found
        }
        return new int[]{minDistance, ancestor};
    }

    // Validate a single vertex
    private void validateVertex(int v) {
        if (v < 0 || v >= G.V()) {
            throw new IllegalArgumentException("Vertex out of bounds");
        }
    }

    // Validate a collection of vertices
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("Iterable cannot be null");
        }
        for (int v : vertices) {
            validateVertex(v);
        }
    }

    // Do unit testing of this class
    public static void main(String[] args) {
        // Unit testing can be added here
    }
}