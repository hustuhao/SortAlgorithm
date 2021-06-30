package cn.tuhao.dp.decision.robot;

import java.util.LinkedList;

/**
 * dfs 非递归解法
 */
public class RobotMoveCountDfsNoRecursiveTest {
    private static final int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static int staticThreshold;
    public static int staticRows;
    public static int staticCols;
    public static int count = 0;

    /**
     * 题目分解：
     * 1、计算能够进入的总格子数
     * 2、计算数位之和
     * 3、判断是否能够进入目标格子
     * 4、深度优先遍历或者广度优先遍历
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public int movingCount(int threshold, int rows, int cols) {
        // 初始化坐标轴
        boolean[][] visited = new boolean[rows][cols];
        staticThreshold = threshold;
        staticRows = rows;
        staticCols = cols;
        backtrace(visited, 0, 0);
        return count;
    }

    /**
     * 回溯算法核心
     * @param visited
     * @param x
     * @param y
     */
    private void backtrace(boolean[][] visited, int x, int y) {
        LinkedList<Point> stack = new LinkedList<>();
        stack.push(new Point(x, y));
        // 深度优先遍历的非递归实现
        while (!stack.isEmpty()) {
            // 结束条件
            Point pos = stack.pop();
            if(!check(visited, pos.x, pos.y)){
                continue;
            }
            visited[x][y] = true;
            count++;
            for(int[] d: direction){
                int nx = x + d[0];
                int ny = y + d[1];
                // 如果访问过，就不加入栈中
                if (!visited[nx][ny]) {
                    stack.push(new Point(nx, ny));
                }
            }
        }
    }

    /**
     * 判断机器人是否能够进入坐标为(row,col)的方格
     * @param visited
     * @param row
     * @param col
     * @return
     */
    private boolean check(boolean[][] visited, int row, int col){
        return row >= 0 && row < staticRows && col >= 0 && col < staticCols
                && getDigitSum(row) + getDigitSum(col) <= staticThreshold
                && !visited[row][col];
    }

    /**
     * 计算数位之和
     * @param number
     * @return
     */
    int getDigitSum(int number){
        int sum = 0;
        while(number > 0 ){
            sum += number % 10;
            number /=10;
        }
        return sum;
    }

    class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        RobotMoveCountTest robotMoveCountTest = new RobotMoveCountTest();
        int threshold = 1,rows = 2,cols = 3;
        int i = robotMoveCountTest.movingCount(threshold, rows, cols);
        System.out.println(i);
    }
}
