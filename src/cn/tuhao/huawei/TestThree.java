package cn.tuhao.huawei;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TestThree {
    /**
     * 原题：将数组分成两部分，使得这两部分的和的差最小
     * @url https://blog.csdn.net/shanshanhi/article/details/67639562
     * 数组长度为10，将数组分为两个部分。
     * 两部分和最小
     * 10 9 8 7 6 5 4 3 2 1
     *
     * 核心思路：使数组的和趋近总和的一半
     * @param args
     */
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String scores = in.nextLine();
        String scores = "10 9 8 7 6 5 4 3 2 1";
        List<Integer> scoreList = Arrays.stream(scores.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        TestThree testThree = new TestThree();
        int result = testThree.getResult(scoreList);
    }

    public int getResult(List<Integer> values) {
        int N = values.size();
        int sum = 0;
        for (Integer x : values) {
            sum = x + sum;
        }
        int W = sum/2;
        values.add(0 , 0);

        // 0-1背包问题，固定模板
        int [][] fun = new int[N+1][W+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1;j<= sum/2; j++) {
                if (values.get(i) <= j) {
                    fun[i][j] = Math.max(fun[i-1][j],fun[i-1][j- values.get(i)] + values.get(i));
                } else {
                    fun[i][j] = fun[i-1][j];
                }
            }
        }
        return sum - 2 * fun[N][W];
    }
}
