package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	
	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		head = new LLNode<E> (null);
		tail = new LLNode<E> (null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if(element == null){
			throw new NullPointerException("Remember last object cannot store null pointer.");
		}
		LLNode<E> addedList = new LLNode<E> (element);
		/* A wrong way
		 * tail.prev.next =  addedList;
		 * addedList.prev = tail.prev;
		 * tail.prev = addedList;
		 * addedList.next = tail;		
		*/
		addedList.prev = tail.prev;
		addedList.next = tail;
		tail.prev.next = addedList;
		addedList.next.prev = addedList;
		size++;
		
		
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if(index < 0 || index >= size){
				throw new IndexOutOfBoundsException("Remember index should be valid.");
		}
		LLNode<E> list = new LLNode<E>(null);	
		list = head.next;
		for(int i = 0; i < index; i++){
			list = list.next;
		}
		return list.data;		
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if(element == null){
			throw new NullPointerException("Remember added element cannot be null.");
		}
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("Remember index should be valid.");
		}
		LLNode<E> addedList = new LLNode<E> (null);
		addedList.data = element;
		LLNode<E> prevList = new LLNode<E> (null);
		LLNode<E> nextList = new LLNode<E> (null);
		//if(size < 2){
			if(index == 0){
				addedList.next = head.next;
				addedList.prev = addedList.next.prev;
				addedList.next.prev = addedList;
				addedList.prev.next = addedList;
			}
			else if(index == size - 1){
				addedList.prev = tail.prev;
				addedList.next = tail;
				tail.prev.next = addedList;
				addedList.next.prev = addedList;
			}
		//}
		else{
			//if(index == 0){
			//	nextList = tail.prev;
			//	for(int i = size - 1; i > index; i--){
			//		nextList = nextList.prev;
			//	}
			//	addedList.next = head.next;
			//	addedList.prev = addedList.next.prev;
			//	addedList.next.prev = addedList;
			//	head.next = addedList;
			//	size++;
			//}
			prevList = head.next;
			nextList = tail.prev;
			for(int i = 0; i < index - 1; i++){
				prevList = prevList.next;
			}
			for(int i = size - 1; i > index; i--){
				nextList = nextList.prev;
			}
			addedList.next = nextList;
			addedList.prev = prevList;
			prevList.next = addedList;
			nextList.prev = addedList;
			
			//addedList.prev = nextList.prev;
			//addedList.next = nextList;
			//nextList.prev.next = addedList;
			//addedList.next.prev = addedList;
			
		}
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Remember index should be valid.");
		}
		LLNode<E> removedList = new LLNode<E> (null);
		removedList = head.next;
		for(int i = 0; i < index; i++){
			removedList = removedList.next;
		}
		E value = removedList.data;
		removedList.prev.next = removedList.next;
		removedList.next.prev = removedList.prev;
		size--;
		return value;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if(element == null){
			throw new NullPointerException("Remember added element cannot be null.");
		}
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Remember index should be valid.");
		}
		LLNode<E> changedList = head.next;
		for(int i = 0; i < index; i++){
			changedList = changedList.next;
		}
		E value = changedList.data;
		changedList.data = element;
		return value;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
