package cn.tuhao.dp.decision.robot;

/**
 * 题目描述：求机器人的运动范围
 * 从(0,0)出发，不能达到(x,y)其中x+y>threshold的格子
 *
 * DFS递归解法
 */
public class RobotMoveCountTest {
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
        // 结束条件
        if(!check(visited, x, y)){
            return;
        }
        visited[x][y] = true;
        count++;
        dfs(visited, x, y);
    }

    /**
     * 深度优先遍历的递归实现
     * @param visited
     * @param x
     * @param y
     */
    private void dfs(boolean[][] visited, int x, int y) {
        // DFS，深度优先遍历。遍历选择列表:再从该点将上下左右四个方向移动
        for(int[] d: direction){
            int nx = x + d[0];
            int ny = y + d[1];
            backtrace(visited, nx, ny);
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

    public static void main(String[] args) {
        RobotMoveCountTest robotMoveCountTest = new RobotMoveCountTest();
        int threshold = 1,rows = 2,cols = 3;
        int i = robotMoveCountTest.movingCount(threshold, rows, cols);
        System.out.println(i);
    }
}
