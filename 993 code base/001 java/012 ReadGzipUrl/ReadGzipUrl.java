package expedia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class ReadGzipUrl {
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new GZIPInputStream(new URL("https://s3.amazonaws.com/expedia-static-feed/United+States+(.com)_Agency%2BMerchant_Price.csv.gz").openStream())));

			String line = null;
			int i=0;
			while ((line = reader.readLine()) != null && i<100) {
				System.out.println(line);
				i++;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}