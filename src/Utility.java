import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class Utility {
	
	public static TreeNode<Integer> createBinaryTree(int size, int range) {
		return createBinaryTree(size, range, true);
	}
	
	public static TreeNode<Integer> createBinaryTree(int size, int range, boolean isFullTree) {
		Random generator = new Random();
		int rootValue = generator.nextInt(range);
		TreeNode<Integer> root = new TreeNode<>(rootValue);
		
		List<TreeNode<Integer>> visiting = new ArrayList<>();
		visiting.add(root);
		Set<Integer> set = new HashSet<>();
		set.add(rootValue);
		int i=0;
		while(i+1!=size) {
			if(!isFullTree && visiting.size()>1) { 
				Random g = new Random();
				int curr = g.nextInt(2);
				if(curr==1) {
					visiting.remove(0);
					continue;
				}
			}

			int tempValue = generator.nextInt(range);
			while(set.contains(tempValue)) tempValue = generator.nextInt(range);
			set.add(tempValue);
			TreeNode<Integer> target = visiting.get(0);
			TreeNode<Integer> toAttach = new TreeNode<Integer>(tempValue);

			if(target.left==null) {
				target.left = toAttach;
			} else {
				target.right = toAttach;
				visiting.remove(0);
			}
			visiting.add(toAttach);
			i++;
		}
		return root;
	}
	
	
	public static void main(String[] args) {
		BTreePrinter.printNode(createBinaryTree(5, 5, false));
	}
}
