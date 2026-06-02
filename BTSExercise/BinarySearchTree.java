package BTSExercise;

public class BinarySearchTree<E extends Comparable<E>> {

    protected class Node {
        E data;
        Node left;
        Node right;
        int height;

        // crea un nodo nuevo con altura inicial 1.
        Node(E data) {
            this.data = data;
            this.height = 1;
        }
    }

    protected Node root;

    // inserta un valor en el arbol.
    public void insert(E value) {
        if (value == null) {
            throw new IllegalArgumentException("Valor nulo");
        }

        root = insertRecursive(root, value);
    }

    // inserta recursivamente en la posicion correcta.
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

        return current;
    }

    // verifica si un valor existe en el arbol.
    public boolean contains(E value) {
        return containsRecursive(root, value);
    }

    // busca recursivamente un valor en el arbol.
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

    // busca y devuelve el valor almacenado en el arbol.
    public E search(E value) {
        Node result = searchRecursive(root, value);

        if (result == null) {
            return null;
        }

        return result.data;
    }

    // busca recursivamente el nodo que contiene el valor.
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

    // elimina un valor del arbol.
    public void remove(E value) {
        root = removeRecursive(root, value);
    }

    // elimina recursivamente un nodo y reacomoda enlaces.
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

        return current;
    }

    // obtiene el valor minimo de un subarbol
    protected E findMin(Node current) {

        while (current.left != null) {
            current = current.left;
        }

        return current.data;
    }

    // recorre el arbol en in-order e imprime los elementos
    public void inOrder() {
        inOrderRecursive(root);
    }

    // recorrido in-order recursivo
    private void inOrderRecursive(Node current) {

        if (current == null) {
            return;
        }

        inOrderRecursive(current.left);
        System.out.println(current.data);
        inOrderRecursive(current.right);
    }
}

