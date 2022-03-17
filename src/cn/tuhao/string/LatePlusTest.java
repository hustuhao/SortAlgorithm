package cn.tuhao.string;

public class LatePlusTest {
    static int dfs(int n,int len ,int absent, int late) {
        if (len >= n) {
            return 1;
        }
        int count = 0;
        count += dfs(n, len + 1, absent, 0);
        if (absent < 1) {
            count += dfs(n, len + 1, absent + 1, 0);
        }
        if (late < 2) {
            late++;
            count += dfs(n, len + 1, absent, late);
        }
        return count;
    }

    static int dfss(int n, int day,int absent, int late) {
        if (day >= n) {
            return 1;
        }

        // 回溯开始
        int ans = 0;
        // 1. Present随便放
        ans = (ans + dfs(n, day + 1, absent, 0));
        // 2. Absent最多只能放一个
        if (absent < 1) {
            ans = (ans + dfs(n, day + 1, 1, 0));
        }
        // 3. Late最多连续放2个
        if (late < 2) {
            ans = (ans + dfs(n, day + 1,absent, late + 1));
        }

        return ans;
    }


    public static void main(String[] args) {
        // System.out.println(dfs(1,0,0,0));// 3
        // System.out.println(dfs(2,0,0,0));// 8
        System.out.println(dfss(3,0,0,0));// 8 ： 
    }
}
