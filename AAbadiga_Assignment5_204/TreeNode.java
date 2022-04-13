/**
 * @author Adam Abadiga
 * @param <T>
 */

public class TreeNode<T> {
	protected T data;
	protected TreeNode<T> left;
	protected TreeNode<T> right;

	/**
	 * Create a new TreeNode
	 * @param data
	 */
	public TreeNode(T data) {
		this.data = data;
		left = null;
		right = null;
	}

	/**
	 * Used for making deep copies
	 * @param treeNode
	 */
	public TreeNode(TreeNode<T> treeNode) {
		this.left = treeNode.left;
		this.right = treeNode.right;
		this.data = treeNode.data;
	}

	/**
	 * Return the data within this TreeNode
	 * @return data
	 */
	public T getData() {
		return data;
	}
}