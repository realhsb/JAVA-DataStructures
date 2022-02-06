package linkedlist;

import list.ListInterface;

public class LinkedList<E> implements ListInterface<E> {
	private Node<E> head;
	private int numItems;
	public LinkedList() {
		numItems = 0;
		head = new Node<>(null, null);
	}
	
	//[알고리즘 5-10] 구현 : 연결 리스트에 원소 x 삽입하기 (더미 헤드를 두는 대표 버전) 
	@Override
	public void add(int index, E item) {
		if(index >= 0 && index <= numItems) {
			Node<E> prevNode = getNode(index - 1);
			Node<E> newNode = new Node<>(item, prevNode.next);
			numItems++;
		}
	}

	@Override
	public void append(E item) {
		Node<E> prevNode = head;	// 더미 노드 
		while(prevNode.next != null) {
			prevNode = prevNode.next;
		}
		Node<E> newNode = new Node<>(item, null);
		prevNode.next = newNode;
		numItems++;
	}
	
	//[알고리즘 5-12] : 리스트의 원소 삭제하기 (더미 헤드를 두는 대표 버전) 
	@Override
	public E remove(int index) {
		if(index >= 0 && index <= numItems-1) {
			Node<E> prevNode = getNode(index - 1);
			Node<E> currNode = prevNode.next;
			prevNode.next = currNode.next;
			numItems--;
			return currNode.item;
		}else return null;				//error	
	}
	
	@Override
	public boolean removeItem(E x) {
		Node<E> prevNode, currNode = head;	//더미 노드 	
		for (int i = 0; i < numItems - 1; i++) {
			prevNode = currNode;
			currNode = currNode.next;
			if(((Comparable)(currNode.item)).compareTo(x) == 0) {
				prevNode.next = currNode.next;
				numItems--;
				return true;
			}
		}
		return false;
	}
	
	//[알고리즘 5-13] : 연결 리스트의 k번째 원소 알려주기
	public E get(int index) {
		if(index >= 0 && index <= numItems - 1) {
			return getNode(index).item;
		} else
			return null;		//error
	}
	
	public Node<E> getNode(int index){
		if (index >= -1 && index <= numItems - 1) {
			Node<E> currNode = head;
			for(int i = 0; i <= index; i++) {
				currNode = currNode.next;
			}
			return currNode;
		} else {
			return null;
		}
	}
	
	//[알고리즘 5-14] : 연결 리스트의 k번째 원소를 x로 대체하기
	public void set(int index, E x) {
		if(index >0 && index <= numItems - 1) {
			getNode(index).item = x;
		} else { /* 예외 처리 */ }
	}
	
	//[알고리즘 5-15] : 원소 x가 연결 리스트의 몇 번째 원소인지 알려주기 
	public final int NOT_FOUND = -12345;
	public int indexOf(E x) {
		Node<E> currNode = head;	//더미 노드
		int i;
		for(i = 0; i < numItems; i++) {
			currNode = currNode.next;
			if(((Comparable)(currNode.item)).compareTo(x) == 0) {
				return i;
			}
		}
		return NOT_FOUND;		//not found
	}
	
	//[알고리즘 5-16(1)] : 리스트의 총 원소 수 알려주기 
	@Override
	public int len() {
		return numItems;
	}
	
	//[알고리즘 5-16(2)] : 리스트가 비었는지 알려주기 
	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}
	
	//알고리즘 5-16(3)] : 리스트 깨끗이 청소하기 
	@Override
	public void clear() {
		numItems = 0;
		head = new Node<>(null, null);
	}


}
