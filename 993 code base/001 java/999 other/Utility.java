
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.BeansException;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ustrip.delegate.ApplicationContextProvider;
import com.ustrip.delegate.TransactionDelegate;
import com.ustrip.entity.Config;
import com.ustrip.entity.RedisConfig;
import com.ustrip.impl.HotelServiceImpl;
import com.ustrip.service.HotelService;

public class Utility {
	private static Logger logger = LogManager.getLogger(Utility.class);
	public boolean contains(JSONArray jsonArray, Object obj){
//		System.out.println("HD_test contains");
//		return true;
		
		for(int i = 0;i<jsonArray.length();i++){
			if(jsonArray.getString(i).equals(obj.toString())){
				return true;
			}
		}
		return false;
	}
	
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
    
    public static String getConfigValue(String key){ 
    	String rtv="";
    	try {
			StringRedisTemplate redisTemplate = ApplicationContextProvider.getApplicationContext().getBean("redisTemplate", StringRedisTemplate.class);
			String cache = redisTemplate.opsForValue().get("Config");
			if(null==cache){
				HotelService hotelService=ApplicationContextProvider.getApplicationContext().getBean("hotelServiceImpl", HotelServiceImpl.class);
				List<Config> configs= hotelService.getConfig();
				for(int i=0;i<configs.size();i++){
					if(key.equals(configs.get(i).getKey())){
						rtv=configs.get(i).getValue();
						break;
					}
				}
				Gson gson = new Gson();
				redisTemplate.opsForValue().set("Config", gson.toJson(configs));
			}else{
				Gson gson = new Gson();
				Type listType = new TypeToken<ArrayList<Config>>(){}.getType();
				List<Config> configs= gson.fromJson(cache, listType);
				for(int i=0;i<configs.size();i++){
					if(key.equals(configs.get(i).getKey())){
						rtv=configs.get(i).getValue();
						break;
					}
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			logger.fatal(e.getStackTrace());
		}
    	return rtv;
    }
    public static int getConfigIntValue(String key){ 
    	int rtv=0;
    	try {
			StringRedisTemplate redisTemplate = ApplicationContextProvider.getApplicationContext().getBean("redisTemplate", StringRedisTemplate.class);
			String cache = redisTemplate.opsForValue().get("Config");
			if(null==cache){
				HotelService hotelService=ApplicationContextProvider.getApplicationContext().getBean("hotelServiceImpl", HotelServiceImpl.class);
				List<Config> configs= hotelService.getConfig();
				for(int i=0;i<configs.size();i++){
					if(key.equals(configs.get(i).getKey())){
						rtv=Integer.valueOf(configs.get(i).getValue());
						break;
					}
				}
				Gson gson = new Gson();
				redisTemplate.opsForValue().set("Config", gson.toJson(configs));
			}else{
				Gson gson = new Gson();
				Type listType = new TypeToken<ArrayList<Config>>(){}.getType();
				List<Config> configs= gson.fromJson(cache, listType);
				for(int i=0;i<configs.size();i++){
					if(key.equals(configs.get(i).getKey())){
						rtv=Integer.valueOf(configs.get(i).getValue());
						break;
					}
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			logger.fatal(e.getStackTrace());
		}
    	return rtv;
    }    
	public static String UUID(int partnerId){
//		String RANDOM_PATTERN1 = "yyMMddHH";
//		String RANDOM_PATTERN2 = "mmssSSS";
//		
//		String s= "",date1="",date2="";
//		try {
//			Date now = new Date();
//			
//			SimpleDateFormat sdf1 = new SimpleDateFormat(RANDOM_PATTERN1);
//			date1 = sdf1.format(now);
////			logger.debug(date1);
//			
//			SimpleDateFormat sdf2 = new SimpleDateFormat(RANDOM_PATTERN2);
//			date2 = sdf2.format(now);
////			logger.debug(date2);
//			
//			date1=Integer.toHexString(Integer.parseInt(date1));
////			logger.debug(date1);
//			
//			date2=Integer.toHexString(Integer.parseInt(date2));
////			logger.debug(date2);
//			
//			Random random = new Random();
//			s=Integer.toHexString(random.nextInt());
////			logger.debug(s);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new MiniException(UsiTripConstant.ERRORCODE_ER1412, ErrorCodeEnum.ER1412.getMsg());
//		}
//		return date1+date2+s;
//		return String.valueOf((long)System.currentTimeMillis()/1000)+String.valueOf(partnerId);
		return getUUID();
	}

	public static String NotNullString(String str){ 
		String rtv="";
		try {
			if(null!=str && !(str.trim().equals("")) && !(str.trim().toLowerCase().equals("null")) ){
				rtv=str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtv;
	}
	public static String genTimeStamp4HBSi(){
		String rtv="";
		try {
			//rtv format is: 2005-05-03T15:47:24-04:00
			Date now=new Date();
			String formatTimeStamp = "yyyy-MM-dd'T'HH:mm:ssZ";
		    SimpleDateFormat sdf = new SimpleDateFormat(formatTimeStamp) {
		        public StringBuffer format(Date date, StringBuffer toAppendTo, java.text.FieldPosition pos) {
		            StringBuffer toFix = super.format(date, toAppendTo, pos);
		            return toFix.insert(toFix.length()-2, ':');
		        };
		    };
		    rtv= sdf.format(sdf.format(now));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtv;
	}
	
	public static void error(Logger logger,String exMsg){ 
		try {
//			StackTraceElement stackTraceElement= ex.getStackTrace()[ex.getStackTrace().length-1];
	    	Throwable t=new Throwable();
//	    	t.printStackTrace();
	        StackTraceElement stackTraceElement = t.getStackTrace()[1];
			logger.fatal("error info:File="+stackTraceElement.getClassName()+" Method="+stackTraceElement.getMethodName()+" Line="+stackTraceElement.getLineNumber()+" Message="+exMsg);
		} catch (Exception e) {
			logger.fatal("error found when getting error info:"+e.getMessage());
//			e.printStackTrace();
		}
	}
}
