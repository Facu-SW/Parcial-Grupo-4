package BTSExercise;

public class BinarySearchTree<E extends Comparable<E>> {

    private class Node {
        E data;
        Node left;
        Node right;

        Node(E data) {
            this.data = data;
        }
    }

    private Node root;

    public void insert(E value) {
        if (value == null) {
            throw new IllegalArgumentException("Valor nulo");
        }

        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node current, E value) {

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

        return current;
    }

    public boolean contains(E value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(Node current, E value) {

        if (current == null) {
            return false;
        }

        int comparison = value.compareTo(current.data);

        if (comparison == 0) {
            return true;
        }

        if (comparison < 0) {
            return containsRecursive(current.left, value);
        }

        return containsRecursive(current.right, value);
    }

    public E search(E value) {
        Node result = searchRecursive(root, value);

        if (result == null) {
            return null;
        }

        return result.data;
    }

    private Node searchRecursive(Node current, E value) {

        if (current == null) {
            return null;
        }

        int comparison = value.compareTo(current.data);

        if (comparison == 0) {
            return current;
        }

        if (comparison < 0) {
            return searchRecursive(current.left, value);
        }

        return searchRecursive(current.right, value);
    }

    public void remove(E value) {
        root = removeRecursive(root, value);
    }

    private Node removeRecursive(Node current, E value) {

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

        return current;
    }

    private E findMin(Node current) {

        while (current.left != null) {
            current = current.left;
        }

        return current.data;
    }

    public void inOrder() {
        inOrderRecursive(root);
    }

    private void inOrderRecursive(Node current) {

        if (current == null) {
            return;
        }

        inOrderRecursive(current.left);
        inOrderRecursive(current.right);

        System.out.println(current.data);
}
}

