package netty.nio.channel.fileChannel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannelTest {
 private   static  FileOutputStream fos =null;

    public static void writeTest() throws IOException {
        fos =new FileOutputStream(new File("C:\\Users\\Administrator\\IdeaProjects\\SunBird\\src\\main\\resources\\read.txt"));
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
            fos =new FileOutputStream(new File("C:\\Users\\Administrator\\IdeaProjects\\SunBird\\src\\main\\resources\\read.txt"));
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


    /**
     *  <p> 验证long write(ByteBuffer srcs) 方法 具有同步性 </p>
     * @param
     * @throws IOException
     */
    public static void writeTest1() throws IOException {
        FileChannel fileChannel= null;
        try {
            fos =new FileOutputStream(new File("C:\\Users\\Administrator\\IdeaProjects\\SunBird\\src\\main\\resources\\read.txt"));
            fileChannel = fos.getChannel();
            for (int i = 0; i < 10; i++){
                final FileChannel finalFileChannel = fileChannel;
                Thread thread =new Thread(){
                @Override
                public void run() {
                    try {
                        finalFileChannel.write(ByteBuffer.wrap("1234567\r\n".getBytes()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread thread1 =new Thread(){
                    @Override
                    public void run() {
                        try {
                            finalFileChannel.write(ByteBuffer.wrap("我是中国人\r\n".getBytes()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                thread1.start();
            }
            Thread.sleep(1000);
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (fileChannel!=null){
                fileChannel.close();
            }
        }
    }

    /**
     *  File Channel 管道读取文件
     * @throws FileNotFoundException
     */
    public static void readTest() throws IOException {
       //  第二种方式获取FileChannel
        RandomAccessFile raf = new RandomAccessFile("C:\\Users\\Administrator\\IdeaProjects\\SunBird\\src\\main\\resources\\read.txt","r");
          FileChannel fileChannel=raf.getChannel();
        // 获取ByteBuffer
             ByteBuffer buffer=ByteBuffer.allocate(100);
//        ByteBuffer buffer= ByteBuffer;
        int len = -1;
        // 翻转 。 buffer 默认是写入数据
//        buffer.flip();
        byte[] getByteArray  = buffer.array();
        while ((len = fileChannel.read(buffer)) != -1){
            fileChannel.position(3);
            System.out.println("limit :"+buffer.limit());
            System.out.println("position :"+buffer.position());
            System.out.println(len);
            fileChannel.position(4);
            buffer.clear();
            System.out.println("limit :"+buffer.limit());
            System.out.println("position :"+buffer.position());
//            System.out.println(len);
        }
        fileChannel.close();
    }

    public static  void readThreadTest() throws IOException, InterruptedException {
        final FileInputStream fis = new FileInputStream(new File("C:\\Users\\Administrator\\IdeaProjects\\SunBird\\src\\main\\resources\\read.txt"));
        final FileChannel fisChannel = fis.getChannel();
        for (int i =0; i < 1; i++){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    ByteBuffer b = ByteBuffer.allocate(10);
                    try {
                        int readLength = fisChannel.read(b);
                        while ((readLength = fisChannel.read(b)) != -1){


                            byte[] getByte = b.array();
                            b.clear();
                            System.out.println(new String(getByte,0,readLength));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread thread1 = new Thread(){
                Charset charset = Charset.forName("UTF-8");
                CharsetDecoder decoder = charset.newDecoder();
                @Override
                public void run() {
                    ByteBuffer b = ByteBuffer.allocate(10);
                    try {
                        int readLength = -1;

                        CharBuffer cBuf = CharBuffer.allocate(32);
                        decoder.decode(b, cBuf, true);
                        while ((readLength = fisChannel.read(b)) != -1){
                            byte[] getByte = b.array();
                            b.clear();
                            System.out.println("数据：" + new String(getByte,0,readLength));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
            thread1.start();
        }
        Thread.sleep(1000);
        fisChannel.close();
        fis.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        try {
//            writeTest();
//            writeTest(1);
//            writeTest1();
            readThreadTest();
//            readTest();
        }finally {
            Thread.sleep(1000);
            if (fos!= null)
            fos.close();
        }
    }
}

