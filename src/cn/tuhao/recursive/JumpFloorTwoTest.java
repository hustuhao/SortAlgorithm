package cn.tuhao.recursive;

public class JumpFloorTwoTest {
    /**
     * f(3) = f(2) + f(1)
     * f(4) = f(3) + f(2) + f(1)
     * f(5) = f(4) + f(3) + f(2) + f(1)
     * f(n) = f(n-1) + f(n-2) + ... + f(1)
     * f(n-1) = f(n-2) + ... + f(1)
     * 两个式子相减：f(n) = 2 * f(n-1)
     * @param target
     * @return
     */
    public int jumpFloorII(int target) {
        if (target <= 2) {
            return target;
        }
        return 2 * jumpFloorII(target - 1);
    }

    /**
     * 非递归的方法
     * @param target
     * @return
     */
    public int jumpFloorIITwo(int target) {
        if (target <= 2) {
            return target;
        } else if (target == 3) {
            return 6;
        }
        // 从 target = 3开始，公式成立
        int a = 4;
        int b = 0;
        while (target-- > 3) {
            b = 2 * a;
            a = b;
        }
        return b;
    }
}
