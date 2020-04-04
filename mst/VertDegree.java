import java.io.*;
import java.util.*;

public class VertDegree {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");

        int n = in.nextInt();
        int countEdges = in.nextInt();

        int[] deg = new int[n];
        for (int i = 0; i < countEdges; ++i) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            ++deg[a];
            ++deg[b];
        }
        in.close();

        for (int d : deg) {
            out.print(d + " ");
        }
        out.close();
    }
}
