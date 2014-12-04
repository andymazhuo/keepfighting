

public class ListNode {

	private ListNode next;

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	private int val;

	public ListNode(int val) {
		this.val = val;
		this.next = null;
	}
	
	public static ListNode revert(ListNode target){
		if(target == null) {
			return target;
		} else if(target.getNext()==null) {
			return target;
		} else {
			ListNode next = target.getNext();
			target.setNext(null);
			ListNode newHead = revert(next);
			next.setNext(target);
			return newHead;
		}
	}
	
	public static ListNode revertIterative(ListNode target){
		if(target==null) return target;
		ListNode curr = target.next;
		target.next = null;
		while(curr != null) {
			ListNode temp = curr.next;
			curr.next = target;
			target = curr;
			curr = temp;
		}
		return target;
	}
	
	public static void print(ListNode temp) {
		while(temp!=null) {
			System.out.println(temp.getVal());
			temp = temp.getNext();
		}
	}
	
	public static void printListBackword(ListNode target) {
		if(target==null) return;
		printListBackword(target.next);
		System.out.println(target.val);
	}
	
	public static ListNode deleteDuplicates(ListNode head) {
        if(head==null) return head;
        ListNode pre = head, curr = pre.next;
        while(curr != null) {
            if(curr.val!=pre.val) {
                pre.next = curr;
                pre = curr;
            }
            curr = curr.next;
        }
        pre.next = null;
        return head;
    }
}
