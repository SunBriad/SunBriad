package netty.nio.channel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
 private   static  FileOutputStream fos =null;

    public static void writeTest() throws IOException {
        fos =new FileOutputStream(new File("C:\\Users\\Administrator\\IdeaProjects\\SunBird\\src\\main\\resources\\abc.txt"));
        //通过文件输出流 获得管道
        FileChannel fchannel = fos.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.wrap("abcd".getBytes());
        // 查看position 变化
        System.out.println("A fileChannel position:" + fchannel.position());
        System.out.println("write 返回值:" + fchannel.write(byteBuffer));
        System.out.println("after fileChannel position:" + fchannel.position());
        fchannel.position(2);
        byteBuffer.rewind();//倒带 可以重新写入一次
        // 从当前的位置开始写入
        System.out.println("write 返回值："+ fchannel.write(byteBuffer));
        System.out.println("rewind after fileChannel position : "+fchannel.position());
        fchannel.close();


    }

    /**
     *  <p> 验证long write(ByteBuffer[] srcs) 方法将 ByteBuffer 写入remaining </p>
     * @param count
     * @throws IOException
     */
    public static void writeTest(int count) throws IOException {
        FileChannel fileChannel= null;
        try {
            fos =new FileOutputStream(new File("C:\\Users\\Administrator\\IdeaProjects\\SunBird\\src\\main\\resources\\abc.txt"));
            fileChannel = fos.getChannel();
            fileChannel.write(ByteBuffer.wrap("1234567".getBytes()));
            fileChannel.position(3);

            ByteBuffer byteBuffer = ByteBuffer.wrap("abcde1".getBytes());
            ByteBuffer byteBuffer1 =ByteBuffer.wrap("abcde2".getBytes());
            ByteBuffer[] byteBuffers = new ByteBuffer[]{byteBuffer,byteBuffer1};
            byteBuffer.position(1);
            byteBuffer.limit(3);
            System.out.println("byteBuffer after fileChannel position : "+fileChannel.position());
            byteBuffer1.position(2);
            byteBuffer1.limit(4);
            System.out.println("byteBuffer1 after fileChannel position : "+fileChannel.position());
            fileChannel.write(byteBuffers);
            System.out.println("rewind after fileChannel position : "+fileChannel.position());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
               if (fileChannel!=null){
                   fileChannel.close();
               }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            writeTest();
            writeTest(1);
        }finally {
            Thread.sleep(1000);
            if (fos!= null)
            fos.close();
        }
    }
}

