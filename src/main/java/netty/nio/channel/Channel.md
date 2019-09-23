## Channel　管道
 NIO中一个连接就是用一个Channel 来表示。一个管道就是一个底层的文件描述符。 Java NIO 通道类型接口有11 种，和12种实现最为重要的实现是 
  FileChannel、 ServerSocketChannel 、 SocketChannel、 DatagramChannel这四种。
  ### 接口
    这些管道接口都是 继承 Channel .而Channel 实现了Closeable ，可以自动关闭，而不是需要显示式的调用close();
![Channel 接口](https://img-blog.csdnimg.cn/20190923132447283.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM4ODI4Nzc2,size_16,color_FFFFFF,t_70)
 
 ### 实现
 1) **FileChannel : 文件管道，用于文件的数据读写 ，继承了AbstractInterruptibleChannel**
 2) **ServerSocketChannel ：  服务器嵌套字管道， 允许监听TCP 连接请求，为每一个监听请求建立一个SocketChannel**
 3) **SocketChannel : 套接字管道，用于Socket 套接字TCP 连接的数据读写**
 4) **DatagramChannel : 数据报管道，用于UDP 协议的读写数据。**
 5) AsynchronousFileChannel ：
 6) AsynchronousServerSocketChannel ： 
 7) AsynchronousSocketChannel :
 8) Pipe.SourceChannel :
 9) Pipe.SinkChannel :
 10) SelectableChannel :
 11) AbstractInterruptibleChannel :  作用是提供了一个可以被中断的管道基本实现
 12) AbstractSelectableChannel :
 
 #### FileChannel 类
 
    FlieChannel 的主要作用是对文件进行读、写、映射和操作文件的通道。该通道是阻塞操作。
    FileChannel 在内部维护当前文件的position ，可对其进行查看和修改。
    除了字节通道中常见的读取＼写入和关闭操作外，此类还有定义了下列特定于文件的操作
    1) 以不影响通道当前位置的方式，对文件中绝对位置的字节进行读取或者写入。
    2) 将文件中某个区域直接映射到内存中，对于较大的文件，这通常比调用read()和 write () 方法更加高效。
    3) 强制对底层存储设备进行文件更新，确保在系统崩溃的时候不丢失数据。
    4）以一种可被很多操作系统优化为直接向文件系统缓存发送或者从中读取的高速传输方法，将字节从文件传输到某一个其他通道。
    5）可以锁定某个文件区域，以阻止其他程序对其进行访问。
    
 此类没有定义打开现有文件或者创建新的文件的方法，只能从FileInputStream 、 FileOutputStream 、 RandomAccessFile 对象中获取文件管道， 这个会返回一个连接到相同底层文件
 的文件通道。 通过FileInputStream 实例getChannel(); 获取FileChannel  通道进行读取操作。 只能从FileOutputStream 实例getChannel(); 只能获取FileChannel 通道的写入操作。
 RandomAccessFile 可以设置 "r" 、 "w" 、"rw" 三种模式实例， 来获取不同的操作.
 
     
 
 