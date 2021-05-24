package com.yunlin;

import java.util.*;

public class BinaryTree {
    public static String NULL_VALUE = "#";

    public static String listToStr(List<Integer> valueList) {
        if (valueList.size() == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (Integer i: valueList) {
            sb.append(i);
        }
        return  sb.toString();
    }

    // O(n)
    public static TreeNode strToTree(String str) {
        if (str.length() == 0) return null;

        String[] nodes = str.split(" ");
        int len = nodes.length;
        Deque<TreeNode> stack = new ArrayDeque<>();
        int i = 0;
        TreeNode root = new TreeNode(Integer.parseInt(nodes[i]));
        stack.push(root);

        while (i<len-1) {
            i++;
            TreeNode node = stack.peek();
            String cur = nodes[i];

            if (node.val == Integer.MAX_VALUE) {
                stack.pop();
                TreeNode parent = stack.pop();
                if (cur.equals(NULL_VALUE)) {
                    parent.right = null;
                    stack.push(new TreeNode(Integer.MAX_VALUE));
                } else {
                    parent.right = new TreeNode(Integer.parseInt(cur));
                    stack.push(parent.right);
                }
            } else {
                if (cur.equals(NULL_VALUE)) {
                    node.left = null;
                    stack.push(new TreeNode(Integer.MAX_VALUE));
                } else {
                    node.left = new TreeNode(Integer. parseInt(cur));
                    stack.push(node.left);
                }
            }
        }

        return root;
    }

    public static List<Integer> preorderIterative(TreeNode node) {
        if (node == null) return new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            valueList.add(cur.val);
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
        return valueList;
    }

    public static void preorderRecursive(TreeNode node, List<Integer> valueList) {
        if (node == null) return;
        valueList.add(node.val);
        preorderRecursive(node.left, valueList);
        preorderRecursive(node.right, valueList);
    }

    public static List<Integer> inorderIterative(TreeNode node) {
        if (node == null) return new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = node;
        while (cur!=null || stack.size()>0) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            valueList.add(cur.val);
            cur = cur.right;
        }

        return valueList;
    }

    public static void inorderRecursive(TreeNode node, List<Integer> valueList) {
        if (node == null) return;
        inorderRecursive(node.left, valueList);
        valueList.add(node.val);
        inorderRecursive(node.right, valueList);
    }

    public static void postorderRecursive(TreeNode node, List<Integer> valueList) {
        if (node == null) return;
        postorderRecursive(node.left, valueList);
        postorderRecursive(node.right, valueList);
        valueList.add(node.val);
    }

    public static List<Integer> BFS(TreeNode treeNode) {
        if (treeNode == null) return new ArrayList<>();
        List<Integer> valueList = new ArrayList<Integer>();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(treeNode);
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i=0; i<n; i++) {
                    TreeNode cur = q.poll();
                    valueList.add(cur.val);
                    if (cur.left != null) q.add(cur.left);
                    if (cur.right != null) q.add(cur.right);
                }
        }
            return valueList;
    }


    public static TreeNode findParentNode(TreeNode root, TreeNode node) {
        if (root == null || node == null) return null;
        if (node == root) return null;
        TreeNode current = root;

        if (current.left != null && current.left.val == node.val) {
            return current;
        }

        if (current.right != null && current.right.val == node.val) {
            return current;
        }


        TreeNode leftparent = findParentNode(current.left, node);
        if (leftparent != null) return leftparent;
        return findParentNode(current.right, node);
    }


    public static void main(String[] args) {
        // build a tree from given string
        TreeNode root = strToTree("4 1 0 # # 2 # 3 # # 5");

        // preorder
        String res11 = listToStr(preorderIterative(root));

        List<Integer> l12 = new ArrayList<>();
        preorderRecursive(root, l12);
        String res12 = listToStr(l12);


        // inorder
        String res21 = listToStr(inorderIterative(root));

        List<Integer> l22 = new ArrayList<>();
        inorderRecursive(root, l22);
        String res22 = listToStr(l22);

        // postorder
        List<Integer> l3 = new ArrayList<>();
        postorderRecursive(root, l3);
        String res3 = listToStr(l3);

        // BFS
        String res4 = listToStr(BFS(root));


        // print to check
        System.out.println("toString() in preorder :" + root);
        System.out.println("preorder 410235:" + res11);
        System.out.println("preorder 410235:" + res12);
        System.out.println("inorder 012345:" + res21);
        System.out.println("inorder 012345:" + res22);
        System.out.println("postorder 032154:" + res3);
        System.out.println("BFS 415023:" + res4);


        TreeNode root1 = strToTree("4 1 0 # # 2 # 3 # # 5");
        TreeNode root2 = strToTree("4 1 0 # # 2 # 3 # 5 # #");
        System.out.println("should be true" + root.equals(root1));
        System.out.println("should be false" + root.equals(root2));

        TreeNode parent1 = findParentNode(root, root.right);
        System.out.println("should be true" + (parent1.val == root.val));

        TreeNode parent2 = findParentNode(root, root.left.right);
        System.out.println("should be true" + (parent2.val==root.left.val));

    }

}
