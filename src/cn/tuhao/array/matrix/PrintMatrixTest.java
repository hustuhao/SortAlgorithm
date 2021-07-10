package cn.tuhao.array.matrix;

import org.junit.Test;

import java.util.ArrayList;

/**
 * JZ19 顺时针打印矩阵

 */
public class PrintMatrixTest {

    /**
     * 大佬的解法
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrixOne(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return list;
        }
        int up = 0;
        int down = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;
        while(true){
            // 最上面一行
            for(int col=left;col<=right;col++){
                list.add(matrix[up][col]);
            }
            // 向下逼近
            up++;
            // 判断是否越界
            if(up > down){
                break;
            }
            // 最右边一行
            for(int row=up;row<=down;row++){
                list.add(matrix[row][right]);
            }
            // 向左逼近
            right--;
            // 判断是否越界
            if(left > right){
                break;
            }
            // 最下面一行
            for(int col=right;col>=left;col--){
                list.add(matrix[down][col]);
            }
            // 向上逼近
            down--;
            // 判断是否越界
            if(up > down){
                break;
            }
            // 最左边一行
            for(int row=down;row>=up;row--){
                list.add(matrix[row][left]);
            }
            // 向右逼近
            left++;
            // 判断是否越界
            if(left > right){
                break;
            }
        }
        return list;
    }

    /**
     * 自己的解法
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<>();
        }
        int cStart = 0, cEnd = matrix.length - 1;
        int rStart = 0, rEnd = matrix[0].length - 1;
        ArrayList<Integer> aList = new ArrayList<>();
        while (cStart <= cEnd && rStart <= rEnd) {
            //特殊情况：加入条件判断，防止出现单行或者单列的情况。
            if(cStart == cEnd){
                for(int j=rStart;j<=rEnd;j++){
                    aList.add(matrix[j][cStart]);
                }
                return aList;
            }
            if(rEnd == rStart){
                for(int i=cStart;i<=cEnd;i++){
                    aList.add(matrix[rStart][i]);
                }
                return aList;
            }
            // 左上角 -> 右上角
            for (int i = rStart; i <= rEnd; i++) {
                aList.add(matrix[cStart][i]);
            }
            cStart++;
            // 右上角 -> 右下角
            for (int i = cStart; i <= cEnd; i++) {
                aList.add(matrix[i][rEnd]);
            }
            rEnd--;

            // 右下角 -> 左下角
            for (int i = rEnd; i >= rStart; i--) {
                aList.add(matrix[cEnd][i]);
            }
            cEnd--;

            // 左下角 -> 左上角
            for (int i = cEnd; i >= cStart; i--) {
                aList.add(matrix[i][rStart]);
            }
            rStart++;
        }
        return aList;
    }

    /**
     * 将矩阵转置
     * 原文链接：https://blog.csdn.net/peach90/article/details/40422097
     * @param temp
     */
    public static void reverse(int temp [][] ) {
        for (int i = 0; i < temp.length; i++) {
            for (int j = i; j < temp[i].length; j++) {
                int k = temp[i][j];
                temp[i][j] = temp[j][i];
                temp[j][i] = k;
            }
        }
    }



    @Test
    public void test() {
        /**
         * [
         * [1,2,3,4],
         * [5,6,7,8],
         * [9,10,11,12],
         * [13,14,15,16]
         * ]
         *[1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10]
         */
        int[][] matrix = new int[4][4];
        matrix[0] = new int[]{1,2,3,4};
        matrix[1] = new int[]{5,6,7,8};
        matrix[2] = new int[]{9,10,11,12};
        matrix[3] = new int[]{13,14,15,16};

        int[][] matrix2 = new int[2][2];
        matrix2[0] = new int[]{1,2};
        matrix2[1] = new int[]{3,4};


        PrintMatrixTest printMatrixTest = new PrintMatrixTest();
        ArrayList<Integer> arrayList = printMatrixTest.printMatrix(matrix2);
        System.out.println(arrayList);
    }
}
