package com.metocs.common.aop;


import com.alibaba.fastjson.JSON;
import com.metocs.common.annotation.DataSource;
import com.metocs.common.annotation.SourceName;
import com.metocs.common.config.MyDynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Parameter;

@Slf4j
@Aspect
@Configuration
public class DataSourceAspect  {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);


    public DataSourceAspect() {
    }

    @Before("@annotation(dataSource)")
    public void changeDataSource(JoinPoint point, DataSource dataSource) throws Throwable {
        String dataName = null;
        MethodSignature signature= (MethodSignature) point.getSignature();
        Parameter[] parameters = signature.getMethod().getParameters();
        for (Parameter parameter : parameters) {
            AnnotatedType annotatedType = parameter.getAnnotatedType();
            if (annotatedType.getType() instanceof SourceName){
            }
        }
        String value = dataSource.value();
        if ("primary".equals(value)){
            MyDynamicDataSource.setDataSource("default");
        }else if (!"".equals(value)){
            MyDynamicDataSource.setDataSource(value);
        }else {
            MyDynamicDataSource.setDataSource("default");//默认使用主数据库
        }
    }

    @After("@annotation(dataSource)") //清除数据源的配置
    public void restoreDataSource(JoinPoint point, DataSource dataSource) {
        MyDynamicDataSource.clearDataSource();
    }

}