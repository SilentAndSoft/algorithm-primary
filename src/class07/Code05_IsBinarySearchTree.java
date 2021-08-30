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
    public static Info process1(TreeNode x) {
        //
        if (x == null) {
            return null;
        }
        //左树的信息
        Info leftInfo = process1(x.left);
        //右树的信息
        Info rightInfo = process1(x.right);

        int max = x.val;
        int min = x.val;
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

        //左树为空 || 左树的值与root的值比较
        boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
        //右树为空 || 右树的值与root的值比较
        boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > x.val);
        //非BST的条件（左树大于root || 右树小于root）
        if (!(leftMaxLessX && rightMinMoreX)) {
            isBST = false;
        }
        return new Info(isBST, max, min);
    }

    public static Info process2(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process2(x.left);
        Info rightInfo = process2(x.right);
        int max = x.val;
        int min = x.val;
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
        boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
        boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > x.val);
        if (leftIsBst && rightIsBst && leftMaxLessX && rightMinMoreX) {
            isBST = true;
        }
        return new Info(isBST, max, min);
    }

}
