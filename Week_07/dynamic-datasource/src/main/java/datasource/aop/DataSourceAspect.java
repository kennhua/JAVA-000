package datasource.aop;

import datasource.config.DataSourceContextHolder;
import datasource.config.SlaveConfig;
import datasource.eumn.DataSourceType;
import datasource.eumn.LoadBalanceType;
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
import java.util.concurrent.atomic.AtomicInteger;

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

    private static AtomicInteger index = new AtomicInteger(0);
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
        doLoadBalance();
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        //清理掉当前设置的数据源，让默认的数据源不受影响
        DataSourceContextHolder.clearDataSource();
    }

    /**
     * 负载均衡策略
     */
    private void doLoadBalance() {
        final int size = slaveConfig.getSlave().size();
        if (size > 1) {
            final LoadBalanceType loadBalance = slaveConfig.getLoadBalance();
            switch (loadBalance) {
                case RANDOM:
                    random(size);
                    break;
                case ROUND_ROBIN:
                    roundRobin(size);
                    break;
                default:
                    roundRobin(size);
            }
        } else {
            DataSourceContextHolder.setDataSource(DataSourceType.slaveDataSource + "_0");
        }
    }

    /**
     * 随机负载均衡
     *
     * @param size
     */
    private void random(final int size) {
        Random random = new Random();
        final int i = random.nextInt(100) % size;
        DataSourceContextHolder.setDataSource(DataSourceType.slaveDataSource + "_" + i);
    }

    /**
     * 轮询负载均衡
     *
     * @param size
     */
    private void roundRobin(final int size) {
        if (index.intValue() == size * 1000) {//超过1000次轮询之后从0开始
            index.set(0);
        }
        final int i = index.intValue() % size;
        DataSourceContextHolder.setDataSource(DataSourceType.slaveDataSource + "_" + i);
        index.incrementAndGet();
    }
}
