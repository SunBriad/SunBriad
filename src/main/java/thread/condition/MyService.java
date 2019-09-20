package thread.condition;

import java.math.BigDecimal;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  仓库
 */
public class MyService {
   private ReentrantLock lock = new ReentrantLock();
   private Condition condition = lock.newCondition();
    BlockingQueue queue = new LinkedBlockingDeque(10);
    private BigDecimal price;
   private boolean hasValue = false;

   public void set(){
       try{
           lock.lock();
           while (hasValue == true){
               System.out.println("打印❤❤");
               condition.await();;
           }
           queue.offer("a");
           
           System.out.println("打印❤");
           hasValue =true;
           //唤醒 消费线程
           condition.signal();
       }catch (InterruptedException e){
           e.printStackTrace();
           System.out.println("出现了线程中断异常");
       }finally {
           lock.unlock();
       }
   }

   public void get(){
       try{
           lock.lock();
           while (hasValue == false){
               System.out.println("消费☆☆");
               condition.wait();
           }
           System.out.println("消费☆");
           hasValue = false;
           condition.signal();
       }catch (InterruptedException e){
           System.out.println("消费者中断");
       }
       finally {
           lock.unlock();
       }
   }
}
