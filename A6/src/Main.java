import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*Graph graph = new Graph(5);
        System.out.println(graph.getNodes());
        System.out.println(graph.getEdges());

        for(int i=0;i<graph.getNodes().size(); i++)
            System.out.println(graph.getEdgesForNode(i));*/



        Graph graph = new Graph(
                new ArrayList<>(List.of(0, 1, 2, 3, 4)),
                new ArrayList<>(
                        List.of(
                                new ArrayList<>(List.of(1)),
                                new ArrayList<>(List.of(2, 3)),
                                new ArrayList<>(List.of(4)),
                                new ArrayList<>(List.of(0)),
                                new ArrayList<>(List.of(1, 3, 4))
                        )
                )
        );
        HamiltonianCycle hamiltonianCycle=new HamiltonianCycle(graph);
        hamiltonianCycle.startSearch();


        Graph graphWithoutHC = new Graph(
                new ArrayList<>(List.of(0, 1, 2, 3, 4)),
                new ArrayList<>(
                        List.of(
                                new ArrayList<>(List.of(1)),
                                new ArrayList<>(List.of(2, 3)),
                                new ArrayList<>(List.of(4)),
                                new ArrayList<>(List.of(0)),
                                new ArrayList<>(List.of(1, 4))
                        )
                )
        );
        HamiltonianCycle notCycle = new HamiltonianCycle(graphWithoutHC);
        notCycle.startSearch();



    }
}