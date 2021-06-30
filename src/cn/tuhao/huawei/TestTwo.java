//package cn.tuhao.huawei;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class TestTwo {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
////        int N = sc.nextInt();
////        int limit = 3;
////        int[] num = new int[1000]; 13
////3,3,7,4,4,4,4,7,7,3,5,5,5  68 + 16 + 100 = 184
////53,80,68,24,39,76,66,16,100,55,53,80,55
//        int N = 13;
//        int limit = 3;
//        int[] num = new int[1000];
//        // hashMap
//        HashMap<Integer, Participate> map = new HashMap<>();
//        // 参赛次数的数组
//        int[] countArray = new int[1000];
//
//        // 选手参赛
////        String parStrArray = sc.nextLine();
//        String parStrArray ="3,3,7,4,4,4,4,7,7,3,5,5,5";
//        String[] parArray = parStrArray.split(",");
//        List<Integer> parIntArray = Arrays.stream(parArray).map(Integer::parseInt).collect(Collectors.toList());
////        String scoreStrArray = sc.nextLine();
//        String scoreStrArray = "53,80,68,24,39,76,66,16,100,55,53,80,55";
//        String[] scoreArray = scoreStrArray.split(",");
//        List<Integer> scoreIntArray = Arrays.stream(scoreArray).map(Integer::parseInt).collect(Collectors.toList());
//        for (int i = 0; i < parIntArray.size(); i++) {
//            // 参赛次数++
//            countArray[parIntArray.get(i)]++;
//            Participate participate = map.get(parIntArray.get(i));
//            if (null == participate) {
//                map.put(parIntArray.get(i), new Participate(parIntArray.get(i), scoreIntArray.get(i)));
//            } else {
//                participate.score = participate.score + scoreIntArray.get(i);
//            }
//        }
//
//        // 过滤无效的成绩
//        List<Participate> collect = map.entrySet().stream().filter(x -> countArray[x.getKey()] >= 3).map(x -> x.getValue()).sorted(new Comparator<Participate>() {
//            @Override
//            public int compare(Participate o1, Participate o2) {
//                return Integer.compare(o2.score, o1.score);
//            }
//        }).collect(Collectors.toList());
//        System.out.println(collect);
//
//    }
//
//    static class Participate {
//        Integer num;
//        Integer score;
//
//        public Participate() {
//
//        }
//
//        public Participate(Integer num, Integer score) {
//            this.num = num;
//            this.score = score;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Participate that = (Participate) o;
//            return Objects.equals(num, that.num) &&
//                    Objects.equals(score, that.score);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(num, score);
//        }
//
//        public Integer getNum() {
//            return num;
//        }
//
//        public void setNum(Integer num) {
//            this.num = num;
//        }
//
//        public Integer getScore() {
//            return score;
//        }
//
//        public void setScore(Integer score) {
//            this.score = score;
//        }
//    }
//}
