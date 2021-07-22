package cn.tuhao.dp.decision.maze;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 迷宫问题：https://zhuanlan.zhihu.com/p/114936084
 * 核心：
 * 1、栈或者队列：存储这条路径，
 * 2、二维数组：访问状态，避免在一条圈上反复寻找，造成死循环。
 */
public class MazeSolutionOne {
    /**
     * 迷宫是否有出口
     * @param nums
     * @return 一条出去的路
     */
    public List<int[]> solutionDFS(int[][] nums){
        int rows = nums.length;
        int cows = nums[0].length;
        boolean[][] visited = new boolean[rows][cows];
        Stack<Position> stack = new Stack<>();
        // 记录一下当前位置，在【起点】
        Position p = new Position(0,0);
        stack.add(p);
        // true:表示已经访问过了，false表示未访问过
        visited[0][0] = true;
        Position temp;
        //  只要找到了【终点】就退出循环， 始终没有找到，也会导致栈弹空
        while (!stack.isEmpty()&& !(p.row == rows-1 && p.cow == cows-1)){
            // 获取上一个访问过的位置
            p = stack.peek();
            //  按照方向  → ↓  ←  ↑的顺序依次进行试探性的走一步
            //  如果能走通（在迷宫范围内，不是墙，而且没有访问过，就可以认为是可以走）
            if (p.cow+1<cows && nums[p.row][p.cow+1] == 0 && !visited[p.row][p.cow+1]){
                temp = new Position(p.row,p.cow+1);
                stack.add(temp);
                visited[temp.row][temp.cow] = true;
            } else if (p.row+1<rows && nums[p.row+1][p.cow] == 0 && !visited[p.row+1][p.cow]){
                temp = new Position(p.row+1,p.cow);
                stack.add(temp);
                visited[temp.row][temp.cow] = true;
            } else if (p.cow-1>-1 && nums[p.row][p.cow-1] == 0 && !visited[p.row][p.cow-1]) {
                temp = new Position(p.row,p.cow-1);
                stack.add(temp);
                visited[temp.row][temp.cow] = true;
            } else if (p.row-1 >-1 && nums[p.row-1][p.cow] == 0 && !visited[p.row-1][p.cow]){
                temp = new Position(p.row-1,p.cow);
                stack.add(temp);
                visited[temp.row][temp.cow] = true;
            } else {
                // 如果没有尝试了四个方向都没有走通，说明上一个点的选取有问题，直接弹出
                stack.pop();
            }
        }
        // 最后根据还在栈里的的元素，推导出一挑可用路径
        if (stack.isEmpty()) return new LinkedList<>();
        Deque<int[]> deque = new LinkedList<>();
        for (Position po:stack) {
            deque.addLast(new int[]{po.row,po.cow});
        }
        return (List)deque;
    }
}
