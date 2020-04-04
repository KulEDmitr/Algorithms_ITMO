import java.io.*;
import java.util.*;

public class TopSort {
    private static boolean cyclic = false;

    private static void dfs(List<List<Integer>> graph, List<Integer> out, int[] color, int vert) {
        color[vert] = 1;
        for (int dest : graph.get(vert)) {
            int state = color[dest];
            if (state == 0) {
                dfs(graph, out, color, dest);
            } else if (state == 1) {
                cyclic = true;
                break;
            }
        }
        color[vert] = 2;
        out.add(vert + 1);
    }

    private static void topSort(List<List<Integer>> graph, List<Integer> out, int n) {
        int[] color = new int[n];
        for (int i = 0; i < n; ++i) {
            if (color[i] == 0) {
                dfs(graph, out, color, i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("topsort.in"));
        PrintWriter out = new PrintWriter("topsort.out");
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
        }
        in.close();

        List<Integer> sort = new ArrayList<>();
        topSort(graph, sort, n);

        if (cyclic) {
            out.print(-1);
        } else {
            for (int i = n - 1; i >= 0; --i) {
                out.print(sort.get(i) + " ");
            }
        }
        out.close();
    }
}