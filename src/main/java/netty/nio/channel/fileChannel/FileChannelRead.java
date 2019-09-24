package netty.nio.channel.fileChannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelRead {


    public static  void  read() throws IOException {
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\Administrator\\IdeaProjects\\SunBird\\src\\main\\java\\netty\\nio\\channel\\fileChannel\\read.txt"));
        FileChannel fileChannel=fis.getChannel();
        ByteBuffer byteBuffer1=ByteBuffer.allocate(10);
        ByteBuffer byteBuffer2=ByteBuffer.allocate(10);
        ByteBuffer[] arrByte = new  ByteBuffer[]{byteBuffer1,byteBuffer2};
        long  len= fileChannel.read(arrByte);
       while (len != -1) {
           byteBuffer1.clear();
           byteBuffer1.clear();
           fileChannel.truncate(4);
           len= fileChannel.read(arrByte);
           for (int i = 0; i < arrByte.length; i++) {
               ByteBuffer byteBuffer = arrByte[i];
               byte[] getByteArry = byteBuffer.array();
               for (int j = 0; j < getByteArry.length; j++) {
                   if (getByteArry[j] == 0) {
                       System.out.print("空格");
                   } else {
                       System.out.print((char) getByteArry[j]);
                   }
               }
               System.out.println("");
               System.out.println("");
           }
       }
        fileChannel.close();
        fis.close();
    }

    public static void threadRead() throws IOException, InterruptedException {
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\Administrator\\IdeaProjects\\SunBird\\src\\main\\java\\netty\\nio\\channel\\fileChannel\\read.txt"));
        final FileChannel fileChannel = fis.getChannel();
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        ByteBuffer byteBuffer1 = ByteBuffer.allocate(8);
                        ByteBuffer byteBuffer2 = ByteBuffer.allocate(8);
                        ByteBuffer[] arrByte = new ByteBuffer[]{byteBuffer1, byteBuffer2};
                        long len = fileChannel.read(arrByte);
                        while (len != -1) {
                            synchronized (FileChannelRead.class) {
                                for (int j = 0; j < arrByte.length; j++) {
                                    ByteBuffer byteBuffer = arrByte[j];
                                    byte[] getByteArry = byteBuffer.array();
                                    for (int z = 0; z < getByteArry.length; z++) {
                                        if (getByteArry[z] == 0) {
                                            System.out.print("空格");
                                        } else {
                                            System.out.print((char) getByteArry[z]);
                                        }
                                    }
                                }
                                byteBuffer1.clear();
                                byteBuffer2.clear();
                                len = fileChannel.read(arrByte);
//                                fileChannel.truncate(2);
                                System.out.println( "通道的大小1"+fileChannel.size());

                            }
                        }
                    } catch (Exception e) {

                    }
                }
            };
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    try {
                        ByteBuffer byteBuffer1 = ByteBuffer.allocate(8);
                        ByteBuffer byteBuffer2 = ByteBuffer.allocate(8);
                        ByteBuffer[] arrByte = new ByteBuffer[]{byteBuffer1, byteBuffer2};
                        long len = fileChannel.read(arrByte);
                        while (len != -1) {
                            synchronized (FileChannelRead.class) {
                                for (int j = 0; j < arrByte.length; j++) {
                                    ByteBuffer byteBuffer = arrByte[j];
                                    byte[] getByteArry = byteBuffer.array();
                                    for (int z = 0; z < getByteArry.length; z++) {
                                        if (getByteArry[z] == 0) {
                                            System.out.print("空格");
                                        } else {
                                            System.out.print((char) getByteArry[z]);
                                        }
                                    }
                                }
                                // 清除 缓冲区内容
                                byteBuffer1.clear();
                                byteBuffer2.clear();
                                // 重新读取到缓冲区
//                                fileChannel.truncate(2);
                                len = fileChannel.read(arrByte);
                                System.out.println( "通道的大小2"+fileChannel.size());
                            }
                        }
                    } catch (Exception e) {

                    }
                }
            };
            thread1.start();
            thread.start();
        }


        Thread.sleep(3000);
        System.out.println("结束时间"+System.currentTimeMillis());
        fileChannel.close();
        fis.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        read();
        threadRead();
    }
}
