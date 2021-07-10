package cn.tuhao.linklist;

import cn.tuhao.common.ListNode;

import java.util.ArrayList;

/**
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

    public ListNode merge(ArrayList<ListNode> lists){
        // 左右相等说明不能再分
        if (lists.isEmpty()) {
            return null;
        }
        ListNode head = new ListNode(Integer.MIN_VALUE);
        for (ListNode list : lists) {
            head = mergeTwoList(head, list);
        }
        return head.next;
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
        tmp.next = node1!=null?node1:node2;
        return node.next;
    }
}