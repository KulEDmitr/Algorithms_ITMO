import java.io.*;
import java.util.*;

public class FindConnectivityComponent {
    private void bfs(List<List<Integer>> graph, int vert, int[] components) {
        Deque<Integer> currentSet = new ArrayDeque<>();
        currentSet.addLast(vert);

        while (!currentSet.isEmpty()) {
            int parent = currentSet.pop();
            for (int el : graph.get(parent)) {
                if (components[el] == 0) {
                    currentSet.addLast(el);
                    components[el] = components[parent];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("components.in"));
        PrintWriter out = new PrintWriter("components.out");
        int n = in.nextInt();
        int edgesCount = in.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            graph.add(new ArrayList<>());
        }
        int[] components = new int[n];

        for (int i = 0; i < edgesCount; ++i) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        int comp = 0;
        for (int i = 0; i < n; ++i) {
            if (components[i] == 0) {
                components[i] = ++comp;
                new FindConnectivityComponent().bfs(graph, i, components);
            }
        }

        out.println(comp);
        for (int element : components) {
            out.print(element + " ");
        }
        out.close();
    }
}
