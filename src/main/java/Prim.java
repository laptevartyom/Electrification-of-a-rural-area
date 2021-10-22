import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.util.Pair;

public class Prim {

    private List<Vertex> graph;

    public Prim(List<Vertex> graph){
        this.graph = graph;
    }

    public void run(){
        if (graph.size() > 0){
            graph.get(0).setVisited(true);
        }

        while (isDisconnected()){
            Edge nextMinimum = new Edge(Integer.MAX_VALUE);
            Vertex nextVertex = graph.get(0);
            for (Vertex vertex : graph){
                if (vertex.isVisited()){
                    Pair<Vertex, Edge> candidate = vertex.nextMinimum();
                    if (candidate.getValue().getWeight() < nextMinimum.getWeight()){
                        nextMinimum = candidate.getValue();
                        nextVertex = candidate.getKey();
                    }
                }
            }
            nextMinimum.setIncluded(true);
            nextVertex.setVisited(true);
        }
    }

    //check if vertex is visited
    private boolean isDisconnected(){
        for (Vertex vertex : graph){
            if (!vertex.isVisited()){
                return true;
            }
        }
        return false;
    }

    public void resetHistory(){
        for (Vertex vertex : graph){
            Iterator<Map.Entry<Vertex,Edge>> it = vertex.getEdges().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Vertex,Edge> pair = it.next();
                pair.getValue().setCounted(false);
                pair.getKey().setVisited(false);
                pair.getValue().setBlocked(true);
            }
        }
    }

    public void addCombinationToTree(List<Edge> a){
        for (Vertex vertex : graph){
            Iterator<Map.Entry<Vertex,Edge>> it = vertex.getEdges().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Vertex,Edge> pair = it.next();
                for (Edge e : a) {
                    if (e.equals(pair.getValue())) {
                        pair.getKey().setVisited(true);
                        pair.getValue().setIncluded(true);
                    }
                }
            }
        }
    }

    //returning edges cost #iwillfixthis
    public int minimumSpanningTreeCosts(){
        int sum = 0;
        for (Vertex vertex : graph){
            sum += vertex.countIncluded();
        }
        return sum;
    }
}