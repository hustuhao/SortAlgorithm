package cn.tuhao.pack;
//https://www.cnblogs.com/mfrank/p/10803417.html
// https://blog.csdn.net/reed1991/article/details/53352426 优化
/**
 * 完全背包问题
 */
public class CompletePackageTest {
    public static void main(String[] args) {

    }
    /**
     * 知识点：动态规划 + 压缩空间
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
        for (int i = 0; i < num + 1; i++) {
            // m 为背包容量, 为什么需要反序遍历呢？
            for (int m = dpCapacity; m  >= w[i]; m --) {
                for (int k = 1; k * w[i] <= m; k++) {
                    dp[m] = Math.max(dp[m - w[i] * k] + v[i] * k, dp[m - w[i] * k]);
                }
            }
        }
        return dp[dpCapacity];
    }

    public static int getResultTwo (int w[], int v[], int dpCapacity) {
        // 物品的数量
        int num = w.length;
        int[][] dp = new int[num + 1][dpCapacity + 1];
        // 遍历物品
        for (int i = 1; i < num + 1; i++) {
            // m 为背包容量, 为什么需要反序遍历呢？
            for (int m = 1; m  >= w[i]; m --) {
                for (int k = 1; k * w[i] <= m; k++) {
                    dp[i][m] = Math.max(dp[i][m - w[i] * k], dp[i][m + w[i] * k] + k * v[i]);
                }
            }
        }
        return dp[num][dpCapacity];
    }
}
