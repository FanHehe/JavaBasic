public class Node {
	protected int base;
	protected String data;
	protected Node[] children;

	Node () { this.children =  new Node[2]; base = 2; }
	Node (int base) { this.children =  new Node[base]; this.base = base; }
	Node (String data) { this.data = data; this.children = new Node[2]; this.base = 2; }
	Node (String data, int base) { this.data = data; this.children = new Node[base]; this.base = base; }
	
	public String getData () {
		return  data;
	}

	public void setData (String data) {
		this.data = data;
	}
}