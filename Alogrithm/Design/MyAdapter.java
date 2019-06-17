package Alogrithm.Design;



interface ITarget {
    void request();
}

class Adaptee {
    public void provide() {

    }
}

class Adapter extends Adaptee implements ITarget {

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        adaptee.provide();
    }
}


public class MyAdapter {

    public static void main(String[] args) {
        Adaptee ada = new Adaptee();
        Adapter adapter = new Adapter(ada);

        adapter.request();
    }
}

