package singleton;

/**
 * @author liugenghua
 * @date：2020/11/18
 * @Description 饿汉式单例模式
 * @Version:1.0
 **/
public class Singleton01 {

    private static final Singleton01 instance = new Singleton01();

    public static Singleton01 getInstance() {
        return instance;
    }
}
