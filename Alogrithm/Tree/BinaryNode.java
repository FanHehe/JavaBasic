public class BinaryNode extends Node {

	BinaryNode () { super("", 2); }
	BinaryNode (String data) { super(data, 2); }
	BinaryNode (String data, int base) { super(data, base); }

	public void setLeft (BinaryNode node) {
		this.children[0] = node;
	}

	public Node getLeft () {
		return this.children[0];
	}

	public Node getRight () {
		return this.children[1];
	}

	public void setRight (BinaryNode node) {
		this.children[1] = node;
	}
}



public class Name {
    public Name () {
        System.out.println('xx');
    }
}
