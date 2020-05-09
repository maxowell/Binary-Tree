/**
 * Object class representing a binary tree.
 * The tree's elements are maintained in order with the compareTo method from the E objects.
 * There's no algorithm to rearrange the elements.
 * Elements are only present once in the tree.
 * This is a set.
 * @author Maxime
 * @param <E>
 */
public class BinaryTree<E extends Comparable<E>>{

    Node<E> root;

    /**
     * This method searches for a target (key) in the tree.
     * It returns a reference towards the node containing the key and towards the node's parent.
     *
     * parent current | Conclusion
     * -----------------------------------------------
     *  null   null   | The binary tree is empty
     *  null   !null  | The node found is the root
     * !null   null   | The node is not in the tree
     * !null   !null  | The node found is not the root
     *
     * @param target = the key contained by the node we are searching for. Should not by null.
     * @return a pair containing :
     *      -parent : a reference on the parent of the node we are searching for
     *      -current : a reference on the node we are searching for
     */
    public Pair<E> searchNode(E target) {

        Node<E> current = root;
        Node<E> parent = null;

        while (current != null && current.key != target) {

            if (target.compareTo(current.key) < 0) {
                parent = current;
                current = current.left;
            } else if (target.compareTo(current.key) > 0) {
                parent = current;
                current = current.right;
            }

        }

        return new Pair<>(parent, current);
    }


    /**
     * Check if the given key is contained in the tree.
     * @param key = the key we want to check if it is contained. Should not be null.
     * @return boolean true if the key is contained, false otherwise.
     */
    public boolean contains(E key) {
        assert key != null;
        Pair<E> result = searchNode(key);
        return result.current != null;
    }


    /**
     * Adds a key in the tree if it is not already present.
     * @param key = the key we want to add. Should not be null.
     */
    public void insert(E key) {
        Pair<E> p = searchNode(key);

        if (p.current == null) {

            if (p.parent == null) {
                root = new Node<>(key);
            } else {

                if (key.compareTo(p.parent.key) < 0) {
                    p.parent.left = new Node<>(key);
                }else {
                    p.parent.right = new Node<>(key);
                }
            }
        }
    }

    /**
     * Searches for the highest key contained under the node given.
     * @param node = node from where we want to start looking. Should not be null.
     * @return the highest key found under the node.
     */
    public E maxima(Node<E> node) {
        E maxima = null;

        if (node != null) {

            while (node.right != null) {
                node = node.right;
            }
            maxima = node.key;
        }

        return maxima;
    }


    /**
     * Deletes the current node from the tree.
     * If the parent node isn't null, he must have the current node has a child.
     * Else, the current node has to be the root.
     *
     * @param parent = the parent node of the one we want to delete. If it is null, the method deletes the root.
     * @param current = the current node we want to delete. Should not be null.
     */
    public void deleteNode(Node<E> parent, Node<E> current) {
        assert current != null;
        assert parent == null
                ? current == root : (parent.right == current || parent.left == current);

        if (current.left == null) {
            //Kid is left or no kid

            if (parent == null) {
                root = current.right;
            } else {
                if (parent.right == current) {
                    parent.right = current.right;
                }else {
                    parent.left = current.right;
                }
            }

        } else if (current.right == null) {
            //Kid is right

            if (parent == null) {
                root = current.left;
            } else {

                if (parent.left == current) {
                    parent.left = current.left;
                } else {
                    parent.right = current.left;
                }
            }

        } else {
            //Two kids
            Node<E> precedent = new Node<>(current.left.key);
            Node<E> parentPrecedent = new Node<>(current.key);

            while (precedent.right != null) {
                parentPrecedent = precedent;
                precedent = precedent.right;
            }

            current.key = precedent.key;

            if (parentPrecedent == current) {
                parentPrecedent.left = precedent.left;
            } else {
                parentPrecedent.right = precedent.left;
            }
        }
    }


    /**
     * Deletes a key from the tree, if it is already present.
     * @param key = key we want to delete. Should not be null.
     */
    public void delete(E key) {
        assert key != null;
        Pair<E> result = searchNode(key);
        if (result.current != null) {
            deleteNode(result.parent,result.current);
        }
    }
}
