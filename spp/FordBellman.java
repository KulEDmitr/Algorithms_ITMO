import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FordBellman {
    private static long INF = 200_000_000_000L;

    private static class way {
        int from;
        int to;
        long weight;

        way(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("pathmgep.in"));
        PrintWriter out = new PrintWriter("pathmgep.out");

        int n = in.nextInt();
        int s = in.nextInt() - 1;
        int f = in.nextInt() - 1;

        List<way> graph = new ArrayList<>();
        long[] ways = new long[n];

        for (int i = 0; i < n; ++i) {
            ways[i] = INF;
            for (int j = 0; j < n; ++j) {
                int edge = in.nextInt();
                if (edge >= 0) {
                    graph.add(new way(i, j, edge));
                }
            }
        }
        in.close();

        ways[s] = 0;
        for (; ; ) {
            boolean changed = false;
            for (way edge : graph) {
                if (ways[edge.from] != INF && (ways[edge.to] > ways[edge.from] + edge.weight)) {
                    ways[edge.to] = ways[edge.from] + edge.weight;
                    changed = true;
                }
            }
            if (!changed) {
                break;
            }
        }

        out.print((ways[f] != INF) ? ways[f] : -1);
        out.close();
    }
}
