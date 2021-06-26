package cn.tuhao.recursive;

public class SquareTest {
    /**
     * 参考题解:https://blog.nowcoder.net/n/74aadc6d1b264dd3ade8b827f2809a5d
     * 思考：如何从前面推出后面的内容
     * @param target
     * @return
     */
    public int rectCover(int target) {
        if (target <= 3) {
            return target;
        }
        int a = 2;
        int b = 3;
        int c = 0;
        while (target-- > 3) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
