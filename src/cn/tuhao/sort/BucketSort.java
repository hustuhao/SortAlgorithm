package cn.tuhao.sort;

import org.junit.Ignore;
import org.junit.Test;


/*桶排序：分而治之 & 插入排序
* 缺点：空间复杂度高(step的选取)
* 桶使用一个数组的缺点：时间复杂度提高，因为每一次都要遍历整个数组
* */
public class BucketSort {
    /*复习二维数组的使用，不过这里不必用到二维数组*/
    public void bucketSort(int[] arr, int step) {
        /*共用这个桶*/
        int[] BucketArray = new int[step];
        /*存放结果*/
        int[] ResultArray = new int[arr.length];
        //初始化最大值和最小值
        int max = arr[0];
        int min = arr[0];

        //遍历数组，找到最大值和最小值
        //find the minist and the max numbers in the array
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        ResultArray[0] = min;
        //需要桶的个数
        // the count of bucket
        int bucketCount = (int) Math.ceil((max - min + 1) / ((double) step));

        //count is for BucketArray
        int indexBucket = 0;
        //最小的数已经提前找出来了，所以从1开始存放结果
        int indexResult = 1;
        //new  Insert Sort
        //调用插入排序，对桶数组进行插入排序
        InsertSort is = new InsertSort();
        for (int i = 0; i < bucketCount; i++) {
            int guard = min + i * step;
            /*遍历待排序的数组，将数装入桶中*/
            //将桶数组清零
            clearArray(BucketArray);
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] > guard && arr[j] <= guard + step) {
                    BucketArray[indexBucket] = arr[j];
                    indexBucket++;
                }
            }

            //进行插入排序
            is.insertSort(BucketArray);
            indexResult = mergeArrays(ResultArray,indexResult,BucketArray);
            indexBucket = 0;
        }
    }

    /**
     * 将target合并到source中去
     * @param source
     * @param target
     * @return
     */
        public int mergeArrays(int[] source, int position, int[] target){
            if(target.length==0)
                return position;

            //去掉数组中的0，0代表没有数,
            int k=0;
            for(int i=0;i<target.length;i++){
                if(target[i]!=0){
                    k=i;
                    break;
                }
            }
            /* target数组全为0
            *  target array is full of zero
            * */
            if(k==0){
                return position;
            }

            for(int i=k;i<target.length;i++,position++){
                source[position] = target[i];
            }
            //返回指针，指向source最后一个空位置
            return position;
        }

    /**
     * 将桶数组清零
     * @param array
     */
    public void clearArray(int[] array){
            for(int i=0;i<array.length;i++){
                array[i]=0;
            }
        }


    @Test
    public void testArray(){
        int arr[] = new int[]{12,5,9,36,8,21,7};
        int[] result;
        bucketSort(arr,5);

    }

    @Test
    @Ignore
    public  void  testMergeArrays(){
            int []a={5,7,8,9,12,0,0,};
            int []b={0,0,0,0,21};
          //  int []b={0,0,0,0,0};
            mergeArrays(a,5,b);
    }
}
