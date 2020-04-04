import java.io.*;
import java.util.*;

public class CheckBipartite {
    private static int size = 0;
    private static boolean isBipart = true;

    private int invertColor(int color) {
        return (color == 1) ? 2 : 1;
    }

    private void dfs(List<List<Integer>> graph, int[] color, int vert, int col) {
        if (!isBipart) return;
        color[vert] = col;
        for (int el : graph.get(vert)) {
            if (color[el] == col) {
                isBipart = false;
                break;
            }
            if (color[el] == 0) {
                dfs(graph, color, el, invertColor(col));
            }
        }
    }

    private void checkBipartite(List<List<Integer>> graph) {
        int[] color = new int[size];
        for (int i = 0; i < size; ++i) {
            if (color[i] == 0) {
                dfs(graph, color, i, 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("bipartite.in"));
        PrintWriter out = new PrintWriter("bipartite.out");

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
            graph.get(b).add(a);
            if (a == b) {
                isBipart = false;
                break;
            }
        }
        in.close();

        new CheckBipartite().checkBipartite(graph);
        out.print((isBipart) ? "YES" : "NO");
        out.close();
    }
}
