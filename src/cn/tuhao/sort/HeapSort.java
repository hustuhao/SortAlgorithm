package cn.tuhao.sort;

import org.junit.Test;
import sun.security.util.Length;

import java.util.Arrays;

/**
 * 堆排序：
 * 比较适合处理大数据
 * 时间复杂度：O(nlogn)
 */
public class HeapSort {
    /**
     *
     * @param sourceArray
     * @return //注意，最大的那个结点是在数组的末尾
     * @throws Exception
     *
     * 过程：
     * 1-将数组变成大顶堆（用数组表示完全二叉树）
     * 比如数组：{12,5,9,36,8,21,7}
     *      12
     *     /  \
     *    5    9
     *   /\    /\
     *  36 8  21 7
     *  大顶堆{36,12,21,5,8,9,7}
     *      36
     *     /  \
     *   12    21
     *   /\    /\
     *  5 8   9 7
     *
     * 2-将最大的数取出来，将剩下的数组元素重新构建成一个大顶堆。(参考堆的删除数据)
     * 3-继续取出大顶堆里面最大的元素(数组的第一个元素)，直到剩下堆里面的元素只剩一个
     */
    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int len = arr.length;
        //建立最大堆
        buildMaxHeap(arr, len);

        //堆排序，将大顶堆变为有序序列
        for (int i = len - 1; i > 0; i--) {
            //arr[0]从处是最大值，所以把最大值放在最后面。
            swap(arr, 0, i);
            // 重建大顶堆
            // 将arr变成大顶堆，使arr[0]存储当前大顶堆最大值
            heapify(arr, 0, --len);
        }
        return arr;
    }

    /**
     * 构建大顶堆
     * {12,5,9,36,8,21,7}
     *      12
     *     /  \
     *    5    9
     *   /\    /\
     *  36 8  21 7
     *
     *    9        21
     *   / \       /\
     * 21   7     9  7
     *
     *    5         36
     *   / \       /  \
     *  36  8     5    8
     *
     *     12
     *    / \
     *  36   21
     *
     */
    private void buildMaxHeap(int[] arr, int len) {
        //这里需要理解 len/2 ,详情请复习：完全二叉树的性质
        // i/2是求i的父节点，也就是从右往左，从下往上进行堆化
        for (int i = len/2 ; i >= 0; i--){
            //这里的i都是非叶子结点(0,i)
            heapify(arr, i, len);
        }
    }

    /**
     * 将序列转化为堆
     * heapify (堆化)
     * @param arr
     * @param i   某个结点
     * @param len
     */
    private void heapify(int[] arr, int i, int len) {
        //完全二叉树的性质，left表示该结点左孩子的位置，right表示右孩子的位置
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        //大顶堆，顶点是最大的。
        int largest = i;

        //开始调整数组，比较左孩子和根结点的大小
        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }
        //比较右孩子和当前根结点的大小
        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }

        // arr[i]的左右孩子中，有比i更大者
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, len);
        }
    }
    /*交换数组的两个位置*/
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Test
    public void test() throws Exception {
        int arr[] = new int[]{12,5,9,36,8,21,7};
        int[] result;
        result = sort(arr);
    }
}
