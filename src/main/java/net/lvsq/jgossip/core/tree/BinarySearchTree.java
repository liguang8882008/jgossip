package net.lvsq.jgossip.core.tree;

import java.util.Stack;

public class BinarySearchTree {

    private TreeNode root;

    public BinarySearchTree() {}

    public TreeNode getRoot() {
        return root;
    }

    public void insertNode(int data) {
        TreeNode node = new TreeNode(null, null, data);
        insertNode(node);
    }

    public void insertNode(TreeNode node) {
        if (node == null) {
            return;
        }

        if (this.root == null) {
            root = node;
            return;
        }

        TreeNode curNode = root;
        while (true) {
            if (node.getData() < curNode.getData()) {
                if (curNode.getLeft() != null) {
                    curNode = curNode.getLeft();
                    continue;
                } else {
                    curNode.setLeft(node);
                    node.setParent(curNode);
                    break;
                }
            } else if (node.getData() > curNode.getData()) {
                if (curNode.getRight() != null) {
                    curNode = curNode.getRight();
                    continue;
                } else {
                    curNode.setRight(node);
                    node.setParent(curNode);
                    break;
                }
            } else {
                // 结点值相等，直接返回
                return;
            }
        }
    }

    public boolean deleteNode(int data) {
        TreeNode node = findByKey(data);
        if (node == null) {
            return false;
        }

        return this.deleteNode(node);
    }

    public boolean deleteNode(TreeNode node) {
        TreeNode tNode;
        if (node.getLeft() == null && node.getRight() == null) {
            tNode = null;
        } else if (node.getLeft() == null || node.getRight() == null) {
            tNode = (node.getLeft() == null) ? node.getRight() : node.getLeft();
            tNode.setParent(node.getParent());
        } else {
            tNode = this.findPredecessor(node);
            TreeNode successorRight;
            if (tNode.getRight() == null) {
                successorRight = null;
            } else {
                successorRight = tNode.getRight();
                successorRight.setParent(tNode.getParent());
            }
            if (tNode.getParent().getLeft() == tNode) {
                tNode.getParent().setLeft(successorRight);
            } else {
                tNode.getParent().setRight(successorRight);
            }
            node.setData(tNode.getData());

            return true;
        }

        if (node.getParent() != null) {
            if (node.getParent().getLeft() == node) {
                node.getParent().setLeft(tNode);
            } else {
                node.getParent().setRight(tNode);
            }
        } else {
            this.root = tNode;
        }

        return true;
    }

    public TreeNode findByKey(int data) {
        TreeNode tNode = root;
        while (tNode != null) {
            if (tNode.getData() == data) {
                return tNode;
            }
            tNode = tNode.getData() > data ? tNode.getLeft() : tNode.getRight();
        }

        return null;
    }

    /**
     * 节点x的前驱，就是指key值小于x.key的节点中key值最大的那个，若x的左子树不为空， 则x前驱是x节点左子树里最靠右的那个节点，如果x的左子树为空，
     * 那么我们就要向上找x的第一个有右孩子且左子树里没有x节点的祖先。 （此时x就相当于这个祖先的后继，结合后继的查找方式来理解第二种情况会比较简单，看看图中两个节点的位置关系会很有助于理解
     * 
     * @param node
     * @return
     */
    public TreeNode findPredecessor(TreeNode node) {
        // 若x的左子树不为空，则x前驱是x节点左子树里最靠右的那个节点
        if (node == null) {
            return null;
        }

        if (node.getLeft() != null) {
            node = node.getLeft();
            while (node.getRight() != null) {
                node = node.getRight();
            }

            return node;
        } else {
            // 如果x的左子树为空，那么我们就要向上找x的第一个有右孩子且左子树里没有x节点的祖先
            while (node.getParent() != null && node.getParent().getLeft() != node) {
                node = node.getParent();
            }

            return node.getParent();
        }
    }

    public TreeNode findPredecessor(int data) {
        TreeNode node = findByKey(data);

        return findPredecessor(node);
    }

    public TreeNode findSuccessor(TreeNode node) {
        if (node == null) {
            return null;
        }

        if (node.getRight() != null) {
            node = node.getRight();
            while (node.getLeft() != null) {
                node = node.getLeft();
            }

            return node;
        } else {
            while (node.getParent() != null && node.getParent().getRight() != node) {
                node = node.getParent();
            }

            return node.getParent();
        }
    }

    public TreeNode findSuccessor(int data) {
        TreeNode node = findByKey(data);

        return findSuccessor(node);
    }

    public void Traversal(TreeNode node) {
        if (node == null) {
            node = this.root;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(node);
        while (!stack.empty()) {
            TreeNode curNode = stack.pop();
            System.out.println(curNode.getData());

            if (curNode.getRight() != null) {
                stack.push(curNode.getRight());
            }
            if (curNode.getLeft() != null) {
                stack.push(curNode.getRight());
            }
        }
    }

  
}
