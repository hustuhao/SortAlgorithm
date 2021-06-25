package cn.tuhao.linklist;

import cn.tuhao.common.ListNode;

public class DeleteDuplication {
    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode doubleTwo = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.setNext(two);
        two.setNext(doubleTwo);
        doubleTwo.setNext(three);
        three.setNext(four);
        four.setNext(five);
        ListNode listNode = deleteDuplication(one);
        System.out.println("-----------------");
    }

    /**
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
     * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
     * @param pHead
     * @return
     */
    public static ListNode deleteDuplication(ListNode pHead) {
        // 0、边界条件校验
        if (null == pHead || null == pHead.next) {
            return pHead;
        }
        ListNode head = new ListNode(0);
        head.next = pHead;
        ListNode curNode = head.next;
        // pre 给个默认值
        ListNode preNode = head;
        // 遍历链表
        while (null != curNode) {
            // 找到值相同的节点
            if (null != curNode.next && curNode.val == curNode.next.val) {
                // 往后找相同值的节点
                while (null != curNode.next && curNode.next.val == curNode.val) {
                    curNode = curNode.next;
                }
                preNode.next = curNode.next;
                curNode = curNode.next;
            } else {
                preNode = curNode;
                curNode = curNode.next;
            }
        }
        return head.next;
    }
}
