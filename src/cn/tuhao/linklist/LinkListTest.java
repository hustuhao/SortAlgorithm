package cn.tuhao.linklist;

import cn.tuhao.common.ListNode;

/**
 * 反转链表
 */
public class LinkListTest {

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        ListNode revertNodeHead = getRevertNodeHead(one);
        System.out.println("-----------------------");
    }

    public static ListNode getRevertNodeHead (ListNode originHead) {
        if (null == originHead || null == originHead.next) {
            return originHead;
        }

        // 三个指针记录
        ListNode preNode = null;
        ListNode curNode = originHead;
        ListNode nextNode = originHead.next;
        while (null != curNode) {
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
            nextNode = null == nextNode ? null : nextNode.next;
        }
        return preNode;
    }
}
