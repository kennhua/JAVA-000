package singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liugenghua
 * @date：2020/11/18
 * @Description 静态内部类单例模式
 * @Version:1.0
 **/
public class Singleton04 {

    public static Singleton04 getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final Singleton04 instance = new Singleton04();
    }
}
