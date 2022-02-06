package linkedlist;

public class OriginalNode<E> {
	private E item;
	private Node<E> next;
	public OriginalNode(E newItem) {
		item = this.item;
		next = null;
	}
	
	public OriginalNode(E newItem, Node nextNode) {
		item = newItem;
		next = nextNode;
	}
	
	public void setItem(E newItem) {
		item = newItem;
	}
	
	public E getItem() {
		return item;
	}
	
	public void setNext(Node nextNode) {
		next = nextNode;
	}
	
	public Node getNext() {
		return next;
	}
}
