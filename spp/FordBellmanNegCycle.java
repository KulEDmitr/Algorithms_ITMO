import java.io.*;
import java.util.*;
 
public class FordBellmanNegCycle {
    private static long INF = 30_000_000_000_000_000L;
 
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
        Scanner in = new Scanner(new File("negcycle.in"));
        PrintWriter out = new PrintWriter("negcycle.out");
 
        int n = in.nextInt();
 
        List<way> graph = new ArrayList<>();
        long[] ways = new long[n];
        int[] par = new int[n];
 
        for (int i = 0; i < n; ++i) {
            par[i] = -1;
            for (int j = 0; j < n; ++j) {
                int edge = in.nextInt();
                graph.add(new way(i, j, edge));
            }
        }
        in.close();
 
        int changed = 0;
        for (int i = 0; i < n; ++i) {
            changed = -1;
            for (way edge : graph) {
                if (ways[edge.from] != INF && (ways[edge.to] > ways[edge.from] + edge.weight)) {
                    ways[edge.to] = ways[edge.from] + edge.weight;
                    par[edge.to] = edge.from;
                    changed = edge.to;
                }
            }
        }
        List<Integer> cycle = new ArrayList<>();
        if (changed != -1) {
            for (int i = 0; i < n; ++i) {
                changed = par[changed];
            }
            int cur = changed;
            do {
                cycle.add(cur);
                cur = par[cur];
            } while (cur != changed);
            cycle.add(changed);
        }
 
        if (changed == -1) {
            out.print("NO");
        } else {
            out.println("YES");
            int count = cycle.size();
            out.println(count);
            for (int i = count - 1; i >= 0; --i) {
                out.print((cycle.get(i) + 1) + " ");
            }
        }
        out.close();
    }
}