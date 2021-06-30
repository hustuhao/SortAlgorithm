package cn.tuhao.huawei;

import java.util.Arrays;

/**
 * 计算字符串中字符连续出现的最大次数，并输入第N大的字符串的长度。
 *  异常返回-1
 *
 *  1、统计所有字符连续出现的次数
 *  2、通过出现次数进行排序
 *  2、找到第N大的字符串的长度
 */
public class TestOne {
    public static void main(String[] args) {
        String str = "AAAAHHHBBCDHHHH";
        int k = 3;
        if (null == str || 0 == str.length()) {
            System.out.println(-1);
            return;
        }
        int [] intArray = new int[1000];
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char curChar = chars[i];
            int count = 1;
            while (i + 1 < chars.length && curChar == chars[i + 1]) {
                count++;
                i ++;
            }
            if (count > intArray[curChar]) {
                intArray[curChar] = count;
            }
        }
        Arrays.sort(intArray);

        int index = 0;
        for (int i = intArray.length - 1; i >= 0; i--) {
            if (intArray[i] == 0) {
                break;
            }
            index ++;
        }
        if (k > index) {
            System.out.println(-1);
            return;
        } else {
            System.out.println(intArray[intArray.length-k]);
        }
    }
}
