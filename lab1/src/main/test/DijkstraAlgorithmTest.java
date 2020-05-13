import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DijkstraAlgorithmTest {

    private final ArrayList<Vertex> vertices = new ArrayList<>(Arrays.asList(new Vertex[]{
            new Vertex(0, 0, 0, 0, "v0"),
            new Vertex(12, 0, 0, 1, "v1"),
            new Vertex(12, 16, 0, 2, "v2"),
            new Vertex(9, 9, 0, 3, "v3"),
            new Vertex(6, 8, 0, 4, "v4"),
            new Vertex(3, 16, 0, 5, "v5"),
    }));

    private final ArrayList<Edge> edges = new ArrayList<>(Arrays.asList(new Edge[]{
            new Edge("e010", vertices.get(1), vertices.get(0)),
            new Edge("e121", vertices.get(2), vertices.get(1)),
            new Edge("e223", vertices.get(2), vertices.get(3)),
            new Edge("e334", vertices.get(3), vertices.get(4)),
            new Edge("e440", vertices.get(4), vertices.get(0)),
            new Edge("e536", vertices.get(3), vertices.get(5))
    }));

    private final Graph graph1 = new Graph(vertices, edges);

    private final DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(graph1);

    @Test
    public void executeAndGetPath(){
        final int sourceIndex = 2;

        dijkstraAlgorithm.setEdges(graph1);
        dijkstraAlgorithm.execute(graph1.getVerticesList().get(sourceIndex));

        List<Vertex> path20 = dijkstraAlgorithm.getPath(graph1.getVerticesList().get(0));
        assertEquals(4, path20.size());
        assertTrue(vertices.get(2).equals(path20.get(0)));
        assertTrue(vertices.get(3).equals(path20.get(1)));
        assertTrue(vertices.get(4).equals(path20.get(2)));
        assertTrue(vertices.get(0).equals(path20.get(3)));

        List<Vertex> path21 = dijkstraAlgorithm.getPath(graph1.getVerticesList().get(1));
        assertEquals(2, path21.size());
        assertTrue(vertices.get(2).equals(path21.get(0)));
        assertTrue(vertices.get(1).equals(path21.get(1)));

        List<Vertex> path25 = dijkstraAlgorithm.getPath(graph1.getVerticesList().get(5));
        assertEquals(3, path25.size());
        assertTrue(vertices.get(2).equals(path25.get(0)));
        assertTrue(vertices.get(3).equals(path25.get(1)));
        assertTrue(vertices.get(5).equals(path25.get(2)));
    }
}