package cn.tuhao.sort;

import java.util.Arrays;
import java.util.LinkedList;

/* 快速排序非递归实现 */
public class QuickSort2 {
    public void quickSort(int[] arr){
        int low = 0;
        int high = arr.length - 1;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.offer(low); // 存区间 [low , high]
        queue.offer(high);
        while(!queue.isEmpty()){ // 用队列存储区间信息
            low = queue.poll();
            high = queue.poll();
            int i = low ,j = high;

            int key = arr[low];
            //本轮结束条件 i==j
            while(i<j)
            {
                //从后面找，直到直到符合条件的数 x < key
                while(i<j && arr[j]>=key) j--;

                //交换a[i]和a[j]
                if(i<j)
                {
                    int temp=arr[j];
                    arr[j]=arr[i];
                    arr[i]=temp;
                    i++;
                }
                //从前面找，直到直到符合条件的数 x > key
                while(i<j && arr[i]<=key)i++;
                //交换a[i]和a[j]
                if(i<j){
                    int temp=arr[j];
                    arr[j]=arr[i];
                    arr[i]=temp;
                    j--;
                }
            }

            if(i>low){
                queue.offer(low);
                queue.offer(i-1);
            }

            if(j<high){
                queue.offer(i+1);
                queue.offer(high);
            }

        }
    }

    public void swap (int[] arr, int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int [] arr = new int[]{4,3,2,1};
        QuickSort2 qs = new QuickSort2();
        qs.quickSort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
