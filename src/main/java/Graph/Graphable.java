
package Graph;

public interface Graphable {
    
    boolean addEdge(int from, int to);
    
    boolean deleteEdge(int from, int to);
    
    int addNode();
    
    boolean deleteNode(int nodeNumber);
    
    Integer[] getNeighbours(int nodeNumber);
    
    int size();
}
