package class06;

/**
 * 返回一棵树的最大高度
 * 测试链接：https://leetcode.com/problems/maximum-depth-of-binary-tree
 **/
public class Code04_MaximumDepthOfBinaryTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    // 以root为头的树，最大高度是多少返回！
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //左树高度和右数高度最大的 + 自己
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
