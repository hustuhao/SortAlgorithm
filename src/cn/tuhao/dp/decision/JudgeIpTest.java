package cn.tuhao.dp.decision;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯通用规则：
 * 1、终止条件：ip段最多4段，超过4段终止。如果等于4段但是不是一开始完整的字符串也终止。如果4段并且组合起来是一开始的字符串则添加到结果里面去。
 * 2、过滤条件：如果字符串在0-255之外，过滤。如果字符串存在01这种情况，过滤。
 * 3、经典选择回退：先选择这个字符串，演算完所有相关情况，最后回退选择。
 *
 * 作者：wo-ai-chi-mang-mang
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses/solution/leecodekai-xin-shua-hui-su-tao-lu-fu-yua-hr7a/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class JudgeIpTest {

    /**
     * 方法一：回溯法
     * 1、终止条件
     * 2、过滤条件
     * 3、经典选择回退
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        backtrace(s, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrace(String s, int startIndex, List<String> path, List<String> res) {
        // 终止条件：ip段最多4段，超过4段终止。如果等于4段但是不是一开始完整的字符串也终止。如果4段并且组合起来是一开始的字符串则添加到结果里面去。
        if(path.size() > 4) {
            return;
        }
        if(path.size() == 4 && startIndex != s.length()) {
            return;
        }

        if(path.size() == 4) {
            res.add(String.join(".", path));
            return;
        }

        for(int i = startIndex; i < s.length(); i++) {
            // 过滤条件：如果字符串在0-255之外，过滤。如果字符串存在01这种情况，过滤。
            String newStr = getLegalStr(s, startIndex, i);
            if (newStr == null) {
                continue;
            }
            //回溯：先选择这个字符串，演算完所有相关情况，最后回退选择。
            path.add(newStr);
            backtrace(s, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 获取合法的字符串
     * @param s 整个ip数字字符串
     * @param startIndex 起点位置
     * @param endIndex 终点位置
     * @return 不合法：返回null；合法：返回非null
     */
    private String getLegalStr(String s, int startIndex, int endIndex) {
        // 截取[startIndex, endIndex]的字符串数字
        String newStr = s.substring(startIndex, endIndex + 1);
        if((newStr.length() > 1 && newStr.startsWith("0")) || newStr.length() > 3) {
            return null;
        }
        int value = Integer.parseInt(newStr);
        if(value < 0 || value > 255) {
            return null;
        }
        return newStr;
    }

    /**
     *  方法二：暴力解法
      * @param s ip数字字符串
     * @return
     */
    public List<String> restoreIpAddressesTwo(String s) {
        List<String> list = new ArrayList();
        for(int a=1; a<4; a++){
            for(int b=1; b<4; b++){
                for(int c=1; c<4; c++){
                    for(int d=1; d<4; d++){
                        if(a + b + c + d == s.length()){
                            String s1 = s.substring(0, a);
                            String s2 = s.substring(a, a+b);
                            String s3 = s.substring(a+b, a+b+c);
                            String s4 = s.substring(a+b+c, a+b+c+d);

                            if(check(s1) && check(s2) && check(s3) && check(s4)) {
                                String ip = s1+"."+s2+"."+s3+"."+s4;
                                list.add(ip);
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * 检查ip的每一段数字是否合法
     * 应该满足：
     * 1、范围在[0,255]
     * 2、如果是正整数，则首位不能为0
     * @param s
     * @return
     */
    public boolean check(String s){
        int num = Integer.parseInt(s);
        if(num > 255 || num < 0){
           return false;
        }
        return s.charAt(0) != '0' || s.charAt(0) == '0' && s.length() == 1;
    }

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        System.out.println("Hello World!");
    }
}
