package cn.tuhao.linklist;

import cn.tuhao.common.ListNode;

import java.util.ArrayList;

public class EntryNodeOfLoopTest {
    /**
     * 描述
     * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
     *
     * 输入描述：
     * 输入分为2段，第一段是入环前的链表部分，第二段是链表环的部分，后台将这2个会组装成一个有环或者无环单链表
     *
     * 返回值描述：
     * 返回链表的环的入口结点即可。而我们后台程序会打印这个节点
     */

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(three);
        ListNode listNode = EntryNodeOfLoop(one);
        System.out.println("-------------------");
    }
    public static ListNode EntryNodeOfLoop(ListNode pHead) {
        if (null == pHead) {
            return null;
        }
       // 两个指针：x1,x2
       ListNode fast = pHead;
       ListNode slow = pHead;
       while (null != fast) {
            fast = null == fast.next ? null : fast.next.next;
            slow = slow.next;
            if (null == fast || null == fast.next) {
                return null;
            }
           if (fast.val == slow.val) {
               break;
           }
       }

       fast = pHead;
       while (true) {
           if (fast.val == slow.val) {
               return fast;
           } else {
               fast = fast.next;
               slow = slow.next;
           }
       }
    }
}
