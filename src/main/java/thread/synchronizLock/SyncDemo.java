package thread.synchronizLock;

import org.junit.Test;
import java.util.concurrent.locks.ReentrantLock;

public  class SyncDemo  {
    // 首先新建两个线程 对同一常量进行操作
    private volatile int count = 0;

    private ReentrantLock lock = new ReentrantLock();
    @Test
    public  void test() throws InterruptedException {
        Threadlock1  th1 =new Threadlock1 ();

        Thread[]  th2 =new Thread [10];
//        th1.start();

        for (int i = 0 ; i < 10 ;i++){
            th2[i] = new Thread(new Threadlock());
            th2[i].start();

            th2[i] = new Thread(new Threadlock1 ());
            th2[i].start();

        }
//        th1.start();
        Thread.sleep(1000);
    }

    public   void add() {

        try {
            lock.lock();
            count++;
            System.out.println(1 + "：" + count);
        }finally {
            lock.unlock();
        }
//        synchronized (SyncDemo.class) {

//        }
    }
     class Threadlock extends Thread {
        @Override
        public  void run(){
           add();
        }
    }

     class Threadlock1 extends Thread {
        @Override
        public void run(){
          for (int i = 0;i < 10 ; i++ ) {
              add();
          }
        }
    }
}
