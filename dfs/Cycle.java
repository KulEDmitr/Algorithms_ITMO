import java.io.*;
import java.util.*;

/** @noinspection Duplicates*/
public class Cycle {
    private static boolean cyclic = false;
    private static int from = -1;
    private static int to = -1;

    private static void dfs(List<List<Integer>> graph, int[] prev, int[] color, int vert) {
        color[vert] = 1;
        for (int dest : graph.get(vert)) {
            int state = color[dest];

            if (state == 0) {
                prev[dest] = vert;
                dfs(graph, prev, color, dest);
            } else if (state == 1) {
                cyclic = true;
                to = vert;
                from = dest;
                break;
            }
        }
        color[vert] = 2;
    }

    private static void topSort(List<List<Integer>> graph, int[] prev, int n) {
        int[] color = new int[n];
        for (int i = 0; i < n; ++i) {
            if (color[i] == 0) {
                dfs(graph, prev, color, i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("cycle.in"));
        PrintWriter out = new PrintWriter("cycle.out");
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

        int[] prev = new int[n];
        topSort(graph, prev, n);

        if (cyclic) {
            List<Integer> cycle = new ArrayList<>();
            cycle.add(from);
            for (int vert = to; vert != from; vert = prev[vert]) {
                cycle.add(vert);
            }

            out.println("YES");
            for (int i = cycle.size() - 1; i >= 0; --i) {
                out.print((cycle.get(i) + 1) + " ");
            }
        } else {
            out.println("NO");

        }
        out.close();
    }
}
