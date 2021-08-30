package class07;

/**
 * 判断是否为平衡二叉树
 * https://leetcode.com/problems/balanced-binary-tree
 **/
public class Code02_BalancedBinaryTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static boolean isBalanced(TreeNode root) {
        return process(root).isBalanced;
    }

    /**
     * 以某节点为头的树
     * 是否平衡，整棵树的高度
     **/
    public static class Info {
        public boolean isBalanced;
        public int height;

        public Info(boolean i, int h) {
            isBalanced = i;
            height = h;
        }
    }

    /**
     * 递归
     **/
    public static Info process(TreeNode root) {
        //空树是平衡
        if (root == null) {
            return new Info(true, 0);
        }
        //左树递归，返回左树高度、是否平衡
        Info leftInfo = process(root.left);
        //右树递归，返回左树高度、是否平衡
        Info rightInfo = process(root.right);
        //左右树高度+1就是以root为根节点树的高度
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        //左树平衡 && 右树平衡 && 左右两棵树高度差不超过1，整棵树就是平衡树
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced
                && Math.abs(leftInfo.height - rightInfo.height) < 2;
        //返回root节点的高度、是否平衡
        return new Info(isBalanced, height);
    }

}
