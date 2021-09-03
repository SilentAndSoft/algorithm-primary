package class07;

/**
 * 判断是否为搜索二叉树
 * 当前节点的左节点小于当前节点，当前节点的右节点大于当前节点
 **/
public class Code05_IsBinarySearchTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 以某节点为头的树
     **/
    public static class Info {
        //是否为BST
        public boolean isBST;
        //整棵树的最大值（其实只用左树的最大值）
        public int max;
        //整棵树的最小值（其实只用右树的最小值）
        public int min;

        public Info(boolean is, int ma, int mi) {
            isBST = is;
            max = ma;
            min = mi;
        }
    }

    /**
     * 解法1
     **/
    public static Info process1(TreeNode root) {
        //
        if (root == null) {
            return null;
        }
        //左树的信息
        Info leftInfo = process1(root.left);
        //右树的信息
        Info rightInfo = process1(root.right);

        int max = root.val;
        int min = root.val;
        if (leftInfo != null) {
            //收集左树的最值
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            //收集右树的最值
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }
        boolean isBST = true;
        //收集左树是否是BST
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        //收集右树是否是BST
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        //左树最大值是否小于root
        boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < root.val);
        //右树最小值是否大于root
        boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > root.val);
        //左树最大值大于root || 右树最小值小于root，说明不是BST
        if (!(leftMaxLessX && rightMinMoreX)) {
            isBST = false;
        }
        return new Info(isBST, max, min);
    }

    public static Info process2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info leftInfo = process2(root.left);
        Info rightInfo = process2(root.right);
        int max = root.val;
        int min = root.val;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }
        boolean isBST = false;
        boolean leftIsBst = leftInfo == null ? true : leftInfo.isBST;
        boolean rightIsBst = rightInfo == null ? true : rightInfo.isBST;
        boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < root.val);
        boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > root.val);
        //同时满足左树是BST、右树是BST、左树最大值小于root、右树最小值大于root
        if (leftIsBst && rightIsBst && leftMaxLessX && rightMinMoreX) {
            isBST = true;
        }
        return new Info(isBST, max, min);
    }
}
