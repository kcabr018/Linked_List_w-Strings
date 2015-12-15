//=======================================================
// Author:	Kevin Cabrera
// Description:	This class uses a Linked List to store
//		a list of strings. Actions may be performed on
//		the strings, such as appending to a string, 
//		searching a string, displaying the list, etc.
//=======================================================
//		
// A linked list is a sequence of nodes with efficient
// element insertion and removal.
// This class contains a subset of the methods of the
// standard java.util.LinkedList class.

import java.util.NoSuchElementException;

public class LinkedList
{

	String list = "{ ";
	int position = 0;

	//nested class to represent a node
	private class Node
	{
		public Object data;
		public Node next;
	}

	//only instance variable that points to the first node.
	private Node first;

	// Constructs an empty linked list.
	public LinkedList()
	{
		first = null;
	}


	// Returns the first element in the linked list.
	public Object getFirst()
	{
		if (first == null)
		{
			NoSuchElementException ex = new NoSuchElementException();
			throw ex;
		}
		else
			return first.data;
	}

	// Removes the first element in the linked list.
	public Object removeFirst()
	{
		if (first == null)
		{
			NoSuchElementException ex = new NoSuchElementException();
			throw ex;
		}
		else
		{
			Object element = first.data;
			first = first.next;  //change the reference since it's removed.
			return element;
		}
	}

	// Adds an element to the front of the linked list.
	public void addFirst(Object element)
	{
		//create a new node
		Node newNode = new Node();
		newNode.data = element;
		newNode.next = first;

		//change the first reference to the new node.
		first = newNode;
	}

	// Returns an iterator for iterating through this list.
	public ListIterator listIterator()
	{
		return new LinkedListIterator();
	}

	// Returns a a string of elements in LinkedList
	public String toString()
	{
		LinkedListIterator it = new LinkedListIterator();
		String result = "{ ";

		while (it.hasNext())
			result += it.next() + " ";
		result += "}\n";

		return result;

	}

	// Returns size of list
	public int size()
	{
		LinkedListIterator it = new LinkedListIterator();
		int count = 0;
		while (it.hasNext())
		{
			it.next();
			count++;
		}
		return count;
	}

	// The searchElement method returns the index of the element (string). 
	// If the element does not exist, then it returns -1
	// **next() Moves the iterator past the next element, and returns the traversed data
	// **hasNext() Tests if there is an element after the iterator position.
	public int searchElement(Object element)
	{
		LinkedListIterator it = new LinkedListIterator();
		int result = 0;

		for (int i = 0 ; i < size(); i ++)
		{
			if (it.hasNext())
				if (it.next().equals(element))
					return i;
				else if (!it.hasNext())
					result = -1;
		}

		return result;
	}

	// The getElement method returns an element at the given index (passed as its 
	// parameter). If the parameter index is larger or smaller than the existing
	// indices, it should throw an object of the type IndexOutOfBoundsException class.
	public Object getElement(int index)
	{
		LinkedListIterator it = new LinkedListIterator();

		if (index < 0 || index > size() - 1)
		{
			IndexOutOfBoundsException  ex = new IndexOutOfBoundsException();
			throw ex;
		}

		for (int i = 0; i < index; i++)
			it.next();
		return it.next();
	}

	// The addElement adds the element at the specified index. The element at the
	// index and any elements at the later indices will be shifted towards the end
	// of the list. If the parameter index is larger or smaller than the existing 
	// indices, it should throw an object of the IndexOutOfBoundsException class.
	// hasNext() test if there's something after iterator position
	// *next() Moves the iterator past the next element, and returns
	//  the traversed element's data.
	// *add() adds element before iterator position
	// *set() Sets the last traversed element to a different value.
	public void addElement (int index, Object element)
	{
		LinkedListIterator it = new LinkedListIterator();

		if (index < 0 || index > size())
		{
			IndexOutOfBoundsException  ex = new IndexOutOfBoundsException();
			throw ex;
		}

		for (int i = 0; i < index; i++)
		{
			if (it.hasNext())
				it.next();
		}

		if (it.hasNext())
		{
			Object temp = it.next();
			it.set(element);
			while (it.hasNext())
			{
				Object temp2 = it.next();
				it.set(temp);
				temp = temp2;
			}
			it.add(temp);
		}
		else
			it.add(element);
	}

	//The removeElement removes the element at the specified index and the removed
	//element should be returned. If the parameter index is larger or smaller than
	//the existing indices, it should throw an object of the 
	//IndexOutOfBoundsException class.
	public Object removeElement(int index)
	{
		LinkedListIterator it = new LinkedListIterator();
		Object result = getElement(index);

		if (index < 0 || index > size() - 1)
		{
			IndexOutOfBoundsException  ex = new IndexOutOfBoundsException();
			throw ex;
		}

		if (index < size() - 1)
		{
			for (int i = 0; i < index + 1; i++)
				it.next();
			it.set(getElement(index + 1));
			it.next();
			index++;
			it.remove();   
		}
		else
		{
			for (int i = 0; i < index + 1; i++)
				it.next();
			it.remove();
		}
		return result;
	}

	// The appendStringAfter method searches the first occurrence of the first 
	// parameter string (str1), and appends (add) the second parameter string (str2)
	// after the first string occurrence. If the linked list does not contain a 
	//string str1, then the second parameter string is added at the end of the linked list.
	public void appendStringAfter(Object str1, Object str2)
	{
		LinkedListIterator it = new LinkedListIterator();
		for (int i = 0; i < size(); i++)
		{
			if (it.next().equals(str1))
			{
				addElement(i + 1, str2);  
				break;
			}
			else if (i == size() - 1)
			{
				addElement(i + 1, str2);
				break;
			}
		}
	}

	// The countOccurrences method counts how many times the object appears in the linked list 
	// and returns it. It returns 0 if the element does not exist in the linked list 
	public int countOccurrences (Object element)
	{
		LinkedListIterator it = new LinkedListIterator();
		int count = 0;

		for (int i = 0; i < size(); i ++)
		{
			if (it.hasNext())
				if (it.next().equals(element))
					count++;   
		}
		return count;
	}

	//nested class to define its iterator
	private class LinkedListIterator implements ListIterator
	{
		private Node position; //current position
		private Node previous; //it is used for remove() method

		// Constructs an iterator that points to the front
		// of the linked list.
		public LinkedListIterator()
		{
			position = null;
			previous = null;
		}

		// Tests if there is an element after the iterator position.
		public boolean hasNext()
		{
			if (position == null) //not traversed yet
			{
				if (first != null)
					return true;
				else
					return false;
			}
			else
			{
				if (position.next != null)
					return true;
				else
					return false;
			}
		}

		// Moves the iterator past the next element, and returns
		// the traversed element's data.
		public Object next()
		{
			if (!hasNext())
			{
				NoSuchElementException ex = new NoSuchElementException();
				throw ex;
			}
			else
			{
				previous = position; // Remember for remove

				if (position == null)
					position = first;
				else
					position = position.next;

				return position.data;
			}
		}

		// Adds an element before the iterator position
		// and moves the iterator past the inserted element.
		public void add(Object element)
		{
			if (position == null) //never traversed yet
			{
				addFirst(element);
				position = first;
			}
			else
			{
				//making a new node to add
				Node newNode = new Node();
				newNode.data = element;
				newNode.next = position.next;
				//change the link to insert the new node
				position.next = newNode;
				//move the position forward to the new node
				position = newNode;
			}
			//this means that we cannot call remove() right after add()
			previous = position;
		}

		// Removes the last traversed element. This method may
		// only be called after a call to the next() method.
		public void remove()
		{
			if (previous == position)  //not after next() is called
			{
				IllegalStateException ex = new IllegalStateException();
				throw ex;
			}
			else
			{
				if (position == first)
				{
					removeFirst();
				}
				else
				{
					previous.next = position.next; //removing
				}
				//stepping back
				//this also means that remove() cannot be called twice in a row.
				position = previous;
			}
		}

		// Sets the last traversed element to a different value.
		public void set(Object element)
		{
			if (position == null)
			{
				NoSuchElementException ex = new NoSuchElementException();
				throw ex;
			}
			else
				position.data = element;
		}

	} //end of LinkedListIterator class
} //end of LinkedList class