import java.io.*;
 
public class DijkstraQueueAllToAll {
 
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("pathmgep.in"));
        PrintWriter out = new PrintWriter("pathmgep.out");
 
        String[] data = in.readLine().split(" ");
        int n = Integer.valueOf(data[0]);
        int s = Integer.valueOf(data[1]) - 1;
        int f = Integer.valueOf(data[2]) - 1;
 
        long[][] graph = new long[n][n];
        long[] ways = new long[n];
 
        long INF = 3_000_000_000_000L;
        for (int i = 0; i < n; ++i) {
            ways[i] = INF;
            String[] line = in.readLine().split(" ");
            for (int j = 0; j < n; ++j) {
                long cur = Long.valueOf(line[j]);
                graph[i][j] = (cur == -1) ? INF : cur;
            }
        }
        in.close();
 
        boolean[] calc = new boolean[n];
        ways[s] = 0;
        for (int i = 0; i < n; ++i) {
            int vert = -1;
            for (int j = 0; j < n; ++j) {
                if (!calc[j] && (vert == -1 || ways[j] < ways[vert])) {
                    vert = j;
                }
            }
            if (ways[vert] == INF) {
                break;
            }
            calc[vert] = true;
 
            for (int j = 0; j < n; ++j) {
                ways[j] = Math.min(ways[vert] + graph[vert][j], ways[j]);
            }
        }
        out.print((ways[f] != INF) ? ways[f] : -1);
        out.close();
    }
}