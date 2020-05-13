/**
 * Represents a directed edge of a weighted graph.
 * */
public class Edge {
    private final String id;
    private final Vertex source;
    private final Vertex destination;
    private final int weight;

    /**
     * The weight of edge is set.
     * */
    public Edge(String id, Vertex source, Vertex destination) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = source.toPoint(destination);
    }

    public String getId() {
        return id;
    }

    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
}
