package cn.tuhao.string;

// leetcode 551
public class LateTest {
    public boolean checkRecord(String s) {
        int late = 0;
        int absent = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'L') {
                late++;
            } else if (chars[i] == 'A') {
                absent++;
                late = 0;
            } else {
                late = 0;
            }

        }
        if (absent > 2 || late > 2) {
            return false;
        }
        return true;
    }
    
}
