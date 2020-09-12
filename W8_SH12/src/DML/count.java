package DML;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class count {

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
			Scan scan = new Scan(Bytes.toBytes(1));
			ResultScanner scanner = hTable.getScanner(scan);
			int count = 0;
			for (Result r = scanner.next(); r != null; r = scanner.next()) {
				count++;
			}
			System.out.println("Number of Rows = " + count);

			hTable.close();
		} else
			System.out.println("Table does not exist");
		sc.close();
		admin.close();

	}

}
