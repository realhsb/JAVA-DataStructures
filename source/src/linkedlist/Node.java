package linkedlist;

//접근성을 높이기 위해 기존의 Node와는 달리 필드를 public으로 선언하고 getter, setter를 제거 
public class Node<E> {
	public E item;
	public Node<E> next;
	public Node(E newItem) {
		item = this.item;
		next = null;
	}
	
	public Node(E newItem, Node nextNode) {
		item = newItem;
		next = nextNode;
	}
}
