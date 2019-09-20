package thread.fairLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 *  公平锁 、先创建先行
 */
public class LockService {

     private ReentrantLock lock;

     public LockService(boolean isFair){
         super();
         lock = new ReentrantLock(isFair);
     }

     public void serviceMethod(){
         try{
             lock.lock();
             System.out.println("ThreadName=" + Thread.currentThread().getName()+"获得锁定");

         }finally {
             lock.unlock();
         }
     }

}
