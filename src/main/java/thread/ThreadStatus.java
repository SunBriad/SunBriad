package thread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ThreadStatus {
    public static void main(String[] args) {

//       new Thread(new Runnable() {
//            public void run() {
//                while (true) {
//                    try {
//                        TimeUnit.SECONDS.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        },"TIME_WAITING").start();
//
//
//        new Thread(new Runnable() {
//            public void run() {
//                while (true) {
//                    synchronized (ThreadStatus.class) {
//                        try {
//                            TimeUnit.SECONDS.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        },"WAIT").start();
//        Thread thread =new Thread(new ThreadTest());
//        thread.start();
//       long tid= thread.getId();
//       long tid1=  Thread.currentThread().getId();
//      Thread thread1=Thread.currentThread();
//        System.out.println(thread1.getName());
//        System.out.println(thread.getName());
//        System.out.println(thread.getContextClassLoader());
//        System.out.println(thread1.getContextClassLoader());
//      System.out.println(tid);
//        System.out.println(tid1);
//        System.out.println( tid == tid1);
//        try {
//            thread.wait(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(thread.isAlive());
    }



    @Test
    public  void  TestThread(){
    Thread thread = Thread.currentThread();
        ThreadTest thread1 = new ThreadTest();
        ThreadTest thread2 = new ThreadTest();
        thread1.start();
        thread2.start();
//        thread.interrupt();
//        thread1.interrupt();
//        System.out.println("是否中止"+thread.interrupted());
//        System.out.println("是否中止1"+thread.interrupted());
//        System.out.println("是否中止"+thread1.isInterrupted());
        System.out.println("run 1:" + thread1.isAlive());
        System.out.println(thread1.getId());
        System.out.println(thread1.getName());
        System.out.println(thread.getName());
        System.out.println(thread.getId());
    }
     class ThreadTest extends Thread{
        private volatile int i = 0;
        private boolean flg =true;
        @Override
        public  void run(){
            while (flg) {
//                if (this.isInterrupted()) {
                    System.out.println("----");
                    System.out.println("run :" + this.isAlive());
                    System.out.println("我加了啊"+i);

//                }
                i++;
                  if (i == 1000){
                      flg=false;
                  }
            }
         }

    }


}
