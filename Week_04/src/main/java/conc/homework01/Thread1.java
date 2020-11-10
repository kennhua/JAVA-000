package conc.homework01;

/**
 * @author liugenghua
 * @date：2020/11/9
 * @Description 创建Thread调用join优先执行新创建的线程
 * @Version:1.0
 **/
public class Thread1 {

    private volatile static int result = 0;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        Thread1 t = new Thread1();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread thread = new Thread(() -> {
            result = t.sum();//这是得到的返回值
        });
        thread.start();
        thread.join();//优先执行新创建的线程

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
