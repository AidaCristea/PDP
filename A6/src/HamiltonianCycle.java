import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HamiltonianCycle {
    public Graph graph;

    public HamiltonianCycle(Graph graph) {
        this.graph = graph;
    }

    public void startSearch() {
        ArrayList<Integer> path = new ArrayList<>();

        try {
            path.add(0);
            search(0, path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean checkVisited(int node, List<Integer> path) {
        return path.contains(node);
    }

    private void search(int currentNode, List<Integer> path) throws InterruptedException {
        //when all nodes are in the path and from the last node we can get to the first node from the path then it is true

        if (graph.getEdgesForNode(currentNode).contains(0) && path.size() == graph.getNodes().size()) {
            System.out.println("Found solution: " + path);
            return;
        }


        //all nodes visited but no solution
        if (path.size() == graph.getNodes().size()) {
            return;
        }

        for (int i = 0; i < graph.getNodes().size(); i++) {
            //if possible to add i in the path
            if (graph.getEdgesForNode(currentNode).contains(i) && !checkVisited(i, path)) {
                path.add(i);
                graph.getEdgesForNode(currentNode).remove(Integer.valueOf(i));

                //recursive search for the current node
                /*int node =i;
                search(node, path);
                graph.getEdgesForNode(currentNode).add(i);
                path.remove(path.size()-1);*/

                //recursive search for the current node on a new thread
                //we will hav e 4 threads
                ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
                final int node = i;
                final Runnable task = () -> {
                    try {
                        search(node, path);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                };

                executor.execute(task);
                executor.shutdown();
                executor.awaitTermination(50, TimeUnit.SECONDS);

                //replace the removed edge so the paths can be explored correctly
                graph.getEdgesForNode(currentNode).add(i);

                //delete the path after i was checked
                path.remove(path.size() - 1);

            }
        }
    }

}
