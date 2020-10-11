package hw7;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Start {
    int beforeMethod = 0;
    int afterMethod = 0;

    public static void start(Class cl) throws Exception {
        Start start = new Start();
        Method[] methods = cl.getDeclaredMethods();
        ArrayList<Method> arrayList = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                start.beforeMethod++;
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                start.afterMethod++;
            }
//            System.out.println(method);
        }
        if ((start.afterMethod | start.beforeMethod) > 1) {
            throw new RuntimeException("Ошибка: 2 или более метода @BeforeSuite / @AfterSuite");
        }

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                method.invoke(null);
            }
            if (method.isAnnotationPresent(Test.class)) {
                arrayList.add(method);
            }
        }

        Collections.sort(arrayList, new Sort());

        for (int i = arrayList.size() - 1; i >= 0; i--) {
            arrayList.get(i).invoke(null);
        }
        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterSuite.class)) {
                method.invoke(null);
            }
        }

    }

    public static class Sort implements Comparator<Method> {
        @Override
        public int compare(Method method1, Method method2) {
            return Integer.compare(method1.getAnnotation(Test.class).priority(), method2.getAnnotation(Test.class).priority());
        }
    }
}
