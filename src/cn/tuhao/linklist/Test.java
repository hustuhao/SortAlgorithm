package cn.tuhao.linklist;

import cn.tuhao.common.ListNode;

public class Test {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        Test test = new Test();
        ListNode listNode = test.ReverseList(node1);
    }

    public ListNode ReverseList(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode preNode = null;
        ListNode curNode = head;
        ListNode nextNode;
        while (null != curNode) {
            // 1|反转
            nextNode = curNode.next;
            curNode.next = preNode;
            // 2|指针移动
            preNode = curNode;
            curNode = nextNode;
        }
        return preNode;
    }
}
