package cn.tuhao.linklist;

import cn.tuhao.common.ListNode;
import cn.tuhao.common.RandomListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RandomListCopyTest {

    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) {
            return null;
        }
        // 定义指针
        RandomListNode pNode = head, copyHead = null, copyNode = null;
        // 1、映射表：存入原节点和新节点的映射关系
        Map<RandomListNode, RandomListNode> map = new HashMap<>();

        // 2、构建顺序链表：先遍历不带随机节点的链表
        while(pNode != null){
            RandomListNode node = new RandomListNode(pNode.label);
            node.next = null;
            node.random = null;

            //保存新链表头
            if(pNode == head){
                copyHead = copyNode = node;
            } else {
                copyNode.next = node;
                copyNode = copyNode.next;
            }
            //把原链表节点pNode和新链表节点copyNode放入map集合中
            map.put(pNode, copyNode);
            pNode = pNode.next;
        }
        //遍历map的同事，给新链表的节点拷贝对应的原链表节点的随机节点
        for(Map.Entry<RandomListNode, RandomListNode> m: map.entrySet()){
            m.getValue().random = map.get(m.getKey().random);
        }
        return copyHead;
    }














    public static void main(String[] args) {
        RandomListNode one = new RandomListNode(1);
        RandomListNode two = new RandomListNode(2);
        RandomListNode three = new RandomListNode(3);
        RandomListNode four = new RandomListNode(4);
        one.next = two;
        two.next = three;
        three.next = four;
        two.random = four;
        RandomListNode randomListNode = copyRandomListTwo(one);
        System.out.println("---------------------------");
    }

    /**
     * 映射表法
     * 优点：实现起来简单，容易理解
     * 缺点：需要使用哈希表来存储旧-新节点映射关系，占用更多的内存空间
     * @param head
     * @return
     */
    public static RandomListNode copyRandomListTwo(RandomListNode head) {
        if (null == head) {
            return head;
        }
        // 1、定义映射表：原-新节点关系映射
        Map<RandomListNode, RandomListNode> nodeMap = new HashMap<>(16);

        // 2、复制链表
        RandomListNode copyHead = null;
        RandomListNode copyNode = null;
        RandomListNode pNode = head;
        while (null != pNode) {
            // tempNode 为当前复制的节点
            RandomListNode tempNode = new RandomListNode(pNode.label);
            tempNode.random = pNode.random;
            // 确定复制链表的头节点
            if (pNode == head) {
                copyHead = copyNode = tempNode;
            } else {
                copyNode.next = tempNode;
            }
            // 这里易错 ：应该存的是当前的旧-新节点的内存空间对象的映射关系
            nodeMap.put(pNode, tempNode);
            pNode = pNode.next;
            copyNode = tempNode;
        }

        // 3、修正新链表的random指针的指向
        copyNode = copyHead;
        while (null != copyNode) {
            copyNode.random = nodeMap.get(copyNode.random);
            copyNode = copyNode.next;
        }
        return copyHead;
    }
}
