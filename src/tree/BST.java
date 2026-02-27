package tree;

public class BST {

    private Node root;

    // Each node stores a location name
    private static class Node {
        String locationName;
        Node left, right;

        Node(String name) {
            this.locationName = name;
        }
    }

    // Insert a new location into BST
    public void insert(String name) {
        root = insertRec(root, name);
    }

    private Node insertRec(Node node, String name) {
        if (node == null) return new Node(name);
        if (name.compareTo(node.locationName) < 0)
            node.left = insertRec(node.left, name);
        else if (name.compareTo(node.locationName) > 0)
            node.right = insertRec(node.right, name);
        return node;
    }

    // Search for a location in BST
    public boolean search(String name) {
        return searchRec(root, name);
    }

    private boolean searchRec(Node node, String name) {
        if (node == null) return false;
        if (name.equals(node.locationName)) return true;
        if (name.compareTo(node.locationName) < 0)
            return searchRec(node.left, name);
        return searchRec(node.right, name);
    }

    // Display all locations in alphabetical order (in-order traversal)
    public void displayInOrder() {
        System.out.println("\n[BST] All locations (alphabetical order):");
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(Node node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.print(node.locationName + "  ");
            inOrderRec(node.right);
        }
    }
}