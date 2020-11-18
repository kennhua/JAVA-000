package singleton;

/**
 * @author liugenghua
 * @date：2020/11/18
 * @Description 懒汉式单例模式
 * @Version:1.0
 **/
public class Singleton02 {

    private static Singleton02 instance;

    public static synchronized Singleton02 getInstance() {
        if (null == instance) {
            instance = new Singleton02();
        }
        return instance;
    }
}
