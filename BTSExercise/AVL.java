package BTSExercise;

public class AVL<E extends Comparable<E>> extends BinarySearchTree<E> {

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private Node balance(Node node) {
        updateHeight(node);

        int balanceFactor = getBalance(node);

        if (balanceFactor > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }

        if (balanceFactor > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balanceFactor < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }

        if (balanceFactor < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    @Override
    protected Node insertRecursive(Node current, E value) {

        if (current == null) {
            return new Node(value);
        }

        int comparison = value.compareTo(current.data);

        if (comparison < 0) {
            current.left = insertRecursive(current.left, value);
        }
        else if (comparison > 0) {
            current.right = insertRecursive(current.right, value);
        }
        else {
            throw new IllegalArgumentException("Elemento duplicado");
        }

        return balance(current);
    }

    @Override
    protected Node removeRecursive(Node current, E value) {

        if (current == null) {
            return null;
        }

        int comparison = value.compareTo(current.data);

        if (comparison < 0) {
            current.left = removeRecursive(current.left, value);
        }
        else if (comparison > 0) {
            current.right = removeRecursive(current.right, value);
        }
        else {

            if (current.left == null && current.right == null) {
                return null;
            }

            if (current.left == null) {
                return current.right;
            }

            if (current.right == null) {
                return current.left;
            }

            E minimum = findMin(current.right);
            current.data = minimum;
            current.right = removeRecursive(current.right, minimum);
        }

        return balance(current);
    }
}
