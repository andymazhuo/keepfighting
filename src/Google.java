import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class Google {
	
	/**
	 * when create the graph, need to create all the edges for all the vertex.
	 */
	public static boolean isRelated(Map<String, List<String>> map, String start, String end) {
		if(start.equals(end)) return true;
		if(map.containsKey(start)) {
			for(String n : map.get(start)) {
				if(isRelated(map, n, end)) return true;
			}
		}
		return false;
		/**
		Map<String, List<String>> map = new HashMap<>();
		map.put("A", new LinkedList<String>(){{add("B");add("D");add("C");}});
		map.put("B", new LinkedList<String>(){{add("E");}});
		map.put("F", new LinkedList<String>(){{add("G");}});
		System.out.println(isRelated(map, "A", "A"));
		System.out.println(isRelated(map, "A", "E"));
		System.out.println(isRelated(map, "C", "A"));
		 */
	}

	/**
	 * Anagram Substring Search (Or Search for all permutations)
	 * Solution: sliding window + hash
	 * More: what about the size of hash, do you want to remove it if the count==0?
	 * External: http://www.geeksforgeeks.org/anagram-substring-search-search-permutations/
	 */
	public static void findAnagramSubstring(String target, String search) {
		HashMap<Character, Integer> map = getCountMap(search);
		HashMap<Character, Integer> window = getCountMap(target.substring(0, search.length()));
		for(int i=search.length(); i<target.length(); i++) {
			if(isMatched(map, window)) System.out.println(i-search.length());
			int head = i - search.length();
			window.put(target.charAt(head), window.get(target.charAt(head))-1);
			Character c = target.charAt(i);
			window.put(c, window.get(c)==null ? 1 : window.get(c)+1);
		}
		if(isMatched(map, window)) System.out.println(target.length()-search.length());
	}
	
	private static boolean isMatched(HashMap<Character, Integer> map, HashMap<Character, Integer> window) {
		for(Character key : map.keySet()) if(window.get(key)==null || map.get(key)!=window.get(key)) return false;
		return true;
	}

	private static HashMap<Character, Integer> getCountMap(String search) {
		HashMap<Character, Integer> ans = new HashMap<>();
		for(Character c : search.toCharArray()) ans.put(c, ans.get(c)==null ? 1 : ans.get(c)+1);
		return ans;
	}

	/**
	 * Serialize and Deserialize an N-ary Tree
	 * The algorithm is print out an end marker to indicate the end of children. ")"
	 * @param root
	 * @return
	 */
	public static String serializeNaryTree(Node root) {
		if(root==null) return "";
		StringBuilder sb = new StringBuilder();
		sb.append(root.value);
		for(Node c : root.children) sb.append(serializeNaryTree(c));
		return sb.append(")").toString();
	}

	public static Node deserializeNaryTree(String input) {
		Stack<Node> stack = new Stack<>();
		Node root = null;
		for(char c : input.toCharArray()) {
			if(c==')') {
				root = stack.pop();
			} else {
				Node child = new Node(Character.toString(c));
				if(!stack.isEmpty()) stack.peek().addChild(child);
				stack.push(child);
			}
		}
		return root;
	}
	
	static int index = 0;
	public static Node deserializeNaryTree2(String input) {
		if(index == input.length()) return null;
		if(input.charAt(index)==')') {
			index++;
			return null;
		}
		Node root = new Node(Character.toString(input.charAt(index++)));
		while(index<input.length()) {
			Node child = deserializeNaryTree2(input);
			if(child!=null) {
				root.addChild(child);
			} else {
				break;
			}
		}
		return root;
	}

	/**
	 * 打印一个数的所有乘数组合，从大到小，不要有重复
	 * @param n
	 * @return
	 */
	public static List<List<Integer>> getFactorCombination(int n) {
        List<List<Integer>> result = new ArrayList<>();
        getFactorCombinationHelper(n, n/2, result, new ArrayList<Integer>());
        return result;
    }
	
	private static void getFactorCombinationHelper(int n, int max, List<List<Integer>> result, List<Integer> sub) {
        if(n==1) {
            result.add(new ArrayList<Integer>(sub));
        } else {
        	for(int i = max; i >= 2; i--) {
                if(n % i != 0) continue;
                sub.add(i);
                getFactorCombinationHelper(n / i, i, result, sub);
                sub.remove(sub.size() - 1);
            }
        }
    }

	public static TreeNode<Integer> reverse(TreeNode<Integer> root) {
		if(root==null || root.left==null) {
			return root;
		} else {
			TreeNode<Integer> newRoot = reverse(root.left);
			root.left.left = root.right;
			root.left.right = root;
			root.right = null;
			root.left = null;
			return newRoot;
		}
	}
	
	public static TreeNode<Integer> reverseWithStack(TreeNode<Integer> root) {
		TreeNode<Integer> ans = null;
		Stack<TreeNode<Integer>> s = new Stack<>();
		while(root!=null) {
			s.push(root);
			root = root.left;
		}
		
		while(!s.isEmpty()) {
			TreeNode<Integer> curr = s.pop();
			if(ans==null) ans = curr;
			if(!s.isEmpty()) {
				curr.left = s.peek().right;
				curr.right = s.peek();
			} else {
				curr.left = null;
				curr.right = null;
			}
		}
		return ans;
	}

	public static int read(char[] buf, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<(n/4)+1; i++) {
            char[] temp = new char[4];
            int count = read4(temp);
            if(count!=4) {
                append(sb, Math.min(count, n-sb.length()), temp);
                break;
            } else if (n-sb.length()<4) {
                append(sb, n-sb.length(), temp);
                break;
            } else {
                sb.append(temp);
            }
        }
        buf = new char[sb.length()];
        for(int i=0; i<sb.length(); i++) buf[i] = 'a';
        return buf.length;
    }
    
    private static int read4(char[] temp) {
    	temp = new char[4];
    	temp[0] = 'a';
		return 1;
	}

	private static void append(StringBuilder sb, int toAppend, char[] temp) {
        for(int i=0; i<toAppend; i++) sb.append(temp[i]);
    }

	/**
	 * require all the entry to be non-zero, otherwise, the base case need return something else.
	 * what if from any node, see leetcode for solution.
	 */
	static int max = 0;
	public static int getMaxPathFromTwoLeafNode(TreeNode<Integer> root) {
		if(root==null) return 0;
		if(root.left==null && root.right==null) {
			return root.val;
		} else {
			int left = getMaxPathFromTwoLeafNode(root.left);
			int right = getMaxPathFromTwoLeafNode(root.right);
			max = Math.max(max, left+right+root.val);
			return Math.max(left, right) + root.val;
		}
	}
	
	public static void printAllLeafs(TreeNode<Integer> root) {
		if(root==null) return;
		if(root.left == null && root.right == null) {
			System.out.println(root.val);
		} else {
			printAllLeafs(root.left);
			printAllLeafs(root.right);
		}
	}
	
	public static boolean isValidUTF8(byte[] input) {
		int index = 0;
		while(index < input.length) {
			int type = getType(index++, input);
			if(type==0) {
				continue;
			} else {
				for(int i=1; i<type; i++) {
					if(!isGood(input, index++)) {
						return false;
					}
				}
			}
		}
		return index == input.length;
	}
	
	
	private static int getType(int index, byte[] input) {
		int type = 0;
		int mask = 1 << 7;
		byte target = input[index];
		while(mask>0) {
			if((target & mask) == 0) return type;
			type++;
			mask = mask >> 1;
		}
		return type;
	}

	private static boolean isGood(byte[] input, int index) {
		if(index>=input.length) return false;
		int mask = 1 << 7;
		return   (input[index] & (1<<7))!=0 && //it's != 0, not == 1
				 (input[index] & (1<<6))==0;
	}
	
	private static TreeNode<Integer> curr, pre, ans;
	public static void findNextNode(TreeNode<Integer> target, TreeNode<Integer> root) {
		if(root==null || ans!=null) return;
		findNextNode(target, root.left);
		pre = curr;
		curr = root;
		if(target.equals(pre)) ans = curr;
		findNextNode(target, root.right);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		TreeNode<Integer> root = new TreeNode<Integer>(4);
		root.left = new TreeNode<Integer>(2);
		root.left.left = new TreeNode<Integer>(1);
		root.left.right = new TreeNode<Integer>(3);
		root.right = new TreeNode<Integer>(6);
		root.right.left = new TreeNode<Integer>(5);
		root.right.right = new TreeNode<Integer>(7);
		BTreePrinter.printNode(root);
		findNextNode(root.right.left, root);
		System.out.println(ans.val);
		
		String[] ss = {"", "A", "B"};
		Arrays.sort(ss);
		
		/**
		byte[] output = "快乐幸福".getBytes();
		for(byte t : output) {
			System.out.println(String.format("%8s", Integer.toBinaryString(t & 0xFF)).replace(' ', '0'));
		}
		
		System.out.println(new String(output, "UTF-8"));
		System.out.println(isValidUTF8(output));
		
		byte[] sec = new byte[output.length-1];
		for(int i=0; i<sec.length; i++) {
			sec[i] = output[i];
		}
		System.out.println(isValidUTF8(sec));
		
		
		TreeNode<Integer> root = new TreeNode<Integer>(1);
		root.left = new TreeNode<Integer>(2);
		root.left.left = new TreeNode<Integer>(4);
		root.left.right = new TreeNode<Integer>(5);
		root.left.left.left = new TreeNode<Integer>(6);
		root.left.left.right = new TreeNode<Integer>(7);
		root.right = new TreeNode<Integer>(3);
		BTreePrinter.printNode(root);
		TreeNode<Integer> temp = reverseWithStack(root);
		BTreePrinter.printNode(temp);
		
		for(List<Integer> l : getFactorCombination(24)) {
			System.out.println(Arrays.toString(l.toArray()));
		}
		
		Node root = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		Node e = new Node("E");
		Node f = new Node("F");
		Node g = new Node("G");
		Node h = new Node("H");
		Node i = new Node("I");
		Node j = new Node("J");
		Node k = new Node("K");
		root.addChild(b);
		root.addChild(c);
		root.addChild(d);
		b.addChild(e);
		b.addChild(f);
		f.addChild(k);
		d.addChild(g);
		d.addChild(h);
		d.addChild(i);
		d.addChild(j);
		String input = serializeNaryTree(root);
		System.out.println(input);
		Node n = deserializeNaryTree(input);
		String input2 = serializeNaryTree(n);
		System.out.println(input2);
		Node n2 = deserializeNaryTree2(input2);
		System.out.println(serializeNaryTree(n2));
		**/
	}
}

