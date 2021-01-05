package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestRedisPool {
	private static JedisPool pool = null;

	public static void main(String[] args) {
		pool = JedisUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		
		
		System.out.println(jedis.ping());

		jedis.close();
		JedisUtil.shutdownJedisPool();
	}
}
