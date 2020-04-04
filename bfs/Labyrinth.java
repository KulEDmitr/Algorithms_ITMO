import java.io.*;
import java.util.*;

public class Labyrinth {
    private List<Integer> checkN(int vert, StringBuilder maze, int len, int width) {
        List<Integer> edges = new ArrayList<>();
        if (vert + width < len * width && maze.charAt(vert + width) != '#') {
            edges.add(vert + width);
        }
        if (vert - width >= 0 && maze.charAt(vert - width) != '#') {
            edges.add(vert - width);
        }
        if (vert < len * width && vert % width < (vert + 1) % width && maze.charAt(vert + 1) != '#') {
            edges.add(vert + 1);
        }
        if (vert > 0 && vert % width > (vert - 1) % width && maze.charAt(vert - 1) != '#') {
            edges.add(vert - 1);
        }
        return edges;
    }

    private int bfs(StringBuilder maze, int[] parents, int start, int terminal, int len, int width) {
        if (terminal == -1 || start == -1) return -1;

        Deque<Integer> elementsQueue = new ArrayDeque<>();
        int n = len * width;
        int[] used = new int[n];
        int[] ways = new int[n];

        elementsQueue.addLast(start);
        used[start] = 1;

        while (!elementsQueue.isEmpty() && used[terminal] == 0) {
            int parent = elementsQueue.pop();
            for (int el : checkN(parent, maze, len, width)) {
                if (used[el] == 0) {
                    elementsQueue.addLast(el);
                    ways[el] = ways[parent] + 1;
                    parents[el] = parent;
                    used[el] = 1;
                }
            }
        }
        return (used[terminal] == 1) ? ways[terminal] : -1;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("output.txt");
        int len = in.nextInt();
        int width = in.nextInt();
        StringBuilder maze = new StringBuilder();

        String kostyl = "";
        if (width == 1) {
            kostyl = "#";
            width = 2;
        }

        for (int i = 0; i < len; ++i) {
            maze.append(in.next());
            maze.append(kostyl);
        }
        in.close();

        int start = -1;
        int terminal = -1;
        int count = len * width;
        for (int i = 0; i < count; ++i) {
            char mode = maze.charAt(i);
            if (mode == 'S') {
                start = i;
            } else if (mode == 'T') {
                terminal = i;
            }
        }

        int[] parents = new int[count];
        int ans = new Labyrinth().bfs(maze, parents, start, terminal, len, width);

        if (ans == -1) {
            out.print(ans);
        } else {

            int[] path = new int[ans + 1];
            path[ans] = terminal;
            int way = ans;
            while (way > 0) {
                path[way - 1] = parents[path[way]];
                --way;
            }

            out.println(ans);
            for (int i = 0; i < ans; ++i) {
                int dif = path[i] - path[i + 1];
                out.print((dif > 0)
                        ? (dif == 1) ? 'L' : 'U'
                        : (dif == -1) ? 'R' : 'D');
            }
        }
        out.close();
    }
}
