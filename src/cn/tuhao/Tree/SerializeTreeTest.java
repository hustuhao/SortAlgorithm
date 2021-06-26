package cn.tuhao.Tree;

import cn.tuhao.common.TreeNode;

import java.util.LinkedList;

public class SerializeTreeTest {
    public static void main(String[] args) {
        /**
         *     8
         *    / \
         *   6   10
         *  / \  /\
         * 5  7 9 11
         * 8|6|10|5|7|9|11|#|#|#|#|
         */
        TreeNode one = new TreeNode(8);
        TreeNode two = new TreeNode(6);
        TreeNode three = new TreeNode(10);
        TreeNode four = new TreeNode(5);
        TreeNode five = new TreeNode(7);
        TreeNode six = new TreeNode(9);
        TreeNode seven = new TreeNode(11);

        one.left = two;
        one.right = three;
        two.left = four;
        two.right = five;
        three.left = six;
        three.right = seven;
        SerializeTreeTest serializeTreeTest = new SerializeTreeTest();
        String serialize = serializeTreeTest.Serialize(one);
        TreeNode deserializeTree = serializeTreeTest.Deserialize(serialize);
        /**
         *     5
         *   4  #
         *  3
         * # 2
         */
        TreeNode anOther = serializeTreeTest.Deserialize("5|4|-10086|3|-10086|-10086|2|-10086|-10086|");
        System.out.println("----------------------");
    }

    /**
     * 设置空节点的数值
     */
    static int NULL_NODE = -10086;
    /**
     * 空节点的字符串值
     */
    static String NULL_NODE_STR = "-10086";

    /**
     * 层序遍历列化二叉树
     * 等价于 BFS 遍历二叉树
     * 核心思路：利用队列
     * @param root 根节点
     * @return 以 | 分割的 BFS 结果
     */
    String Serialize(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.remove(0);
            if (null == curNode) {
                sb.append(NULL_NODE).append('|');
            } else {
                sb.append(curNode.val).append('|');
                queue.offer(curNode.left);
                queue.offer(curNode.right);
            }
        }
        return sb.toString();
    }
    TreeNode Deserialize(String str) {
        // 1|获取字符串数组
        String[] nodeStrList = str.split("\\|");
        // 2|创建所有的节点
        TreeNode[] nodeArray = createNodeArray(nodeStrList);
        // 3|增加节点之间的父子关系
        addRelation(nodeArray);
        // 4|返回根节点
        return nodeArray[0];
    }

    /**
     * 给树数组增加父子关系
     * @param nodeArray
     */
    private void addRelation(TreeNode[] nodeArray) {
        // 两个指针，m指向父节点，n指向两个子节点：起始位置是m指向父节点，n指向左孩子节点
        int m = 0, n = 1;
        while (m < nodeArray.length) {
            // 当父节点为空时，跳过该节点，继续下一个父节点
            if (null == nodeArray[m]) {
                m++;
                continue;
            }
            // 给父节点的左孩子节点赋值
            nodeArray[m].left = n < nodeArray.length ? nodeArray[n] : null;
            // 给父节点的右孩子节点赋值
            nodeArray[m].right = n + 1 < nodeArray.length ? nodeArray[n + 1] : null;
            // 父节点的指针移动
            m ++;
            // 子节点的指针移动
            n +=2;
        }
    }

    /**
     * 根据字符串数组创建没有父子关系的树节点数组
     * @param nodeStrList
     * @return
     */
    private TreeNode[] createNodeArray(String[] nodeStrList) {
        // 判断根节点是否为空
        if (NULL_NODE_STR.equals(nodeStrList[0])) {
            // 只有根节点，且根节点为空
            return new TreeNode[1];
        }
        TreeNode[] nodeList = new TreeNode[nodeStrList.length];
        for (int i = 0; i < nodeStrList.length; i++) {
            nodeList[i] = getTreeNode(nodeStrList[i]);
        }
        return nodeList;
    }

    /**
     * 根据字符串的值获取节点
     * @param value 节点的字符串值，节点的值为"-10086"表示空节点
     * @return 节点
     */
    TreeNode getTreeNode(String value) {
        if (NULL_NODE_STR.equals(value)) {
            return null;
        } else {
            return new TreeNode(Integer.parseInt(value));
        }
    }
}
