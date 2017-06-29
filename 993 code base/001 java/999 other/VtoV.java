
import java.lang.reflect.Type;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axis2.databinding.ADBBean;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class VtoV
{
	public JSONObject XmlToJsonObject(String response )
	{
		JSONObject responseJson = XML.toJSONObject(response);
		return responseJson;
	}
	
	public Map<String, Object> JsonObjectToMap(JSONObject response )
	{
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> responseMap = gson.fromJson(response.toString(), type);
		return responseMap;
	}
	public String MapToJsonString(Map<String, Object> response )
	{
		Gson gson = new Gson();
		return gson.toJson(response);
	}
	public JSONObject MapToJsonObject(Map<String, Object> response )
	{
		Gson gson = new Gson();
		return XmlToJsonObject(gson.toJson(response));
	}
	public Map<String, Object> XmlToMap(String response)
	{
		//response=filter(response);
		JSONObject responseJson = XML.toJSONObject(response);
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> responseMap =gson.fromJson(responseJson.toString(), type);
		return responseMap;
		
	}
	public String filterString(String response) 
	{
		String temp = "";
		for(int i = 0;i<response.length();i++)
		{
			if(response.charAt(i)>=0 && response.charAt(i) <= 127)
			{
				temp+=response.charAt(i);
			}
		}
		return temp;
	}

	public String MapToXml(Map<String, Object> response)
	{
		Gson gson = new Gson();
		String json = gson.toJson(response);
		JSONObject jsonObj = new JSONObject(json);
		String xml = XML.toString(jsonObj);
		return xml;
		
	}
	public JSONObject jsonStringTojsonObject(String jsonRequest) {
		JSONObject jsonObj = new JSONObject(jsonRequest);
		return jsonObj;
	}	
	
	public static JSONArray xmltoJSONArray(String xml) throws Exception{
		try {
			return new JSONArray(xml);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MiniException(UsiTripConstant.ERRORCODE_ER1303, ErrorCodeEnum.ER1303.getMsg());
		}
	}
	
	public static String AxisObjToXml(ADBBean ab,QName qname) throws Exception{
		OMElement om= ab.getOMElement(qname, OMAbstractFactory.getOMFactory());
		return om.toStringWithConsume();
	}
	public static Map<String, Object> AxisObjToMap(ADBBean ab,QName qname) throws Exception{
		OMElement om= ab.getOMElement(qname, OMAbstractFactory.getOMFactory());
		return new VtoV().XmlToMap(om.toStringWithConsume());
	}
}