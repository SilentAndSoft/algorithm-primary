package class04;

// 测试链接：https://leetcode.com/problems/add-two-numbers/
public class Code05_AddTwoNumbers {

	// 不要提交这个类
	public static class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int val) {
			this.val = val;
		}

		public ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
		int len1 = listLength(head1);
		int len2 = listLength(head2);
		//长链表的head就是结果的head
		ListNode l = len1 >= len2 ? head1 : head2;
		ListNode s = l == head1 ? head2 : head1;
		ListNode curL = l;
		ListNode curS = s;
		ListNode last = curL;
		//进位
		int carry = 0;
		//紧跟长链表的最后一个节点，最后判断需不需要进位
		int curNum = 0;
		//第一阶段，长短链表都在
		while (curS != null) {
			curNum = curL.val + curS.val + carry;
			curL.val = (curNum % 10);
			carry = curNum / 10;
			last = curL;
			curL = curL.next;
			curS = curS.next;
		}
		//第二阶段，短链表结束，长链表还在
		while (curL != null) {
			curNum = curL.val + carry;
			curL.val = (curNum % 10);
			carry = curNum / 10;
			last = curL;
			curL = curL.next;
		}
		//长短链表都结束，是否进位
		if (carry != 0) {
			last.next = new ListNode(1);
		}
		return l;
	}

	// 求链表长度
	public static int listLength(ListNode head) {
		int len = 0;
		while (head != null) {
			len++;
			head = head.next;
		}
		return len;
	}

}
