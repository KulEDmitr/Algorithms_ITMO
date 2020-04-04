import java.io.*;
import java.util.Scanner;

public class AVLinAVL {
    static class Node {
        String key;
        Node data;
        Node left;
        Node right;
        int height;

        Node(String key, Node data, Node left, Node right, int height) {
            this.key = key;
            this.data = data;
            this.left = left;
            this.right = right;
            this.height = height;
        }

        void setNode(String key, Node data, Node left, Node right) {
            this.key = key;
            this.data = data;
            this.left = left;
            this.right = right;
            this.height = Math.max(getHeight(left), getHeight(right)) + 1;
        }
    }

    private static int getHeight(Node tmp) {
        return (tmp == null) ? 0 : tmp.height;
    }

    private int getBalance(Node tmp) {
        return (tmp == null) ? 0 : getHeight(tmp.right) - getHeight(tmp.left);
    }

    private Node findMax(Node tree) {
        return (tree != null && tree.right != null) ? findMax(tree.right) : tree;
    }

    private void leftRotation(Node tree) {
        Node root = tree.right;
        Node tmp1 = new Node(tree.key, tree.data, tree.left, root.left,
                Math.max(getHeight(tree.left), getHeight(root.left)) + 1);
        tree.setNode(root.key, root.data, tmp1, root.right);
    }

    private void rightRotation(Node tree) {
        Node root = tree.left;
        Node tmp1 = new Node(tree.key, tree.data, root.right, tree.right,
                Math.max(getHeight(tree.right), getHeight(root.right)) + 1);
        tree.setNode(root.key, root.data, root.left, tmp1);
    }

    private void makeRotation(Node tree) {
        if (tree == null) {
            return;
        }

        if (tree.right != null) {
            tree.height = getHeight(tree.right) + 1;
        }
        if (tree.left != null) {
            tree.height = Math.max(getHeight(tree), getHeight(tree.left) + 1);
        }

        if (getBalance(tree) > 1) {
            if (getBalance(tree.right) == -1) {
                rightRotation(tree.right);
            }
            leftRotation(tree);
        } else if (getBalance(tree) < -1) {
            if (getBalance(tree.left) == 1) {
                leftRotation(tree.left);
            }
            rightRotation(tree);
        }
    }

    private boolean check(Node data, String key) {
        if (data == null) {
            return false;
        }
        if (key.equals(data.key)) {
            return true;
        }
        if (key.compareToIgnoreCase(data.key) < 0) {
            return check(data.left, key);
        } else {
            return check(data.right, key);
        }
    }

    public boolean check(Node tree, String key, String data) {
        if (tree == null) {
            return false;
        }

        if (key.equalsIgnoreCase(tree.key)) {
            return check(tree.data, data);
        }

        if (key.compareToIgnoreCase(tree.key) < 0) {
            return check(tree.left, key, data);
        } else {
            return check(tree.right, key, data);
        }
    }

    private Node insert(Node data, String key) {
        if (data == null) {
            return new Node(key, null, null, null, 1);
        }
        if (!key.equalsIgnoreCase(data.key)) {
            if (key.compareToIgnoreCase(data.key) < 0) {
                data.left = insert(data.left, key);
            } else {
                data.right = insert(data.right, key);
            }
            makeRotation(data);
        }
        return data;
    }

    public Node insert(Node tree, String key, String data) {
        if (tree == null) {
            return new Node(key, new Node(data, null, null, null, 1),
                    null, null, 1);
        }

        if (key.equalsIgnoreCase(tree.key)) {
            tree.data = insert(tree.data, data);
        } else {
            if (key.compareToIgnoreCase(tree.key) < 0) {
                tree.left = insert(tree.left, key, data);
            } else {
                tree.right = insert(tree.right, key, data);
            }
            makeRotation(tree);
        }
        return tree;
    }

    private Node delete(Node data, String key) {
        if (data == null) {
            return null;
        }

        if (key.equals(data.key)) {
            if (data.left == null && data.right == null) {
                data = null;
            } else if (data.left == null) {
                data = data.right;
            } else {
                data.key = findMax(data.left).key;
                data.left = delete(data.left, data.key);
            }
        } else if (key.compareToIgnoreCase(data.key) < 0) {
            data.left = delete(data.left, key);
        } else {
            data.right = delete(data.right, key);
        }
        makeRotation(data);
        return data;
    }

    public Node delete(Node tree, String key, String data) {
        if (tree == null) {
            return null;
        }

        if (key.equals(tree.key)) {
            tree.data = delete(tree.data, data);
            if (tree.data == null) {
                if (tree.left == null && tree.right == null) {
                    tree = null;
                } else if (tree.left == null) {
                    tree = tree.right;
                } else {
                    tree.key = findMax(tree.left).key;
                    tree.left = delete(tree.left, tree.key, data);
                }
                makeRotation(tree);
            }
        } else if (key.compareToIgnoreCase(tree.key) < 0) {
            tree.left = delete(tree.left, key, data);
        } else {
            tree.right = delete(tree.right, key, data);
        }
        return tree;
    }

    private void print(Node data, String key, PrintWriter out) {
        if (data != null) {
            out.print(key + '\n' + data.key + "(balance: " + getBalance(data) + "), " +
                    ((data.left != null) ? data.left.key + "(balance: " + getBalance(data.left) + "), " : "") +
                    ((data.right != null) ? data.right.key + "(balance: " + getBalance(data.right) + "), " : ""));
            print(data.left, out);
            print(data.right, out);
            out.println();
        }
    }

    public void print(Node tree, PrintWriter out) {
        if (tree != null) {
            print(tree.data, tree.key, out);
            print(tree.left, out);
            print(tree.right, out);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("operations.in"));
        PrintWriter out = new PrintWriter("operations.out");
        Node tree = null;
        while (in.hasNext()) {
            char operation = in.next().charAt(0);

            if (operation == 'W') {
                out.println("\nwalk: (tree balance: " + new AVLinAVL().getBalance(tree) + ")");
                new AVLinAVL().print(tree, out);
                out.println();
            } else {

                String key = in.next();
                String data = in.next();
                switch (operation) {
                    case 'A':
                        tree = new AVLinAVL().insert(tree, key, data);
                        break;
                    case 'C':
                        out.println("pair (" + key + ", " + data + ") is" +
                                (new AVLinAVL().check(tree, key, data) ? " " : "n't ") + "exist");
                        break;
                    case 'D':
                        tree = new AVLinAVL().delete(tree, key, data);
                        break;
                }
            }
        }
        in.close();
        out.close();
    }
}
