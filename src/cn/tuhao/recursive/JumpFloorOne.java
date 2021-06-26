package cn.tuhao.recursive;

public class JumpFloorOne {
    public static void main(String[] args) {
        JumpFloorOne recursiveTest = new JumpFloorOne();
        System.out.println(recursiveTest.jumpFloorThree(1));
        System.out.println(recursiveTest.jumpFloorThree(2));
        System.out.println(recursiveTest.jumpFloorThree(3));
        System.out.println(recursiveTest.jumpFloorThree(4));
        System.out.println(recursiveTest.jumpFloorThree(5));
        System.out.println(recursiveTest.jumpFloorThree(6));
    }
    /**
     * 跳台阶
     * 1:1
     * 2:2
     * 3:1+2
     * 4:(2+2) + (1)
     * 5:(3+2) + (1)
     * 6:(jumFloot(4) + 2) + 1
     * @param target
     * @return
     */
    public int jumpFloorTwo(int target) {
        if (target <= 0) {
            return 0;
        } else if (target <= 2) {
            return target;
        } else {
            return jumpFloorTwo(target - 2) + jumpFloorTwo(target - 1);
        }
    }

    public int jumpFloorOne(int target) {
        if (target <= 2)
            return target;
        int a = 1;
        int b = 2;
        int c = 0;
        for (int i = 3; i <= target; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     *
     * @param target
     * @return
     */
    public int jumpFloorThree(int target) {
        if (target <= 2)
            return target;
        int[] array = new int[target + 1];
        array[1] = 1;
        array[2] = 2;
        for (int i = 3; i <= target; i++) {
            array[i] = array[i-1]+array[i-2];
        }
        return array[target];
    }
}
