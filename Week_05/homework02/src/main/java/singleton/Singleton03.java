package singleton;

/**
 * @author liugenghua
 * @date：2020/11/18
 * @Description 双重检测单例模式
 * @Version:1.0
 **/
public class Singleton03 {

    private static Singleton03 instance;

    public static Singleton03 getInstance() {
        if (null == instance) {
            synchronized (Singleton03.class) {
                if (null == instance) {
                    instance = new Singleton03();
                }
            }
        }
        return instance;
    }
}
