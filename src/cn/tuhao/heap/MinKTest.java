package cn.tuhao.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * JZ29 最小的K个数
 * 考点：排序 | 堆 | 有序数组
 *
 */
public class MinKTest {
    public static void main(String[] args) {
        MinKTest minKTest = new MinKTest();
//        minKTest.GetLeastNumbers_Solution(new int[]{1,4,2,3,5}, 2);
        ArrayList<Integer> arrayList = minKTest.GetLeastNumbers_SolutionTwo(new int[]{4, 5, 1, 6, 2, 7, 3, 8}, 10);


    }

    /**
     * 方法1：对数组进行排序
     * 缺点：比较消耗内存和CPU
     * 优点：实现起来非常简单，适用于数据量比较小的时候。
     * @param input 输入数组
     * @param k 最小的K个数
     * @return 包含最小的K个数的数组（排好序）
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if (k > input.length) {
            return new ArrayList<Integer>();
        }
        Arrays.sort(input);
        ArrayList<Integer> result = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            result.add(input[i]);
        }
        return result;
    }

    /**
     * 方法二：改进方法一，维护一个长度为K 的有序数组
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_SolutionTwo(int [] input, int k) {
        // 维护 K 大小的数组

        // 二分查找，找到input[i]的位置
        return null;
    }

    /**
     * 方法三：利用有序队列维护固定大小的堆
     * PriortyQueue 底层是由二叉树实现
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_SolutionThree(int [] input, int k) {
        if (k > input.length || k <= 0) {
            return new ArrayList<Integer>();
        }
        // 定义最大堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue(k, (Comparator<Integer>) (o1, o2) -> Integer.compare(o2, o1));
        maxHeap.add(input[0]);
        for (int i = 1; i < input.length; i++) {
            if (maxHeap.size() < k) {
                maxHeap.offer(input[i]);
                continue;
            }
            if (maxHeap.peek() > input[i]) {
                maxHeap.poll();
                maxHeap.offer(input[i]);
            }
        }
        ArrayList<Integer> result = new ArrayList<>(k);
        for (int i = k - 1; i >= 0; i--) {
            result.add(maxHeap.poll());
        }
        return result;
    }

}
