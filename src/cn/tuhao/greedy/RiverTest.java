package cn.tuhao.greedy;

public class RiverTest {
    /**
     * 解题思路参考：https://mp.weixin.qq.com/s/yDVQ_18kQoB-Z-yluklwuw
     *
     * 题目描述：有n个人要过河，只有一条船，船上最多只能坐两人。
     *
     * 已知每人划船过河的速度不同，有的快有的慢。
     *
     * 当两个人在一艘船上时，过河所需要的时间为两人所需时间的最大值。
     *
     * 在已知所有人划船过河所需时间的前提下，求最少需要多少时间能让所有人都过河？
     *
     * 思考 1，2，3，4个人：(x,y)_z 表示 x,y 一起过河，z返回对岸。
     * 1 -> 1
     * 1,2 -> 2
     * 1,2,3 -> (1,2)_1 (1,3) -> 2 + 1 + 3 =6
     * 1,2,3,4 -> (1,2)_1 (3,4)_2 ->变成1,2的问题 = (2 + 1) + (4 + 2) + 2 = 11
     * 1,2,3,4,5 -> (1,2)_1 (4,5)_2 变成1,2,3的问题 =(2 + 1) + (5 + 2)+ 6 = 16
     *
     * 用字母更好思考这些：有 A,B,C,D 人，其过河用时分别为 a<=b<=c<=d
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] costTimeArray = new int[]{1,2,3,4,5};
        System.out.println(test1(costTimeArray, costTimeArray.length));
    }

    public static int test1(int[] sortedArray, int num) {
        if (num == 0)
            return 0;
        if (num == 1) {
            return sortedArray[0];
        }
        if (num == 2) {
            return sortedArray[1];
        }
        if (num == 3) {
            return getAnInt(sortedArray) + sortedArray[2];
        }

        if (num > 3) {
            return test1(sortedArray, num - 2) + sortedArray[num - 1] + getAnInt(sortedArray);
        }
        return num;
    }

    private static int getAnInt(int[] sortedArray) {
        return 2 * sortedArray[0] + sortedArray[1];
    }
}
