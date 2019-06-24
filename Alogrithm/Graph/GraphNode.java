package Alogrithm.Graph;

public class GraphNode {

    public String name;
    public GraphNode next;

    double weight = Math.round(Math.random() * 100);

    public GraphNode(String name) {
        this(name, null);
    }

    public GraphNode(char name) {
        this(String.valueOf(name), null);
    }

    public GraphNode(String name, GraphNode node) {
        this.name = name;
        this.next = node;
    }

    public GraphNode setName(String name) {
        this.name = name;
        return this;
    }

    public GraphNode setNext(GraphNode node) {
        this.next = node;
        return this;
    }
}
