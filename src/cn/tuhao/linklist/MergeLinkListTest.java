package cn.tuhao.linklist;

import cn.tuhao.common.ListNode;

import java.util.ArrayList;

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

    //JZ16 合并两个排序的链表
    public static ListNode Merge(ListNode list1, ListNode list2) {
        // 0、特殊情况校验
        if (null == list1) {
            return list2;
        }
        if (null == list1) {
            return list2;
        }
        // 1、以一条链表为基准插入另外一条链表
        ListNode pOne = list1;
        ListNode pTwo = list2;
        ListNode curNode = new ListNode(Math.min(list1.val, list2.val));
        ListNode newHead = curNode;
        while (null != pOne && null != pTwo) {
            ListNode temp = null;
            if (pOne.val > pTwo.val) {
                temp = new ListNode(pTwo.val);
                pTwo = pTwo.next;
            } else {
                temp = new ListNode(pOne.val);
                pOne = pOne.next;
            }
            curNode.setNext(temp);
            curNode = temp;
        }
        if (null == pOne) {
            curNode.setNext(pTwo);
        } else {
            curNode.setNext(pOne);
        }
        return newHead.next;
    }
}
