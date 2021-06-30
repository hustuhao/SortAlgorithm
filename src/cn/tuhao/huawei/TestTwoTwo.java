package cn.tuhao.huawei;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 题目描述：
 * 第一行：选手
 * 第二行：得分
 * 给参加射击超过三次的选手求最大三次的分数总和，并排序，分数相同按照选手id降序排序
 *
 * 1、筛选出参加三次以上射击的选手
 * 2、计算选手的总分
 * 3、将选手按总分排序
 */
public class TestTwoTwo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int limit = 3;
        int[] num = new int[1000];
//3,3,7,4,4,4,4,7,7,3,5,5,5  68 + 16 + 100 = 184
//53,80,68,24,39,76,66,16,100,55,53,80,55
//        int N = 13;
//        int limit = 3;
//        int[] num = new int[1000];
        // hashMap
        HashMap<Integer, Participate> map = new HashMap<>();
        // 参赛次数的数组
        int[] countArray = new int[1000];

        // 选手参赛
        String parStrArray = sc.nextLine();
//        String parStrArray ="3,3,7,4,4,4,4,7,7,3,5,5,5";
        String[] parArray = parStrArray.split(",");
        List<Integer> parIntArray = Arrays.stream(parArray).map(Integer::parseInt).collect(Collectors.toList());
        String scoreStrArray = sc.nextLine();
//        String scoreStrArray = "53,80,68,24,39,76,66,16,100,55,53,80,55";
        String[] scoreArray = scoreStrArray.split(",");
        List<Integer> scoreIntArray = Arrays.stream(scoreArray).map(Integer::parseInt).collect(Collectors.toList());

        ArrayList<Participate> arrayList = new ArrayList<>(25);
        for (int i = 0; i < parIntArray.size(); i++) {
            arrayList.add(new Participate(parIntArray.get(i), scoreIntArray.get(i)));
        }

        for (int i = 0; i < parIntArray.size(); i++) {
            // 参赛次数++
            countArray[parIntArray.get(i)]++;
        }
        Map<Integer, List<Participate>> parScores = arrayList.stream().filter(x -> countArray[x.num] >= 3).collect(Collectors.groupingBy(Participate::getNum));
        List<Participate> collect = parScores.entrySet().stream().map(x -> {
            List<Integer> scoreList = x.getValue().stream().map(Participate::getScore).collect(Collectors.toList());
            int[] aList = new int[scoreList.size()];
            for (int i = 0; i < scoreList.size(); i++) {
                aList[i] = scoreList.get(i);
            }
            Arrays.sort(aList);
            int score = 0;
            for (int i = aList.length - 1; i >= aList.length - 3; i--) {
                score += aList[i];
            }
            return new Participate(x.getKey(), score);
        }).sorted((o1, o2) -> {
            int compare = Integer.compare(o2.score, o1.score);
            if (compare == 0) {
                return Integer.compare( o2.num, o1.num);
            } else {
                return compare;
            }
        }).collect(Collectors.toList());
        // 过滤无效的成绩
        for (int i = 0; i < collect.size(); i++) {
            System.out.print(collect.get(i).getNum());
            if (i != collect.size() - 1) {
                System.out.print(',');
            }
        }
    }

    static class Participate {
        Integer num;
        Integer score;

        public Participate() {

        }

        public Participate(Integer num, Integer score) {
            this.num = num;
            this.score = score;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Participate that = (Participate) o;
            return Objects.equals(num, that.num) &&
                    Objects.equals(score, that.score);
        }

        @Override
        public int hashCode() {
            return Objects.hash(num, score);
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }
    }
}
