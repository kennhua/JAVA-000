package redis.demo.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

/**
 * 基于redis的分布式锁
 * @author liugenghua
 **/
public class RedisLock {

    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;

    private static final RedisLock instance = new RedisLock();

    public static RedisLock getInstance() {
        return instance;
    }

    private JedisPool jedisPool = new JedisPool();

    /**
     * 加锁
     * @param lockKey
     * @param uuid
     * @return
     */
    public boolean lock(String lockKey, String uuid) {
        try(Jedis jedis = jedisPool.getResource()) {
            return LOCK_SUCCESS.equals(jedis.set(lockKey, uuid, "NX", "EX", 10));
        }
    }

    /**
     * 释放锁
     * @param lockKey
     * @param uuid
     * @return
     */
    public boolean release(String lockKey, String uuid) {
        String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then " + "return redis.call('del',KEYS[1]) else return 0 end";
        try(Jedis jedis = jedisPool.getResource()) {
            return RELEASE_SUCCESS.equals(jedis.eval(luaScript, Collections.singletonList(lockKey), Collections.singletonList(uuid)));
        }
    }
}
