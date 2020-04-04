import java.io.*;
import java.util.*;

public class ShortestWayInGraph {
    private void bfs(List<List<Integer>> graph, int[] ways, int n) {
        Deque<Integer> currentSet = new ArrayDeque<>();
        currentSet.addLast(0);
        int[] used = new int[n];
        used[0] = 1;

        while (!currentSet.isEmpty()) {
            int parent = currentSet.pop();
            for (int el : graph.get(parent)) {
                if (used[el] == 0) {
                    used[el] = 1;
                    currentSet.addLast(el);
                    ways[el] = ways[parent] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("pathbge1.in"));
        PrintWriter out = new PrintWriter("pathbge1.out");
        int n = in.nextInt();
        int countEdges = in.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < countEdges; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        in.close();

        int[] ways = new int[n];
        new ShortestWayInGraph().bfs(graph, ways, n);
        for (int el : ways) {
            out.print(el + " ");
        }
        out.close();
    }
}
