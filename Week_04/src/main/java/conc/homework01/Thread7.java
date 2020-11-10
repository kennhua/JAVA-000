package conc.homework01;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author liugenghua
 * @date：2020/11/10
 * @Description 使用CyclicBarrier让子线程全部执行完成之后执行主线程
 * @Version:1.0
 **/
public class Thread7 {

    private volatile static int result = 0;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, new Runnable() {
            @Override
            public void run() {
                // 确保  拿到result 并输出
                System.out.println("异步计算结果为：" + result);
                System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
            }
        });
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    result = sum();//这是得到的返回值
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

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
