import java.io.*;
import java.util.Scanner;

public class CheckValidationOfSimpleUndirectedGraph {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int n = in.nextInt();
        byte[][] graph = new byte[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                graph[i][j] = in.nextByte();
            }
        }
        in.close();

        boolean valid = true;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (graph[i][j] != graph[j][i] || (graph[i][j] == 1 && i == j)) {
                    valid = false;
                    break;
                }
            }
        }

        out.print(valid ? "YES" : "NO");
        out.close();
    }
}
