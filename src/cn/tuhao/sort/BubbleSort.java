package cn.tuhao.sort;

import cn.tuhao.common.ListNode;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 从左到右，从小到大
 */
public class BubbleSort {
    public static void bubbleSort(int[] array){
        for(int i=0;i<array.length-1;i++){//最后一个的时候并不用，因为最后一个肯定是最大的了
            for(int j=0;j<array.length-1-i;j++){//没运行(i+1)轮，最后面就排序好了最大的数
                if(array[j]>array[j+1]){
                    int temp =array[j];
                    array[j]= array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    @Test
    public void testbubleSort(){
        int array[] = {6,4,3,7,5,1,2};
        BubbleSort.bubbleSort(array);
    }


}
