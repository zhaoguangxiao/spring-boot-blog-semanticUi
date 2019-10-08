package com.zhao.guang.xiao.top.validate.Impl;

import com.zhao.guang.xiao.top.po.TypeBean;
import com.zhao.guang.xiao.top.service.BlogCategoryService;
import com.zhao.guang.xiao.top.validate.BlogCategoryNameExist;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Administrator
 * @version 1.0
 * @date 2019/10/8 11:26
 */
public class BlogCategoryNameExistValidator implements ConstraintValidator<BlogCategoryNameExist, String> {



    @Autowired
    private BlogCategoryService blogCategoryService;


    /**
     * 初始化
     * @param constraintAnnotation
     */
    @Override
    public void initialize(BlogCategoryNameExist constraintAnnotation) {}

    /**验证逻辑
     *
     * @param name
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        TypeBean beanByName = blogCategoryService.getTypeBeanByName(name);
        return  null != beanByName ? false : true;
    }


}
