package cidrProj;

import java.text.Collator;

import org.apache.commons.net.util.SubnetUtils;

public class Test {

	public static void main(String[] args) {
		SubnetUtils su=new SubnetUtils("1.1.254.0/25");
		System.out.println(su.getInfo().getLowAddress());
		System.out.println(su.getInfo().getHighAddress());

		System.out.println(isSame("a", "b"));
		System.out.println(isSame("a", "ив"));
		System.out.println(isSame("a", "A"));
	}
	public static boolean isSame(String a, String b) {
	    Collator insenstiveStringComparator = Collator.getInstance();
//	    insenstiveStringComparator.setStrength(Collator.PRIMARY);
//	    insenstiveStringComparator.setStrength(Collator.SECONDARY);
	    insenstiveStringComparator.setStrength(Collator.TERTIARY);
	    return insenstiveStringComparator.compare(a, b) == 0;
	}
}
