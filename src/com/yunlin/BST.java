package com.yunlin;

import java.util.ArrayList;
import java.util.List;

public class BST extends BinaryTree {

    public static void addNode(TreeNode root, int val) {
//        if (root == null) {
//            return;
//        }
//        TreeNode p = root;
//        while (true) {
//            if (val == p.val) return;
//            else if (val < p.val) {
//                if (p.left == null) {
//                    p.left = new TreeNode(val);
//                    return;
//                }
//                p = p.left;
//            }
//            else {
//                if (p.right == null) {
//                    p.right = new TreeNode(val);
//                    return;
//                }
//                p = p.right;
//            }
//        }

        TreeNode parent = null;
        TreeNode p = root;
        while (p != null) {
            parent = p;
            if (val < p.val)
                p = p.left;
            else
                p = p.right;
        }

        if (parent == null)
            root = new TreeNode(val);
        else if (val < parent.val)
            parent.left = new TreeNode(val);
        else
            parent.right = new TreeNode(val);
    }

//    public static void deleteNode(TreeNode root, TreeNode node) {
//        if (node == null) return;
//
//        TreeNode parent = findParentNode(root, node); // parent node for node to be deleted
//
//        // left subtree is empty, replace with right
//        if (node.left == null) {
//            if (parent == null)
//                root = root.right;
//            else if (parent.left == node)
//                parent.left = node.right;
//            else
//                parent.right = node.right;
//            return;
//        }
//
//
//        TreeNode temp = node.left;;  // store node for replace
//        TreeNode tempparent = null;   // parent node of node for replace
//        //left subtree is not empty, find replace node
//        while (temp.right != null) {
//            tempparent = temp;
//            temp = temp.right;
//        }
//
//        if (parent == null) {
//            if (tempparent == null) {
//                temp.right = root.right;
//                root = temp;
//                return;
//            }
//            temp.right = root.right;
//            tempparent.right = temp.left;
//            temp.left = root.left;
//            root = temp;
//            return;
//        }
//
//        if (tempparent == null) {
//            if (parent.left == node) {
//                temp.right = node.right;
//                parent.left = temp;
//                return;
//            }
//            if (parent.right == node) {
//                temp.right = node.right;
//                parent.right = temp;
//                return;
//            }
//        }
//
//        tempparent.right = temp.left;
//        temp.left = node.left;
//        temp.right = node.right;
//        if (parent.left == node)
//            parent.left = temp;
//        if (parent.right == node)
//            parent.right = temp;
//    }

//    public static void deleteValue(TreeNode root, int val) {
//        if (root == null) return;;
//        if (val < root.val)
//
//
//    }

    public static TreeNode deleteNode(TreeNode root, TreeNode node) {
        if (node == null) return null;

        TreeNode parent = findParentNode(root, node); // parent node for node to be deleted

        if (parent == null) { // parent is root
            if (node.left == null) {
                root = root.right;
                return root;
            }
            TreeNode temp = node.left;;  // node for replace
            TreeNode tempparent = null;   // parent node of node for replace
            //left subtree is not empty, find replace node
            while (temp.right != null) {
                tempparent = temp;
                temp = temp.right;
            }
            if (tempparent == null) { // node.left has no right subtree
                temp.right = root.right;
                root = temp;
            } else {
                temp.right = root.right;
                tempparent.right = temp.left;
                temp.left = root.left;
                root = temp;
            }
            return  root;
        }


        // when code reaches here, node to be deleted is not root
        // left subtree is empty, replace with right
        if (node.left == null) {
            if (parent.left == node)
                parent.left = node.right;
            else
                parent.right = node.right;
            return  root;
        }

        // left subtree not empty
        TreeNode temp = node.left;;  // store node for replace
        TreeNode tempparent = null;   // parent node of node for replace
        //left subtree is not empty, find replace node
        while (temp.right != null) {
            tempparent = temp;
            temp = temp.right;
        }

        if (tempparent == null) {// node.left has no right subtree
            if (parent.left == node) {
                temp.right = node.right;
                parent.left = temp;
                return  root;
            }
            if (parent.right == node) {
                temp.right = node.right;
                parent.right = temp;
                return  root;
            }
        }

        temp.right = node.right;
        tempparent.right = temp.left;
        temp.left = node.left;
        if (parent.left == node)
            parent.left = temp;
        if (parent.right == node)
            parent.right = temp;
        return  root;
    }


    public static void main(String[] args) {
        // build a tree from given string
        TreeNode root = strToTree("4 1 0 # # 2 # 3 # # 5");
        System.out.println("Original:" + root);

//        String res21 = listToStr(inorderIterative(root));
//
//        List<Integer> l22 = new ArrayList<>();
//        inorderRecursive(root, l22);
//        String res22 = listToStr(l22);
//        System.out.println("inorder 012345:" + res22);

        // add node
        addNode(root, 6);
        System.out.println("4102356: " + root);

        // delete node
        root = deleteNode(root, root.left);
        System.out.println("402356: " + root);

//        String res21 = listToStr(inorderIterative(root));
//
//        List<Integer> l22 = new ArrayList<>();
//        inorderRecursive(root, l22);
//        String res22 = listToStr(l22);
//        System.out.println("inorder:" + res22);

        root = deleteNode(root, root.right);
        System.out.println("40236: " + root);
//
//        String res21 = listToStr(inorderIterative(root));
//
//        List<Integer> l22 = new ArrayList<>();
//        inorderRecursive(root, l22);
//        String res22 = listToStr(l22);
//        System.out.println("inorder:" + res22);


        root = deleteNode(root, root);
        System.out.println("3026: " + root);

        List<Integer> l22 = new ArrayList<>();
        inorderRecursive(root, l22);
        String res22 = listToStr(l22);
        System.out.println("inorder:" + res22);
    } // main

}
