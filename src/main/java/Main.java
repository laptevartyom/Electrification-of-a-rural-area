import org.paukov.combinatorics3.Generator;
import org.paukov.combinatorics3.IGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static int connections;
    private static int totalVillages;
    private static int leftBankVillages;
    private static int minConnectionsCrossedRiver;
    private static IGenerator<List<Edge>> collection;

    private static ArrayList<Edge> crossRiver = new ArrayList<>();

    public static void main(String[] args) {

        int minimalCost = 2_000_000;

        readInput();

        List<Vertex> list = createGraph();

        Prim prim = new Prim(list);

       // all possible combinations generator
        collection = Generator.combination(crossRiver).simple(minConnectionsCrossedRiver);

        // trying all combinations
        for (List<Edge> a : collection) {
            prim.resetHistory();
            prim.addCombinationToTree(a);
            prim.run();
            int x = prim.minimumSpanningTreeCosts();
            if (minimalCost > x) {
                minimalCost = x;
            }
        }

        System.out.println(minimalCost);
    }

    //creating graph of connections
    public static List<Vertex> createGraph() {

        List<Vertex> graph = new ArrayList<>();

        for (int i = 0; i < totalVillages; i++) {
            graph.add(new Vertex(i));
        }

        for (int i = 0; i < connections; i++) {

            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();

            Edge edge = new Edge(c);

            if (a > leftBankVillages ^ b > leftBankVillages) {
                crossRiver.add(edge);
            }

            graph.get(a).addEdge(graph.get(b), edge);
            graph.get(b).addEdge(graph.get(a), edge);
        }

        return graph;
    }

    // Reading input data
    public static void readInput() {

        connections = scanner.nextInt();
        totalVillages = scanner.nextInt();
        leftBankVillages = scanner.nextInt();
        minConnectionsCrossedRiver = scanner.nextInt();

    }

}
