import java.io.*;
import java.util.*;

public class SpanTreeFullGraph {
    public static class vert {
        int x;
        int y;

        vert(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("spantree.in"));
        PrintWriter out = new PrintWriter("spantree.out");

        int n = in.nextInt();
        vert[] vertexes = new vert[n];
        boolean[] used = new boolean[n];
        long[] min_edg = new long[n];

        for (int i = 0; i < n; ++i) {
            int x = in.nextInt();
            int y = in.nextInt();
            vertexes[i] = new vert(x, y);
            min_edg[i] = Long.MAX_VALUE;
        }

        min_edg[0] = 0;
        double ans = 0;

        for (int i = 0; i < n; ++i) {
            int v = -1;
            for (int j = 0; j < n; ++j) {
                if (!used[j] && (v == -1 || min_edg[j] < min_edg[v])) {
                    v = j;
                }
            }

            used[v] = true;
            ans += Math.sqrt(min_edg[v]);

            for (int j = 0; j < n; ++j) {
                if (!used[j]) {
                    long dx = vertexes[v].x - vertexes[j].x;
                    long dy = vertexes[v].y - vertexes[j].y;
                    long new_edge = dx * dx + dy * dy;

                    min_edg[j] = Math.min(min_edg[j], new_edge);
                }
            }
        }

        out.printf("%.10f", ans);
        out.close();
    }
}
