package cn.tuhao.linklist;

import cn.tuhao.common.ListNode;

import java.util.LinkedList;

/**
 * JZ14 链表中倒数最后k个结点
 * 解法：
 * 1、双指针
 * 2、大小为 k 的栈
 */
public class FindKList {
    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        ListNode resultNode = findKthToTailTwo(one, 5);
        System.out.println("-----------------------");
    }
    public static ListNode findKthToHead(ListNode pHead, int k) {
        ListNode curNode = pHead;
        ListNode preNode = null;
        int count = 0;
        while (null != curNode && k != count) {
            preNode = curNode;
            curNode = curNode.next;
            count++;
        }
        return k == count ? (null == preNode ? new ListNode() : preNode) : new ListNode();
    }

    /**
     * 最优解法   双指针
     * k + (x - k) = y
     * 1、第一个指针先走K步，此时第二个指针开始走
     * 2、等到第一个指针走到终点，第二个指针的位置就是距离终点K步
     * @param pHead
     * @param k
     * @return
     */
    public ListNode FindKthToTail (ListNode pHead, int k) {
        ListNode first = pHead;
        for(int  i= 0;i<k;i++){
            if(first == null) return first;
            first = first.next;

        }
        ListNode last = pHead;
        while(first!=null){
            first = first.next;
            last = last.next;
        }
        return last;
    }

    /**
     * 暴力遍历
     * @param pHead
     * @param k
     * @return
     */
    public static ListNode findKthToTailOne(ListNode pHead, int k) {
        if (null == pHead) {
            return null;
        }
        if (k == 0) {
            return null;
        }
        ListNode curNode = pHead;
        int n = 0;
        while (null != curNode) {
            curNode = curNode.next;
            n++;
        }
        if (k > n) {
            return null;
        }
        ListNode anotherCurNode = pHead;
        int count = 0;
        // 1 4  2 4 3 4  4 4
        while (null != anotherCurNode && count != (n - k)) {
            anotherCurNode = anotherCurNode.next;
            count++;
        }
        return anotherCurNode;
    }

    /**
     * 双指针
     * @param pHead
     * @param k
     * @return
     */
    public static ListNode findKthToTailTwo(ListNode pHead, int k) {
        if (null == pHead) {
            return null;
        }
        if (k == 0) {
            return null;
        }
        ListNode pOne = pHead;
        ListNode pTwo = null;
        int count = 0;
        LinkedList<ListNode> listNodes = new LinkedList<>();
        while (null != pOne) {
            if (k + 1 >= count) {

                listNodes.push(pOne);
            }
            pOne = pOne.next;
            count ++;
            if (k + 1 == count) {
                pTwo = listNodes.getLast().next;
            } else if (null != pTwo) {
                pTwo = pTwo.next;
            }
        }

        if (null != pTwo) {
            return pTwo;
        }

        if (k == count) {
            return pHead;
        } else {
            return null;
        }
    }
}
