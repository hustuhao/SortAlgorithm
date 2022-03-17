package cn.tuhao.array;
import java.util.Arrays;

import org.junit.Test;
/**

原地消除字符数组的重复空白（例："ab c d e" => "abcde"）
这是函数体：public static int RemoveSpaces(char[] s, int length) { }
参数s表示传进来的字符数组，length表示数组的有效数据长度，返回值是消除后的数组有效数据长度

 */


 /*
 核心思路：
*/

public class RemoveSpacesTest {
    public static char BLANK = ' ';
    public static int RemoveSpaces(char[] s, int length) {
        if (length == 0) {
            return 0;
        }
        int a = 0; // 记录当前数组中空白字符最靠前的数组下标
        int b = 0; // b-c 为不包含空白字符串的序列， 即[b-c]为不包含空白字符
        int c = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == BLANK && s[a] != BLANK) { // 记录最靠前的空白字符
                a = i;
                continue;
            } else if (s[i] == BLANK) { // 非最靠前的空白字符就跳过
                continue;
            } else if (s[i] != BLANK && s[a] != BLANK) {
                continue;
            }

            // 找出不包含空白字符的序列
            b = i;
            for (int k = i; k < s.length; k++) { // b 移动
                // 第一次找到非空白处
                if (s[k] == BLANK) { 
                    c = k-1;
                    // 进行整体赋值
                    memcopy(s, s, a, b, c-b+1);
                    a +=c-b+1; // 刷新a的位置
                    i = c;
                    break;
                }
                
                // 最后
                if (k == s.length - 1) {
                    c = k;
                     // 进行整体赋值
                     memcopy(s, s, a, b, c-b+1);
                     a +=c-b+1; // 刷新a的位置
                     i = c;
                }
            }
        }
        int len = a;
        if (a == 0 && s[length-1] != BLANK) {
            len = length;
        }
        return len;
    }

    // 可以替换成其他效率更高的赋值方法
    public static void memcopy(char[] dst, char[] src , int l1, int l2, int size) { 
        for (int i = 0; i < size; i++) {
            dst[l1] = src[l2];
            src[l2] = ' ';
            l1++;
            l2++;
        }
    }

    @Test
    public void test () {
        char[] s = {'a', 'b', ' ', 'd', 'e', ' ', 'f', 'g'};
        int r = RemoveSpaces(s, s.length);
        System.out.printf("%s,%d\n",Arrays.toString(s), r);

        char[] s1 = {' ','a', ' ', 'b', 'c', ' ', 'd', 'e'};
        int r1 = RemoveSpaces(s1, s1.length);
        System.out.printf("%s,%d\n",Arrays.toString(s1), r1);


        char[] s2 = {'a','b', 'c', 'd', 'e'};
        int r2 = RemoveSpaces(s2, s2.length);
        System.out.printf("%s,%d\n",Arrays.toString(s2), r2);

        char[] s3 = {' ',' ', ' ', ' ', ' '};
        int r3 = RemoveSpaces(s3, s3.length);
        System.out.printf("%s,%d\n",Arrays.toString(s3), r3);

        char[] s4 = {};
        int r4 = RemoveSpaces(s4, s4.length);
        System.out.printf("%s,%d\n",Arrays.toString(s4), r4);

    }
}


