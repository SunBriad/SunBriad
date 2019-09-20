package pattern.slimpleFactory;

public class CourseFactory {

    /**
     *
     * @param className 调用工厂
     * @return
     */
    public ICourse course(Class className){
            try{
                if (!(null == className || ("").equals(className))){
                    return (ICourse) className.newInstance ();
                }
            }catch (Exception e){
                e.fillInStackTrace();
            }
            return null;
    }
}
