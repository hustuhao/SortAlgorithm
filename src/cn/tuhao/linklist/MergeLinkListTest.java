package cn.tuhao.linklist;

import cn.tuhao.common.ListNode;

/**
 * JZ16 合并两个排序的链表
 */
public class MergeLinkListTest {
    public static void main(String[] args) {
        ListNode one = new ListNode(2);
        ListNode three = new ListNode(4);
        one.setNext(three);

        ListNode two = new ListNode(1);
        ListNode four = new ListNode(6);
        two.setNext(four);
        ListNode merge = Merge(one, two);
        System.out.println("-----------------------");
    }

    public static ListNode Merge(ListNode list1,ListNode list2) {
        // 1|考虑特殊情况
        if (null == list1) {
            return list2;
        }
        if (null == list2) {
            return list1;
        }
        // 2|哨兵节点，方便处理边界条件
        ListNode head = new ListNode(Integer.MIN_VALUE);
        // 3|定义三个指针，均指向头节点
        ListNode p = head;
        ListNode p1 = list1;
        ListNode p2 = list2;
        // 4|合并链表
        while (p1 != null && p2 != null) {
            // 找出较小值的节点，将其拼到新的链表上
            if (p1.val <= p2.val) {
                p.next = new ListNode(p1.val);;
                p1 = p1.next;
            } else {
                p.next = new ListNode(p2.val);;
                p2 = p2.next;
            }
            p = p.next;
        }
        // 如果p1不为空，则p2为空，将p1后面的链表节点追加到新的链表上
        while (null !=p1) {
            p.next = new ListNode(p1.val);
            p = p.next;
            p1 = p1.next;
        }
        // 如果p2不为空，则p1为空，将p2后面的链表节点追加到新的链表上
        while (null !=p2) {
            p.next = new ListNode(p2.val);
            p = p.next;
            p2 = p2.next;
        }
        return head.next;
    }
}
