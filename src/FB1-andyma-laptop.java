import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;


public class FB1 {

	public FB1() {
	}

	public static void printLogInfo(Pair[] pairs) {
		Map<Integer, Integer> m = new TreeMap<>(); 
		for(Pair p : pairs) {
			updateMap(m, p.start, true);
			updateMap(m, p.end, false);
		}
		
		Iterator<Integer> it = m.keySet().iterator();
		int start = it.next();
		int startValue = m.get(start);
		
		while(it.hasNext()) {
			int key = it.next();
			if(m.get(key)!=0) {
				System.out.println(start + "--"+ key + ":" + startValue);
				startValue += m.get(key);
				start = key;
			}
		}
		System.out.println(start + "--infinite:" + startValue);
	}
	
	private static void updateMap(Map<Integer, Integer> m, int time, boolean login) {
		int temp = 0;
		if(m.containsKey(time)) {
			temp = m.get(time);
		}

		if(login) {
			m.put(time, temp+1);	
		} else {
			m.put(time, temp-1);	
		}
	}
	
	public static String countAndSay(int n) {
        if(n==1) return "1";
        String pre = countAndSay(n-1);
        
        int count=1;
        char curr = pre.charAt(0);
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<pre.length(); i++) {
            if(pre.charAt(i)==curr) {
                count++;
            } else {
                sb.append(count).append(curr);
                count=1;
                curr = pre.charAt(i);
            }
        }
        sb.append(count).append(curr);
        return sb.toString();
    }

	
	/**
	 * Using Set as value can avoid duplicates, if not, a linked list will be good enough.
	 * @param words
	 * @return
	 */
	public static List<Set<String>> getAnagrams(List<String> words){
		Map<String, Set<String>> map = new HashMap<>();
		
		for(String w : words) {
			char[] chars = w.toCharArray();
	        Arrays.sort(chars);
	        String sorted = new String(chars);
	        if(map.containsKey(sorted)) {
	        	map.get(sorted).add(w);
	        } else {
	        	Set<String> temp = new HashSet<>();
	        	temp.add(w);
	        	map.put(sorted, temp);
	        }
		}
		List<Set<String>> ans = new ArrayList<>();
		for(Set<String> list :map.values()) {
			ans.add(list);
		}
		return ans;
	}

	public static class BTPostorderIterator implements Iterator<TreeNode> {

		Stack<TreeNode> stack;
		Set<TreeNode> processed;
		
		public BTPostorderIterator(TreeNode node) {
			stack = new Stack<>();
			processed = new HashSet<>();
			pushNodeAndLefts(node);
		}
		
		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public TreeNode next() {
			TreeNode top = stack.peek();
			if(!processed.contains(top)) {
				processed.add(top);
				pushNodeAndLefts(top.right);
			} else {
				processed.remove(top);
			}
			return stack.pop();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
		}
		
		private void pushNodeAndLefts(TreeNode t) {
			while(t!=null) {
				stack.push(t);
				t = t.left;
			}
		}
	}

	public static class BTInorderIterator implements Iterator<TreeNode> {

		Stack<TreeNode> stack;

		public BTInorderIterator(TreeNode node) {
			stack = new Stack<>();
			pushNodeAndLefts(node);
		}
		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public TreeNode next() {
			if(hasNext()) {
				TreeNode temp = stack.pop();
				pushNodeAndLefts(temp.right);
				return temp;
			} else {
				return null;
			}
		}

		private void pushNodeAndLefts(TreeNode t) {
			while(t!=null) {
				stack.push(t);
				t = t.left;
			}
		}
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}
	
	static class PointComparator implements Comparator<Point> {
	    @Override
	    public int compare(Point b, Point a) {
	    	int asum = a.x*a.x + a.y*a.y;
	    	int bsum = b.x*b.x + b.y*b.y;
	    	if(asum>bsum) {
	    		return 1;
	    	} else if(asum<bsum) {
	    		return -1;
	    	} else {
	    		return 0;
	    	}
	    }
	}

	public static PriorityQueue<Point> findKNearest(int k, List<Point> points) {
		PointComparator com = new PointComparator();
		PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(k, com);
		int size = 0;
		for(Point p : points) {
			if(size<k) {
				maxHeap.add(p);
				size++;
			} else {
				if(com.compare(p, maxHeap.peek())>0) {
					maxHeap.poll();
					maxHeap.add(p);
				}
			}
		}
		return maxHeap;
	}
	
	private boolean exist(char[][] board, int r, int c, String word, int index) {
        if(index==word.length()) {
            return true;
        } else if(r<0 || r>=board.length || c<0 || c>=board[0].length) {
            return false;  
        } else {
            if(board[r][c] == word.charAt(index)) {
                if(exist(board, r-1 , c, word, index+1)) return true;
                if(exist(board, r+1 , c, word, index+1)) return true;
                if(exist(board, r, c-1, word, index+1))  return true;
                if(exist(board, r, c+1, word, index+1))  return true;
            }
            return false;
        }
    }
	
	public static boolean  isOneEditAway(String s, String t, int count) {
		if(Math.abs(s.length()-t.length())>1) {
			return false;
		} else if(count==1) {
			return s.equals(t);
		} else if(s.length()==0) {
			return (t.length()==1 && count==0) || (count==1 && t.length()==0);
		} else if(t.length()==0) {
			return (s.length()==1 && count==0) || (count==1 && s.length()==0);
		} else {
			if(s.charAt(0) == t.charAt(0)) {
				return isOneEditAway(s.substring(1), t.substring(1), 0);
			} else {
				if(isOneEditAway(s.substring(1), t.substring(1), 1)) return true; //replace
				if(isOneEditAway(s.substring(1), t, 1)) return true; //insert
				if(isOneEditAway(s, t.substring(1), 1)) return true; //remove
				return false;
			}
		}
	}
	
	public static String multiply(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carrier = 0;
        int curr = 0;
        
        while(curr<=(num1.length() + num2.length()) || carrier != 0) {
            int temp = carrier;
            for(int i=0; i<=curr; i++) {
                int num1Index = num1.length()-1-i;
                if (num1Index<0) continue; 
                int num2Index = num2.length()-1-(curr-i);
                if (num2Index<0) continue; 
                temp += (num1.charAt(num1Index) - '0') * (num2.charAt(num2Index) - '0');
            }
            carrier = temp/10;
            sb.insert(0, temp%10);
            curr++;
        }
        int start = 0;
        while(start<sb.length() && sb.charAt(start)=='0') {
            start++;
        }
        if(start == sb.length()) return "0";

        return sb.toString().substring(start);
    }
	
	public static ListNode reverseAlternateKelements(ListNode head, int k) {
		return null;
	}
	
	public static ListNode reverseLinkedList(ListNode root) {
		if(root==null || root.getNext()==null) return root;
		ListNode next = root.getNext();
		root.setNext(null);
		ListNode temp = reverseLinkedList(next);
		next.setNext(root);
		return temp;
	}
	
	public static ListNode reverseLinkedListByK(ListNode head, int k) {
		if(k==1 || head==null || head.getNext()==null) return head;
		ListNode dummy = new ListNode(-1);
		dummy.setNext(head);
		ListNode start = dummy;
		ListNode curr = head.getNext();
		
		while(curr!=null){
			int i=1;
			while(curr!=null && i<k) {
				i++;
				ListNode temp = curr.getNext();
				head.setNext(temp);
				curr.setNext(start.getNext());
				start.setNext(curr);
				curr = temp;
			}
			
			while(curr!=null && i>=1) {
				i--;
				start = curr;
				curr = curr.getNext();
			}
			if(curr!=null) {
				head = curr;
				curr = curr.getNext();
			}
		}
		return dummy.getNext();
	}
	
	public static int getMinEdit(String m, String n) {
		String l = "", s = "";
		if(m.length()>n.length()) {
			l = m;
			s = n;
		} else {
			l = n;
			s = m;
		}
		return getMinEdit(l, 0, s, 0);
	}
	
	
	
	private static int getMinEdit(String l, int l_i, String s, int s_i) {
		if(l_i==l.length()) return s.length()-s_i;
		if(s_i==s.length()) return l.length()-l_i;
		if(l.charAt(l_i) == s.charAt(s_i)) {
			return getMinEdit(l, l_i+1, s, s_i+1);
		} else {
			int a = 1 + getMinEdit(l, l_i+1, s, s_i);
			int b = 1 + getMinEdit(l, l_i+1, s, s_i+1);
			return Math.min(a, b);
		}
	}

	public static int robHouse(int[] houses) {
		int[] best = new int[houses.length];
		best[0] = houses[0];
		if(houses[0]>houses[1]) {
			best[1] = houses[0];
		} else {
			best[1] = houses[1];
		}
		
		for(int i=2; i<houses.length; i++) {
			best[i] = Math.max(houses[i]+best[i-2], best[i-1]);
		}
		return best[best.length-1];
	}
	
	public static boolean isMatch(String s, String p) {
        return isMatch(s, 0, p, 0);
    }
    
    private static boolean isMatch(String target, int targetIndex, String pattern, int patternIndex) {
        if(patternIndex == pattern.length()) return targetIndex == target.length();
        
        if(patternIndex+1<pattern.length() && pattern.charAt(patternIndex+1)=='*') {
        	if(isMatch(target, targetIndex, pattern, patternIndex+2)) return true;
            while(isCharMatch(target, targetIndex, pattern, patternIndex)) {
                if(isMatch(target, targetIndex+1, pattern, patternIndex+2)) {
                    return true;
                }
                targetIndex++;
            }
            return false;
            //return isMatch(target, targetIndex, pattern, patternIndex+2);
        } else {
            if(isCharMatch(target, targetIndex, pattern, patternIndex)) {
                return isMatch(target, targetIndex+1, pattern, patternIndex+1);
            } else {
                return false;
            }
        }
    }
    
    private static boolean isCharMatch(String target, int targetIndex, String pattern, int patternIndex) {
        if(targetIndex == target.length() || patternIndex == pattern.length()) return false;
        return (target.charAt(targetIndex)==pattern.charAt(patternIndex) || '.'==pattern.charAt(patternIndex));
    }
    
    public static List<List<Integer>> getResults(int target, int[] canadiates) {
    	List<List<Integer>> ans = new ArrayList<>();
    	getResults(new LinkedList<Integer>(), ans, target, 0, canadiates);
    	return ans;
    }
	
	private static void getResults(LinkedList<Integer> path, List<List<Integer>> ans, int target, int start, int[] canadiates) {
		if(target<0) {
			return;
		} if(target==0) {
			ans.add(new ArrayList<Integer>(path));
		} else {
			for(int i =start; i<canadiates.length; i++) {
				path.add(canadiates[i]);
				getResults(path, ans, target-canadiates[i], i, canadiates);
				path.pollLast();
			}
		}
	}
	
	public static int findLongestIncreasingSub(int[] input) {
		int maxLength=0;

		int length=1;
		for(int i=1; i<input.length; i++) {
			if(input[i] >= input[i-1]) {
				length++;
			} else {
				if(length>maxLength) {
					maxLength = length;
					length =1;
				}
			}
		}
		if(length>maxLength) maxLength = length;
		return maxLength;
	}

	public static int binaryToGray(int n) {
		return (n>>1) ^ n;
	}

	public static void printTreeByCol(TreeNode root) {
		TreeMap<Integer, List<TreeNode>> map = new TreeMap<>();
		populateMap(map, root, 0);
		int pre = map.firstKey();
		for(int key : map.keySet()) {
			if(key==pre) {
				System.out.print(Arrays.toString(map.get(key).toArray()));
			} else {
				System.out.println();
				System.out.print(Arrays.toString(map.get(key).toArray()));
			}
			key = pre;
		}
	}

	private static void populateMap(TreeMap<Integer, List<TreeNode>> map, TreeNode root, int id) {
		if(root==null) return;
		if(map.containsKey(id)) {
			map.get(id).add(root);
		} else {
			List<TreeNode> temp = new LinkedList<>();
			temp.add(root);
			map.put(id, temp);
		}
		populateMap(map, root.left, id-1);
		populateMap(map, root.right, id+1);
	}
	
	public static ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if(matrix.length==0) return ans;
        int r = matrix.length, c = matrix[0].length;
        int loop = Math.min(r+1,c+1)/2;
        for(int l=0; l<loop; l++) {
            printLoop(ans, matrix, l);
        }
        return ans;
    }
    
    private static void printLoop(ArrayList<Integer> ans, int[][] matrix, int loop) {
        int r = matrix.length-1, c = matrix[0].length-1;
        int topRow = loop, bottomRow=r-loop, leftCol = loop, rightCol = c-loop;
        
        for(int temp=leftCol; temp<=rightCol; temp++)  ans.add(matrix[topRow][temp]);
        for(int temp=topRow+1; temp<=bottomRow; temp++)  ans.add(matrix[temp][rightCol]);
        if(bottomRow!=topRow) for(int temp=rightCol-1; temp>=leftCol; temp--)  ans.add(matrix[bottomRow][temp]);
        if(rightCol !=leftCol) for(int temp=bottomRow-1; temp>topRow; temp--)  ans.add(matrix[temp][leftCol]);
    }

	public static void main(String[] args) {
		/**
		Pair p1 = new Pair(0,1);
		Pair p2 = new Pair(0,2);
		Pair p3 = new Pair(1,3);
		Pair[] pairs = {p1,p2,p3};
		
		printLogInfo(pairs);

		List<String> words = new ArrayList<>();
		words.add("star");
		words.add("rats");
		words.add("car");
		words.add("arc");
		words.add("arts");
		words.add("arts");
		getAnagrams(words);
		
		TreeNode root = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		root.left = n1;
		root.right = n2;

		BTInorderIterator it = new BTInorderIterator(root);
		while(it.hasNext()) {
			System.out.println(it.next().val);
		}

		int k = 3;
		List<Point> points = new ArrayList<>();
		points.add(new Point(1,2));
		points.add(new Point(1,2));
		points.add(new Point(3,5));
		points.add(new Point(7,8));
		points.add(new Point(-1,2));
		Object[] ob = (findKNearest(k, points).toArray());
		for(Object p : ob) {
			System.out.println(((Point)p).x + " " + ((Point)p).y);
		}

		System.out.println(multiply("12345678987654321", "123456789123456789"));
		System.out.println("1524157887364730998475842112635269");
		
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		ListNode l5 = new ListNode(5);
		ListNode l6 = new ListNode(6);
		ListNode l7 = new ListNode(7);
		ListNode l8 = new ListNode(8);
		ListNode l9 = new ListNode(9);
		
		l1.setNext(l2);
		l2.setNext(l3);
		l3.setNext(l4);
		l4.setNext(l5);
		l5.setNext(l6);
		l6.setNext(l7);
		l7.setNext(l8);
		l8.setNext(l9);
		
		ListNode ans = reverseLinkedListByK(l1,4);
		while(ans!=null) {
			System.out.println(ans.getVal());
			ans = ans.getNext();
		}
		
		System.out.println(getMinEdit("abc", "eaee"));
		
		System.out.println(robHouse(new int[]{1,5,3}));
		
		System.out.println(isMatch("a", "a*a"));
		
		List<List<Integer>> ans = getResults(12, new int[]{2,3,6});
		for(List<Integer> l : ans) {
			System.out.println(Arrays.toString(l.toArray()));
		}
		
		
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		TreeNode n8 = new TreeNode(8);
		TreeNode n9 = new TreeNode(9);
		n9.left = n5;
		n9.right = n8;
		n5.left = n3;
		n5.right = n4;
		n8.left = n6;
		n8.right = n7;
		n3.left = n1;
		n3.right = n2;

		BTPostorderIterator it = new BTPostorderIterator(n9);
		while(it.hasNext()) {
			System.out.println(it.next().val);
		}
		
		System.out.println(findLongestIncreasingSub(new int[]{
				
				}));
		
		for(int i=0; i<4; i++) {
			System.out.println(binaryToGray(i));
		}
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);
		TreeNode n6 = new TreeNode(6);
		TreeNode n7 = new TreeNode(7);
		TreeNode n8 = new TreeNode(8);
		TreeNode n9 = new TreeNode(9);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		printTreeByCol(n1);
		**/
		int[][] matrix  = new int[2][1];
		matrix[0][0] = 3;
		matrix[1][0] = 2;
		spiralOrder(matrix);
	}
}

