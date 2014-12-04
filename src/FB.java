import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class FB {

	public FB() {
	}

	public static void printLogInfo(Pair[] pairs) {
		Map<Integer, Integer> m = new TreeMap<>();
		for (Pair p : pairs) {
			updateMap(m, p.start, true);
			updateMap(m, p.end, false);
		}

		Iterator<Integer> it = m.keySet().iterator();
		int start = it.next();
		int startValue = m.get(start);

		while (it.hasNext()) {
			int end = it.next();
			if (m.get(end) != 0) {
				System.out.println(start + "--" + end + ":" + startValue);
				startValue += m.get(end);
				start = end;
			}
		}
		System.out.println(start + "--infinite:" + startValue);
	}

	private static void updateMap(Map<Integer, Integer> m, int time,
			boolean login) {
		int temp = 0;
		if (m.containsKey(time)) {
			temp = m.get(time);
		}

		if (login) {
			m.put(time, temp + 1);
		} else {
			m.put(time, temp - 1);
		}
	}

	public static String countAndSay(int n) {
		if (n == 1)
			return "1";
		String pre = countAndSay(n - 1);

		int count = 1;
		char curr = pre.charAt(0);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < pre.length(); i++) {
			if (pre.charAt(i) == curr) {
				count++;
			} else {
				sb.append(count).append(curr);
				count = 1;
				curr = pre.charAt(i);
			}
		}
		sb.append(count).append(curr);
		return sb.toString();
	}

	/**
	 * Using Set as value can avoid duplicates, if not, a linked list will be
	 * good enough.
	 * 
	 * @param words
	 * @return
	 */
	public static List<Set<String>> getAnagrams(List<String> words) {
		Map<String, Set<String>> map = new HashMap<>();

		for (String w : words) {
			char[] chars = w.toCharArray();
			Arrays.sort(chars);
			String sorted = new String(chars);
			if (map.containsKey(sorted)) {
				map.get(sorted).add(w);
			} else {
				Set<String> temp = new HashSet<>();
				temp.add(w);
				map.put(sorted, temp);
			}
		}
		List<Set<String>> ans = new ArrayList<>();
		for (Set<String> list : map.values()) {
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
			if (!processed.contains(top)) {
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
			while (t != null) {
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
			if (hasNext()) {
				TreeNode temp = stack.pop();
				pushNodeAndLefts(temp.right);
				return temp;
			} else {
				return null;
			}
		}

		private void pushNodeAndLefts(TreeNode t) {
			while (t != null) {
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
			int asum = a.x * a.x + a.y * a.y;
			int bsum = b.x * b.x + b.y * b.y;
			if (asum > bsum) {
				return 1;
			} else if (asum < bsum) {
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
		for (Point p : points) {
			if (size < k) {
				maxHeap.add(p);
				size++;
			} else {
				if (com.compare(p, maxHeap.peek()) > 0) {
					maxHeap.poll();
					maxHeap.add(p);
				}
			}
		}
		return maxHeap;
	}

	private boolean exist(char[][] board, int r, int c, String word, int index) {
		if (index == word.length()) {
			return true;
		} else if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
			return false;
		} else {
			if (board[r][c] == word.charAt(index)) {
				if (exist(board, r - 1, c, word, index + 1))
					return true;
				if (exist(board, r + 1, c, word, index + 1))
					return true;
				if (exist(board, r, c - 1, word, index + 1))
					return true;
				if (exist(board, r, c + 1, word, index + 1))
					return true;
			}
			return false;
		}
	}

	public static boolean isOneEditAway(String s, String t, int count) {
		if (Math.abs(s.length() - t.length()) > 1) {
			return false;
		} else if (count == 1) {
			return s.equals(t);
		} else if (s.length() == 0) {
			return (t.length() == 1 && count == 0)
					|| (count == 1 && t.length() == 0);
		} else if (t.length() == 0) {
			return (s.length() == 1 && count == 0)
					|| (count == 1 && s.length() == 0);
		} else {
			if (s.charAt(0) == t.charAt(0)) {
				return isOneEditAway(s.substring(1), t.substring(1), 0);
			} else {
				if (isOneEditAway(s.substring(1), t.substring(1), 1))
					return true; // replace
				if (isOneEditAway(s.substring(1), t, 1))
					return true; // insert
				if (isOneEditAway(s, t.substring(1), 1))
					return true; // remove
				return false;
			}
		}
	}

	public static String multiply(String num1, String num2) {
		StringBuilder sb = new StringBuilder();
		int carrier = 0;
		int curr = 0;

		while (curr <= (num1.length() + num2.length()) || carrier != 0) {
			int temp = carrier;
			for (int i = 0; i <= curr; i++) {
				int num1Index = num1.length() - 1 - i;
				if (num1Index < 0)
					continue;
				int num2Index = num2.length() - 1 - (curr - i);
				if (num2Index < 0)
					continue;
				temp += (num1.charAt(num1Index) - '0')
						* (num2.charAt(num2Index) - '0');
			}
			carrier = temp / 10;
			sb.insert(0, temp % 10);
			curr++;
		}
		int start = 0;
		while (start < sb.length() && sb.charAt(start) == '0') {
			start++;
		}
		if (start == sb.length())
			return "0";

		return sb.toString().substring(start);
	}

	public static ListNode reverseAlternateKelements(ListNode head, int k) {
		return null;
	}

	public static ListNode reverseLinkedList(ListNode root) {
		if (root == null || root.getNext() == null)
			return root;
		ListNode next = root.getNext();
		root.setNext(null);
		ListNode temp = reverseLinkedList(next);
		next.setNext(root);
		return temp;
	}

	public static ListNode reverseLinkedListByK(ListNode head, int k) {
		if (k == 1 || head == null || head.getNext() == null)
			return head;
		ListNode dummy = new ListNode(-1);
		dummy.setNext(head);
		ListNode pre = dummy;
		ListNode curr = head.getNext();

		while (curr != null) {
			int i = 1;
			while (curr != null && i < k) {
				i++;
				ListNode temp = curr.getNext();
				head.setNext(temp);
				curr.setNext(pre.getNext());
				pre.setNext(curr);
				curr = temp;
			}

			while (curr != null && i >= 1) {
				i--;
				pre = curr;
				curr = curr.getNext();
			}

			if (curr != null) {
				head = curr;
				curr = curr.getNext();
			}
		}
		return dummy.getNext();
	}

	public static int getMinEdit(String m, String n) {
		String l = "", s = "";
		if (m.length() > n.length()) {
			l = m;
			s = n;
		} else {
			l = n;
			s = m;
		}
		return getMinEdit(l, 0, s, 0);
	}

	private static int getMinEdit(String l, int l_i, String s, int s_i) {
		if (l_i == l.length())
			return s.length() - s_i;
		if (s_i == s.length())
			return l.length() - l_i;
		if (l.charAt(l_i) == s.charAt(s_i)) {
			return getMinEdit(l, l_i + 1, s, s_i + 1);
		} else {
			int a = 1 + getMinEdit(l, l_i + 1, s, s_i);
			int b = 1 + getMinEdit(l, l_i + 1, s, s_i + 1);
			return Math.min(a, b);
		}
	}

	public static int robHouse(int[] houses) {
		int[] best = new int[houses.length];
		best[0] = houses[0];
		if (houses[0] > houses[1]) {
			best[1] = houses[0];
		} else {
			best[1] = houses[1];
		}

		for (int i = 2; i < houses.length; i++) {
			best[i] = Math.max(houses[i] + best[i - 2], best[i - 1]);
		}
		return best[best.length - 1];
	}

	public static boolean isMatch(String s, String p) {
		return isMatch(s, 0, p, 0);
	}

	private static boolean isMatch(String target, int targetIndex,
			String pattern, int patternIndex) {
		if (patternIndex == pattern.length())
			return targetIndex == target.length();

		if (patternIndex + 1 < pattern.length()
				&& pattern.charAt(patternIndex + 1) == '*') {
			if (isMatch(target, targetIndex, pattern, patternIndex + 2))
				return true;
			while (isCharMatch(target, targetIndex, pattern, patternIndex)) {
				if (isMatch(target, targetIndex + 1, pattern, patternIndex + 2)) {
					return true;
				}
				targetIndex++;
			}
			return false;
			// return isMatch(target, targetIndex, pattern, patternIndex+2);
		} else {
			if (isCharMatch(target, targetIndex, pattern, patternIndex)) {
				return isMatch(target, targetIndex + 1, pattern,
						patternIndex + 1);
			} else {
				return false;
			}
		}
	}

	private static boolean isCharMatch(String target, int targetIndex,
			String pattern, int patternIndex) {
		if (targetIndex == target.length() || patternIndex == pattern.length())
			return false;
		return (target.charAt(targetIndex) == pattern.charAt(patternIndex) || '.' == pattern
				.charAt(patternIndex));
	}

	public static List<List<Integer>> getResults(int target, int[] canadiates) {
		List<List<Integer>> ans = new ArrayList<>();
		getResults(new LinkedList<Integer>(), ans, target, 0, canadiates);
		return ans;
	}

	private static void getResults(LinkedList<Integer> path,
			List<List<Integer>> ans, int target, int start, int[] canadiates) {
		if (target < 0) {
			return;
		}
		if (target == 0) {
			ans.add(new ArrayList<Integer>(path));
		} else {
			for (int i = start; i < canadiates.length; i++) {
				path.add(canadiates[i]);
				getResults(path, ans, target - canadiates[i], i, canadiates);
				path.pollLast();
			}
		}
	}

	public static int findLongestIncreasingSub(int[] input) {
		int maxLength = 0;

		int length = 1;
		for (int i = 1; i < input.length; i++) {
			if (input[i] >= input[i - 1]) {
				length++;
			} else {
				if (length > maxLength) {
					maxLength = length;
					length = 1;
				}
			}
		}
		if (length > maxLength)
			maxLength = length;
		return maxLength;
	}

	public static List<List<Integer>> footballScore(int target, int[] scores) {
		Arrays.sort(scores);
		List<List<Integer>> ans = new LinkedList<>();
		footballScore(ans, new LinkedList<Integer>(), target, scores, 0);
		return ans;
	}

	private static void footballScore(List<List<Integer>> ans,
			LinkedList<Integer> path, int target, int[] scores, int start) {
		if (target < 0) {
			return;
		} else if (target == 0) {
			ans.add(new LinkedList<Integer>(path));
		} else {
			for (int i = start; i < scores.length; i++) {
				path.add(scores[i]);
				footballScore(ans, path, target - scores[i], scores, i);
				path.removeLast();
			}
		}
	}

	public static int maxPathSum(TreeNode<Integer> root) {
		int[] max = { Integer.MIN_VALUE };
		rec(root, max);
		return max[0];
	}

	public static int rec(TreeNode<Integer> root, int[] max) {
		if (root == null) {
			return 0;
		}

		int leftSubtreeMaxSum = rec(root.left, max);
		int rightSubtreeMaxSum = rec(root.right, max);

		int maxPathAcrossRootToParent = Math.max(root.val,
				Math.max(leftSubtreeMaxSum, rightSubtreeMaxSum) + root.val);
		max[0] = Math.max(max[0], Math.max(leftSubtreeMaxSum + root.val
				+ rightSubtreeMaxSum, maxPathAcrossRootToParent));
		return maxPathAcrossRootToParent;
	}

	public static void printAllPath(TreeNode root) {
		printAllPath(root, new LinkedList<TreeNode>());
	}

	private static void printAllPath(TreeNode root, LinkedList<TreeNode> path) {
		if (root == null)
			return;
		path.add(root);
		if (root != null && root.left == null && root.right == null) {
			for (TreeNode n : path) {
				System.out.print(n.val);
			}
			System.out.println();
		} else {
			printAllPath(root.left, new LinkedList<TreeNode>(path));
			printAllPath(root.right, new LinkedList<TreeNode>(path));
		}
	}

	public static DoubleLinkedListNode multiplyTwoList(DoubleLinkedListNode a,
			DoubleLinkedListNode b) {
		DoubleLinkedListNode curr = new DoubleLinkedListNode(0);

		DoubleLinkedListNode bb = b;

		while (a != null) {
			b = bb;
			while (b != null) {

				int temp = a.getValue() * b.getValue() + curr.getValue();
				curr.setValue(temp % 10);
				updateCarrier(temp / 10, curr);

				b = b.getNext();
				if (curr.getNext() != null) {
					curr = curr.getNext();
				} else if (b != null) {
					DoubleLinkedListNode next = new DoubleLinkedListNode(0);
					curr.setNext(next);
					curr = next;
				}
			}
			if (curr.getPre() == null)
				curr.setPre(new DoubleLinkedListNode(0));
			curr = curr.getPre();
			a = a.getNext();
		}

		while (curr.getPre() != null)
			curr = curr.getPre();
		return curr;
	}

	private static void updateCarrier(int result, DoubleLinkedListNode curr) {
		DoubleLinkedListNode temp = curr;
		while (result > 0) {
			temp = getOrCreatePre(temp);
			temp.setValue(temp.getValue() + result % 10);
			result /= 10;
		}
	}

	private static DoubleLinkedListNode getOrCreatePre(DoubleLinkedListNode temp) {
		if (temp.getPre() == null) {
			DoubleLinkedListNode n = new DoubleLinkedListNode(0);
			temp.setPre(n);
		}
		return temp.getPre();
	}

	public static void chooseKfromN(int k, int n) {
		StringBuilder sb = new StringBuilder();
		chooseKfromN(0, k, sb, n);
	}

	private static void chooseKfromN(int index, int k, StringBuilder buffer,
			int n) {
		if (k == buffer.length()) {
			System.out.println(buffer.toString());
		} else {
			for (int i = index; i < n; i++) {
				buffer.append(i + 1);
				chooseKfromN(i + 1, k, buffer, n);
				buffer.deleteCharAt(buffer.length() - 1);
			}
		}
	}

	public static TreeNode convertBTtoLinkedList(TreeNode root) {
		if (root == null || (root.left == null && root.right == null))
			return root;
		convertBTtoLinkedList(root.right);
		TreeNode curr = root;
		while (curr.right != null)
			curr = curr.right;
		TreeNode temp = root.left;
		root.left = null;
		curr.right = convertBTtoLinkedList(temp);
		return root;
	}

	public static TreeNode[] convertBTtoLinkedList2(TreeNode root) {
		if (root == null || (root.left == null && root.right == null)) {
			return new TreeNode[] { root, root };
		}

		TreeNode[] ln = convertBTtoLinkedList2(root.right);
		TreeNode temp = root.left;
		root.left = null;
		TreeNode[] rn = convertBTtoLinkedList2(temp);
		ln[1].right = rn[0];
		return new TreeNode[] { root, rn[1] };
	}

	public static void writeBinaryTree(TreeNode root, StringBuilder sb) {
		if (root == null) {
			sb.append("# ");
		} else {
			sb.append(root.val + " ");
			writeBinaryTree(root.left, sb);
			writeBinaryTree(root.right, sb);
		}
	}

	public static TreeNode deserializeBinaryTree(List<String> inputs) {
		if (inputs.size() == 0) {
			return null;
		} else {
			String target = inputs.remove(0);
			if (!target.equals("#")) {
				TreeNode root = new TreeNode(Integer.parseInt(target));
				root.left = deserializeBinaryTree(inputs);
				root.right = deserializeBinaryTree(inputs);
				return root;
			} else {
				return null;
			}
		}
	}
	
	public static void writeBST(TreeNode root, StringBuilder sb) {
		if(root==null) {
			return;
		} else {
			sb.append(root.val + " ");
			writeBST(root.left, sb);
			writeBST(root.right, sb);
		}
	}
	
	public static int getNumOfPalin(String input) {
		int l = input.length();
		boolean[][] cache = new boolean[l][l];
		int ans = 0;
		for (int m = 0; m < l; m++) {
			cache[m][m] = true;
			ans++;
		}

		for (int end = 1; end < l; end++) {
			for (int start = 0; start < end; start++) {
				if (input.charAt(start) == input.charAt(end)
						&& (start + 1 == end || cache[start + 1][end - 1])) {
					cache[start][end] = true;
					ans++;
				}
			}
		}
		return ans;
	}

	public static int maximalRectangle(char[][] matrix) {
		int max = 0;
		if (matrix == null || matrix.length == 0)
			return max;
		int row = matrix.length, col = matrix[0].length;

		int[][] buffer = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int c = 0; c < col; c++) {
				if (i == 0) {
					buffer[i][c] = matrix[i][c] - '0';
				} else {
					if (buffer[i - 1][c] != 0 && matrix[i][c] - '0' != 0) {
						buffer[i][c] = buffer[i - 1][c] + 1;
					}
				}
			}
		}

		for (int[] b : buffer)
			max = Math.max(max, largestRectangleArea(b));
		return max;
	}

	private static int largestRectangleArea(int[] height) {
		Stack<Integer> left = new Stack<Integer>();
		int cur = 0, area = 0;
		while (cur < height.length) {
			if (left.isEmpty() || height[cur] >= height[left.peek()]) {
				left.push(cur);
				cur++;
			} else {
				int top = left.pop(); // the width of the rectangle is
										// [left.peek()+1, cur)
				area = Math.max(area, height[top]
						* (left.isEmpty() ? cur : (cur - left.peek() - 1)));
			}
		}

		while (!left.isEmpty()) {
			int top = left.pop();
			area = Math.max(area, height[top]
					* (left.isEmpty() ? cur : (cur - left.peek() - 1)));
		}
		return area;
	}

	public static String getPermutation(int n, int k) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			list.add(i);
		}
		StringBuffer sb = new StringBuffer();
		getPermutation(k - 1, list, sb);
		return sb.toString();
	}

	private static void getPermutation(int k, ArrayList<Integer> list,
			StringBuffer sb) {
		if (k == 0) {
			for (Integer i : list) {
				sb.append(i);
			}
		} else {
			int itemsInBucket = getFac(list.size() - 1);
			int bucket = k / itemsInBucket;
			Integer toAppend = list.get(bucket);
			sb.append(toAppend);
			list.remove(toAppend);
			getPermutation(k - bucket * itemsInBucket, list, sb);
		}
	}
	
	private static int getFac(int n) {
		if (n == 0)
			return 1;
		return n * getFac(n - 1);
	}
	
	public static boolean isSum(int target, int[] input) {
		if(target==0) return true;
		if(target<0) return false;
		for(int i=0; i<input.length; i++) {
			if(isSum(target-input[i], input)) {
				return true;
			}
		}
		return false;
	}
	
	static Hashtable<String, Integer> cache = new Hashtable<>();
	public static int wordBreak(String s, Set<String> dict) {
		cache.put("", 1);
		if(s==null || s.isEmpty() || dict==null) return -1;
		return wordBreakHelper(s, dict);
	}

	private static int wordBreakHelper(String s, Set<String> dict) {
		if(cache.containsKey(s)) {
			return cache.get(s);
		} else if(s.length()==0) {
			return 1;
		} else {
			int temp = 0;
			for(int end=1; end<=s.length(); end++) {
				if(dict.contains(s.substring(0, end))) {
					temp += wordBreakHelper(s.substring(end), dict);
				}
			}
			cache.put(s, temp);
			return temp;
		}
	}

	public static void main(String[] args) {
		/**
		 * Pair p1 = new Pair(0,1); Pair p2 = new Pair(0,2); Pair p3 = new
		 * Pair(1,3); Pair[] pairs = {p1,p2,p3};
		 * 
		 * printLogInfo(pairs);
		 * 
		 * List<String> words = new ArrayList<>(); words.add("star");
		 * words.add("rats"); words.add("car"); words.add("arc");
		 * words.add("arts"); words.add("arts"); getAnagrams(words);
		 * 
		 * TreeNode root = new TreeNode(0); TreeNode n1 = new TreeNode(1);
		 * TreeNode n2 = new TreeNode(2); root.left = n1; root.right = n2;
		 * 
		 * BTInorderIterator it = new BTInorderIterator(root);
		 * while(it.hasNext()) { System.out.println(it.next().val); }
		 * 
		 * int k = 3; List<Point> points = new ArrayList<>(); points.add(new
		 * Point(1,2)); points.add(new Point(1,2)); points.add(new Point(3,5));
		 * points.add(new Point(7,8)); points.add(new Point(-1,2)); Object[] ob
		 * = (findKNearest(k, points).toArray()); for(Object p : ob) {
		 * System.out.println(((Point)p).x + " " + ((Point)p).y); }
		 * 
		 * System.out.println(multiply("12345678987654321",
		 * "123456789123456789"));
		 * System.out.println("1524157887364730998475842112635269");
		 * 
		 * ListNode l1 = new ListNode(1); ListNode l2 = new ListNode(2);
		 * ListNode l3 = new ListNode(3); ListNode l4 = new ListNode(4);
		 * ListNode l5 = new ListNode(5); ListNode l6 = new ListNode(6);
		 * ListNode l7 = new ListNode(7); ListNode l8 = new ListNode(8);
		 * ListNode l9 = new ListNode(9);
		 * 
		 * l1.setNext(l2); l2.setNext(l3); l3.setNext(l4); l4.setNext(l5);
		 * l5.setNext(l6); l6.setNext(l7); l7.setNext(l8); l8.setNext(l9);
		 * 
		 * ListNode ans = reverseLinkedListByK(l1,4); while(ans!=null) {
		 * System.out.println(ans.getVal()); ans = ans.getNext(); }
		 * 
		 * System.out.println(getMinEdit("abc", "eaee"));
		 * 
		 * System.out.println(robHouse(new int[]{1,5,3}));
		 * 
		 * System.out.println(isMatch("a", "a*a"));
		 * 
		 * List<List<Integer>> ans = getResults(12, new int[]{2,3,6});
		 * for(List<Integer> l : ans) {
		 * System.out.println(Arrays.toString(l.toArray())); }
		 * 
		 * 
		 * TreeNode n1 = new TreeNode(1); TreeNode n2 = new TreeNode(2);
		 * TreeNode n3 = new TreeNode(3); TreeNode n4 = new TreeNode(4);
		 * TreeNode n5 = new TreeNode(5); TreeNode n6 = new TreeNode(6);
		 * TreeNode n7 = new TreeNode(7); TreeNode n8 = new TreeNode(8);
		 * TreeNode n9 = new TreeNode(9); n9.left = n5; n9.right = n8; n5.left =
		 * n3; n5.right = n4; n8.left = n6; n8.right = n7; n3.left = n1;
		 * n3.right = n2;
		 * 
		 * BTPostorderIterator it = new BTPostorderIterator(n9);
		 * while(it.hasNext()) { System.out.println(it.next().val); }
		 * 
		 * List<List<Integer>> temp = footballScore(12, new int[]{2,4});
		 * for(List<Integer> l : temp) { for(Integer i : l) { System.out.print(i
		 * + ", "); } System.out.println(); }
		 * 
		 * TreeNode root = new TreeNode(-3); root.left = new TreeNode(1);
		 * System.out.println(maxPathSum(root));
		 * 
		 * DoubleLinkedListNode a = new DoubleLinkedListNode(1); a.setNext(new
		 * DoubleLinkedListNode(0){{setNext(new DoubleLinkedListNode(1));}});
		 * DoubleLinkedListNode b = new DoubleLinkedListNode(2); b.setNext(new
		 * DoubleLinkedListNode(0){{setNext(new DoubleLinkedListNode(2));}});
		 * DoubleLinkedListNode ans = multiplyTwoList(b, a); while(ans != null)
		 * { System.out.print(ans.getValue()); ans = ans.getNext(); }
		 * 
		 * chooseKfromN(1, 5);
		 * 
		 * 
		 * TreeNode n1 = new TreeNode(1); TreeNode n2 = new TreeNode(2);
		 * TreeNode n3 = new TreeNode(3); TreeNode n4 = new TreeNode(4);
		 * TreeNode n5 = new TreeNode(5); TreeNode n6 = new TreeNode(6);
		 * TreeNode n7 = new TreeNode(7); TreeNode n8 = new TreeNode(8);
		 * TreeNode n9 = new TreeNode(9); n9.left = n5; n9.right = n8; n5.left =
		 * n3; n5.right = n4; n8.left = n6; n8.right = n7; n3.left = n1;
		 * n3.right = n2;
		 * 
		 * StringBuilder sb = new StringBuilder(); writeBinaryTree(n9, sb);
		 * System.out.println(sb.toString());
		 * 
		 * List<String> inputs = new LinkedList<>(); for(String s :
		 * sb.toString().split(" ")) inputs.add(s);
		 * 
		 * TreeNode res = deserializeBinaryTree(inputs);
		 * 
		 * StringBuilder sb1 = new StringBuilder(); writeBinaryTree(res, sb1);
		 * System.out.println(sb1.toString());
		 
//		DoubleLinkedListNode a = new DoubleLinkedListNode(6);
//		a.setNext(new DoubleLinkedListNode(0) {
//			{
//				setNext(new DoubleLinkedListNode(1));
//			}
//		});
//		DoubleLinkedListNode b = new DoubleLinkedListNode(2);
//		b.setNext(new DoubleLinkedListNode(0) {
//			{
//				setNext(new DoubleLinkedListNode(2));
//			}
//		});
//		DoubleLinkedListNode ans = multiplyTwoList(b, a);
//		while (ans != null) {
//			System.out.print(ans.getValue());
//			ans = ans.getNext();
//		}
		//System.out.println(isSum(5, new int[]{2,4,3}));
		Set<String> dict = new HashSet<>();
		dict.add("pea");
		dict.add("nuts");
		dict.add("peanuts");
		dict.add("butter");
		dict.add("but");
		dict.add("ter");
		System.out.println(wordBreak("peanutsbutter", dict));
		**/
		TreeNode root = new TreeNode(4);
		TreeNode n2 = new TreeNode(2);
		TreeNode n6 = new TreeNode(6);
		root.left = n2;
		root.right = n6;
		n2.left = new TreeNode(1);
		n2.right = new TreeNode(3);
		n6.left = new TreeNode(5);
		n6.right = new TreeNode(7);
		StringBuilder sb = new StringBuilder();
		writeBST(root, sb);
		System.out.println(sb.toString());
	}
}
