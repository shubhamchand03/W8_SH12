package DDL;

import java.io.IOException;
import java.util.Scanner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class disable {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {

		// To create HBase Admin
		Configuration c = HBaseConfiguration.create();
		HBaseAdmin admin = new HBaseAdmin(c);

		// Name of the table by user
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Table Name: ");
		String x = sc.nextLine();
		sc.close();

		// To disable the table if it is enabled
		try {
			if (admin.isTableDisabled(x))
				System.out.println("Table is disabled already");
			else {
				admin.disableTable(x);
				System.out.println("Table is disabled successfully");
			}

		} catch (TableNotFoundException e) {

			System.out.println("No Table exist" + e.toString());
		}

		admin.close();
	}
}
