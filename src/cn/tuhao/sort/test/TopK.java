package cn.tuhao.sort.test;

import java.util.ArrayList;
import java.util.Arrays;
/*找最大的K个数*/
public class TopK {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList alist = new ArrayList();
        if (input.length < k)
            return alist;

        int[] arr = Arrays.copyOf(input, input.length);
        //构建大小为k的最小堆
        buildMinHeap(arr, k);

        for(int i=k+1;i<arr.length;i++){
            add(arr, arr[i],k);
        }

        Arrays.sort(arr,0,k);

        for(int i=0;i<k;i++){
            alist.add(arr[i]);
        }
        return alist;
    }

    /*构建小顶堆*/
    private void buildMinHeap(int[] arr, int len) {
        //这里需要理解 len/2 ,详情请复习：完全二叉树的性质
        // i/2是求i的父节点，也就是从右往左，从下往上进行堆化
        for (int i = len / 2; i >= 0; i--) {
            //这里的i都是非叶子结点(0,i)
            heapify(arr, i, len);
        }
    }

    /**
     * 将序列转化为堆
     * heapify (堆化)
     *
     * @param arr
     * @param i   某个结点
     * @param len 堆中元素的个数
     */
    private void heapify(int[] arr, int i, int len) {
        //完全二叉树的性质，left表示该结点左孩子的位置，right表示右孩子的位置
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        //大顶堆，顶点是最大的。
        int smallest = i;

        //开始调整数组，比较左孩子和根结点的大小
        if (left < len && arr[left] < arr[smallest]) {
            smallest = left;
        }
        //比较右孩子和当前根结点的大小
        if (right < len && arr[right] < arr[smallest]) {
            smallest = right;
        }

        // arr[i]的左右孩子中，有比i更小
        if (smallest != i) {
            swap(arr, i, smallest);
            heapify(arr, smallest, len);
        }
    }

    /**
     * 给最小堆里面添加元素
     * @param arr 堆数组
     * @param num 需要添加的元素
     * @param len 堆的大小(元素个数)
     */
    private void add(int[] arr, int num,int len) {
        if (arr[0] < num) {
            arr[0] = num;
            //维护最小堆
            heapify(arr, 0, len);
        }
    }

    /*交换数组的两个位置*/
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{33,2,55,7,6,0,555,22,5,23,29};
        //int arr[]=new int[]{7,6,5,4,3,2,1,0};
        TopK t = new TopK();
        //t.buildMinHeap(arr,arr.length);
        ArrayList<Integer> alist = t.GetLeastNumbers_Solution(arr,5);
    }
}