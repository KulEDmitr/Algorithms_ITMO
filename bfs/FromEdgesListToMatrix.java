import java.io.*;
import java.util.Scanner;

public class FromEdgesListToMatrix {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int n = in.nextInt();
        int edgesCount = in.nextInt();

        byte[][] graph = new byte[n][n];
        for (int i = 0; i < edgesCount; ++i) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            graph[from][to] = 1;
        }
        in.close();

        for (byte[] line : graph) {
            for (byte element : line) {
                out.print(element + " ");
            }
            out.println();
        }
        out.close();
    }
}
