public class MyLinkedList<E>{

    Node<E> head;
    Node<E> tail;
    
    int count = 0;

    public static class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        public Node(E value) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    public void add(E value) {
        Node<E> node = new Node<>(value);

        if (tail == null) {
            head = tail = node;
            return;
        }

        node.prev = tail;
        tail.next = node;
        tail = node;

        count++;
    }


    public int size() {
        return count;
    }

    public void clear() {
       
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean contains(Object o) {
        return false;
    }


    public boolean remove(Object o) {
        return true;
    }

    public void printAll() {
        Node<E> temp = head;
        
        while(temp != null) {
            System.out.print(temp.value);
            temp = temp.next;
        }

        System.out.println("");
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        list.printAll();
    }
}