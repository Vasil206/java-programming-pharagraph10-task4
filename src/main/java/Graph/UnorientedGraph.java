
package Graph;

import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeSet;

public class UnorientedGraph implements Graphable {
    
    private final ArrayList<TreeSet<Integer>> nodes;
    private final Stack<Integer> deleted_nodes;
    
    public UnorientedGraph(int size){
        
        deleted_nodes = new Stack<>();
        nodes = new ArrayList<>(2 * size);
        
        for(int i = 0; i < size; i++){
            
            nodes.add(new TreeSet<>());
        }
        
    }

    @Override
    public boolean addEdge(int from, int to) {
        
        if((from < 0) || (from >= nodes.size())
            || (to < 0) || (to >= nodes.size())
            || deleted_nodes.contains(from) || deleted_nodes.contains(to)){
            
            return false;
        }
        
        return (nodes.get(from).add(to) && nodes.get(to).add(from));
    }

    @Override
    public boolean deleteEdge(int from, int to) {
        
        if((from < 0) || (from >= nodes.size())
            || (to < 0) || (to >= nodes.size())
            || deleted_nodes.contains(from) || deleted_nodes.contains(to)){
            
            return false;
        }
        
        return (nodes.get(from).remove(to) && nodes.get(to).remove(from));
    }

    @Override
    public int addNode() {
        
        if(!deleted_nodes.empty()){
            
            return deleted_nodes.pop();
        }
        
        nodes.add(new TreeSet<>());
        return nodes.size() - 1;
    }

    @Override
    public boolean deleteNode(int nodeNumber)  {
        
        if((nodeNumber >= nodes.size()) || (nodeNumber < 0)
                || deleted_nodes.contains(nodeNumber)){
            
            return false;
        }
        
        nodes.forEach(node -> node.remove(nodeNumber));
        
        nodes.get(nodeNumber).clear();
        deleted_nodes.push(nodeNumber);
        
        return true;
    }

    @Override
    public Integer[] getNeighbours(int node) throws IllegalArgumentException {

        if((node >= nodes.size()) || (node < 0)
                || deleted_nodes.contains(node)){
            
            throw new IllegalArgumentException("UnorientedGraph :"
                    + " not valid parameter 'node'");
        }
        
        return nodes.get(node).toArray(Integer[]::new);
    }

    @Override
    public int size() {
        
        return nodes.size() - deleted_nodes.size();
    }
    
}
