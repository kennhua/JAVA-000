package conc.homework01;

/**
 * @author liugenghua
 * @date：2020/11/10
 * @Description 使用synchronize方式阻塞主线程
 * @Version:1.0
 **/
public class Thread5 {

    private volatile static int result = 0;
    private static Object lock = new Object();

    public static void main(String[] args) {

        Thread5 t = new Thread5();
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                result = t.sum();//这是得到的返回值
                lock.notify();
            }
        });
        thread.start();
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

}
