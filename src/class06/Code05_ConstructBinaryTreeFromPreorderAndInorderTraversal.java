package class06;

import java.util.HashMap;

/**
 * 根据先序结果和中序结果还原一棵树，返回root节点
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 **/
public class Code05_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 有一棵树，先序结果是pre[L1...R1]，中序结果是in[L2...R2]，无重复元素
     * (L R代表下标位置)
     * 请建出整棵树返回头节点
     * 头结点必然是先序遍历结果 pre 数组的 L1 位置，pre[L1]
     * 复杂度 O(n)
     **/
    public static TreeNode buildTree1(int[] pre, int[] in) {
        //非空 || 中序长度不等于先序长度
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        return f(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public static TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2) {
        //校验数组有效范围
        //左树和右树为空的情况
        if (L1 > R1) {
            return null;
        }
        //头结点，是先序遍历的第一个节点
        TreeNode head = new TreeNode(pre[L1]);
        //两种遍历结果只有一个数，直接生成节点返回
        if (L1 == R1) {
            return head;
        }
        //遍历中序结果in[]，寻找头结点，从L2的位置找起
        int find = L2;
        //直到找到头结点pre[L1]，找到之后，中序遍历的左右规模就确定了
        while (in[find] != pre[L1]) {
            find++;
        }
        //构建左树
        //中序遍历结果的左树范围： L2 ~ find-1
        //先序遍历结果的左树范围： L1+1 ~ L1+find-L2
        head.left = f(pre, L1 + 1, L1 + find - L2, in, L2, find - 1);
        //构建右树
        //中序遍历结果的右树范围： find+1 ~ R2
        //先序遍历结果的右树范围： L1+find-L2+1 ~ R1
        head.right = f(pre, L1 + find - L2 + 1, R1, in, find + 1, R2);
        return head;
    }

    /**
     * 优化版
     * 统计中序遍历的结果和下标，后面查询用，不需要再遍历in[]寻找头结点
     **/
    public static TreeNode buildTree2(int[] pre, int[] in) {
        //非空 || 中序长度不等于先序长度
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        //统计中序遍历的结果和下标，后面查询用，不需要再遍历in[]寻找头结点了
        HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            valueIndexMap.put(in[i], i);
        }
        return g(pre, 0, pre.length - 1, in, 0, in.length - 1, valueIndexMap);
    }

    public static TreeNode g(int[] pre, int L1, int R1, int[] in, int L2, int R2, HashMap<Integer, Integer> valueIndexMap) {
        if (L1 > R1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[L1]);
        if (L1 == R1) {
            return head;
        }
        //不需要再遍历in[]寻找头结点了，直接从哈希表里把头结点查出来，空间换时间
        int find = valueIndexMap.get(pre[L1]);
        head.left = g(pre, L1 + 1, L1 + find - L2, in, L2, find - 1, valueIndexMap);
        head.right = g(pre, L1 + find - L2 + 1, R1, in, find + 1, R2, valueIndexMap);
        return head;
    }

}
