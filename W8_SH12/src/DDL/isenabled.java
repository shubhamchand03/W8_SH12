package DDL;

import java.io.IOException;
import java.util.Scanner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class isenabled {

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

		// To check table whether it is enable or not
		try {
			if (admin.isTableEnabled(x))
				System.out.println("Table is enabled");
			else
				System.out.println("Table is not enabled");

		} catch (TableNotFoundException e) {
			System.out.println("Table does not exist" + e.toString());
		}

		admin.close();
	}
}
