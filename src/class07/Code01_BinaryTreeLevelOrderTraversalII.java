package class07;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，返回其节点值自底向上的层序遍历
 * 测试链接：https://leetcode.com/problems/binary-tree-level-order-traversal-ii
 **/
public class Code01_BinaryTreeLevelOrderTraversalII {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        //定义一个队列存下每层的所有节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            //收集每层有几个节点，即收集节点的次数
            int size = queue.size();
            List<Integer> curAns = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                //当前节点出队的同时，子节点入队
                TreeNode curNode = queue.poll();
                curAns.add(curNode.val);
                //节点有左先加左
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                //节点有右先再加右
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
            //倒序记录结果
            ans.add(0, curAns);
        }
        return ans;
    }

}
