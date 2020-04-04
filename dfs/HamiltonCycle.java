import java.io.*;
import java.util.*;

public class HamiltonCycle {
    private static int size = 0;
    private static boolean way = true;

    private static void dfs(List<List<Integer>> graph, List<Integer> out, int[] used, int vert) {
        used[vert] = 1;
        for (int el : graph.get(vert)) {
            if (used[el] == 0) {
                dfs(graph, out, used, el);
            }
        }
        out.add(vert);
    }

    private void get_way(List<List<Integer>> graph) {
        int[] used = new int[size];
        List<Integer> out = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            if (used[i] == 0) {
                dfs(graph, out, used, i);
            }
        }

        for (int i = size - 2; i >= 0; --i) {

            if (!graph.get(out.get(i + 1)).contains(out.get(i))) {
                way = false;
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("hamiltonian.in"));
        PrintWriter out = new PrintWriter("hamiltonian.out");
        size = in.nextInt();
        int countEdges = in.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < countEdges; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            graph.get(a).add(b);
        }
        in.close();

        new HamiltonCycle().get_way(graph);

        out.print(way ? "YES" : "NO");
        out.close();
    }
}
