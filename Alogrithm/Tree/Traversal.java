import java.util.ArrayList;

public class Traversal {

	public static void levelTraversal (Node tree) {

		if (tree == null) return ;

		BinaryNode temp;

		ArrayList<Node> list = new ArrayList<Node>();

		list.add(tree);

		while (!list.isEmpty()) {

			temp = (BinaryNode)list.remove(0);

			if (temp.getLeft() != null) list.add(temp.getLeft());
			if (temp.getRight() != null) list.add(temp.getRight());

			System.out.print(temp.getData());
		}
	}

	public static void prevTraversalRecursion (Node tree) {
		if (tree != null) {
			System.out.print(tree.getData());
			prevTraversalRecursion(((BinaryNode)tree).getLeft());
			prevTraversalRecursion(((BinaryNode)tree).getRight());
		}
	}

	public static void prevTraversal (Node tree) {

		Node temp = tree;
		ArrayList<Node> stack = new ArrayList<Node>(10);

		while (temp != null || !stack.isEmpty()) {
			if (temp != null)
			{
				System.out.print(temp.getData());
				if (((BinaryNode)temp).getRight() != null) stack.add(((BinaryNode)temp).getRight());

				temp = ((BinaryNode)temp).getLeft();
			} else {
				temp = stack.remove(stack.size() - 1);
			}
		}
	}

	public static void midTraversal  (Node tree) {
		Node temp = tree;
		ArrayList<Node> stack = new ArrayList<Node>(10);

		while (temp != null || !stack.isEmpty()) {
			if (temp != null) {
				stack.add(temp);
				temp = ((BinaryNode)temp).getLeft();
			} else {
				temp = stack.remove(stack.size() - 1);
				System.out.print(temp.getData());
				temp = ((BinaryNode)temp).getRight();
			}
		}
	}

	public static void midTraversalRecursion (Node tree) {
		if (tree != null) {
			midTraversalRecursion(((BinaryNode)tree).getLeft());
			System.out.print(tree.getData());
			midTraversalRecursion(((BinaryNode)tree).getRight());
		}
	}

	  //    	  A
	  //  		↙   ↘
	  //  	  D       G
	  //   ↙   ↘    ↙   ↘
	  // F      B  C     E

	public static void tailTraversal (Node tree) {
		Node temp = tree;
		Node lastVisit = tree;

		ArrayList<Node> stack = new ArrayList<Node>(10);

		while (temp != null || !stack.isEmpty()) {
			while (temp != null) {
				stack.add(temp);
				temp = ((BinaryNode)temp).getLeft();
			}

			temp = stack.get(stack.size() - 1);

			if (((BinaryNode)temp).getRight() == null || lastVisit == ((BinaryNode)temp).getRight()) {
				System.out.print(temp.getData());
				lastVisit = temp;
				stack.remove(stack.size() - 1);
				temp = null;
			} else {
				temp = ((BinaryNode)temp).getRight();
			}
		}
	}

	public static void tailTraversalRecursion (Node tree) {
		if (tree != null) {
			tailTraversalRecursion(((BinaryNode)tree).getLeft());
			tailTraversalRecursion(((BinaryNode)tree).getRight());
			System.out.print(tree.getData());
		}
	}

}