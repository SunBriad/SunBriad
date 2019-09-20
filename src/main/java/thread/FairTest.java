package thread;

import org.junit.Test;
import thread.fairLock.LockService;

public class FairTest {

    /**
     *  公平锁
     */
    @Test
    public void test(){
      final LockService lockService = new LockService(true);
      Runnable runnable = new Runnable() {
          public void run() {
              System.out.println("☆线程："+Thread.currentThread().getName()+"运行了");
              lockService.serviceMethod();
          }
      };
      Thread[] threads = new Thread[10];
      for (int i = 0;i < 10;i++){
          threads[i] =new Thread(runnable);
      }
        for (int i = 0;i < 10;i++){
        threads[i].start();
        }

    }

    /**
     *  不公平锁
     */
    @Test
    public void test1(){
        final LockService lockService = new LockService(false);
        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("☆线程："+Thread.currentThread().getName()+"运行了");
                lockService.serviceMethod();
            }
        };
        Thread[] threads = new Thread[10];
        for (int i = 0;i < 10;i++){
            threads[i] =new Thread(runnable);
        }
        for (int i = 0;i < 10;i++){
            threads[i].start();
        }

    }
}
