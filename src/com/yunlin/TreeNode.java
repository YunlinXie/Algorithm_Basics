package com.yunlin;

public class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof TreeNode)) {
            return false;
        }
        return equals(this.val, ((TreeNode) o).val)
                && equals(this.left, ((TreeNode) o).left)
                && equals(this.right, ((TreeNode) o).right);
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
