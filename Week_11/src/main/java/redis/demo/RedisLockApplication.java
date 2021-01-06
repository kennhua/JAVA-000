package redis.demo;

import redis.demo.lock.RedisLock;

import java.util.UUID;

/**
 * @author liugenghua
 **/
public class RedisLockApplication {

    private static final String LOCK = "myLock";
    private static int amount = 10;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            final String uuid = UUID.randomUUID().toString();
            lock(uuid);
        });
        Thread t2 = new Thread(() -> {
            final String uuid = UUID.randomUUID().toString();
            lock(uuid);
        });
        t1.start();
        t2.start();
    }

    private static void lock(String uuid) {
        if (!RedisLock.getInstance().lock(LOCK, uuid)) {
            System.out.println("线程" + Thread.currentThread().getName() + "获取锁失败");
            return;
        }

        System.out.println("线程" + Thread.currentThread().getName() + "获得锁");
        amount -= 1;
        System.out.println("库存-1,剩余库存为:" + amount);

        if (RedisLock.getInstance().release(LOCK, uuid)) {
            System.out.println("线程" + Thread.currentThread().getName() + "释放锁成功");
        }
    }

}
