package datastructures.concrete;

import datastructures.interfaces.IList;
import misc.exceptions.EmptyContainerException;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Note: For more info on the expected behavior of your methods, see the source
 * code for IList.
 */
public class DoubleLinkedList<T> implements IList<T> {
    private Node<T> front;
    private Node<T> back;
    private int size;

    public DoubleLinkedList() {
        this.front = null;
        this.back = null;
        this.size = 0;
    }

    @Override
    public void add(T item) {
        if (size == 0) {
            front = new Node<T>(item);
            back = front;
        } else {
            back.next = new Node<T>(back, item, null);
            back = back.next;
        }
        size++;
    }

    @Override
    public T remove() {
        if (this.size == 0) {
            throw new EmptyContainerException();
        }
        T temp = back.data;
        if (this.size == 1) {
            back = null;
            front = null;
        } else {
            back = back.prev;
            back.next = null;
        }
        size--;
        return temp;
    }

    @Override

    public T get(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        return nodeSearch(index).data;

    }

    @Override
    public void set(int index, T item) {
        // throw new NotYetImplementedException();

        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        if (this.size == 1) {
            front = new Node<T>(item);
            back = front;
        } else if (index == this.size - 1) {
            back.prev.next = new Node<T>(back.prev, item, null);
            back = back.prev.next;
        } else if (index == 0) {
            front.next.prev = new Node<T>(null, item, front.next);
            front = front.next.prev;
        } else {
            Node<T> curr = nodeSearch(index);
            curr.prev.next = new Node<T>(curr.prev, item, curr.next);
            curr.next.prev = curr.prev.next;
        }

    }

    @Override
    public void insert(int index, T item) {
        if (index < 0 || index >= this.size() + 1) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size || size == 0) {
            this.add(item);
        } else {
            if (index == 0) {
                front = new Node<T>(null, item, front);
                front.next.prev = front;
            } else if (index == this.size - 1) {
                back.prev = new Node<T>(back.prev, item, back);
                back.prev.prev.next = back.prev;
            } else {
                Node<T> curr = nodeSearch(index);
                curr.prev = new Node<T>(curr.prev, item, curr);
                curr.prev.prev.next = curr.prev;
            }
            size++;
        }
    }

    @Override
    public T delete(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 1 || index == this.size - 1) {
            return this.remove();
        } else {
            T del = front.data;
            if (index == 0) {
                front = front.next;
                front.prev = null;
            } else {
                Node<T> curr = nodeSearch(index);
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                del = curr.data;
            }
            size--;
            return del;
        }
    }

    @Override
    public int indexOf(T item) {
        Node<T> curr = front;
        int i = 0;
        while (curr != null) {
            if (curr.data == item || curr.data != null && curr.data.equals(item)) {
                return i;
            }
            i++;
            curr = curr.next;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T other) {
        return indexOf(other) != -1;
    }

    private Node<T> nodeSearch(int i) {
        Node<T> curr = front;
        int j = 0;
        if (i < size / 2) {
            while (j < i) {
                curr = curr.next;
                j++;
            }
        } else {
            j = size - 1;
            curr = back;
            while (j > i) {
                curr = curr.prev;
                j--;
            }
        }
        return curr;
    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedListIterator<>(this.front);
    }

    private static class Node<E> {
        public final E data;
        public Node<E> prev;
        public Node<E> next;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public Node(E data) {
            this(null, data, null);
        }
    }

    private static class DoubleLinkedListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public DoubleLinkedListIterator(Node<T> current) {
            this.current = current;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = current.data;
            current = current.next;
            return item;
        }
    }
}
