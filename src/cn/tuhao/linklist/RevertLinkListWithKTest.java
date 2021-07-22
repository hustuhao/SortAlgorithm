package cn.tuhao.linklist;

import cn.tuhao.common.ListNode;
import org.junit.Test;

import java.util.LinkedList;

/**
 *     作者：LeetCode-Solution
 *     链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/k-ge-yi-zu-fan-zhuan-lian-biao-by-leetcode-solutio/
 *     来源：力扣（LeetCode）
 *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 *
 * 将给出的链表中的节点每\ k k 个一组翻转，返回翻转后的链表
 * 如果链表中的节点数不是\ k k 的倍数，将最后剩下的节点保持原样
 * 你不能更改节点中的值，只能更改节点本身。
 * 要求空间复杂度 \ O(1) O(1)
 * 例如：
 * 给定的链表是1\to2\to3\to4\to51→2→3→4→5
 * 对于 \ k = 2 k=2, 你应该返回 2\to 1\to 4\to 3\to 52→1→4→3→5
 * 对于 \ k = 3 k=3, 你应该返回 3\to2 \to1 \to 4\to 53→2→1→4→5
 *
 * 示例1
 * 输入
 * {1,2,3,4,5},2
 * 输出
 * {2,1,4,3,5}
 *
 *
 * 方法：
 * 1、栈
 * 2、分组翻转
 */
public class RevertLinkListWithKTest {

    /**
     * 分组翻转
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dumpNode = new ListNode(0);
        dumpNode.next = head;
        ListNode pre = dumpNode;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return dumpNode.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return dumpNode.next;
    }

    public ListNode[] myReverse(ListNode head, ListNode tail) {
        // 下一个节点
        ListNode prev = tail.next;
        // 当前节点
        ListNode p = head;
        while (prev != tail) {
            // 翻转
            p.next = prev;
            // 移动指针
            prev = p;
            p =  p.next;
        }
        return new ListNode[]{tail, head};
    }

    public ListNode reverseKGroupTwo(ListNode head, int k) {
        // 特殊情况判断
        if (null == head) {
            return null;
        }

        LinkedList<ListNode> stack = new LinkedList<>();
        ListNode p = head;
        ListNode dumpNode = new ListNode(0);
        ListNode pre = dumpNode;
        while (null != p) {
            // 当栈未满的时候：
            // 1 2 3 4 5     2 1 3 4 5
            while (null != p && stack.size() < k) {
                stack.push(p);
                p = p.next;
            }
            // 栈满了：翻转
            if (stack.size() == k) {
                while (!stack.isEmpty()) {
                    ListNode cur = stack.pop();
                    pre.next = cur;
                    pre = cur;
                    cur.next = null;
                }
            } else {
                pre.next = stack.peekLast();
            }
        }
        return dumpNode.next;
    }

    @Test
    public void test() {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        RevertLinkListWithKTest revertLinkListWithKTest = new RevertLinkListWithKTest();
        ListNode listNode = revertLinkListWithKTest.reverseKGroup(one, 2);
    }
}
