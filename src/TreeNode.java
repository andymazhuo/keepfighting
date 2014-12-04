public class TreeNode<T extends Comparable<?>> {

	TreeNode<T> left;
	TreeNode<T> right;
	T val;

	public TreeNode(T value) {
		val = value;
	}

	@Override
	public String toString() {
		return String.valueOf(this.val);
	}
}
