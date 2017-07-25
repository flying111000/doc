
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jboss.resteasy.spi.HttpRequest;
import org.json.JSONObject;


public class POSTREST
{
	private static final Logger logger = LogManager.getLogger(POSTREST.class.getName());

	public String postJson(String urlString,String jsonString,Map<String,String> requestProperty) throws IOException
	{
	    return postJson("POST", urlString, jsonString, requestProperty);
	}
	
	public String postJson(String method,String urlString,String jsonString,Map<String,String> requestProperty) throws IOException
	{
	    URL url = new URL(urlString);
	    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	    connection.setRequestMethod(method);

	    for (Map.Entry<String, String> entry : requestProperty.entrySet()) {
	    	connection.setRequestProperty(entry.getKey(), entry.getValue());
		}
	    
	    connection.setDoOutput(true);
	    connection.setInstanceFollowRedirects(false);
	    connection.setConnectTimeout(15000);
	    return postJson(jsonString,connection);
	}
	
	public String postJson(String jsonString,HttpURLConnection connection) throws IOException
	{
		    StringBuffer sbuffer = new StringBuffer();
		    sbuffer.append(jsonString);
		    OutputStream os = connection.getOutputStream();
		    os.write(sbuffer.toString().getBytes());
		    os.flush();

		    //requestBody=connection.getInputStream();
		    
		    String requestBody = "";
		    StringBuilder stringBuilder = new StringBuilder();
		    BufferedReader bufferedReader = null;
		    try {
		        InputStream inputStream = connection.getInputStream();
		        if (inputStream != null) {
		            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		            char[] charBuffer = new char[128];
		            int bytesRead = -1;
		            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
		                stringBuilder.append(charBuffer, 0, bytesRead);
		            }
		        } else {
		            stringBuilder.append("");
		        }
		    } catch (IOException ex) {
		        throw ex;
		    } finally {
		        if (bufferedReader != null) {
		            try {
		                bufferedReader.close();
		            } catch (IOException ex) {
		                throw ex;
		            }
		        }
		    }
		    requestBody = stringBuilder.toString();
		return requestBody;
		
	}
	
	
	public String postJson(String urlString,String jsonString) throws IOException
	{
		    URL url = new URL(urlString);
		    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", "application/json");
		    
		    connection.setDoOutput(true);
		    connection.setInstanceFollowRedirects(false);
		    connection.setConnectTimeout(15000);
		    StringBuffer sbuffer = new StringBuffer();
		    sbuffer.append(jsonString);
		    OutputStream os = connection.getOutputStream();
		    os.write(sbuffer.toString().getBytes());
		    os.flush();

		    //requestBody=connection.getInputStream();
		    
		    String requestBody = "";
		    StringBuilder stringBuilder = new StringBuilder();
		    BufferedReader bufferedReader = null;
		    try {
		        InputStream inputStream = connection.getInputStream();
		        if (inputStream != null) {
		            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		            char[] charBuffer = new char[128];
		            int bytesRead = -1;
		            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
		                stringBuilder.append(charBuffer, 0, bytesRead);
		            }
		        } else {
		            stringBuilder.append("");
		        }
		    } catch (IOException ex) {
		        throw ex;
		    } finally {
		        if (bufferedReader != null) {
		            try {
		                bufferedReader.close();
		            } catch (IOException ex) {
		                throw ex;
		            }
		        }
		    }
		    requestBody = stringBuilder.toString();
		return requestBody;
		
	}
	
	public String sendXML(String method,String urlString,String XMLString,Map<String,String> requestProperty) throws IOException
	{
	    URL url = new URL(urlString);
	    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	    connection.setRequestMethod(method);

	    for (Map.Entry<String, String> entry : requestProperty.entrySet()) {
	    	connection.setRequestProperty(entry.getKey(), entry.getValue());
		}
	    
	    connection.setDoOutput(true);
	    connection.setInstanceFollowRedirects(false);
	    connection.setConnectTimeout(15000);
	    return postXML(XMLString,connection);
	}
	
	public String postXML(String XMLString,HttpURLConnection connection) throws IOException,SocketTimeoutException
	{
		    StringBuffer sbuffer = new StringBuffer();
		    sbuffer.append(XMLString);
		    OutputStream os = connection.getOutputStream();
		    os.write(sbuffer.toString().getBytes());
		    os.flush();
		    if(HttpURLConnection.HTTP_GATEWAY_TIMEOUT==connection.getResponseCode()){
		    	logger.error("GatewayTimeOut");
		    	throw new SocketTimeoutException();
		    }
		    //requestBody=connection.getInputStream();
		    
		    String requestBody = "";
		    StringBuilder stringBuilder = new StringBuilder();
		    BufferedReader bufferedReader = null;
		    try {
		        InputStream inputStream = connection.getInputStream();
		        if (inputStream != null) {
		            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		            char[] charBuffer = new char[128];
		            int bytesRead = -1;
		            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
		                stringBuilder.append(charBuffer, 0, bytesRead);
		            }
		        } else {
		            stringBuilder.append("");
		        }
		    } catch (SocketTimeoutException e) {
		    	InputStream inputStream = connection.getErrorStream();
		    	 if (inputStream != null) {
			            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			            char[] charBuffer = new char[128];
			            int bytesRead = -1;
			            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
			                stringBuilder.append(charBuffer, 0, bytesRead);
			            }
			            requestBody = stringBuilder.toString();
				    	 logger.error("SocketTimeOut:"+requestBody);
		    	 }else{
		    		 logger.error("SocketTimeOut:"+e.getMessage());
		    	 }
		    	throw e;
		    } catch (IOException ex) {
		    	InputStream inputStream = connection.getErrorStream();
		    	 if (inputStream != null) {
			            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			            char[] charBuffer = new char[128];
			            int bytesRead = -1;
			            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
			                stringBuilder.append(charBuffer, 0, bytesRead);
			            }
			            requestBody = stringBuilder.toString();
				    	 logger.error(requestBody);
		    	 } 

		        throw ex;
		    } catch (Exception ex) {
		    	InputStream inputStream = connection.getErrorStream();
		    	 if (inputStream != null) {
			            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			            char[] charBuffer = new char[128];
			            int bytesRead = -1;
			            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
			                stringBuilder.append(charBuffer, 0, bytesRead);
			            }
			            requestBody = stringBuilder.toString();
				    	 logger.error("OtherException:"+requestBody);
		    	 }else{
		    		 logger.error("OtherException:"+ex.getMessage());
		    	 }

		        throw ex;
		    } finally {
		    
		        if (bufferedReader != null) {
		            try {
		                bufferedReader.close();
		            } catch (IOException ex) {
		                throw ex;
		            }
		        }
		        if(connection!=null){
		        	connection.disconnect();
		        }
		    }
		    requestBody = stringBuilder.toString();
		return requestBody;
		
	}
	
	public String postXML(String urlString,String XMLString) throws IOException,SocketTimeoutException
	{
		    URL url = new URL(urlString);
		    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", "application/xml");
		    connection.setDoOutput(true);
		    connection.setInstanceFollowRedirects(false);
		    connection.setConnectTimeout(15000);
		    //connection.setReadTimeout(5000);
		    StringBuffer sbuffer = new StringBuffer();
		    sbuffer.append(XMLString);
		    OutputStream os = connection.getOutputStream();
		    os.write(sbuffer.toString().getBytes());
		    os.flush();
		    if(HttpURLConnection.HTTP_GATEWAY_TIMEOUT==connection.getResponseCode()){
		    	logger.error("GatewayTimeOut");
		    	throw new SocketTimeoutException();
		    }
		    //requestBody=connection.getInputStream();
		    
		    String requestBody = "";
		    StringBuilder stringBuilder = new StringBuilder();
		    BufferedReader bufferedReader = null;
		    try {
		        InputStream inputStream = connection.getInputStream();
		        if (inputStream != null) {
		            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		            char[] charBuffer = new char[128];
		            int bytesRead = -1;
		            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
		                stringBuilder.append(charBuffer, 0, bytesRead);
		            }
		        } else {
		            stringBuilder.append("");
		        }
		    } catch (SocketTimeoutException e) {
		    	InputStream inputStream = connection.getErrorStream();
		    	 if (inputStream != null) {
			            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			            char[] charBuffer = new char[128];
			            int bytesRead = -1;
			            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
			                stringBuilder.append(charBuffer, 0, bytesRead);
			            }
			            requestBody = stringBuilder.toString();
				    	 logger.error("SocketTimeOut:"+requestBody);
		    	 }else{
		    		 logger.error("SocketTimeOut:"+e.getMessage());
		    	 }
		    	throw e;
		    } catch (IOException ex) {
		    	InputStream inputStream = connection.getErrorStream();
		    	 if (inputStream != null) {
			            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			            char[] charBuffer = new char[128];
			            int bytesRead = -1;
			            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
			                stringBuilder.append(charBuffer, 0, bytesRead);
			            }
			            requestBody = stringBuilder.toString();
				    	 logger.error(requestBody);
		    	 } 

		        throw ex;
		    } catch (Exception ex) {
		    	InputStream inputStream = connection.getErrorStream();
		    	 if (inputStream != null) {
			            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			            char[] charBuffer = new char[128];
			            int bytesRead = -1;
			            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
			                stringBuilder.append(charBuffer, 0, bytesRead);
			            }
			            requestBody = stringBuilder.toString();
				    	 logger.error("OtherException:"+requestBody);
		    	 }else{
		    		 logger.error("OtherException:"+ex.getMessage());
		    	 }

		        throw ex;
		    } finally {
		    
		        if (bufferedReader != null) {
		            try {
		                bufferedReader.close();
		            } catch (IOException ex) {
		                throw ex;
		            }
		        }
		        if(connection!=null){
		        	connection.disconnect();
		        }
		    }
		    requestBody = stringBuilder.toString();
		return requestBody;
		
	}
	public String postJsonWithSercurity(String urlString,String jsonString) throws IOException
	{
		    URL url = new URL(urlString);
		    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", "application/json");
		    
		    connection.setDoOutput(true);
		    connection.setInstanceFollowRedirects(false);
		    connection.setConnectTimeout(15000);
		    StringBuffer sbuffer = new StringBuffer();
		    sbuffer.append(jsonString);
		    OutputStream os = connection.getOutputStream();
		    os.write(sbuffer.toString().getBytes());
		    os.flush();

		    //requestBody=connection.getInputStream();
		    
		    String requestBody = "";
		    StringBuilder stringBuilder = new StringBuilder();
		    BufferedReader bufferedReader = null;
		    try {
		        InputStream inputStream = connection.getInputStream();
		        if (inputStream != null) {
		            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		            char[] charBuffer = new char[128];
		            int bytesRead = -1;
		            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
		                stringBuilder.append(charBuffer, 0, bytesRead);
		            }
		        } else {
		            stringBuilder.append("");
		        }
		    } catch (IOException ex) {
		        throw ex;
		    } finally {
		        if (bufferedReader != null) {
		            try {
		                bufferedReader.close();
		            } catch (IOException ex) {
		                throw ex;
		            }
		        }
		    }
		    requestBody = stringBuilder.toString();
		return requestBody;
		
	}
	public String postXMLWithSercurity(String urlString,String XMLString) throws IOException
	{
		    URL url = new URL(urlString);
		    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", "application/xml");
		    connection.setDoOutput(true);
		    connection.setInstanceFollowRedirects(false);
		    connection.setConnectTimeout(15000);
		    StringBuffer sbuffer = new StringBuffer();
		    sbuffer.append(XMLString);
		    OutputStream os = connection.getOutputStream();
		    os.write(sbuffer.toString().getBytes());
		    os.flush();

		    //requestBody=connection.getInputStream();
		    
		    String requestBody = "";
		    StringBuilder stringBuilder = new StringBuilder();
		    BufferedReader bufferedReader = null;
		    try {
		        InputStream inputStream = connection.getInputStream();
		        if (inputStream != null) {
		            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		            char[] charBuffer = new char[128];
		            int bytesRead = -1;
		            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
		                stringBuilder.append(charBuffer, 0, bytesRead);
		            }
		        } else {
		            stringBuilder.append("");
		        }
		    } catch (IOException ex) {
		        throw ex;
		    } finally {
		        if (bufferedReader != null) {
		            try {
		                bufferedReader.close();
		            } catch (IOException ex) {
		                throw ex;
		            }
		        }
		    }
		    requestBody = stringBuilder.toString();
		return requestBody;
		
	}
	
	
	
	public String getRaw(HttpRequest request) throws IOException
	{
		 String requestBody = "";
		    StringBuilder stringBuilder = new StringBuilder();
		    BufferedReader bufferedReader = null;
		    try {
		        InputStream inputStream = request.getInputStream();
		        if (inputStream != null) {
		            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		            char[] charBuffer = new char[128];
		            int bytesRead = -1;
		            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
		                stringBuilder.append(charBuffer, 0, bytesRead);
		            }
		        } else {
		            stringBuilder.append("");
		        }
		    } catch (IOException ex) {
		        throw ex;
		    } finally {
		        if (bufferedReader != null) {
		            try {
		                bufferedReader.close();
		            } catch (IOException ex) {
		                throw ex;
		            }
		        }
		    }
		    requestBody = stringBuilder.toString();
		return requestBody;
		
	}

	public String getRaw(HttpServletRequest request) throws IOException
	{
		 String requestBody = "";
		    StringBuilder stringBuilder = new StringBuilder();
		    BufferedReader bufferedReader = null;
		    try {
		        InputStream inputStream = request.getInputStream();
		        if (inputStream != null) {
		            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		            char[] charBuffer = new char[128];
		            int bytesRead = -1;
		            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
		                stringBuilder.append(charBuffer, 0, bytesRead);
		            }
		        } else {
		            stringBuilder.append("");
		        }
		    } catch (IOException ex) {
		        throw ex;
		    } finally {
		        if (bufferedReader != null) {
		            try {
		                bufferedReader.close();
		            } catch (IOException ex) {
		                throw ex;
		            }
		        }
		    }
		    requestBody = stringBuilder.toString();
		return requestBody;
		
	}

	public String getStatusFromString(String response) {
		String temp="";
		
		for(int i=0;i<response.length();i++)
		{
			
			
		}
		
		return null;
	}

	public String getRequest(String urlString) throws IOException
	{
		    URL url = new URL(urlString);
		    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		    connection.setRequestMethod("GET");
		    connection.setDoOutput(true);
		    connection.setInstanceFollowRedirects(false);

		    connection.connect();
		    
		    String requestBody = "";
		    StringBuilder stringBuilder = new StringBuilder();
		    BufferedReader bufferedReader = null;
		    try {
		        InputStream inputStream = connection.getInputStream();
		        if (inputStream != null) {
		            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		            char[] charBuffer = new char[128];
		            int bytesRead = -1;
		            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
		                stringBuilder.append(charBuffer, 0, bytesRead);
		            }
		        } else {
		            stringBuilder.append("");
		        }
		    } catch (IOException ex) {
		        throw ex;
		    } finally {
		        if (bufferedReader != null) {
		            try {
		                bufferedReader.close();
		            } catch (IOException ex) {
		                throw ex;
		            }
		        }
		    }
		    requestBody = stringBuilder.toString();
		return requestBody;
		
	}

}
