package cn.tuhao.test;


import java.util.LinkedList;

/**
 * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
 * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。 [表情]
 *
 * ()[]{}
 * [{()}]}
 * [{()}]}}
 *
 * public boolean isValid (String s) {
 *     // write code here
 *
 * }
 */
public class TestOne {

    public static void main(String[] args) {
        TestOne testOne = new TestOne();
        String a = "[{()}]";
        String b = "[{()}]}}";
        boolean valid1 = testOne.isValid(a);
        boolean valid = testOne.isValid(b);
        System.out.println("_______________________");
    }

    public boolean isValid (String s) {
        if (null == s || 0 == s.length()) {
            return false;
        }
        // 1| 栈,括号序列
        LinkedList<Character> stack = new LinkedList<Character>();
        Character LEFT_A = '(';
        Character LEFT_B = '{';
        Character LEFT_C = '[';
        Character RIGHT_A = ')';
        Character RIGHT_B = '}';
        Character RIGHT_C = ']';
        // 2| 遍历字符串,如果碰到
        char[] chars = s.toCharArray();
        stack.push(chars[0]);

        for (int i = 1; i < chars.length; i++) {
            Character last = null;
            if (stack.isEmpty()) {
                stack.push(chars[i]);
                continue;
            } else {
                last = stack.getFirst();
            }
            if(LEFT_A.equals(last)) {
                if(RIGHT_A.equals(chars[i])) {
                    stack.pop();
                } else {
                    stack.push(chars[i]);
                }
                continue;
            } else if(LEFT_B.equals(last)) {
                if(RIGHT_B.equals(chars[i])) {
                    stack.pop();
                } else {
                    stack.push(chars[i]);
                }
                continue;
            } else if(LEFT_C.equals(last)) {
                if(RIGHT_C.equals(chars[i])) {
                    stack.pop();
                } else {
                    stack.push(chars[i]);
                }
                continue;
            }
        }
        return stack.isEmpty();
    }

}
