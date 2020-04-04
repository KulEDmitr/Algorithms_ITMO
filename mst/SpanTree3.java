import java.io.*;
import java.util.*;

public class SpanTree3 {

    public static class dsu {
        int[] parent;
        int[] rank;

        dsu(int vert) {
            parent = new int[vert];
            rank = new int[vert];
            for (int i = 0; i < vert; ++i) {
                parent[i] = i;
            }
        }

        int find (int v) {
            if (v == parent[v]) {
                return v;
            }
            return parent[v] = find(parent[v]);
        }

        void union (int a, int b) {
            a = find(a);
            b = find(b);
            if (a != b) {
                if (rank[a] < rank[b]) {
                    int c = a;
                    a = b;
                    b = c;
                }
                parent[b] = a;
                if (rank[a] == rank[b]) {
                    ++rank[a];
                }
            }
        }
    }

    public static class edge {
        int weight;
        int from;
        int to;

        edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new File("spantree3.in"));
        PrintWriter out = new PrintWriter("spantree3.out");

        int n = in.nextInt();
        int countEdges = in.nextInt();
        List<edge> graph = new ArrayList<>();

        for (int i = 0; i < countEdges; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int weight = in.nextInt();
            graph.add(new edge(a, b, weight));
        }
        in.close();

        graph.sort(Comparator.comparingInt(a -> a.weight));
        dsu components = new dsu(n);
        long cost = 0;
        for (int i = 0; i < countEdges; ++i) {
            edge cur = graph.get(i);
            int a = cur.from;
            int b = cur.to;
            if (components.find(a) != components.find(b)) {
                cost += cur.weight;
                components.union(a, b);
            }
        }

        out.print(cost);
        out.close();
    }
}
