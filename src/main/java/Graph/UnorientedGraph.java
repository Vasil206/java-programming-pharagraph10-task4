
package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class UnorientedGraph implements Graphable {
    
    private final ArrayList<TreeSet<Integer>> nodes;
    private final Stack<Integer> deletedNodes;
    
    public UnorientedGraph(int size){
        
        deletedNodes = new Stack<>();
        nodes = new ArrayList<>(2 * size);
        
        for(int i = 0; i < size; i++){
            
            nodes.add(new TreeSet<>());
        }
        
    }

    @Override
    public boolean addEdge(int from, int to) {
        
        if((from < 0) || (from >= nodes.size())
            || (to < 0) || (to >= nodes.size())
            || deletedNodes.contains(from) || deletedNodes.contains(to)){
            
            return false;
        }
        
        return (nodes.get(from).add(to) && nodes.get(to).add(from));
    }

    @Override
    public boolean deleteEdge(int from, int to) {
        
        if((from < 0) || (from >= nodes.size())
            || (to < 0) || (to >= nodes.size())
            || deletedNodes.contains(from) || deletedNodes.contains(to)){
            
            return false;
        }
        
        return (nodes.get(from).remove(to) && nodes.get(to).remove(from));
    }
    
    @Override
    public List<Integer> getNodes(){
        
        return IntStream.range(0, nodes.size()).boxed()
                .filter(node -> !deletedNodes.contains(node)).toList();
    }

    @Override
    public int addNode() {
        
        if(!deletedNodes.empty()){
            
            return deletedNodes.pop();
        }
        
        nodes.add(new TreeSet<>());
        return nodes.size() - 1;
    }

    @Override
    public boolean deleteNode(int nodeNumber)  {
        
        if((nodeNumber >= nodes.size()) || (nodeNumber < 0)
                || deletedNodes.contains(nodeNumber)){
            
            return false;
        }
        
        nodes.forEach(node -> node.remove(nodeNumber));
        
        nodes.get(nodeNumber).clear();
        deletedNodes.push(nodeNumber);
        
        return true;
    }

    @Override
    public List<Integer> getNeighbours(int node) throws IllegalArgumentException {

        if((node >= nodes.size()) || (node < 0)
                || deletedNodes.contains(node)){
            
            throw new IllegalArgumentException("UnorientedGraph :"
                    + " not valid parameter 'node'");
        }
        
        return new ArrayList<>(nodes.get(node));
    }

    @Override
    public int size() {
        
        return nodes.size() - deletedNodes.size();
    }
    
}
