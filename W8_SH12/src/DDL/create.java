package DDL;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableExistsException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class create {

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

		// To create table and column families
		HTableDescriptor table = new HTableDescriptor(TableName.valueOf(x));
		HColumnDescriptor family1 = new HColumnDescriptor("column_family_1");
		HColumnDescriptor family2 = new HColumnDescriptor("column_family_2");

		// To add column families
		table.addFamily(family1);
		table.addFamily(family2);

		// Create the table
		try {
			admin.createTable(table);
			System.out.println("Table is created");
		} catch (TableExistsException e) {
			System.out.println("Duplicate table exist.\n" + e.toString());
		}
		admin.close();
	}
}
