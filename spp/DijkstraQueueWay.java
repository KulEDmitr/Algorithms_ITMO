import java.io.*;
import java.util.*;
 
public class DijkstraQueue {
    private static long INF = 200_000_000_000L;
 
    private static class way {
        int to;
        long weight;
 
        way(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }
 
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("pathbgep.in"));
        PrintWriter out = new PrintWriter("pathbgep.out");
 
        int n = in.nextInt();
        int m = in.nextInt();
 
        List<List<way>> graph = new ArrayList<>();
        long[] ways = new long[n];
 
        for (int i = 0; i < n; ++i) {
            ways[i] = INF;
            graph.add(new ArrayList<>());
        }
 
        for (int k = 0; k < m; ++k) {
            int i = in.nextInt() - 1;
            int j = in.nextInt() - 1;
            long cost = in.nextInt();
            graph.get(i).add(new way(j, cost));
            graph.get(j).add(new way(i, cost));
        }
        in.close();
 
        PriorityQueue<way> storage = new PriorityQueue<>(Comparator.comparingLong((way w) -> w.weight));
        boolean[] used = new boolean[n];
 
        ways[0] = 0;
        storage.add(new way(0, 0));
        for (int i = 0; i < n; ++i) {
            boolean edge = false;
            int cur = 0;
            while (!edge) {
                if (storage.isEmpty()) {
                    break;
                }
 
                way min = storage.poll();
 
                cur = min.to;
                if (used[cur] || ways[cur] != min.weight) {
                    continue;
                }
 
                edge = true;
                used[cur] = true;
            }
 
            for (way to : graph.get(cur)) {
                if (ways[to.to] > ways[cur] + to.weight) {
                    ways[to.to] = ways[cur] + to.weight;
                    storage.add(new way(to.to, ways[to.to]));
                }
            }
        }
        for (long way : ways) {
            out.print(way + " ");
        }
        out.close();
    }
}