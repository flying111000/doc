package test;

import java.net.InetAddress;
import java.net.URL;

import sun.net.util.IPAddressUtil;

public class ParseDomainName {
	InetAddress myServer = null;
	InetAddress myIPaddress = null;
	String domainName = null;

	public ParseDomainName(String domainName) {
		this.domainName = domainName;
	}

	public InetAddress getServerIP() {
		try {
			myServer = InetAddress.getByName(domainName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (myServer);
	}

	// 取得LOCALHOST的IP地址
	public InetAddress getMyIP() {
		try {
			myIPaddress = InetAddress.getLocalHost();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (myIPaddress);
	}
	public static void printJar(Class klass){
		URL location = klass.getResource('/' + klass.getName().replace('.', '/') + ".class");
		System.out.println(location);
	}
	public static void main(String[] args) {
		printJar(IPAddressUtil.class);
		
		for(int i=0;i<1;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ParseDomainName pdn = new ParseDomainName("www.usitrip.com");
			// System.out.println("Your host IP is: " +
			// pdn.getMyIP().getHostAddress());
			System.out.println("The Server IP is :" + pdn.getServerIP().getHostAddress());
		}
	}

}