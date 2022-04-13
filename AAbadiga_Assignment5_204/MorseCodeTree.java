import java.util.ArrayList;

/**
 * Used to morse code into English
 * @author Adam Abadiga
 */

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
	private TreeNode<String> root;

	/**
	 * Calls on buildTree()
	 */
	public MorseCodeTree() {
		buildTree();
	}

	/**
	 * Inserts nodes of tree levels
	 */
	@Override
	public void buildTree() {
		root = new TreeNode<>("");
		insert(".", "e");
		insert("-", "t");

		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");

		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");

		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}

	/**
	 * Returns the root node
	 * @return rootNode
	 */
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	/**
	 * Set root node
	 * @param node
	 */
	@Override
	public void setRoot(TreeNode<String> node) {
		root = new TreeNode<String> (node);
	}

	/**
	 * Insert a letter into tree
	 * @param code
	 * @param letter
	 */
	@Override
	public void insert (String code, String letter) {
		addNode(root, code, letter);
	}

	/**
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * @throws UnsupportedOperationException
	 */
	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Add element
	 * @param root
	 * @param code
	 * @param letter
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		if (code.length() > 1) {
			if (code.charAt(0) == '-') {
				addNode(root.right, code.substring(1), letter);
			} else if (code.charAt(0) == '.') {
				addNode(root.left, code.substring(1), letter);
			}
		} else if (code.length() == 1) {
			if (code.charAt(0) == '.') {
				root.left = new TreeNode<String>(letter);
			} else if (code.charAt(0) == '-') {

				root.right = new TreeNode<String>(letter);
			}
		}
	}

	/**
	 * Fetch data
	 * @param code
	 * @return the string that corresponds to the code
	 */
	@Override
	public String fetch (String code) {
		String fetch = fetchNode (root, code);
		return fetch;
	}

	/**
	 * Fetches data of TreeNode
	 * @param root
	 * @param code
	 * @return letter
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		String s = "";
		if (code.length() > 1) {
			if (code.charAt(0) == '-') {
				return fetchNode(root.right, code.substring(1));
			} else if (code.charAt(0) == '.') {
				return fetchNode(root.left, code.substring(1));
			}
		} else {
			if (code.equals("-")) {
				return root.right.getData();
			} else if (code.equals(".")) {
				return root.left.getData();
			}
		}
		return s;
	}

	/**
	 * @return an ArrayList of the items in the linkedTree
	 */
	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> list = new ArrayList<String>();
		LNRoutputTraversal (root, list);
		return list;
	}

	/**
	 * @param root
	 * @param list
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root == null) {
			return;
		}
		if (root.left != null) {
			LNRoutputTraversal(root.left, list);
		}
		list.add(root.data);
		if (root.right != null) {
			LNRoutputTraversal(root.right, list);
		}
	}
}