package netty.nio.buffer;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.util.logging.Logger;

public class Test {

    static IntBuffer intBuffer = null;
    static DoubleBuffer doubleBuffer = null;

    /**
     * 新建一个Buffer 区
     */
    public static void allocatTest() {
        //调用allocate 方法，而不是使用new
        intBuffer = IntBuffer.allocate(20); //设置缓冲区大小
        doubleBuffer = DoubleBuffer.allocate(100);
        // s输出buffer 的主要属性值
        System.out.println(doubleBuffer.capacity());
        System.out.println("----after allocate ------");
        System.out.println("postition =" + intBuffer.position());
        System.out.println("limit =" + intBuffer.limit());
        System.out.println("capacity =" + intBuffer.capacity());
        System.out.println("mark=" + intBuffer.mark());
    }

    /**
     * 给缓冲区容器给值
     */
    public static void putTest() {
        //调用allocate 方法，而不是使用new
        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }

        System.out.println("----after put ------");
        System.out.println("postition =" + intBuffer.position());
        System.out.println("limit =" + intBuffer.limit());
        System.out.println("capacity =" + intBuffer.capacity());
        System.out.println("mark=" + intBuffer.mark());
    }

    /**
     * 翻转缓冲区 ，将写入转变为读取模式
     */
    public static void flipTest() {
        intBuffer.flip();
        System.out.println("----after flip ------");
        System.out.println("postition =" + intBuffer.position());
        System.out.println("limit =" + intBuffer.limit());
        System.out.println("capacity =" + intBuffer.capacity());
        System.out.println("mark=" + intBuffer.mark());

    }

    /**
     * 读取模式下得到数据
     */
    public static void getTest() {
        for (int i = 0; i < 2; i++) {
            int j = intBuffer.get();

            System.out.println(j);
            if ( i == 1){
                intBuffer.mark();
            }
        }
        intBuffer.reset();
        System.out.println("----after get ------");
        System.out.println("postition =" + intBuffer.position());
        System.out.println("limit =" + intBuffer.limit());
        System.out.println("capacity =" + intBuffer.capacity());
        System.out.println("mark=" + intBuffer.mark());
    }

    /**
     * 倒带 ，重新读取一次数据
     */
    public static void rewindTest() {
        intBuffer.rewind();
        System.out.println("----after rewind ------");
        System.out.println("postition =" + intBuffer.position());
        System.out.println("limit =" + intBuffer.limit());
        System.out.println("capacity =" + intBuffer.capacity());
        System.out.println("mark=" + intBuffer.mark());
    }

    /**
     *  压缩  // 翻转为写的模式  压缩
     */
    public static void compactTest() {
        intBuffer.compact();
        System.out.println("----after compact ------");
        System.out.println("postition =" + intBuffer.position());
        System.out.println("limit =" + intBuffer.limit());
        System.out.println("capacity =" + intBuffer.capacity());
        System.out.println("mark=" + intBuffer.mark());
    }


    /**
     *  清除之前的 标志
     */
    public static void clearTest() {
        intBuffer.clear();
        System.out.println("----after clear ------");
        System.out.println("postition =" + intBuffer.position());
        System.out.println("limit =" + intBuffer.limit());
        System.out.println("capacity =" + intBuffer.capacity());
        System.out.println("mark=" + intBuffer.mark());
    }

    public static void main(String[] args) {
        Test.allocatTest();
        Test.putTest();
        Test.flipTest();
        Test.getTest();
        Test.rewindTest();
        Test.compactTest();
        Test.clearTest();
    }
}
