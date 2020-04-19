import java.io.*;
 
public class floydWarshallNegCycles {
    public static void main(String[] args) throws IOException {
        long INF = 2_000_000_000_000_000_000L;
 
        BufferedReader in = new BufferedReader(new FileReader("path.in"));
        PrintWriter out = new PrintWriter("path.out");
 
        String[] data = in.readLine().split(" ");
        int n = Integer.valueOf(data[0]);
        int m = Integer.valueOf(data[1]);
        int s = Integer.valueOf(data[2]) - 1;
 
        long[][] graph = new long[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                graph[i][j] = INF;
            }
            graph[i][i] = 0;
        }
 
        for (int e = 0; e < m; ++e) {
            String[] edge = in.readLine().split(" ");
            int from = Integer.valueOf(edge[0]) - 1;
            int to = Integer.valueOf(edge[1]) - 1;
            long cost = Long.valueOf(edge[2]);
            graph[from][to] = Math.min(graph[from][to], cost);
        }
        in.close();
 
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (graph[i][k] < INF && graph[k][j] < INF && graph[i][j] >= graph[i][k] + graph[k][j]) {
                        graph[i][j] = (graph[k][k] < 0) ? -INF : graph[i][k] + graph[k][j];
                    }
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            long way = graph[s][i];
            if (way == INF) {
                ans.append("*");
            } else if (way == -INF) {
                ans.append("-");
            } else {
                ans.append(way);
            }
            ans.append("\n");
        }
        out.print(ans);
        out.close();
    }
}