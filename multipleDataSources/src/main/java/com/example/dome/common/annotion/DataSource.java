package com.example.dome.common.annotion;

import java.lang.annotation.*;

/**
 *  @Time 2019/8/27 18:40
 *  @Author GuiPing.Xu
 *  @Version 1.0.0
 *  @Explain 自定义注解类
 *
 * java 四大元注解作用
 * 1.@Target ：用于描述注解的使用范围，也就是说使用了@Target去定义一个注解，
 *             那么可以决定定义好的注解能用在什么地方
 *             1.CONSTRUCTOR:用于描述构造器
 * 　　　　     2.FIELD:用于描述域
 * 　　　　     3.LOCAL_VARIABLE:用于描述局部变量
 * 　　　　     4.METHOD:用于描述方法
 * 　　　　     5.PACKAGE:用于描述包
 * 　　　　     6.PARAMETER:用于描述参数
 * 　　　　     7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
 * 2.@Retention：用于描述注解的生命周期，也就是说这个注解在什么范围内有效，
 *               注解的生命周期和三个阶段有关：源代码阶段、CLASS文件中有效、运行时有效，
 *               故其取值也就三个值，分别代表着三个阶段
 *               1、RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；
 *               2、RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期；
 *               3、RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
 * 3.@Documented：表示该注解是否可以生成到 API文档中。在该注解使用后，如果导出API文档，
 *                会将该注解相关的信息可以被例如javadoc此类的工具文档化。
 *                注意：Documented是一个标记注解，没有成员。
 * 4.@Inherited：使用@Inherited定义的注解具备继承性
 *              假设一个注解在定义时，使用了@Inherited，然后该注解在一个类上使用，如果这个类有子类，
 *              那么通过反射我们可以从类的子类上获取到同样的注解、
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSource {
    String name() default "";
}
