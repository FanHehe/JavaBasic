class Tree<T extends Comparable<T>> {

    public Node root;

    public class Node {
        public Node left;
        public Node right;
        public T value;

        Node (T value) {
            this(value, null, null);
        }

        Node (T value, Node left, Node right) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }

    boolean insert(T value) {

        if (this.root == null) {
            this.root = new Node(value);
            return true;
        }

        return insert(this.root, value);
    }

    boolean insert(Node target, T value) {

        if (target == null) {
            return false;
        }

        Node parent = null;

        while (target != null) {
            parent = target;
            if (target.value.compareTo(value) > 0) {
                target = target.left;
            } else if (target.value.compareTo(value) < 0) {
                target = target.right;
            } else {
                return false;
            }
        }

        target = new Node(value);

        if (parent.value.compareTo(value) > 0) {
            parent.left = target;
        } else {
            parent.right = target;
        }

        return true;
    }

    boolean delete(T value) {
        return delete(this.root, value);
    }

    boolean delete(Node root, T value) {

        if (root == null) {
            return false;
        }

        Node temp = root;
        Node parent = null;

        boolean isLeft = true;

        while (temp != null) {

            parent = temp;

            if (temp.value.compareTo(value) > 0) {
                isLeft = true;
                temp = temp.left;
            } else if (temp.value.compareTo(value) < 0) {
                isLeft = false;
                temp = temp.right;
            } else {
                break;
            }

            if (temp == null) {
                return false;
            }
        }

        // 左右都为null
        if (temp.left == null && temp.right == null) {

            if (temp == root) {
                this.root = null;
                return true;
            } else if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }

        // 左为null
        } else if (temp.left == null) {

            if (temp == root) {
                this.root = temp.right;
            } else if (isLeft) {
                parent.left = temp.right;
            } else {
                parent.right = temp.right;
            }

        // 右为null
        } else if (temp.right == null) {

            if (temp == root) {
                this.root = temp.left;
            } else if (isLeft) {
                parent.left == temp.left;
            } else {
                parent.right = temp.left;
            }

        } else {
        // 左右都不为null
            Node par = temp;
            Node sub = temp.left;

            while (sub.right != null) {
                par = sub;
                sub = sub.right;
            }

            if (sub.left) {
                if (par == temp) {
                    temp.left = sub.left;
                } else {
                    par.right = sub.left;
                }
            }

            if (parent.left == temp) {
                parent.left == sub;
            } else {
                parent.right = sub;
            }

            sub = null;
        }
    }
}
