import java.util.ArrayList;


public class Linkedin {

	//mitbbs: http://www.mitbbs.com/article_t/JobHunting/32582287.html
	
	/**
	 * Given a binary tree, return the mirrored binary tree.
	 */
	public static TreeNode<Integer> getMirror(TreeNode<Integer> root) {
		if(root==null) return root;
		TreeNode<Integer> left = getMirror(root.right);
		TreeNode<Integer> right = getMirror(root.left);
		root.left = left;
		root.right = right;
		return root;

		/**
		TreeNode<Integer> root = new TreeNode<Integer>(1);
		root.left = new TreeNode<Integer>(2);
		root.left.left = new TreeNode<Integer>(4);
		root.left.right = new TreeNode<Integer>(5);
		root.left.left.left = new TreeNode<Integer>(6);
		root.left.left.right = new TreeNode<Integer>(7);
		root.right = new TreeNode<Integer>(3);
		BTreePrinter.printNode(root);
		BTreePrinter.printNode(getMirror(root));
		**/
	}

	/**
	 * 就是给出一个数，找出所有Unique的组合
	 * @param target
	 */
	public static void printSumCombination(int target) {
		printSumCombination(target, 1, new ArrayList<Integer>());
	}

	private static void printSumCombination(int target, int start, ArrayList<Integer> sb) {
		if(target<0) {
			return;
		} else if(target==0) {
			for(int i : sb) {
				System.out.print(i + " ");
			}
			System.out.println();
		} else {
			for(int i=start; i<=target; i++) {
				sb.add(i);
				printSumCombination(target-i, i, sb);
				sb.remove(sb.size()-1);
			}
		}
	}

	public static void main(String[] args) {
	}
}
