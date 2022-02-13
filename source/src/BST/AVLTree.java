package BST;

public class AVLTree implements IndexInterface<AVLNode> {
	private AVLNode root;
	static final AVLNode NIL = new AVLNode(null, null, null, 0);
	
	public AVLTree() {
		root = NIL;
	}
	
	//[알고리즘 10-1] : 검색 
	public AVLNode search(Comparable x) {
		return searchItem(root, x);
	}
	
	private AVLNode searchItem(AVLNode tNode, Comparable x) {
		if(tNode == NIL) return NIL;
		else if(x.compareTo(tNode.item)==0) return tNode;
		else if(x.compareTo(tNode.item) < 0)
			return searchItem(tNode.left, x);
		else
			return searchItem(tNode.right, x);
	}
	
	//[알고리즘 10-3] : 삽입
	public void insert(Comparable x) {
		root = insertItem(root, x);
	}
	
	private AVLNode insertItem(AVLNode tNode, Comparable x) {
		if(tNode == NIL) {	//insert after a leaf (or into an empty tree)
			tNode = new AVLNode(x);
		}else if(x.compareTo(tNode.item) < 0) {	//branch left
			tNode.left = insertItem(tNode.left, x);
			tNode.height = 1 + Math.max(tNode.right.height, tNode.left.height);
			int type = needBalance(tNode);
			if(type != NO_NEED)
				tNode = balanceAVL(tNode, type);
		} else {	//branch right
			tNode.right = insertItem(tNode.right, x);
			tNode.height = 1 + Math.max(tNode.right.height, tNode.left.height);
			int type = needBalance(tNode);
			if(type != NO_NEED)
				tNode = balanceAVL(tNode, type);
		}
		return tNode;
	}
	
	//[알고리즘 10-3] : 삭제
	public void delete(Comparable x) {
		root = deleteItem(root, x);
	}
	
	private AVLNode deleteItem(AVLNode tNode, Comparable x) {
		if(tNode == NIL) return NIL;
		else {
			if (x.compareTo(tNode.item) == 0) {
				tNode = deleteNode(tNode);
			}else if(x.compareTo(tNode.item) < 0) {
				tNode.left = deleteItem(tNode.left, x);
				tNode.height = 1 + Math.max(tNode.right.height, tNode.left.height);
				int type = needBalance(tNode);
				if(type != NO_NEED)
					tNode = balanceAVL(tNode, type);
			}else {
				tNode.right = deleteItem(tNode.right, x);
				tNode.height = 1 + Math.max(tNode.right.height, tNode.left.height);
				int type = needBalance(tNode);
				if(type != NO_NEED)
					tNode = balanceAVL(tNode, type);
			}
			return tNode;
		}
	}
	
	private AVLNode deleteNode(AVLNode tNode) {
		//3가지 case
		//	1. tNode가 리프 노드
		//	2. tNode가 자식이 하나만 있음
		//	3. tNode가 자식이 둘 있음
		if((tNode.left == NIL) && (tNode.right == NIL))	//case1 (자식이 없음)
			return NIL;
		else if(tNode.left == NIL)	//case2 (오른자식뿐)
			return tNode.right;
		else if(tNode.right == NIL)	//case2 (왼자식뿐)
			return tNode.left;
		else {						//case3 (두 자식이 다 있음)
			returnPair rPair = deleteMinItem(tNode.right);
			tNode.item = rPair.item; tNode.right = rPair.node;
			tNode.height = 1 + Math.max(tNode.right.height, tNode.left.height);
			int type = needBalance(tNode);
			if(type != NO_NEED)
				tNode = balanceAVL(tNode, type);
			return tNode;
		}
	}
	
	private returnPair deleteMinItem(AVLNode tNode) {
		int type;
		if(tNode.left == NIL) {
			return new returnPair(tNode.item, tNode.right);
		}else {
			returnPair rPair = deleteMinItem(tNode.left);
			tNode.left = rPair.node;
			tNode.height = 1 + Math.max(tNode.right.height, tNode.left.height);
			type = needBalance(tNode);
			if(type != NO_NEED)
				tNode = balanceAVL(tNode, type);
			rPair.node = tNode;
			return rPair;
		}
	}
	
	private class returnPair {
		private Comparable item;
		private AVLNode node;
		private returnPair(Comparable it, AVLNode nd) {
			item = it;
			node = nd;
		}
	}
	
	// 균형 잡기
	private AVLNode balanceAVL(AVLNode tNode, int type) {
		AVLNode returnNode = NIL;
		switch(type) {
		case LL :
			returnNode = rightRotate(tNode);
			break;
		case LR :
			tNode.left = leftRotate(tNode.left);
			returnNode = rightRotate(tNode);
			break;
		case RL :
			tNode.right = rightRotate(tNode.right);
			returnNode = leftRotate(tNode);
			break;
		default :
			System.out.println("Impossible type! Should be on of LL, LR, RR, RL");
			break;
		}
		return returnNode;
	}
	
	//[알고리즘 11-1] : 좌회전 
	private AVLNode leftRotate(AVLNode t) {
		AVLNode RChild = t.right;
		if(RChild == NIL)
			System.out.println(t.item + " 's RChild shouldn't be NIL!");
		AVLNode RLChild = RChild.left;
		RChild.left = t;
		t.right = RLChild;
		t.height = 1 + Math.max(t.left.height, t.right.height);
		RChild.height = 1 + Math.max(RChild.left.height, RChild.right.height);
		
		return RChild;
	}
	
	//[알고리즘 11-1] : 우회전 
	private AVLNode rightRotate(AVLNode t) {
		AVLNode LChild = t.right;
		if(LChild == NIL)
			System.out.println(t.item + " 's LChild shouldn't be NIL!");
		AVLNode LRChild = LChild.left;
		LChild.left = t;
		t.right = LRChild;
		t.height = 1 + Math.max(t.left.height, t.right.height);
		LChild.height = 1 + Math.max(LChild.left.height, LChild.right.height);
		
		return LChild;
	}
	
	private final int LL = 1, LR = 2, RR = 3, RL = 4, NO_NEED = 0, ILLEGAL = -1;
	private int needBalance(AVLNode t) {
		int type = ILLEGAL;
		if(t.left.height + 2 <= t.right.height) {				//R 유형
			if((t.right.left.height) < (t.right.right.height))	//RR
				type = RR;
			else												//RL
				type = RL;
		}else if((t.left.height) >= t.right.height + 1) {		//L
			if((t.left.left.height) >= t.left.right.height)		//LL
				type = LL;
			else
				type = LR;
		}else {
			type = NO_NEED;
		}
		return type;
	}
	
	// 기타
	public boolean isEmpty() {
		return root == NIL;
	}
	
	public void clear() {
		root = NIL;
	}
}




















