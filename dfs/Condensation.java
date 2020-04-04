import java.io.*;
import java.util.*;

public class Condensation {
    private static int size = 0;

    private void bfs(List<List<Integer>> graph_out, List<Integer> way, int[] used, int vert) {
        used[vert] = 1;
        for (int el : graph_out.get(vert)) {
            if (used[el] == 0) {
                bfs(graph_out, way, used, el);
            }
        }
        way.add(vert);
    }

    private void reversedBfs(List<List<Integer>> graph_in, int[] comp, int vert, int c) {
        comp[vert] = c;
        for (int el : graph_in.get(vert)) {
            if (comp[el] == -1) {
                reversedBfs(graph_in, comp, el, c);
            }
        }
    }

    private int find_Components(List<List<Integer>> graph_in, List<List<Integer>> graph_out, int[] comp) {
        int[] used = new int[size];
        List<Integer> way = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            if (used[i] == 0) {
                bfs(graph_out, way, used, i);
            }
        }

        int c = 0;
        for (int i = size - 1; i >= 0; --i) {
            int cur = way.get(i);
            if (comp[cur] == -1) {
                ++c;
                reversedBfs(graph_in, comp, way.get(i), c);
            }
        }
        return c;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("cond.in"));
        PrintWriter out = new PrintWriter("cond.out");

        size = in.nextInt();
        int countEdges = in.nextInt();

        List<List<Integer>> graph_in = new ArrayList<>();
        List<List<Integer>> graph_out = new ArrayList<>();
        int[] components = new int[size];
        for (int i = 0; i < size; ++i) {
            graph_in.add(new ArrayList<>());
            graph_out.add(new ArrayList<>());
            components[i] = -1;
        }

        for (int i = 0; i < countEdges; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            graph_out.get(a).add(b);
            graph_in.get(b).add(a);
        }
        in.close();

        out.println(new Condensation().find_Components(graph_in, graph_out, components));
        for (int el : components) {
            out.print(el + " ");
        }
        out.close();
    }
}
