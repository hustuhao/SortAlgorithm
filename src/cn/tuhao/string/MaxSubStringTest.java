package cn.tuhao.string;

import org.junit.Test;

import java.util.LinkedList;

/**
 *
 * https://blog.nowcoder.net/n/4a23ac77a65d48d0b577860a2c6f9c35
 * 给出一个仅包含字符'('和')'的字符串，计算最长的格式正确的括号子串的长度。
 * 对于字符串"(()"来说，最长的格式正确的子串是"()"，长度为2.
 * 再举一个例子：对于字符串")()())",来说，最长的格式正确的子串是"()()"，长度为4.
 */
public class MaxSubStringTest {
    /**
     * 未检查
     * @param s string字符串
     * @return int整型
     */
    public int longestValidParentheses (String s) {
        // 特殊情况处理
        if (null == s || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 0;
        }

        // 定义括号
        char left = '(';
        LinkedList<Integer> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            // 如果是左括号就进栈
            if (chars[i] == left) {
                stack.push(i);
            } else {
                // 如果是右括号就出栈
                stack.pop();
                if (stack.isEmpty()) {

                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;

    }


    /**
     * https://blog.csdn.net/qq_41033011/article/details/108436108
     * 核心算法思路：
     * 括号要是有效的，必定是成对出现的。
     * 比如一个长度为 7 的字符串，我们可以先以 6 个字符为窗口进行滑动判断，
     * 如果 6 个字符没有匹配的，再以 4 个字符为窗口进行滑动判断。
     * @param s
     * @return
     */
    public int longestValidParenthesesViolence (String s) {
        int len = s.length();
        // sz 为滑动窗口的大小
        int sz;
        if(len % 2 == 0)
            sz = len;
        else
            sz = len - 1;
        for(;sz >= 2; sz -= 2) {
            int left = 0, right = left + sz - 1;
            while(right < s.length()) {
                if(isMatch(s.substring(left, sz))) {
                    return sz;
                } else {
                    left++;
                    right++;
                }
            }
        }
        return 0;
    }

    /**
     * 是否为有效括号字符串
     * @param str
     * @return
     */
    public boolean isMatch(String str) {
        if (null == str || str.length() == 0 || str.length() == 1) {
            return false;
        }

        LinkedList<Character> stack = new LinkedList<>();
        char[] chars = str.toCharArray();
        if (chars[0] != '(') {
            return false;
        }

        for (int i = 0; i < chars.length; i++) {
            if (stack.isEmpty()) {
                stack.push(chars[i]);
                continue;
            }

            if (chars[i] == '(') {
                return false;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test () {
        MaxSubStringTest maxSubStringTest = new MaxSubStringTest();
//        System.out.println(maxSubStringTest.longestValidParentheses("(()("));
//        System.out.println(maxSubStringTest.longestValidParentheses("(()()(()"));
//        System.out.println(maxSubStringTest.longestValidParentheses("("));
        System.out.println(maxSubStringTest.isMatch("()()"));
        System.out.println(maxSubStringTest.isMatch("()("));
        System.out.println(maxSubStringTest.isMatch("(()"));
        System.out.println(maxSubStringTest.isMatch(""));
        System.out.println(maxSubStringTest.isMatch("("));
        System.out.println(maxSubStringTest.isMatch(")"));
    }
}
