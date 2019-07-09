package cn.tuhao.common;

import org.junit.Test;

public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode() {
    }
    public ListNode(int val) {
        this.val = val;
    }

    /**
     * 根据数据生成链表
     * @param array
     * @return head 链表头节点
     */
    public static ListNode generateList(int array[]){
        if(array.length==0){
            return null;
        }
        ListNode head = new ListNode(array[0]);
        ListNode temp = head;
        for(int i=1;i<array.length;i++){
            ListNode node = new ListNode(array[i]);
            temp.next = node;
            temp = temp.next;
        }
        return head;
    }

    public static void printList(ListNode head){
        if(head == null){
            System.out.println("链表为空");
        }
        ListNode temp = head;
        while(temp!=null){
            System.out.print(temp.val+" ");
            temp = temp.next;
        }
    }

    /*
    public static void  main(String[] args){
        int[] array = new int[]{1,2,3,4,5,6};
        ListNode head = generateList(array);
        printList(head);

        System.out.println("------------");
        int[] arrayz = new int[5];
        System.out.println(arrayz.length);
        ListNode heade = generateList(arrayz);
        printList(heade);
    }
    */
}