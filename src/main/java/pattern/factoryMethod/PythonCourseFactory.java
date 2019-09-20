package pattern.factoryMethod;

import pattern.slimpleFactory.ICourse;
import pattern.slimpleFactory.PythonCourse;

public class PythonCourseFactory implements ICourseFacory {
    public ICourse create() {
        return new PythonCourse();
    }
}
