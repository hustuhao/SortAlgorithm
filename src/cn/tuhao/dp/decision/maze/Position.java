package cn.tuhao.dp.decision.maze;

/**
 * 表示坐标
 */
public class Position{
    int row;
    int cow;
    public Position(int row,int cow){
        this.cow = cow;
        this.row = row;
    }
    public void show(){
        System.out.println(this.row + "    " + this.cow);
    }
}