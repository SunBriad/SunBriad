package netty.nio.channel.fileChannel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 部分批量写入操作
 */
public class FileChannelWrite {

    /**
     * 部分批量写入
     *
     * @param path 写入的文件路径
     * @throws IOException
     */
    public static void write(String path) throws IOException {
        if (path == null) {
            System.out.println("path 文件路径不能为null");
            return;
        }
        FileOutputStream fos = new FileOutputStream(new File(path));
        //获得写入的管道
        FileChannel fileChannel = fos.getChannel();
        // 给buffer 写入数据
        ByteBuffer byteBuffer1 = ByteBuffer.wrap("abcd".getBytes());
//        byteBuffer1.put("afgb".getBytes());
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(10);
        byteBuffer2.put("abdc".getBytes());
        ByteBuffer byteBuffer3 = ByteBuffer.allocate(10);
        byteBuffer3.put("1234".getBytes());
        // 翻转 建写入改为读取。
        byteBuffer2.flip();
        byteBuffer3.flip();
        ByteBuffer[] arrbuffer = new ByteBuffer[]{byteBuffer3, byteBuffer2};
        //第一次写入
        fileChannel.write(byteBuffer1);
        //  根据位置再次写入 之前的位置覆盖
        fileChannel.position(2);

        fileChannel.write(arrbuffer, 0, 2);

        fileChannel.close();
        fos.close();
    }

    /**
     *  写入文件 通过ｂｕｆｆｅｒ的位置　容量写入
     * @throws IOException
     */
    public static void write() throws IOException {
        // 获取文件输出流
        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Administrator\\IdeaProjects\\SunBird\\src\\main\\java\\netty\\nio\\channel\\fileChannel\\read.txt"));
        // 通过文件输出流获取文件管道
        FileChannel fileChannel = out.getChannel();
        // 获取Buffer
        ByteBuffer byteBuffer1 = ByteBuffer.wrap("abdec".getBytes());
        ByteBuffer byteBuffer2 = ByteBuffer.wrap("qwert".getBytes());
        //bufeer  位置写入
        byteBuffer1.position(1);
        byteBuffer1.limit(3);

        ByteBuffer byteBuffer3 = ByteBuffer.wrap("1234567".getBytes());
        // 截取345
        byteBuffer3.position(2);
        byteBuffer3.limit(5);
        ByteBuffer[] arrbuffer = new ByteBuffer[]{byteBuffer3, byteBuffer2,byteBuffer1};

        fileChannel.write(arrbuffer,0,arrbuffer.length);

        fileChannel.close();
        out.close();
    }


    public static void main(String[] args) throws IOException {
//        write("C:\\Users\\Administrator\\IdeaProjects\\SunBird\\src\\main\\java\\netty\\nio\\channel\\fileChannel\\wirte.txt");
            write();
    }
}
