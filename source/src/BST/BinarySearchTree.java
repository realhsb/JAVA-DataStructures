package BST;

public class BinarySearchTree implements IndexInterface<TreeNode> {
	private TreeNode root;
	public BinarySearchTree() {	//생성자 
		root = null;
	}
	
	//[알고리즘 10-1] : 검색
	public TreeNode search(Comparable searchKey) {
		return searchItem(root, searchKey);
	}
	
	private TreeNode searchItem(TreeNode tNode, Comparable searchKey) {
		if(tNode == null)
			return null;	//검색 실패 
		else if(searchKey.compareTo(tNode.key) == 0)
			retrun searchItem(tNode.left, searchKey);
		else
			return searchItem(tNode.right, searchKey);
	}

	//[알고리즘 10-3] : 삽입 
	@Override
	public void insert(Comparable newKey) {
		root = insertItem(root, newKey);
	}
	
	private TreeNode insertItem(TreeNode tNode, Comparable newItem) {
		if(tNode == null)	//insert after a leaf (or into an empty tree)
			tNode = new TreeNode(newItem, null, null);
		else if(newItem.compareTo(tNode.key) < 0)	//branch left
			tNode.left = insertItem(tNode.left, newItem);
		else
			tNode.right = insertItem(tNode.right, newItem);
		return tNode;
	}
	
	
	//[알고리즘 10-4] : 삭제  
	@Override
	public void delete(Comparable newKey) {
		root = deleteItem(root, searchKey);
	}
	
	private TreeNode deleteItem(TreeNode tNode, Comparable searchKey) {
		if(tNode == null) return null;	//key not found!
		else {
			if(searchKey == tNode.key)	//key found ar tNode
				tNode = deleteNode(tNode);
			else if(searchKey.compareTo(tNode.key)<0)
				tNode.left = deleteItem(tNode.left, searchKey);
			else
				tNode.right = deleteItem(tNode.right, searchKey);
			return tNode;
		}
	}
	
	private TreeNode deleteNode(TreeNode tNode) {
	//3가지 case
		//	1. tNode가 리프 노드
		//	2. tNode가 자식이 하나만 있음
		//	3. tNode가 자식이 둘 있음
		if((tNode.left == null) && (tNode.right == null)) //case 1(자식이 없음)
			return null;
		else if(tNode.left == null)		//case2(오른자식뿐)
			return tNode.right;
		else if(tNode.right == null)	//case2(왼자식뿐)
			return tNode.left;
		else {							//case3(두 자식이 다 있음)
			returnPair rPair = deleteMinItem(tNode.right);
			tNode.key = rPair.key;	tNode.right = rPair.node;
			return tNode;
		}
	}
	
	// 기타 
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	
}
