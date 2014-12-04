
public class DoubleLinkedListNode {

	private DoubleLinkedListNode next;
	public DoubleLinkedListNode getNext() {
		return next;
	}

	public void setNext(DoubleLinkedListNode next) {
		this.next = next;
		if(next !=null) next.pre = this;
	}

	private DoubleLinkedListNode pre;
	public DoubleLinkedListNode getPre() {
		return pre;
	}

	public void setPre(DoubleLinkedListNode pre) {
		this.pre = pre;
		if(pre!=null) pre.next = this;
	}

	private int value;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public DoubleLinkedListNode(int v) {
		this.value = v;
	}
	
	
}
