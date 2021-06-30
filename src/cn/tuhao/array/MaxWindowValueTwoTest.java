package cn.tuhao.array;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 题目：滑动窗口的最大值
 * 思路：滑动窗口应当是队列，但为了得到滑动窗口的最大值，队列序可以从两端删除元素，因此使用双端队列。
 *     原则：
 *     对新来的元素k，将其与双端队列中的元素相比较
 *     1）前面比k小的，直接移出队列（因为不再可能成为后面滑动窗口的最大值了!）,
 *     2）前面比k大的X，比较两者下标，判断X是否已不在窗口之内，不在了，直接移出队列
 *     队列的第一个元素是滑动窗口中的最大值
 * 考察：队列以及滑动窗口的特点
 */
public class MaxWindowValueTwoTest {
    public static void main(String[] args) {
        int[] nums = {2,3,4,2,6,2,5,1};
        //[4,4,6,6,6,5]
        int size = 3;
        MaxWindowValueTwoTest maxWindowValueTwoTest = new MaxWindowValueTwoTest();
        ArrayList<Integer> arrayList = maxWindowValueTwoTest.maxInWindows(nums, size);
    }

    public ArrayList<Integer> maxInWindows(int [] nums, int size) {
        // 窗口值大于数组的值
        if (size > nums.length || size == 0) {
            return new ArrayList<>();
        }
        ArrayList<Integer> resultList = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i <= nums.length -1; i++) {
            while (!queue.isEmpty() && nums[queue.getLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
            // 最大值是否过期
            if (queue.getFirst() + size - 1 < i) {
                queue.pollFirst();
            }
            if (i >= size - 1) {
                Integer peek = queue.peek();
                resultList.add(nums[peek]);
            }
        }
        return resultList;
    }

}
