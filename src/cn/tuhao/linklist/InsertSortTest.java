package cn.tuhao.linklist;

import cn.tuhao.common.ListNode;

/**
 * 链表的插入排序实现原理很简单，就是一个元素一个元素的从原链表中取出来，然后按顺序插入到新链表中，时间复杂度为O(n2)，是一种效率并不是很高的算法，但是空间复杂度为O(1)，以高时间复杂度换取了低空间复杂度。代码如下：
 *
 * 哑结点 = 哨兵
 *
 * 哑节点（dummy Node）是一个被人为创建的节点，虽然其内容为NULL，但是它在堆中有占有一定的空间。 哑节点的使用可以避免边界问题的处理，达到简化代码与减少代码出错可能性的目的。
 */
public class InsertSortTest {
    public ListNode insertionSortList(ListNode head) {
        //哑节点
        ListNode dumy = new ListNode(Integer.MIN_VALUE);
        ListNode cur = head;
        ListNode pre = dumy;
        while (cur != null) {
            //保存当前节点下一个节点
            ListNode next = cur.next;
            pre = dumy;
            //寻找当前节点正确位置的一个节点
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            //将当前节点加入新链表中
            cur.next = pre.next;
            pre.next = cur;
            //处理下一个节点
            cur = next;
        }
        return dumy.next;
    }
}
