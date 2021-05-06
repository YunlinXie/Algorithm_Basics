package com.yunlin;

import java.util.*;

public class BinaryTree {
    public static class Node<T> {
        T val;
        Node<T> left, right;

        public Node(T val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        public Node(T val, Node<T> left, Node<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public String preOrder(Node<T> node) {
            if (node == null) return "";
            StringBuilder sb = new StringBuilder();

            Stack<Node<T>> stack = new Stack<>();
            stack.push(node);
            while (!stack.isEmpty()) {
                Node<T> cur = stack.pop();
                sb.append(cur.val);
                if (cur.right != null)
                    stack.push(cur.right);
                if (cur.left != null)
                    stack.push(cur.left);
            }

            return sb.toString();
        }
//        public String preOrder(Node<T> node) {
//            StringBuilder sb = new StringBuilder();
//            preOrderHelper(node, sb);
//            return sb.toString();
//        }
//        private void preOrderHelper(Node<T> node, StringBuilder sb) {
//            if (node == null) return;
//            sb.append(node.val);
//            preOrderHelper(node.left, sb);
//            preOrderHelper(node.right, sb);
//        }

        public String inOrder(Node<T> node) {
            if (node == null) return "";
            StringBuilder sb = new StringBuilder();

            Stack<Node<T>> stack = new Stack<>();
            Node<T> cur = node;
            while (cur!=null || stack.size()>0) {
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
                cur = stack.pop();
                sb.append(cur.val);
                cur = cur.right;
            }

            return sb.toString();
        }
//        public String inOrder(Node<T> node) {
//            StringBuilder sb = new StringBuilder();
//            inOrderHelper(node, sb);
//            return sb.toString();
//        }
//        private void inOrderHelper(Node<T> node, StringBuilder sb) {
//            if (node == null) return;
//            inOrderHelper(node.left, sb);
//            sb.append(node.val);
//            inOrderHelper(node.right, sb);
//        }

        public String postOrder(Node<T> node) {
            StringBuilder sb = new StringBuilder();
            postOrderHelper(node, sb);
            return sb.toString();
        }
        private void postOrderHelper(Node<T> node, StringBuilder sb) {
            if (node == null) return;
            postOrderHelper(node.left, sb);
            postOrderHelper(node.right, sb);
            sb.append(node.val);
        }

        public String BFS(Node<T> node) {
            if (node == null) return "";
            StringBuilder sb = new StringBuilder();

            Queue<Node<T>> q = new LinkedList<>();
            q.add(node);
            while (!q.isEmpty()) {
                int n = q.size();
                for (int i=0; i<n; i++) {
                    Node<T> cur = q.poll();
                    sb.append(cur.val);
                    if (cur.left != null) q.add(cur.left);
                    if (cur.right != null) q.add(cur.right);
                }
            }

            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof Node<?>)) {
                return false;
            }
            return equals(this.val, ((Node<?>) o).val)
                    && equals(this.left, ((Node<?>) o).left)
                    && equals(this.right, ((Node<?>) o).right);
        }

        private boolean equals(Object x, Object y) {
            if (x == null) return y == null;
            return x.equals(y);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this == null) {
                return "";
            }
            sb.append(this.val);
            if (this.left != null)
                sb.append(this.left.toString());
            if (this.right != null)
                sb.append(this.right.toString());
            return sb.toString();
        }
    }


    public static Node<String> strToTree(String data) {
        String[] nodes = data.split(" ");
        List<String> all = new LinkedList<>();
        all.addAll(Arrays.asList(nodes));
        return buildTree(all);
    }

    public static Node<String> buildTree(List<String> tree) {
        if (tree.size() == 0) return null;
        String val = tree.remove(0);
        if (val.equals("n")) return null;
        Node<String> node = new Node<>(val);
        node.left = buildTree(tree);
        node.right = buildTree(tree);
        return node;
    }

    public static void main(String[] args) {
	// write your code here
        Node<String> root = strToTree("4 1 0 n n 2 n 3 n n 5");
        String res1 = root.preOrder(root);
        String res2 = root.inOrder(root);
        String res3 = root.postOrder(root);
        String res4 = root.BFS(root);
        System.out.println("toString():" + root);
        System.out.println("preorder:" + res1);
        System.out.println("inorder:" + res2);
        System.out.println("postorder:" + res3);
        System.out.println("BFS:" + res4);

        Node<String> root1 = strToTree("4 1 0 n n 2 n 3 n n 5");
        Node<String> root2 = strToTree("4 1 0 n n 2 n 3 n 5 n n");
        System.out.println(root.equals(root1));
        System.out.println(root.equals(root2));
    }
}
