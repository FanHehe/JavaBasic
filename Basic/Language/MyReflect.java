package Basic;

import java.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import static java.lang.System.out;

// https://juejin.im/post/598ea9116fb9a03c335a99a4

public class MyReflect {

    public String publicField = "public";
    private String privateField = "private";

    public static String staticField = "static";

    public final static String finalField = "final";

    public void inner(String value) { out.println("inner:" + value); }

    public static void outer(String value) { out.println("outer" + value); }

    public static void handleField() {
        Class clz = MyReflect.class;

        Field[] fields = clz.getFields(); // 包括从父继承的public
        Field[] dfields = clz.getDeclaredFields(); // 只获取自己声明的属性, 不论访问权限

        for (Field field: fields) {
            //获取访问权限并输出
            int modifiers = field.getModifiers();
            out.print(Modifier.toString(modifiers) + " ");
            //输出变量的类型及变量名
            out.println(field.getType().getName() + " " + field.getName());
        }
    }

    public static void hanldeMethod() {
        Class clz = MyReflect.class;

        Method[] methods = clz.getMethods(); // 包括从父继承的public, 也包括Object的, 不论是否静态
        Method[] dmethods = clz.getDeclaredMethods(); // 只获取自己声明的属性, 不论访问权限, 不论是否静态

        for (Method method: dmethods) {
            // 获取并输出方法的访问权限（Modifiers：修饰符）
            int modifiers = method.getModifiers();
            out.print(Modifier.toString(modifiers) + " ");
            // 获取并输出方法的返回值类型
            Class returnType = method.getReturnType();
            out.print(returnType.getName() + " " + method.getName() + "( ");
            // 获取并输出方法的所有参数
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter: parameters) {
                out.print(parameter.getType().getName() + " " + parameter.getName() + ",");
            }
            // 获取并输出方法抛出的异常
            Class[] exceptionTypes = method.getExceptionTypes();
            if (exceptionTypes.length == 0){
                System.out.println(" )");
            }
            else {
                for (Class c : exceptionTypes) {
                    out.println(" ) throws " + c.getName());
                }
            }
        }
    }

    public static void invokeMethod()
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class clz = MyReflect.class;

        @SuppressWarnings("unchecked")
        Method inner = clz.getDeclaredMethod("inner", String.class);
        @SuppressWarnings("unchecked")
        Method outer = clz.getDeclaredMethod("outer", new Class<?>[]{ String.class });

        if (inner != null) {
            inner.setAccessible(true);
            inner.invoke(new MyReflect(), "invoked");
        }

        if (outer != null) {
            outer.setAccessible(true);
            outer.invoke(null, "invoked");
        }
    }

    public static void modifyField()
        throws IllegalAccessException, NoSuchFieldException {
        MyReflect mr = new MyReflect();
        Field field = mr.getClass().getDeclaredField("publicField");

        if (field != null) {
            field.setAccessible(true);
            out.println("Before " + mr.publicField);
            field.set(mr, "new value");
            out.println("After " + mr.publicField);
        }
    }

    public static void main(String[] args) {
        handleField();
        hanldeMethod();

        try {
            modifyField();
        }
        catch(NoSuchFieldException e) {}
        catch(IllegalAccessException e) {}


        try {
            invokeMethod();
        }
        catch (NoSuchMethodException e) {}
        catch (IllegalAccessException e) {}
        catch (InvocationTargetException e) {}

    }
}
