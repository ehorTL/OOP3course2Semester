import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.junit.Test;

import static org.junit.Assert.*;

public class EdgeTest {

    private final Vector3D vect1 = new Vector3D(0,0,0);
    private final Vector3D vect2 = new Vector3D(1,1,1);
    private final Vector3D vect3 = new Vector3D(3,3,3);

    private final Vertex v1 = new Vertex(vect1, 1, "vertex1");
    private final Vertex v2 = new Vertex(vect2, 2, "vertex2");
    private final Vertex v3 = new Vertex(vect3, 3, "vertex3");

    private final String[] edgeId = new String[]{"edge1", "edge1Reverse", "edge2", "edge2Reverse", "edge3", "edge3Reverse"};

    private final Edge e112 = new Edge(edgeId[0], v1, v2);
    private final Edge e221 = new Edge(edgeId[1], v2, v1);
    private final Edge e323 = new Edge(edgeId[2], v2, v3);
    private final Edge e432 = new Edge(edgeId[3], v3, v2);
    private final Edge e513 = new Edge(edgeId[4], v1, v3);
    private final Edge e631 = new Edge(edgeId[5], v3, v1);

    @Test
    public void getId() {
        assertEquals(edgeId[0], e112.getId());
        assertEquals(edgeId[1], e221.getId());
        assertEquals(edgeId[2], e323.getId());
        assertEquals(edgeId[3], e432.getId());
        assertEquals(edgeId[4], e513.getId());
        assertEquals(edgeId[5], e631.getId());
    }

    @Test
    public void getDestination() {
        assertTrue(e112.getDestination().equals(v2));
        assertTrue(e221.getDestination().equals(v1));
        assertTrue(e323.getDestination().equals(v3));
        assertTrue(e432.getDestination().equals(v2));
        assertTrue(e513.getDestination().equals(v3));
        assertTrue(e631.getDestination().equals(v1));
    }

    @Test
    public void getSource() {
        assertTrue(e112.getSource().equals(v1));
        assertTrue(e221.getSource().equals(v2));
        assertTrue(e323.getSource().equals(v2));
        assertTrue(e432.getSource().equals(v3));
        assertTrue(e513.getSource().equals(v1));
        assertTrue(e631.getSource().equals(v3));
    }

    @Test
    public void getWeight() {
        assertEquals(1, e112.getWeight());
        assertEquals(1, e221.getWeight());
        assertEquals(3, e323.getWeight());
        assertEquals(3, e432.getWeight());
        assertEquals(5, e513.getWeight());
        assertEquals(5, e631.getWeight());
    }

    @Test
    public void testToString() {
        assertEquals(v1.toString() + " " + v2.toString(), e112.toString());
        assertEquals(v2.toString() + " " + v1.toString(), e221.toString());
        assertEquals(v2.toString() + " " + v3.toString(), e323.toString());
        assertEquals(v3.toString() + " " + v2.toString(), e432.toString());
        assertEquals(v1.toString() + " " + v3.toString(), e513.toString());
        assertEquals(v3.toString() + " " + v1.toString(), e631.toString());
    }
}