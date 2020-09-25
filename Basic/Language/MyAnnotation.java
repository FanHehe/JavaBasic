package Basic;

import java.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import static java.lang.System.out;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@interface FieldOrMethod {
    public String name() default "hello";
}

public class MyAnnotation {
    // meta annotation
    // @Retention: 定义注解的保留策略
    // - RetentionPolicy.SOURCE
    // - RetentionPolicy.CLASS
    // - RetentionPolicy.RUNTIME

    // @Inherited 如果一个类A使用此注解，则类A的子类也继承此注解
    //
    // @Documented 声明注解能够被javadoc等识别
    //
    // @Target 用来声明注解可用户修饰的范围（枚举ElementType），ElementType可选值
    // - ElementType.TYPE
    // - ElementType.FIELD
    // - ElementType.METHOD
    // - ElementType.PARAMETER
    // - ElementType.CONSTRUCTOR
    // - ElementType.LOCAL_VARIABLE
    // - ElementType.ANNOTATION_TYPE
    // - ElementType.PACKAGE
    //
    // @Native
    // @Repeatable
    //
    // java.lang.reflect.AnnotatedElement 接口定义了基本的注解操作，而Class类实现了AnnotatedElement接口
    //
    // public final class Class<T> implements java.io.Serializable,
    //                          GenericDeclaration,
    //                          Type,
    //                          AnnotatedElement {...

    @FieldOrMethod
    private String value = "value";

    @FieldOrMethod
    public static void main(String[] args) {
         Annotation[] annotations
            = MyAnnotation.class.getAnnotations();

        out.println("length =>" + String.valueOf(annotations.length));

         for (Annotation annotation : annotations) {
            // 打印class的所有注解
            out.println(annotation.toString());
            out.println(annotation.annotationType());
            out.println(annotation.getClass().getName());
            out.println(annotation.getClass().getTypeName());
            out.println(annotation.getClass().toString());
        }

        // 找出所有字段
        List<Field> fields = Arrays.asList(MyAnnotation.class.getDeclaredFields());

        fields.forEach(field -> {
            out.println("field =>" + field.getName());
            for (Annotation annotation : field.getAnnotations()) {
                out.println(((FieldOrMethod)annotation).name());
                out.println(annotation.toString());
                out.println(annotation.annotationType());
                out.println(annotation.getClass().getName());
                out.println(annotation.getClass().getTypeName());
                out.println(annotation.getClass().toString());
            }
        });

    }
}
