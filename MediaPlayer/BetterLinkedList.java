package MediaPlayer;

/**
 * BetterLinkedList class
 * Extends (my custom) LinkedList class.
 * Adds the ability to swap elements and sort them.
 */

public class BetterLinkedList<E extends Comparable<E>> extends LinkedList<E> {
 /**
  * Swaps the elements at the specified positions in this list.
  *
  * @param i the index of the first element to be swapped
  * @param j the index of the second element to be swapped
  * @throws IndexOutOfBoundsException if either index is out of range (i < 0 || i >= size || j < 0 || j >= size)
  */


  //helper method to getnode at a specific index 
  public Node<E> getNode(int index) {
    
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index out of bounds");
    }

    Node<E> current = head; // Start at the head
    for (int i = 0; i < index; i++) {
        current = current.next; // Move to the next node
    }
    return current; // Return the node at the desired index
}


public void swap(int i, int j) {
   
    if (i < 0 || i >= size || j < 0 || j >= size) {
        throw new IndexOutOfBoundsException("Index out of bounds");
    }

    if (i == j) {
        return; 
    }

    Node<E> node1 = getNode(i);
    Node<E> node2 = getNode(j);

    E temp = node1.data;
    node1.data = node2.data;
    node2.data = temp;
}

 /**
  * Swaps the element at the specified position with the next element in this list.
  * Is more efficient than swap(i,i+1) because it avoids two linear searches.
  * @param i the index of the element to be swapped with its next element
  * @throws IndexOutOfBoundsException if the index is out of range (i < 0 || i >= size - 1)
  */
  public void swapWithNext(int i) {
    if (i < 0 || i >= size - 1) {
        throw new IndexOutOfBoundsException("Index out of bounds");
    }

    Node<E> currentNode = getNode(i);
    Node<E> nextNode = currentNode.next;

    E temp = currentNode.data;
    currentNode.data = nextNode.data;
    nextNode.data = temp;
}

 /**
  * Sorts the elements in this list in ascending order.
  * This method uses a simple bubble sort algorithm to arrange the elements.
  * The elements must implement the Comparable interface for the sorting to work correctly.
  *
  * @throws ClassCastException if the list contains elements that do not implement Comparable
  */
 public void sort() {

    if (size <= 1) {
        return;
    }

    Node<E> current = head;

    for (int i = 0; i < size - 1; i++) {
        if (current.data.compareTo(current.next.data) > 0) {
            swapWithNext(i);
        }
        
        current = current.next;
       
    }

    } 

}

