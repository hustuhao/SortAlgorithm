package cn.tuhao.sort.test;

import cn.tuhao.common.ListNode;

/**
 * 提供可供排序的数组和链表，让其他的test类继承
 */
public class BaseSortTest {
    public int array[] = new int[]{6, 4, 3, 7, 5, 1, 2};
    public ListNode head = ListNode.generateList(array);
}
