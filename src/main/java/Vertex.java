import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.util.Pair;

public class Vertex {

    private int label;
    private Map<Vertex, Edge> edges = new HashMap<>();
    private boolean isVisited = false;

    public Vertex(int label){
        this.label = label;
    }

    public int getLabel() {
        return label;
    }

    public Map<Vertex, Edge> getEdges() {
        return edges;
    }

    // adding edges
    public void addEdge(Vertex vertex, Edge edge){
        if (this.edges.containsKey(vertex)){
            if (edge.getWeight() < this.edges.get(vertex).getWeight()){
                this.edges.replace(vertex, edge);
            }
        } else {
            this.edges.put(vertex, edge);
        }
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }


    // search for minimum edge
    public Pair<Vertex, Edge> nextMinimum(){
        Edge nextMinimum = new Edge(Integer.MAX_VALUE);
        Vertex nextVertex = this;
        Iterator<Map.Entry<Vertex,Edge>> it = edges.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Vertex,Edge> pair = it.next();
            if (!(pair.getKey().isVisited())){
                if (!pair.getValue().isIncluded() && !(pair.getValue().isBlocked())) {
                    if (pair.getValue().getWeight() < nextMinimum.getWeight()) {
                        nextMinimum = pair.getValue();
                        nextVertex = pair.getKey();
                    }
                }
            }
        }
        return new Pair<>(nextVertex, nextMinimum);
    }

    // returning cost of included edge
    public int countIncluded(){
        int a = 0;
        if (isVisited()) {
            Iterator<Map.Entry<Vertex,Edge>> it = edges.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Vertex,Edge> pair = it.next();
                if (pair.getValue().isIncluded()) {
                    if (!pair.getValue().isCounted()) {
                        a += pair.getValue().getWeight();
                        pair.getValue().setCounted(true);
                    }
                }
            }
        }
        return a;
    }
}
