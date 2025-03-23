package MediaPlayer; 
import java.util.NoSuchElementException;

/**
 * LinkedList class
 * A implementation of a singly linked list data structure
 * with a head pointer.
 * 
 * AP CS students: Implement all methods in this file
 */
public class LinkedList<E> {
    
    /**
     * Node class for the LinkedList
     * Contains data and a reference to the next node
     */
    protected class Node<E> {
        protected E data;         // Data stored in this node
        protected Node<E> next;      // Reference to the next node in the list
        
        /**
         * Constructor for the Node class
         * @param data The data to store in this node
         */
        public Node(E data) {
            this.data = data; 
        }
        
        /**
         * Constructor for the Node class
         * @param data The data to store in this node
         * @param next The reference to the next node
         */
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next; 
        }
    }
    
    // Instance variables for the LinkedList
    protected Node<E> head;  // Reference to the first node in the list
    protected Node<E> last; 
    protected int size;   // Number of elements in the list
    
    /**
     * Default constructor for the LinkedList class
     * Creates an empty list
     */
    public LinkedList() {
        this.size = 0; 
        this.head = null;
        this.last = null; 
    }
    
    /**
     * Returns the number of elements in the list
     * @return The number of elements in the list
     */
    public int size() {
        return size; 
    }
    
    /**
     * Checks if the list is empty
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Returns the first element in the list without removing it
     * @return The first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty"); 
        }
        return head.data;
    }
    
    /**
     * Returns the last element in the list without removing it
     * @return The last element in the list
     * @throws NoSuchElementException if the list is empty
     */
    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty"); 
        }
        return last.data;
    }
    
    /**
     * Adds an element to the beginning of the list
     * @param element The element to add
     */
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.next = head; 
        head = newNode; 
        if (size == 0) {
            last = newNode;
        }
        size++; 
    }
    
    /**
     * Adds an element to the end of the list
     * @param element The element to add
     */
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);
        if (size == 0) {
            head = newNode;
            last = newNode;
        } else {
            last.next = newNode; 
            last = newNode; 
        }
        size++; 
    }
    
    /**
     * Removes and returns the first element in the list
     * @return The first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        E data = head.data;
        head = head.next;
        if (head == null) {
            last = null;
        }
        size--;
        return data;
    }
    
    /**
     * Removes and returns the last element in the list
     * @return The last element in the list
     * @throws NoSuchElementException if the list is empty
     */
    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        if (size == 1) {
            E data = head.data;
            head = null;
            last = null;
            size--;
            return data;
        }
        Node<E> current = head;
        while (current.next != last) {
            current = current.next;
        }
        E data = last.data;
        last = current;
        last.next = null;
        size--;
        return data;
    }
    
    /**
     * Adds an element at the specified index
     * @param index The index at which to add the element
     * @param element The element to add
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            addFirst(element);
            return;
        }
        if (index == size) {
            addLast(element);
            return;
        }
        Node<E> newNode = new Node<>(element);
        Node<E> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }
    
    /**
     * Returns the element at the specified index without removing it
     * @param index The index of the element to return
     * @return The element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<E> current = head; 
        for (int i = 0; i < index; i++) {
            current = current.next; 
        }
        return current.data; 
    }
    
    /**
     * Removes and returns the element at the specified index
     * @param index The index of the element to remove
     * @return The element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node<E> current = head; 
        for (int i = 0; i < index - 1; i++) {
            current = current.next; 
        }
        E data = current.next.data;
        current.next = current.next.next;
        size--;
        return data;
    }
    
    /**
     * Returns the index of the first occurrence of the specified element
     * @param element The element to search for
     * @return The index of the first occurrence of the element, or -1 if not found
     */
    public int indexOf(E element) {

        if (head == null) {
            return -1; // list is empty
        }

        Node<E> current = head;
        int index = 0;

        while (current != null) {

            if (element == null) {
                if (current.data == null) {
                    return index;
                }
            } else {
                if (element.equals(current.data)) {
                    return index;
                }
            }
            current = current.next;
            index++;
        }

        return -1; // value not found
    }
    
    /**
     * Checks if the list contains the specified element
     * @param element The element to search for
     * @return true if the list contains the element, false otherwise
     */
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }
    
    /**
     * Returns a string representation of the list
     * @return A string representation of the list
     */
    @Override
    public String toString() {

        if (head == null) {
            return "[]"; // empty list
        }

        Node<E> current = head;
        StringBuilder result = new StringBuilder();
        result.append("[");

        while (current != null) {
            result.append(current.data);

            if (current.next != null) {
                result.append(", ");
            }

            current = current.next;
        }

        result.append("]");
        return result.toString();
    }
    
    /**
     * Clears the list, removing all elements
     */
    public void clear() {
        head = null;
        size = 0; 
    }
    
    /**
     * Extra challenge: Reverses the list in place
     */
    public void reverse() {
        
        Node<E> previous = null;
        Node<E> current = head; 
        Node<E> next = null; 

        while(current != null){
            next = current.next; 
            current.next = previous;
            previous = current;
            current = next; 
        }

        head = previous; 

    }
}
