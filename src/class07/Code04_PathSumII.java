package class07;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * https://leetcode.com/problems/path-sum-ii
 **/
public class Code04_PathSumII {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        ArrayList<Integer> path = new ArrayList<>();
        process(root, path, 0, sum, ans);
        return ans;
    }

    /**
     * @param path   前面的路径
     * @param preSum 前面的路径和
     * @param sum    目标路径和
     * @param ans    所有符合条件的路径集合
     **/
    public static void process(TreeNode x, List<Integer> path, int preSum, int sum, List<List<Integer>> ans) {
        // x是叶节点
        if (x.left == null && x.right == null) {
            if (preSum + x.val == sum) {
                //题目关注的是所有的叶节点，如果不是叶节点，直接略过，直到叶节点并且遇到凑出目标路径和，将全局变量改成true
                path.add(x.val);
                //拷贝这次的路径，进结果集
                ans.add(copy(path));
                //把当前的叶节点清理掉，因为即将返回走右树
                path.remove(path.size() - 1);
            }
            return;
        }
        // x为非叶节点
        //把自己加进路径
        path.add(x.val);
        //把自己的值加进preSum
        preSum += x.val;
        if (x.left != null) {
            process(x.left, path, preSum, sum, ans);
        }
        if (x.right != null) {
            process(x.right, path, preSum, sum, ans);
        }
        //把当前的叶节点清理掉
        path.remove(path.size() - 1);
    }

    public static List<Integer> copy(List<Integer> path) {
        List<Integer> ans = new ArrayList<>();
        for (Integer num : path) {
            ans.add(num);
        }
        return ans;
    }

}
