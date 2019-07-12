package cn.tuhao.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 改变了输入的数组
 *
 * 原文地址：https://www.cnblogs.com/of-fanruice/p/7678801.html
 * 算法分析
 * （1）稳定性
 *      　归并排序是一种稳定的排序。
 * （2）存储结构要求
 *     　可用顺序存储结构。也易于在链表上实现。
 * （3）时间复杂度
 *     　对长度为n的文件，需进行趟二路归并，每趟归并的时间为O(n)，故其时间复杂度无论是在最好情况下还是在最坏情况下均是O(nlgn)。
 * （4）空间复杂度
 *    　 需要一个辅助向量来暂存两有序子文件归并的结果，故其辅助空间复杂度为O(n)，显然它不是就地排序。
 *   注意：
 *     　若用单链表做存储结构，很容易给出就地的归并排序
 */
public class MergeSort {
    public static int[] sort(int[] array,int low,int high){
        int mid = (low+high)/2;
        if(low<high){
            sort(array,low,mid);
            sort(array,mid+1,high);
            //左右归并
            merge(array,low,mid,high);
        }
        return array;
    }

    /**
     * 归并已经排好序的子序列
     * @param array
     * @param low  index 索引
     * @param mid  index 索引
     * @param high index 索引
     */
    public static void merge(int[] array, int low, int mid, int high) {
        //临时数组存放合并后的新序列
        int[] temp = new int[high-low+1];
        int i= low;
        int j = mid+1;
        int k=0;
        // 把较小的数先移到新数组中
        while(i<=mid && j<=high){
            if(array[i]<array[j]){
                temp[k++] = array[i++];
            }else{
                temp[k++] = array[j++];
            }
        }
        // 把左边剩余的数移入数组
        while(i<=mid){
            temp[k++] = array[i++];
        }
        // 把右边边剩余的数移入数组
        while(j<=high){
            temp[k++] = array[j++];
        }
        // 把新数组中的数覆盖nums数组
        for(int x=0;x<temp.length;x++){
            array[x+low] = temp[x];
        }
    }
    public static void printArray(int[] array){
        System.out.print("[");
        for(int i:array){
            System.out.print(i+" ");
        }
        System.out.print("]");
    }
    @Test
    public void  testMergeSort(){
        MergeSort ms = new MergeSort();
        int array[] = {6,4,3,7,5,1,2};
        int[] resultArray = ms.sort(array,0,array.length-1);
        ms.printArray(resultArray);
        ms.printArray(array);

    }



}
