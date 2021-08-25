package class04;

// 测试链接：https://leetcode.com/problems/merge-two-sorted-lists
public class Code06_MergeTwoSortedLinkedList {

	// 不要提交这个类
	public static class ListNode {
		public int val;
		public ListNode next;
	}

	public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
		if (head1 == null || head2 == null) {
			return head1 == null ? head2 : head1;
		}
		//两个链表的小头，作为结果最终的头
		ListNode head = head1.val <= head2.val ? head1 : head2;
		//小头的下一个
		ListNode cur1 = head.next;
		//大头
		ListNode cur2 = head == head1 ? head2 : head1;
		//pre指向小头，负责串起新链表
		ListNode pre = head;
		while (cur1 != null && cur2 != null) {
			//pre指向小的，小的往下走
			if (cur1.val <= cur2.val) {
				pre.next = cur1;
				cur1 = cur1.next;
			} else {
				pre.next = cur2;
				cur2 = cur2.next;
			}
			pre = pre.next;
		}
		//谁后面不为空就让pre串起，不需要遍历了
		pre.next = cur1 != null ? cur1 : cur2;
		return head;
	}

}
