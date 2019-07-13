package cn.tuhao.sort.test;

/**
 * 可以设计一个公用的查询测试类
 * 不过所有的查询(xxxSearch)应该继承Search接口，或者类
 * 设计模式：命令模式那种
 */
public class BaseSearchTest {
    /*测试数字：1,30,8,7,50,-5*/
    public final int test[]    = new int[]{1,30,8,7,50,-5};
    public final int predict[] = new int[]{0,6,-1,3,-1,-1};

}
