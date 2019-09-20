package pattern.factoryMethod;

import pattern.slimpleFactory.ICourse;
import pattern.slimpleFactory.JavaCourse;

public class JavaCourseFactory implements  ICourseFacory {
    public ICourse create() {
        return new JavaCourse();
    }
}
