package class07;

/**
 * 判断是否存在路径总和
 * 测试链接：https://leetcode-cn.com/problems/path-sum
 **/
public class Code03_PathSum {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    //全局变量，表示是否存在路径和
    public static boolean isSum = false;

    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        isSum = false;
        process(root, 0, sum);
        return isSum;
    }

    /**
     * @param preSum 当前节点前面的路径和
     * @param sum    目标路径和
     **/
    public static void process(TreeNode x, int preSum, int sum) {
        // x是叶节点
        if (x.left == null && x.right == null) {
            //题目关注的是所有的叶节点，如果不是叶节点，直接略过，直到叶节点并且遇到凑出目标路径和，将全局变量改成true
            if (x.val + preSum == sum) {
                isSum = true;
            }
            return;
        }
        // 累加非叶节点的值
        preSum += x.val;
        if (x.left != null) {
            process(x.left, preSum, sum);
        }
        if (x.right != null) {
            process(x.right, preSum, sum);
        }
    }

//	public static boolean hasPathSum(TreeNode root, int sum) {
//		if (root == null) {
//			return false;
//		}
//		return process(root, sum);
//	}
//
//	public static boolean process(TreeNode root, int rest) {
//		if (root.left == null && root.right == null) {
//			return root.val == rest;
//		}
//		boolean ans = root.left != null ? process(root.left, rest - root.val) : false;
//		ans |= root.right != null ? process(root.right, rest - root.val) : false;
//		return ans;
//	}

}
