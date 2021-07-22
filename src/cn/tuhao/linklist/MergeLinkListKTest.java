package cn.tuhao.linklist;

import cn.tuhao.common.ListNode;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
 * leetcode：合并K个排序链表
 * 合并K个有序的链表：从小到大
 * 考点：合并链表，分治思想，优先队列
 * 三种方法：
 * 1、循环合并链表
 * 2、分治合并链表
 * 3、最小堆
 */
public class MergeLinkListKTest {
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        return merge(lists,0,lists.size()-1);
    }

    /**
     * 方法一：循环合并链表
     * @param lists
     * @return
     */
    public ListNode mergeKListsOne (ArrayList<ListNode> lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.size(); ++i) {
            ans = mergeTwoList(ans, lists.get(i));
        }
        return ans;
    }
    /**
     * 方法二：分治合并链表
     * @param lists
     * @param left
     * @param right
     * @return
     */
    public ListNode merge(ArrayList<ListNode> lists, int left, int right){
        // 左右相等说明不能再分
        if(left == right)
            return lists.get(left);
        if(left > right) {
            return null;
        }
        int mid = left + (right-left)/2;
        ListNode list1 = merge(lists, left, mid);
        ListNode list2 = merge(lists,mid+1, right);
        return mergeTwoList(list1, list2);
    }


    /**
     *合并两个有序链表
     */
    public ListNode mergeTwoList(ListNode node1, ListNode node2){
        // 哨兵
        ListNode node = new ListNode(-1);
        ListNode tmp = node;
        while(node1!=null && node2!=null){
            if(node1.val <= node2.val){
                tmp.next = node1;
                node1 = node1.next;
            }else{
                tmp.next = node2;
                node2 = node2.next;
            }
            tmp = tmp.next;
        }
        tmp.next = node1!= null ? node1 : node2;
        return node.next;
    }

    /**************** 最小堆 ****************/

    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    PriorityQueue<Status> queue = new PriorityQueue<Status>();

    public ListNode mergeKLists(ListNode[] lists) {
        for (ListNode node: lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }
}