package pattern.Test;

import pattern.slimpleFactory.CourseFactory;
import pattern.slimpleFactory.ICourse;
import pattern.slimpleFactory.JavaCourse;
import org.junit.Test;

import java.util.Calendar;

public class SimpleTest {


    @Test
    public void SimpleTest(){
        CourseFactory cf = new CourseFactory();
         ICourse course= cf.course(JavaCourse.class);
        course.record();
        
        Calendar.getInstance();
    }
    @Test
    public void FactoryMethodTest(){


    }
}
