package com.nongye.p2p.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 必须登入，自定义注解
 * @author 89568
 *
 */
@Target(ElementType.METHOD)//说明该注解只能作用方法上面
@Retention(RetentionPolicy.RUNTIME)//说明该注解作用到runtime(运行时期有效)
public @interface RequireLogin {

}
