import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreeProblems {
	
	public static void main(String[] args) {
		TreeNode<Integer> root = new TreeNode<>(3);
		root.left = new TreeNode<>(2);
		root.left.left = new TreeNode<>(1);
		root.left.right = new TreeNode<>(5);
		root.right = new TreeNode<>(4);
		root.right.left = new TreeNode<>(7);
		root.right.right = new TreeNode<>(2);
		System.out.println(findUniqueSubTree(root));
	}

	/**
	 * 给定一个二叉树，所有的节点值（包括中间，叶子节点）有可能重复，题目要求找出所
	 * 有的没有重复节点的子树（包括叶子节点，这个算作一个节点的子树）
	 * Notice: 存放ID 还是Object是个考虑
	 */
	public static int findUniqueSubTree(TreeNode<Integer> root) {
		List<TreeNode<Integer>> ans = new ArrayList<>();
		findUniqueSubTree(root, ans);
		return ans.size();
	}
	
	private static Set<Integer> findUniqueSubTree(TreeNode<Integer> root, List<TreeNode<Integer>> ans) {
		Set<Integer> set = new HashSet<>();
		if(root.left == null && root.right ==null) {
			ans.add(root);
		} else {
			Set<Integer> ls = findUniqueSubTree(root.left, ans);
			Set<Integer> rs = findUniqueSubTree(root.right, ans);
			set.addAll(ls);
			set.addAll(rs);
			if(ls.size()+rs.size()==set.size() && !set.contains(root.val)) {
				ans.add(root);
			}
		}
		set.add(root.val);
		return set;
	}
}
