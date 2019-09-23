NIO Buffer 详解
 在 Buffer 里面存在 四个属性
    private int mark = -1;
    private int position = 0;
    private int limit;
    private int capacity;
 
- mark 标记属性主要是记录 position  \ limit \ capactity 。可以将当前的 position 临时标记在mark 中，需要时再重新拿出来
   
- position 表示当前的位置, position 属性与缓冲区的读写模式有关，在不同的模式下position 位置不一致，当缓冲区进行读写的模式改变，position 会进行改变。  
&emsp;&emsp;在写入的模式下： 
&emsp;&emsp;&emsp;&emsp;（1） 刚刚进入到写模式时，position 为 0 ， 表示当前的写入从 0 开始。  
&emsp;&emsp;&emsp;&emsp;（2） 每当一个数据写到缓冲区后，position 会向后移动到下一个可写的位置。  
&emsp;&emsp;&emsp;&emsp;（3） 初始的position 值为 0 ，最大的position 为里 limit - 1。当 position 等于 limit 时 ,缓冲区就无空间可写。  
&emsp;&emsp;在读取的模式下：  
&emsp;&emsp;&emsp;&emsp;（1） 当缓冲区刚进入读取模式时，position 也会被置 0 。  
        （2） 当从缓冲区读取时，也是从position 位置开始读。读取数据后position 向前移动到下一个可读的位置。  
        （3） position 的最大上限也是 limit ，position 达到 limit 时表示无数据可读。  
     调用 flip() 方法 对读取模式和写入模式进行翻转。<br>
    
- limit 表示读写的最大上限。 也是和模式有一定的关系。 
    在写入的情况下 ：代表可以写入的数据最大上限。在刚刚进入到写的模式时候limit 会被设置为 capactiy 的大小。
    在读取的情况下 ：代表的最多能够从缓冲区读取到多少数据. 
   
- capacity 表示内部容量的大小。一旦写入的对象数据大小超过了capacity ， 缓冲区就满了，不能在写入了。
    capacity 一旦初始化，就不能改变了。Buffer 类的对象在初始化时，会按照capacity 分配内存。

 在 Buffer 里面存在的方法
   
```java
    public abstract class Buffer{    
            private int mark = -1; // 标记记录
            private int position = 0; // 当前标记位置
            private int limit; // 最大的上限
            private int capacity; // 初始化容器的容量 
        //capacity > limit > position > mark
        //构造函数 通过这个创建一个内存容量 
       Buffer(int mark, int pos, int lim, int cap) {       // package-private
        // 总容量不能小于 0 
         if (cap < 0)
             throw new IllegalArgumentException("Negative capacity: " + cap);
        //  设置 内存值容量值
         this.capacity = cap;
         //设置最大的上限值
         limit(lim);
         //当前位置的标志位
         position(pos);
        // 记录临时当前记录  
         if (mark >= 0) {
        //如果 mark  大于0 并且 大于当前位置 抛出异常 mark 大于position
             if (mark > pos)
                 throw new IllegalArgumentException("mark > position: ("
                                                    + mark + " > " + pos + ")");
         //设置临时当前位置的记录   
             this.mark = mark;
         }
     }
   
        /**
          * 扩张设置上限值  limit  <  capacity 小于最大容量值
          * @param  newLimit 新的limit 长度
         **/    
public final Buffer limit(int newLimit) {
    
        if ((newLimit > capacity) || (newLimit < 0))
            throw new IllegalArgumentException();
        
        limit = newLimit;
    
        if (position > limit) position = limit;
    
        if (mark > limit) mark = -1;
    
        return this;
    }
    /**
      * 设置前标志的当前位置
      * @param  newPosition 新的当前位置
     **/
    public final Buffer position(int newPosition) {
               //capacity > limit > position > mark
        if ((newPosition > limit) || (newPosition < 0))
            throw new IllegalArgumentException();
        // 设置新的位置信息
        position = newPosition;
        //capacity > limit > position > mark
        if (mark > position) mark = -1;
        return this;
    }
    
    /**
      * 翻转  （当缓冲区处于写的模式时，调用flip () ，就会将缓冲区变为读的模式） 
     **/
    public final Buffer flip() {
        limit = position; // 将当前缓冲区的最大当前位置 设置为最大可以扩展容量
        position = 0; // 当前 位置重置为 0 
        mark = -1; // 清除之前的mark 标记
        return this;
    }
    /**
    * 清空之前的数据 ，变为写入模式
    **/
   public final Buffer clear() {
        position = 0;
        limit = capacity;
        mark = -1;
        return this;
    }
    
    /**
      * 倒带， 如果数据读完一遍 可以再重新读取一遍
     **/
   public final Buffer rewind() {
        position = 0; // 重置为 0 ，所以可以重新读取缓冲区
        mark = -1; // mark 标记被清除，表示之前的临时位置不可以再用
        return this;
    }
    /**
     *  将当前的position 的值保存起来，放到mark 中
    **/   
    public final Buffer mark() {
        mark = position;
        return this;
    }



    /**
     *  将mark 的值恢复到 position 中
     **/
    public final Buffer reset() {
        int m = mark;
        if (m < 0)
            throw new InvalidMarkException();
        position = m;
        return this;
    }


}
``` 