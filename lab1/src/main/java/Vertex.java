import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 * Represents a vertex of a graph.
 * Wrapper for Vector3D.
 * */
public class Vertex {
    private final int id;
    private final String name;
    public final Vector3D p;

    public Vertex(int x, int y, int z, int id, String name) {
        p = new Vector3D(x, y, z);
        this.id = id;
        this.name = name;
    }

    public Vertex(Vector3D p, int id, String name) {
        this.p = p;
        this.id = id;
        this.name = name;
    }


    /**
     * Distance in field of integers.
     * */
    public int toPoint(Vertex v) {
        return (int) p.distance(v.p);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return name;
    }

    public Vector3D getP() {
        return p;
    }
}
