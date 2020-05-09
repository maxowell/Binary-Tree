/**
 * An object class representing a node composing a binary tree.
 * A node is composed of a key, a right and a left child.
 * @author Maxime
 * @param <E>
 */
public class Node<E> {

    protected E key;
    protected Node<E> left;
    protected Node<E> right;

    Node (E key) {
        this.key = key;
    }
}
