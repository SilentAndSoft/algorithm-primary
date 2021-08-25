package class06;

/**
 * 判断两棵树是否结构相同
 * 测试链接：https://leetcode.com/problems/same-tree
 **/
public class Code02_SameTree {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	public static boolean isSameTree(TreeNode p, TreeNode q) {
		//p和q一个为空，一个不为空
		if (p == null ^ q == null) {
			return false;
		}
		//p和q都为空
		if (p == null && q == null) {
			return true;
		}
		//p和q都不为空
		//头结点相等 && 左树结构相同 && 右数结构相同
		return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

}
