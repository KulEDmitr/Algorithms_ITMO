import java.io.*;
import java.util.Scanner;

public class IsParallelEdgesExist {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int n = in.nextInt();
        int edgesCount = in.nextInt();
        byte[][] graph = new byte[n][n];

        boolean exist = false;
        for (int i = 0; i < edgesCount; ++i) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;

            if (graph[to][from] == 1 || graph[from][to] == 1) {
                exist = true;
                break;
            }
            graph[from][to] = 1;
        }

        out.print(exist ? "YES" : "NO");
        out.close();
    }
}
