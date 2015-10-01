package mission1;

import java.util.EmptyStackException;

public class MyStack<E> implements Stack<E> {

	private Node head;
	
	public MyStack()
	{
		this.head=null;
	}
	
	@Override
	public boolean empty() {
		return head==null;
	}

	@Override
	public E peek() throws EmptyStackException {
		// TODO Auto-generated method stub
		if(empty())
		{
			throw new EmptyStackException();
		}
		return this.head.data;
	}

	@Override
	public E pop() throws EmptyStackException {
		// TODO Auto-generated method stub
		if(empty())
		{
			throw new EmptyStackException();
		}
		Node rm = this.head;
		E data = rm.data;
		this.head=rm.next;
		rm.next=null;
		return data;
	}

	@Override
	public E push(E item) {
		// TODO Auto-generated method stub
		Node add = new Node();
		add.data=item;
		add.next=this.head;
		this.head=add;
		return add.data;
	}
	
	class Node
	{
		public Node next;
		public E data;
		
		public Node()
		{
			this.data=null;
			this.next=null;
		}
	}
}
