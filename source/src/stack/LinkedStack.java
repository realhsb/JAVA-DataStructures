package stack;

import linkedlist.Node;

public class LinkedStack<E> implements StackInterface<E> {
	private Node<E> topNode;
	private final E ERROR = null; // 임의의 에러 값
	public LinkedStack() {
		topNode = null;
	}
	
	//[알고리즘 6-7] : 스택에 원소 x 삽입하기 
	@Override
	public void push(E newItem) {
		topNode = new Node<>(newItem, topNode);
	}
	
	//[알고리즘 6-8] : 스택에서 원소 삭제하기 
	@Override
	public E pop() {
		if(isEmpty()) return ERROR;
		else {
			Node<E> temp = topNode;
			topNode = topNode.next;
			return temp.item;
		}
	}
	
	//[알고리즘 6-9] : 스택 탑 원소 알려주기 
	@Override
	public E top() {
		if(isEmpty()) return ERROR;
		return null;
	}
	
	//[알고리즘 6-10] : 스택이 비었는지 확인하기 
	@Override
	public boolean isEmpty() {
		return (topNode == null);
	}
	
	//[알고리즘 6-11] : 스택 비우기 
	@Override
	public void popAll() {
		topNode = null;
	}

}
