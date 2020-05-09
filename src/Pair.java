/**
 * An object class representing a pair of an object.
 * A pair is composed of a current object and it's parent.
 * @author Maxime
 * @param <E>
 */
public class Pair<E extends Comparable<E>> {

    Node<E> parent;
    Node<E> current;

    Pair (Node<E> parent, Node<E> current) {
        this.parent = parent;
        this.current = current;
    }
}