package com.zhao.guang.xiao.top.validate;

import com.zhao.guang.xiao.top.validate.Impl.BlogCategoryNameExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/** 判断博客分类用户名是否已经存在
 * @author Administrator
 * @version 1.0
 * @date 2019/10/8 11:19
 */


@Target({ ElementType.FIELD})// 注解可以作用的位置：字段、方法
@Retention(RetentionPolicy.RUNTIME)// 运行时注解
@Constraint(validatedBy = BlogCategoryNameExistValidator.class)// 制定注解判断逻辑所在的类，这个类必须实现了ConstraintValidator接口
public @interface BlogCategoryNameExist {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
