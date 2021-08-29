package class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并k个升序链表
 * https://leetcode.com/problems/merge-k-sorted-lists/
 **/
public class Code01_MergeKSortedLists {

    //单链表
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    //比较器
    public static class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }

    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        //按节点值排序的优先级队列（小根堆），时间复杂度为logN
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
        //把链表扔进优先级队列
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }
        if (heap.isEmpty()) {
            return null;
        }
        //结果的头部
        ListNode head = heap.poll();
        ListNode pre = head;
        if (pre.next != null) {
            heap.add(pre.next);
        }
        while (!heap.isEmpty()) {
            //每弹出一个，挂到前一个的next上
            ListNode cur = heap.poll();
            pre.next = cur;
            pre = cur;
            //当前节点从堆弹出，next节点进去
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }
        return head;
    }
}
