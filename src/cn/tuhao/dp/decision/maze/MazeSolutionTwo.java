package cn.tuhao.dp.decision.maze;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 出去的最短路径
 * 核心思路：求点到起点的最近距离。
 */
public class MazeSolutionTwo {
    public int solutionBFS(int[][] nums){
        int rows = nums.length;
        int cows = nums[0].length;
        int[][] count = new int[rows][cows];
        //  首先对计数的数组进行初始化
        for (int i = 0;i<rows;i++){
            Arrays.fill(count[i],Integer.MAX_VALUE);
        }
        // 确定起点
        count[0][0] = 0;
        //  由于深度优先算法是和遍历的层数有关的，所以我们使用双向链表来操作
        //  前面添加，后面取用（还可以使用两个栈来进行交替使用）
        Deque<Position> deque = new LinkedList<>();
        Position p = new Position(0,0);
        deque.add(p);
        int[] r = {0,1,0,-1};
        int[] c = {1,0,-1,0};
        while (!deque.isEmpty()){
            p = deque.pollLast();
            // 四个方向
            for (int i = 0; i<4;i++){
                int tempR = p.row+r[i];
                int tempC = p.cow+c[i];
                // 迷宫边界和障碍
                if (tempR>-1 && tempR<rows && tempC>-1 && tempC<cows && nums[tempR][tempC] == 0){
                    //  如果能够进行更新，那就将这个位置再次压入队列中，等待下一次更新
                    if (count[tempR][tempC] > count[p.row][p.cow]+1){
                        count[tempR][tempC] = count[p.row][p.cow]+1;
                        Position temp = new Position(tempR,tempC);
                        deque.addFirst(temp);
                    }
                }
            }
        }
        // 确定终点
        return count[rows-1][cows-1];
    }
}
