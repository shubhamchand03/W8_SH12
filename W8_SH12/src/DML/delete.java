package DML;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

public class delete {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// To create HBase Admin
		Configuration c = HBaseConfiguration.create();
		HBaseAdmin admin = new HBaseAdmin(c);

		// Name of the table by user
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Table Name: ");
		String x = sc.nextLine();

		if (admin.tableExists(x)) {
			HTable hTable = new HTable(c, x);

			System.out.println("Enter Row, Column Family Name, Column Name");
			String input = sc.nextLine();
			String[] t = input.split(",");

			// Row
			Delete d = new Delete(Bytes.toBytes(t[0].trim()));
			d.deleteColumn(Bytes.toBytes(t[1].trim()),Bytes.toBytes(t[2].trim()));

			hTable.delete(d);
			System.out.println("Deleted");

			hTable.close();
		} else
			System.out.println("Table does not exist");
		sc.close();
		admin.close();

	}

}
