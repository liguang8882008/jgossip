package net.lvsq.jgossip.core.tree;

public class TreeNode {

    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;


    private int data;

    public TreeNode(TreeNode parent, TreeNode left, TreeNode right, int data) {
        super();
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public TreeNode(TreeNode left, TreeNode right, int data) {
        super();
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

}
