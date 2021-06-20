package cn.tuhao.big.bitmap;

/**
 *
 * @author turato
 * @date 2021-06-20
 * @desc bitmap 测试
 * */
public class BitMapTest {
    public static void main(String[] args) {
        /*下面是 Redis bitmap 命令
        > SETBIT bitmapsarestrings 2 1
        > SETBIT bitmapsarestrings 3 1
        > SETBIT bitmapsarestrings 5 1
        > SETBIT bitmapsarestrings 10 1
        > SETBIT bitmapsarestrings 11 1
        > SETBIT bitmapsarestrings 14 1
        > GET bitmapsarestrings
        "42"
         */
        String bitmap = "42";
        // 字节数组：50,52
        byte[] bytes = bitmap.getBytes();
        // bit 数量
        int total_bits = bitmap.length() * 8;
        // 查询字节数组中某一位的值：(bytes[byteOffset] << bitOffset) & 0x80;
        for (int i = 0; i < total_bits; i++) {
            // 找到在字节数组中的位置
            int byteOffset = i / 8;
            // 取出这一位的偏移量
            int bitOffset = i % 8;
            // 取出这一位的值
            int bitValue = (bytes[byteOffset] >> (8 - bitOffset -1)) & 0x1;
            /*下面似乎更好理解
            // 取出这一位的偏移量
            int bitOffset = i % 8;
            // 取出这一位的值
            int bitValue = (bytes[byteOffset] << bitOffset & 0x80;
             */
            System.out.println("bitArray["+ (byteOffset * 8 + bitOffset)+"]" + bitValue);
        }
        /* 结果：
        bitArray[0]0
        bitArray[1]0
        bitArray[2]1
        bitArray[3]1
        bitArray[4]0
        bitArray[5]1
        bitArray[6]0
        bitArray[7]0
        bitArray[8]0
        bitArray[9]0
        bitArray[10]1
        bitArray[11]1
        bitArray[12]0
        bitArray[13]0
        bitArray[14]1
        bitArray[15]0
         */
    }
}
