import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.ustrip.delegate.ApplicationContextProvider;

import redis.clients.jedis.Jedis;

public class RedisCache {
	private static Jedis jedis;
	private static StringRedisTemplate redisTemplate;
	
	private static void getJedis(){
		JedisConnectionFactory connectionFactory=ApplicationContextProvider.getApplicationContext().getBean("connectionFactory", JedisConnectionFactory.class);
		jedis=connectionFactory.getConnection().getNativeConnection();
	}
	
	private static void initRedisTemplate(){
		if(null!=redisTemplate)return;
		StringRedisTemplate rtp = ApplicationContextProvider.getApplicationContext().getBean("redisTemplate", StringRedisTemplate.class);
		redisTemplate=rtp;
	}
	
	public static void set(String keyList,String keyValue){
		try {
			if(null==jedis)
				getJedis();
			jedis.lpush(keyList, keyValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int get(String keyList,String keyValue){
		int rtv=-1;
		try {
			if(null==jedis)
				getJedis();
			List<String> list = jedis.lrange(keyList, 0 ,Integer.MAX_VALUE); 
			for(int i = 0; i<list.size(); i++) { 
				if(keyValue.equals(list.get(i)))
					return i; 
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtv;
	}
	
	public static JSONObject getFromJSON(String keyList,String keyValue){
		JSONObject rtv=null;
		try {
			if(null==jedis)
				getJedis();
			List<String> list = jedis.lrange(keyList, 0 ,Integer.MAX_VALUE); 
			for(int i = 0; i<list.size(); i++) {
				JSONObject json=new JSONObject(list.get(i));
				String roomPid=json.getString("keyPid");
				
				if(keyValue.equals(roomPid))
					return json; 
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtv;
	}
	
	public static String get(String keyList,int i){
		String rtv="";
		try {
			if(null==jedis)
				getJedis();
			List<String> list = jedis.lrange(keyList, 0 ,Integer.MAX_VALUE); 
			if(null!=list)
				return list.get(i); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtv;
	}
	
	public static void putTemplate(String key,String value,long time){
		initRedisTemplate();
		redisTemplate.opsForValue().set(key, value, time, TimeUnit.MINUTES);
	}
	
	public static String getTemplate(String key){
		initRedisTemplate();
		return redisTemplate.opsForValue().get(key);
	}
}
