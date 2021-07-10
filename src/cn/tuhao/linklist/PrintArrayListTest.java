package cn.tuhao.linklist;

import cn.tuhao.common.ListNode;

import java.util.*;

/**
 * 逆序打印链表
 */
public class PrintArrayListTest {
    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        ArrayList<Integer> arrayList = printRevertNodeListTwo(one);
        System.out.println("-----------------------");
    }

    public static ArrayList<Integer> printRevertNodeList(ListNode head) {
        if (null == head) {
            return new ArrayList<>();
        }

        if (null == head.next) {
            return new ArrayList<>(Collections.singleton(head.val));
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        ListNode curNode = head;
        while (null != curNode) {
            arrayList.add(curNode.val);
            curNode = curNode.next;
        }
        ArrayList<Integer> resultList = new ArrayList<>(arrayList.size());
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            resultList.add(arrayList.get(i));
        }
        return resultList;
    }

    public static ArrayList<Integer> printRevertNodeListTwo(ListNode head) {
        if (null == head) {
            return new ArrayList<>();
        }

        if (null == head.next) {
            return new ArrayList<>(Collections.singleton(head.val));
        }
        LinkedList<Integer> linkedList = new LinkedList();
        ListNode curNode = head;
        while (null != curNode) {
            linkedList.add(curNode.val);
            curNode = curNode.next;
        }
        Iterator iterator = linkedList.descendingIterator();
        ArrayList<Integer> resultList = new ArrayList<>(linkedList.size());
        while (iterator.hasNext()) {
            resultList.add((Integer)iterator.next());
        }
        return resultList;
    }

    public static ArrayList<Integer> printRevertNodeListThree(ListNode head) {
        if (null == head) {
            return new ArrayList<>();
        }

        if (null == head.next) {
            return new ArrayList<>(Collections.singleton(head.val));
        }
        LinkedList<Integer> linkedList = new LinkedList();
        ListNode curNode = head;
        while (null != curNode) {
            linkedList.push(curNode.val);
            curNode = curNode.next;
        }
        return new ArrayList<>(linkedList);
    }
}
