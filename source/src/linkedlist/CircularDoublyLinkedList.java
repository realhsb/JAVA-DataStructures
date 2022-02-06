package linkedlist;

import list.ListInterface;

public class CircularDoublyLinkedList<E> implements ListInterface<E> {
	private BidirectionalNode<E> head;	//[코드 5-13] 참조 
	private int numItems;
	
	public CircularDoublyLinkedList() {	//생성자 
		numItems = 0;
		head = new CircularDoublyLinkedList<>(null);	//더미 헤드 
		head.next = head.prev = head;
	}
	
	@Override
	public void add(int index, E x) {
		if(index >= 0 && index <= numItems) {
			BidirectionalNode<E> prevNode = getNode(index - 1);
			BidirectionalNode<E> newNode = new BidirectionalNode<>(prevNode, x, prevNode.next);
			newNode.next.prev = newNode;
			prevNode.next = newNode;
			numItems++;
		}else {/* 예외 처리 */}
	}

	@Override
	public void append(E x) {
		BidirectionalNode<E> prevNode = head.prev;
		BidirectionalNode<E> newNode = new BidirectionalNode<>(prevNode, x, head);
		prevNode.next = newNode;
		head.prev = newNode;
		numItems++;
	}

	@Override
	public E remove(int index) {
		if (index >= 0 && index <= numItems - 1) {
			BidirectionalNode<E> currNode = getNode(index);
			currNode.prev.next = currNode.next;
			currNode.next.prev = currNode.prev;
			numItems--;
			return currNode.item;
		} else
		return null;
	}

	@Override
	public boolean removeItem(E x) {
		BidirectionalNode<E> currNode = head;	//더미 헤드 
		for(int i = 0; i <= numItems - 1; i++) {
			currNode = currNode.next;
			if(((Comparable)(currNode.item)).compareTo(x) == 0) {
				currNode.prev.next = currNode.next;
				currNode.next.prev = currNode.prev;
				numItems--;
				return true;
			}
		}
		return false;
	}

	@Override
	public E get(int index) {
		if(index >= 0 && index <= numItems - 1) {
			return getNode(index).item;
		}else 
		return null;
	}

	@Override
	public void set(int index, E x) {
		if(index >= 0 && index <= numItems - 1) {
			getNode(index).item = x;
		}else {/* 예외 처리*/ }
	}
	
	public BidirectionalNode<E> getNode(int index){
		if(index >= -1 && index <= numItems - 1) {
			BidirectionalNode<E> currNode = head;
			if(index < numItems/2)
				for(int i = 0; i <= index; i++)
					currNode = currNode.next;
			else
				for(int i = numItems - 1; i >= index; i--)
					currNode = currNode.prev;
			return currNode;
		} else
			return null; //error
	}
	
	public final int NOT_FOUND = -12345;
	@Override
	public int indexOf(E x) {
		BidirectionalNode<E> currNode = head;
		for(int i = 0; i <= numItems - 1; i++) {
			currNode = currNode.next;
		}
		return 0;
	}

	@Override
	public int len() {
		return numItems;
	}

	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	@Override
	public void clear() {
		numItems = 0;
		head.next = head.prev = head;
	}

}
