package cn.tuhao.array;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 */
public class MoreThanHalfNumTest {
    /**
     * 排序
     * @param array
     * @return
     */
    public int MoreThanHalfNum_SolutionOne(int [] array) {
        if (array.length == 1) {
            return array[0];
        }
        Arrays.sort(array);
        int halfNum = array.length / 2;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == array[i+1]) {
                int k = i;
                int count = 0;
                while (k < array.length && array[k] == array[i]) {
                    k ++;
                    count++;
                }
                i = i + count - 1;
                if (halfNum < count) {
                    return array[i];
                }
            }
        }
        return -1;
    }

    /**
     * 非众数和众数相互抵消。最后留下众数。
     * https://blog.nowcoder.net/n/34901ad298694eacaa1a32da61a018be
     * @param array
     * @return
     */
    public int MoreThanHalfNum_SolutionTwo(int [] array) {
        if (array.length == 1) {
            return array[0];
        }
        int halfNum = array.length / 2;
        int candidate = -1;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (count == 0) {
                candidate = array[i];
                count++;
                continue;
            }
            if (candidate == array[i]) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

    @Test
    public void test () {
        MoreThanHalfNumTest moreThanHalfNumTest = new MoreThanHalfNumTest();
        int [] array = {1,1,2,2,2};
        moreThanHalfNumTest.MoreThanHalfNum_SolutionTwo(array);
    }
}
