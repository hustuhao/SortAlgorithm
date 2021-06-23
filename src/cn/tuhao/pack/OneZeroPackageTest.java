package cn.tuhao.pack;

public class OneZeroPackageTest {
    /**
     *
     * 动态规划入门——传说中的零一背包问题：
     * - https://mp.weixin.qq.com/s/DnkcM7rWCV36S26cR7W7Ig
     *  01背包问题
     *  1、 从0状态开始，状态0的最有价值为0
     *  2、考虑后效性，确保没有后效性。
     *  3、执行决策的时候，会发生状态转移，记录状态对应的最优解
     *
     *  背包问题中：决策就是获取物品，状态就是背包容量
     *  w[i]:权重数组
     *  v[i]:容量
     *  d[i]:容量为 i 时，价值最大的值
     */
    public static void main(String[] args) {

    }

    /**
     * 知识点：动态规划 + 压缩空间
     * 参考 https://www.cnblogs.com/kkbill/p/12081172.html
     * @param w 占用空间
     * @param v 价值
     * @param dpCapacity 背包容量
     * @return 最大价值
     */
    public static int getResultOne (int w[], int v[], int dpCapacity) {
        // 物品的数量
        int num = w.length;
        int[] dp = new int[dpCapacity + 1];
        // 遍历物品
        for (int i = 1; i < num + 1; i++) {
            // m 为背包容量, 为什么需要反序遍历呢？
            for (int m = dpCapacity; m  >= w[i]; m --) {
                // 这里的前提是：m  >= w[i] 即，物品的大小 >= 背包剩余容量
                dp[m] = Math.max(dp[m - w[i]] + v[i], dp[m - w[i]]);
            }
        }
        return dp[dpCapacity];
    }

    /**
     * 知识点：动态规划
     * 参考 https://www.cnblogs.com/kkbill/p/12081172.html
     * @param w 占用空间
     * @param v 价值
     * @param dpCapacity 背包容量
     * @return 最大价值
     */
    public static int getResultTwo (int w[], int v[], int dpCapacity) {
        // 物品数量
        int m = w.length;
        // 背包容量
        int N = dpCapacity;
        // 初始化背包：(物品，价值)
        int [][] dp= new int[m+1][N+1];
        // 遍历物品
        for (int i = 1; i <= m; i++) {
            // 背包容量，对比上面的正序遍历
            for (int j = 1; j <= N; j++) {
                // 保证背包容量 >= 放入的物品容量
                if (w[i] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]] + v[i]);
                } else {
                    dp[i][j] = dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[m][N];
    }
}
