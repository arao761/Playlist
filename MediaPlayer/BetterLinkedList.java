package MediaPlayer;

/**
 * BetterLinkedList class
 * Extends (my custom) LinkedList class.
 * Adds the ability to swap elements and sort them.
 */

public class BetterLinkedList<E> extends LinkedList<E> {
 /**
  * Swaps the elements at the specified positions in this list.
  *
  * @param i the index of the first element to be swapped
  * @param j the index of the second element to be swapped
  * @throws IndexOutOfBoundsException if either index is out of range (i < 0 || i >= size || j < 0 || j >= size)
  */
 public void swap(int i, int j) {

    if(i < 0 || i >= size || j < 0 || j >= size){
        throw new IndexOutOfBoundsException("index out of bounds");
    }

	
    E removed = remove(j);

     add(i, removed);

 }

 /**
  * Swaps the element at the specified position with the next element in this list.
  * Is more efficient than swap(i,i+1) because it avoids two linear searches.
  * @param i the index of the element to be swapped with its next element
  * @throws IndexOutOfBoundsException if the index is out of range (i < 0 || i >= size - 1)
  */
 public void swapWithNext(int i) {
	if(i < 0 || i >= size - 1){
        throw new IndexOutOfBoundsException("index out of bounds"); 
    }

    E removed = remove(i+1);

     add(i, removed);
     
 }

 /**
  * Sorts the elements in this list in ascending order.
  * This method uses a simple bubble sort algorithm to arrange the elements.
  * The elements must implement the Comparable interface for the sorting to work correctly.
  *
  * @throws ClassCastException if the list contains elements that do not implement Comparable
  */
 public void sort() {
	//todo: implement this method
 }
}
