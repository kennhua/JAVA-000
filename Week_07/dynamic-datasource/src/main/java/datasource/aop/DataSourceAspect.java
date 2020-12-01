package datasource.aop;

import datasource.DataSourceType;
import datasource.config.DataSourceContextHolder;
import datasource.config.SlaveConfig;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * @author liugenghua
 * @date：2020/12/1
 * @Description AOP动态切换数据源
 * @Version:1.0
 **/
@Order(1)
@Aspect
@AutoConfigureAfter(datasource.config.SlaveConfig.class)
@Component
public class DataSourceAspect {

    @Autowired
    private SlaveConfig slaveConfig;

    @Pointcut("@annotation(datasource.aop.ReadOnly)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        ReadOnly annotationClass = method.getAnnotation(ReadOnly.class);//获取方法上的注解
        if (annotationClass == null) {
            annotationClass = joinPoint.getTarget().getClass().getAnnotation(ReadOnly.class);//获取类上面的注解
            if (annotationClass == null) {
                return;
            }
        }
        //模拟随机负载均衡
        Random random = new Random();
        int i = random.nextInt(100) % slaveConfig.getSlave().size();
        //切换数据源
        DataSourceContextHolder.setDataSource(DataSourceType.slaveDataSource + "_" + i);
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        //清理掉当前设置的数据源，让默认的数据源不受影响
        DataSourceContextHolder.clearDataSource();
    }
}
