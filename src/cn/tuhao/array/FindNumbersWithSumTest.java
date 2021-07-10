package cn.tuhao.array;

import java.util.ArrayList;

public class FindNumbersWithSumTest {
    public static ArrayList<Integer> globalList;
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        // 0|参数校验
        if (array.length == 0) {
            return new ArrayList<>();
        }

        // 1|找到边界的sum
        globalList = getNums(array, sum);
        if (null == globalList) {
            return new ArrayList<>();
        }

        // 2|逐步向内
        int p1 = globalList.get(0), p2 = globalList.get(1);

        // 3|结束条件
        getMax(array, sum, p1 + 1, p2 - 1);
        ArrayList<Integer> resultList = new ArrayList<>();
        resultList.add(array[globalList.get(0)]);
        resultList.add(array[globalList.get(1)]);
        return resultList;
    }

    private ArrayList<Integer> getNums(int [] array, int sum) {
        for (int i = 0; i < array.length; i++) {
            for (int k = i + 1; k < array.length; k++) {
                if (array[i] + array[k] == sum) {
                    ArrayList<Integer> aList = new ArrayList<>();
                    aList.add(i);
                    aList.add(k);
                    aList.add(array[i] * array[k]);
                    return aList;
                }
            }
        }
        return null;
    }

    private void getMax(int [] array, int sum, int left, int right) {
        while (left < right) {
            while (array[left] + array[right] > sum) {
                right--;
            }
            while (array[left] + array[right] < sum) {
                left++;
            }
            if (array[left] + array[right] == sum) {
                if (array[left] * array[right] < globalList.get(2)) {
                    globalList.set(0, left);
                    globalList.set(1, right);
                    globalList.set(2, array[left] * array[right]);
                }
                left++;
                right--;
            }
        }
    }

    public static void main(String[] args) {
        FindNumbersWithSumTest findNumbersWithSumTest = new FindNumbersWithSumTest();
//        findNumbersWithSumTest.FindNumbersWithSum(new int[] {1,2,4,7,8,11,15},15);
//        findNumbersWithSumTest.FindNumbersWithSum(new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20},21);
        findNumbersWithSumTest.FindNumbersWithSum(new int[] {1,4,9},8);
        System.out.println(globalList);
    }
}
