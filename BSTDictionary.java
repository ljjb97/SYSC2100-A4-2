import java.util.concurrent.LinkedBlockingQueue;

public class BSTDictionary<E, K extends Sortable> implements Dictionary<E, K> {
	
	private BSTNode<E, K> head;
	
	BSTDictionary(){
		head = null;
	};

	public Object search(Sortable key) {
		BSTNode<E, K> curr = head;
		boolean ever = true;
		for(;ever;) {
			if(curr == null) {
				break;
			}
			else if(key.compareTo(curr.getKey()) == 0) {
				return curr.getElement();
			}
			else if(key.compareTo(curr.getKey()) < 0) {
				curr = curr.getLeft();
			}
			else if(key.compareTo(curr.getKey()) > 0) {
				curr = curr.getRight();
			}
		} return null;
	}

	public void insert(Sortable key, Object element) {
		BSTNode<E, K> node = new BSTNode(key, element, null, null);
		if(head == null) {
			 head = node;
		} else  {
			BSTNode<E, K> curr = head;
			BSTNode<E, K> prev = head;
			for(boolean ever = true; ever;) {
				if(curr == null) {
					break;
				} else if(key.compareTo(curr.getKey()) <= 0) {
					prev = curr;
					curr = curr.getLeft();
				} else if(key.compareTo(curr.getKey()) > 0) {
					prev = curr;
					curr = curr.getRight();
				}
			}
			if(key.compareTo(prev.getKey()) <= 0) {
				prev.setLeft(node);
			}  else if(key.compareTo(prev.getKey()) > 0) {
				prev.setRight(node);
			}
		}
	}

	public void delete(Sortable key) {
		BSTNode<E, K> curr = head;
		BSTNode<E, K> prev = head;
		if(head == null) {
		//Do Nothing	
		}
		else if(key.compareTo(head.getKey()) == 0) {
			curr = curr.getLeft();
			BSTNode<E, K> prev2 = head;
			for(boolean ever = true; ever;) {
				if(curr == null) {
					if(!prev.equals(head)) {
						prev2.setRight(null);
					}
					prev.setLeft(head.getLeft());
					prev.setRight(head.getRight());
					head = prev;
					break;
				} else {
					prev2 = prev; 
					prev = curr;
					curr = curr.getRight();
				}
			}
		}
		else {
			for(boolean ever = true; ever;) {
				if(curr == null) {
					return;
				} else if(key.compareTo(curr.getKey()) == 0) {
					break;
				} else if(key.compareTo(curr.getKey()) < 0) {
					prev = curr;
					curr = curr.getLeft();	
				} else if(key.compareTo(curr.getKey()) > 0) {
					prev = curr;
					curr = curr.getRight();
				}
			}
			if(key.compareTo(prev.getKey()) < 0) {
				prev.setLeft(curr.getLeft());
			}  else if(key.compareTo(prev.getKey()) > 0) {
				prev.setRight(curr.getRight());
			}
		}	
	}

	public void printTree() {
		printer(head);
		System.out.print("\n");
	}

	public int depth() {
		if(head == null) {
			return 0;
		}
		int currDepth = 1;
		LinkedBlockingQueue<BSTNode<E, K>> list2 = new LinkedBlockingQueue<BSTNode<E, K>>();
		LinkedBlockingQueue<BSTNode<E, K>> list1 = new LinkedBlockingQueue<BSTNode<E, K>>();
		list2.add(head);
		BSTNode<E, K> node;
		while(!list2.isEmpty()) {
			list1 = list2;
			list2 = new LinkedBlockingQueue<BSTNode<E, K>>();
			while(!list1.isEmpty()) {
				node = list1.poll();
				if(node.getLeft() != null) {
					list2.add(node.getLeft());
				} if(node.getRight() != null) {
					list2.add(node.getRight());
				}
			}
			currDepth++;
		}
		return currDepth;
	}
	
	public void printer(BSTNode<E, K> node) {
		if(node == null) {
			return;
		} if(node.getLeft() != null) {
			printer(node.getLeft());
		} System.out.print(node.getElement() + " ");
		if(node.getRight() != null) {
			printer(node.getRight());
		}
	}
}
