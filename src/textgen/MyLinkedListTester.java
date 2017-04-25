/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		//System.out.print(shortList.get(1));
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		try {
			shortList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.remove(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		//assertEquals("Remove: check list1.get(0).prev is equal to list1.head ", , list1.head);
		
		// TODO: Add more tests here
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        assertEquals("AddEnd: check first element is correct ", (Integer)65, list1.get(0));
        assertEquals("AddEnd: check second element is correct ", (Integer)21, list1.get(1));
        assertEquals("AddEnd: check size is correct ", 3, list1.size);
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Check size is correct ", 0, emptyList.size);
		emptyList.add(1);
		assertEquals("Check size is correct ", 1, emptyList.size);
		emptyList.remove(0);
		assertEquals("Check size is correct ", 0, emptyList.size);
		
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try{
			shortList.add(null);
		}
		catch (NullPointerException e){
			
		}
		try {
			shortList.add(-1, "a");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.add(3, "a");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		list1.add(0, 100);
			//for(int i = 0; i < list1.size(); i ++){
			//	System.out.print(list1.get(i) + " ");
			//}
			
		assertEquals("AddAtIndex: check first element is correct ", (Integer)100, list1.get(0));
	        //assertEquals("AddEnd: check second element is correct ", (Integer)65, list1.get(1));
		assertEquals("AddAtIndex: check size is correct ", 4, list1.size);
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try{
			shortList.set(0, null);
		}
		catch (NullPointerException e){
			
		}
		try {
			shortList.set(-1, "a");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.set(2, "a");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		int a = list1.set(0, 90);
		assertEquals("Set: check a is correct ", 65, a);
		assertEquals("Set: check first element is correct ", (Integer)90, list1.get(0));
		assertEquals("Set: check size is correct ", 3, list1.size);
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
