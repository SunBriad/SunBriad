package thread;

import org.junit.Test;
import thread.condition.MyService;
import thread.condition.MyThreadCou;
import thread.condition.MyThreadS1;

public class ConditionTest {
    @Test
    public void test(){
    MyService service =new MyService();
    MyThreadS1 s1 = new MyThreadS1(service);
        s1.start();

        MyThreadCou c1 = new MyThreadCou(service);
    c1.start();
    }

    @Test
    public void test1(){
        MyService myService = new MyService();
        MyThreadS1[] threadS1s = new  MyThreadS1[10];
        MyThreadCou[] cs = new  MyThreadCou[10];
        for (int i = 0 ; i< 10;i++){
            threadS1s[i] = new MyThreadS1(myService);
            cs[i] = new MyThreadCou(myService);
            threadS1s[i].start();
            cs[i].start();
        }
    }
}
