package thread.condition;

public class MyThreadCou extends Thread  {

     private MyService myService;

     public MyThreadCou (MyService service){
         super();
         this.myService= service;
     }

     @Override
    public  void  run(){
         for ( int i = 0; i < Integer.MAX_VALUE; i++)
         myService.get();
     }
}
