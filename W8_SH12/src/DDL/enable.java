package DDL;

import java.io.IOException;
import java.util.Scanner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class enable {

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

		// To make table enable
		try {
			if (admin.isTableEnabled(x))
				System.out.println("Table is enabled already");
			else {
				admin.enableTable(x);
				System.out.println("Table is enabled now");
			}

		} catch (TableNotFoundException e) {
			
			System.out.println("Table does not exist" + e.toString());
		}

		admin.close();
	}
}
