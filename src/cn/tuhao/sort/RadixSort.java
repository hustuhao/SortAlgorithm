package cn.tuhao.sort;

import org.junit.Test;

import java.util.ArrayList;

/*
LSD：最低位优先法(Least Significant Digit first)
MSD：最高位优先法(Most Significant Digit first)
*/
public class RadixSort {
    /**
     * @param number 待排序的数组
     * @param d 最大的位数
     * LSD法
     */
    public static void radixSortLsd(int[] number,int d){
        int k = 0;
        int n = 1;
        int m = 1; //控制键值排序依据在哪一位
        ArrayList<Integer>[] bucket = new ArrayList[10];
        //注意这里要对数组里面的对象初始化，否则会报错：NullPointerException
        for(int i=0;i<10;i++){
            bucket[i] = new ArrayList<Integer>();
        }
        //从个位数开始分类
        while(m <= d){
            for(int i = 0; i < number.length; i++)
            {
                int lsd = ((number[i] / n) % 10);
                bucket[lsd].add(number[i]);
            }
            for(int i = 0; i < 10; i++)
            {
                if(!bucket[i].isEmpty())
                    for(int j = 0; j < bucket[i].size(); j++)
                    {
                        number[k] = bucket[i].get(j);
                        k++;
                    }
                bucket[i].clear();
            }
            n *= 10;
            k = 0;
            m++;
        }
    }

    @Test
    public void test(){
        int [] arr = new int[]{1,22,33,2,55,66,77,21};
        radixSortLsd(arr,2);
    }
}
