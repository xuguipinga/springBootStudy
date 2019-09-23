package com.example.dome.common.aop;

import com.example.dome.common.annotion.DataSource;
import com.example.dome.common.mutidatesource.DSEnum;
import com.example.dome.common.mutidatesource.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Time 2019/8/28 11:43
 * @Author GuiPing.Xu
 * @Version 1.0.0
 * @Explain
 */
@Aspect
@Component
@ConditionalOnProperty(prefix = "example",name = "muti-datasource-open",havingValue = "true")
public class    MultiSourceExAop implements Ordered {

    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Pointcut(value = "@annotation(com.example.dome.common.annotion.DataSource)")
    private void cut() {

    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        Signature signature = point.getSignature();
        MethodSignature methodSignature = null;
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) signature;

        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        DataSource datasource = currentMethod.getAnnotation(DataSource.class);
        if (datasource != null) {
            DataSourceContextHolder.setDataSourceType(datasource.name());
            logger.debug("设置数据源为：" + datasource.name());
        } else {
            DataSourceContextHolder.setDataSourceType(DSEnum.DATA_SOURCE_CORE);
            logger.debug("设置数据源为：dataSourceCore");
        }
        try {
            return point.proceed();
        } finally {
            logger.debug("清空数据源信息！");
            DataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * AOP事务顺序  AOP的顺序要早于spring的事务
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
