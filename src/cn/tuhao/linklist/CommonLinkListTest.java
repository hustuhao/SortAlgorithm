package cn.tuhao.linklist;

import cn.tuhao.common.ListNode;

public class CommonLinkListTest {
    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode six = new ListNode(6);
        ListNode seven = new ListNode(7);
        one.next = two;
        two.next = three;
        three.next = six;
        six.next = seven;

        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        four.next = five;
        five.next = six;

        ListNode merge = FindFirstCommonNode(one, four);
        System.out.println("-----------------------");
    }
    /*
    题目描述：输入两个无环的单链表，找出它们的第一个公共结点。
    链表1：1->2->3->6->7，长度为 X = x+z
    链表2：4->5->6->7，长度为 Y = y+z
    公共部分：6->7，长度为z

    方法一：
    x+z = y+z
    x+(y+z) = y+(x+z),x + Y = y + X，括号中的部分组成了一个链表的长度，两边相等说明长度加起来一致，
    如果用两边的长度表示路程，则等于说明两个指针相遇
     */
    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (null == pHead1 || null == pHead2) {
            return null;
        }
        ListNode pOne = pHead1;
        ListNode pTwo = pHead2;
        // 走一条链表
        while (null != pOne && null != pTwo) {
            if (pOne.val == pTwo.val) {
                return pOne;
            }
            pOne = pOne.next;
            pTwo = pTwo.next;
        }

        if (null == pTwo && null == pOne) {
            pTwo = pHead1;
            pOne = pHead2;
        } else if (null == pOne) {
            pOne = pHead2;
        } else {
            pTwo = pHead1;
        }
        // 走完第二条链表
        while (null != pOne && null != pTwo) {
            if (pOne.val == pTwo.val) {
                return pOne;
            }
            pOne = pOne.next;
            pTwo = pTwo.next;
        }

        if (null == pTwo && null == pOne) {
            pTwo = pHead1;
            pOne = pHead2;
        } else if (null == pOne) {
            pOne = pHead2;
        } else {
            pTwo = pHead1;
        }

        while (null != pOne && null != pTwo) {
            if (pOne.val == pTwo.val) {
                return pOne;
            }
            pOne = pOne.next;
            pTwo = pTwo.next;
        }
        return null;
    }

    public static ListNode FindFirstCommonNodeTwo(ListNode pHead1, ListNode pHead2) {
        if (null == pHead1 || null == pHead2) {
            return null;
        }
        ListNode pOne = pHead1;
        ListNode pTwo = pHead2;
        // 很重要：结束条件,有公共节点则走到公共节点，没有则均为null，走到两个链表的最后的节点的next指针
        while (pOne != pTwo) {
            pOne = null == pOne.next ? pHead2 : pOne.next;
            pTwo = null == pTwo.next ? pHead1 : pTwo.next;
        }
        return pOne;
    }
}
