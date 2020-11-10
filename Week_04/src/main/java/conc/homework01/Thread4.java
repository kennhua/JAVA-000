package conc.homework01;

/**
 * @author liugenghua
 * @date：2020/11/10
 * @Description 使用线程的isAlive()阻塞主线程等待子线程执行完毕
 * @Version:1.0
 **/
public class Thread4 {

    private volatile static int result = 0;

    public static void main(String[] args) {

        Thread4 t = new Thread4();
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        Thread thread = new Thread(() -> {
            result = t.sum();//这是得到的返回值
        });
        thread.start();
        while (thread.isAlive()){//阻塞主线程等待子线程执行完毕
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
