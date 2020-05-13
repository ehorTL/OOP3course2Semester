import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphTest {

    private final Vertex v1 = new Vertex(new Vector3D(0,0,0), 1, "vertex1");
    private final Vertex v2 = new Vertex(new Vector3D(1,1,1), 2, "vertex2");
    private final Vertex v3 = new Vertex(new Vector3D(3,3,3), 3, "vertex3");

    private final Edge e112 = new Edge("edge1", v1, v2);
    private final Edge e223 = new Edge("edge2", v2, v3);
    private final Edge e331 = new Edge("edge3", v3, v1);

    private final ArrayList<Edge> edges = new ArrayList<>(Arrays.asList(new Edge[]{e112, e223, e331}));
    private final ArrayList<Vertex> vertices = new ArrayList<>(Arrays.asList(new Vertex[]{v1, v2, v3}));
    private final Graph graph1 = new Graph(vertices, edges);

    @Test
    public void getVerticesList() {
        List<Vertex> vertices = graph1.getVerticesList();

        assertEquals(this.vertices.size(), vertices.size());
        for (int i = 0; i < Math.min(vertices.size(), this.vertices.size()); i++) {
            assertTrue(this.vertices.get(i).equals(vertices.get(i)));
        }
    }

    @Test
    public void getEdges() {
        List<Edge> edges = graph1.getEdges();

        assertEquals(this.edges.size(), edges.size());
        for (int i = 0; i < Math.min(this.edges.size(), edges.size()); i++) {
            assertTrue(this.edges.get(i).equals(edges.get(i)));
        }
    }
}