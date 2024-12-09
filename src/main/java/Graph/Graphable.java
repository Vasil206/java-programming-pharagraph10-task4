
package graph;

import java.util.List;

public interface Graphable {
    
    boolean addEdge(int from, int to);
    
    boolean deleteEdge(int from, int to);
    
    List<Integer> getNodes();
    
    int addNode();
    
    boolean deleteNode(int nodeNumber);
    
    List<Integer> getNeighbours(int nodeNumber);
    
    int size();
}
