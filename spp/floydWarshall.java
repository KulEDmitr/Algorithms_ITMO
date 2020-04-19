import java.io.*;
import java.util.*;

public class floydWarshall {

    public static void main(String[] args) throws IOException {
        long INF = 200_000_000_000L;

        Scanner in = new Scanner(new File("pathmgep.in"));
        PrintWriter out = new PrintWriter("pathmgep.out");

        int n = in.nextInt();
        int s = in.nextInt() - 1;
        int f = in.nextInt() - 1;

        long[][] graph = new long[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int cost = in.nextInt();
                graph[i][j] = (cost < 0) ? INF : cost;
            }
        }
        in.close();

        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (graph[i][k] != INF && graph[k][j] != INF) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    }
                }
            }
        }
        out.print((graph[s][f] != INF) ? graph[s][f] : -1);
        out.close();
    }
}
