package thread.condition;

public class MyThreadS1 extends Thread {

    private MyService myService ;

    public MyThreadS1 (MyService service){
        super();
        this.myService =service;
    }

    @Override
    public  void  run(){
        for ( int i = 0 ;i < Integer.MAX_VALUE ; i++){
            myService.set();
        }
    }
}
