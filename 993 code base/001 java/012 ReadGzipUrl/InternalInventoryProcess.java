import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

public class InternalInventoryProcess {

	private ZipEntry ze;
	private BufferedReader fileContent;
	private String hotelCode=null;
	private String tagName;
	private List<String> sqls=new ArrayList<>();

	public InternalInventoryProcess(ZipEntry ze, BufferedReader fileContent) {
		this.ze = ze;
		this.fileContent = fileContent;
	}

	public void parse() {
		String line;
		int rowNumber = 0;
//		System.err.println("file - " + ze.getName() + " : " + ze.getSize() + " bytes");
		try {
			while ((line = fileContent.readLine()) != null) {// && rowNumber<10
//				System.out.println(line);

				if (line.startsWith("{")) {
					if (line.startsWith("{/")) {
						tagName = "";
					} else {
						tagName = line;
					}

				} else {
					processLine(tagName, line);
				}

				rowNumber++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processLine(String tagName, String line) {
		if ("{CCON}".equalsIgnoreCase(tagName)) {
			parseCCON(sqls, line);
		}
	}

	private void eraseHotel(List<String> sqls, String hotelCode){
		sqls.add("delete from ccon_contract_header where hotel_code='"+hotelCode+"';");
	}
	
	private void parseCCON(List<String> sqls, String line) {
//		System.out.println(line);
		
		StringBuffer sql = new StringBuffer();

		String[] fields = line.split(":",-1);
		hotelCode=fields[7];
		eraseHotel(sqls,hotelCode);
		sql.append("INSERT INTO ccon_contract_header (external_inventory, destination_code, office_code, contract_number, contract_name, company_code, type_of_service, hotel_code, giata_hotel_code, initial_date, end_date, no_hotel, currency, base_board, classification, payment_model, daily_price, release_days_each_night, minimum_child_age, maximum_child_age, opaque, fix_rate, contract_type, maximum_rooms, hotel_content, selling_price_mandatory, internal_field, internal_field_data, internal_classification, is_total_price_per_stay)")
				.append(" VALUES (");
		for(int i=0;i<fields.length;i++){
			if(i>0){
				sql.append( ",");	
			}
			sql.append( "'"+fields[i]+"'");	
		}
		sql.append( ");");
		sqls.add(sql.toString());
	}
	
	public void save(){
		for(String sql:sqls){
			System.out.println(sql);
		}
	}
}
