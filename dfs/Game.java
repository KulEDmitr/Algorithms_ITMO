import java.io.*;
import java.util.*;

public class Game {
    private static int size = 0;

    private void dfs(List<List<Integer>> graph, List<Integer> way, int[] used, int vert) {
        used[vert] = 1;
        for (int el : graph.get(vert)) {
            if (used[el] == 0) {
                dfs(graph, way, used, el);
            }
        }
        way.add(vert);
    }

    private void start(List<List<Integer>> graph, int[] results) {
        List<Integer> way = new ArrayList<>();
        int[] used = new int[size];
        for (int i = 0; i < size; ++i) {
            if (used[i] == 0) {
                dfs(graph, way, used, i);
            }
        }

        for (int el : way) {
            for (int next : graph.get(el)) {
                if (results[next] == 0) {
                    results[el] = 1;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("game.in"));
        PrintWriter out = new PrintWriter("game.out");
        size = in.nextInt();
        int countEdges = in.nextInt();
        int startPoint = in.nextInt() - 1;

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

        int[] results = new int[size];
        new Game().start(graph, results);
        out.print((results[startPoint] == 0) ? "Second player wins" : "First player wins");
        out.close();
    }
}
