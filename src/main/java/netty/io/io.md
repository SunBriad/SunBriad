 ## Netty 能解决什么问题
   框架 ： 就是为了简化一系列解决方案的集合，封装IO操作的集合。
   在复杂的业务场景中解决 IO 与 多线程的问题。
 ## 为什么要封装IO 操作
   1、 Java 中 BIO 、 NIO 、AIO  之间的区别已经应用场景
- IO （BIO）   
 &emsp;&emsp; 同步阻塞IO ： 就是我们普通调用IO ，在IO 进行accpet() 接入是 是属于阻塞的。只能等待数据操作完成写、或者读过后再进行下一步。
- NIO 同步非阻塞IO :  
  &emsp;&emsp; NIO 有三个宝贝 Channel(管道)、Buffer(缓冲)、Selector（选择器）。  
  &emsp;&emsp;&emsp;&emsp;  Channel(管道)：  
  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp; （1）管道是既可以进行读操作又可以进行写操作  
  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp; （2）但是调用对象必须是Buffer  
  &emsp;&emsp;&emsp;&emsp;  Buffer(缓冲)：  
  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 用于缓冲的区域 里面有下面几个属性，对于缓冲容器进行操作。所以Buffer  就是一个容器。  
                   
      // Invariants: mark <= position <= limit <= capacity  
            private int mark = -1;  
            private int position = 0; // 数组当前数值当前位置  
            private int limit;  //当前操作的访问值范围  
            private int capacity; // 大小容量 
            
  &emsp;&emsp;&emsp;&emsp; Selector(选择器)：  
  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 通过selector 选择是哪个来进行数据交互，可以看是哪种状态、数据连接、数据可读、数据可写、数据无效等。  
 - AIO ansyc IO 异步非阻塞IO :   
 &emsp;&emsp; 通过多个线程进行异步化处理。一般操作系统决定性能。通过操作系统调用数据。
          
        
   2、 阻塞与非阻塞的区别  
   阻塞 ： 往往需要等待缓存区里的数据准备好过后才处理其他的事情，否则一直在等待。
   非阻塞 ： 当我们的进程访问我们数据缓冲区的时候，如果数据没有准备好则直接返回，不会等待。如果数据准备好了，也直接返回。
  
   3、  
   Input  ： 往内存里面写东西  
   Output :  在内存里面读东西
