package cidrProj;

import org.apache.commons.net.util.SubnetUtils;

public class Test {

	public static void main(String[] args) {
		SubnetUtils su=new SubnetUtils("1.1.254.0/25");
		System.out.println(su.getInfo().getLowAddress());
		System.out.println(su.getInfo().getHighAddress());

	}

}
