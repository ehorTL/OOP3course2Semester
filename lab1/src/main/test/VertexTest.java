import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.junit.Test;

import static org.junit.Assert.*;

public class VertexTest {

    private final Vertex v1 = new Vertex(0,0,0, 1, "vertex1");
    private final Vertex v2 = new Vertex(1,1,1, 2, "vertex2");
    private final Vertex v3 = new Vertex(3,3,3, 3, "vertex3");

    private final Vertex v11 = new Vertex(new Vector3D(0,0,0), 11, "vertex11");
    private final Vertex v22 = new Vertex(new Vector3D(1,1,1), 22, "vertex22");
    private final Vertex v33 = new Vertex(new Vector3D(3,3,3), 33, "vertex33");

    @Test
    public void getId() {
        assertEquals(1, v1.getId());
        assertEquals(2, v2.getId());
        assertEquals(3, v3.getId());
        assertEquals(11, v11.getId());
        assertEquals(22, v22.getId());
        assertEquals(33, v33.getId());

    }

    @Test
    public void getName() {
        assertEquals("vertex1", v1.getName());
        assertEquals("vertex2", v2.getName());
        assertEquals("vertex3", v3.getName());
        assertEquals("vertex11", v11.getName());
        assertEquals("vertex22", v22.getName());
        assertEquals("vertex33", v33.getName());
    }

    @Test
    public void toPoint() {
        //reflectivity
        assertEquals(0, v1.toPoint(v1));
        assertEquals(0, v2.toPoint(v2));
        assertEquals(0, v3.toPoint(v3));

        //symmetry
        assertEquals(1, v1.toPoint(v2));
        assertEquals(1, v2.toPoint(v1));
        assertEquals(3, v2.toPoint(v3));
        assertEquals(3, v3.toPoint(v2));
        assertEquals(5, v1.toPoint(v3));
        assertEquals(5, v3.toPoint(v1));
    }

    @Test
    public void getP() {
        assertTrue(v1.getP().equals(new Vector3D(0,0,0)));
        assertTrue(v2.getP().equals(new Vector3D(1,1,1)));
        assertTrue(v3.getP().equals(new Vector3D(3,3,3)));
        assertTrue(v11.getP().equals(new Vector3D(0,0,0)));
        assertTrue(v22.getP().equals(new Vector3D(1,1,1)));
        assertTrue(v33.getP().equals(new Vector3D(3,3,3)));
    }

    @Test
    public void testEquals() {
        assertTrue(v1.equals(new Vertex(1,2,3, 1, "")));
        assertTrue(v2.equals(new Vertex(1,2,3, 2, "")));
        assertTrue(v3.equals(new Vertex(1,2,3, 3, "")));
        assertTrue(v11.equals(new Vertex(1,2,3, 11, "")));
        assertTrue(v22.equals(new Vertex(1,2,3, 22, "")));
        assertTrue(v33.equals(new Vertex(1,2,3, 33, "")));
    }

    @Test
    public void testToString() {
        assertEquals("vertex1", v1.toString());
        assertEquals("vertex2", v2.toString());
        assertEquals("vertex3", v3.toString());
        assertEquals("vertex11", v11.toString());
        assertEquals("vertex22", v22.toString());
        assertEquals("vertex33", v33.toString());
    }
}