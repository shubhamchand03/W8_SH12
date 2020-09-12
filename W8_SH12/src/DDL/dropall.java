package DDL;

import java.io.IOException;
import java.util.Scanner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class dropall {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {

		// To create HBase Admin
		Configuration c = HBaseConfiguration.create();
		HBaseAdmin admin = new HBaseAdmin(c);

		// Name of the table by user
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Table Name: ");
		String rgx = sc.nextLine();
		sc.close();

		// Drop table with matching regex after disabling the table
		try {
			for (String x : admin.getTableNames(rgx)) {
				if (admin.isTableEnabled(x)){
					admin.disableTable(x);
				}					
				admin.deleteTable(x);
				System.out.println(x + " is dropped.");
			}

		} catch (TableNotFoundException e) {
			
			System.out.println("Table does not exist" + e.toString());
		}

		admin.close();
	}
}
