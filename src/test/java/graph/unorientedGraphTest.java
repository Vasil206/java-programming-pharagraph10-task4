
package graph;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("unorientedGraph Test")
public class unorientedGraphTest {
    
    @Test
    @DisplayName("full test for unoriented graph")
    public void unorientedGraphFullTest(){
        
        var graph = new UnorientedGraph(10);

        graph.addEdge(0, 5);
        graph.addEdge(0, 3);
        int newNode = graph.addNode();
        System.out.println(newNode);
        graph.addEdge(newNode, 5);
        graph.deleteNode(0);
        newNode = graph.addNode();
        graph.addEdge(newNode, 10);
        graph.addEdge(3, 5);
        
        assertThat(graph.size()).isEqualTo(11);

        var expectedNeibours3 = new ArrayList<Integer>();
        expectedNeibours3.add(5);
        assertThat(graph.getNeighbours(3)).containsSequence(expectedNeibours3);

        var expectedNeibours5 = new ArrayList<Integer>();
        expectedNeibours5.add(3);
        expectedNeibours5.add(10);
        assertThat(graph.getNeighbours(5)).containsSequence(expectedNeibours5);
    }
}
