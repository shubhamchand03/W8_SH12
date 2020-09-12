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
import org.apache.hadoop.hbase.regionserver.NoSuchColumnFamilyException;
import org.apache.hadoop.hbase.util.Bytes;

public class get {

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
			System.out.println("Enter column families");
			String[] families = sc.nextLine().split(",");

			try {
				
				Scan s = new Scan();
				for (String family : families)
					s.addFamily(Bytes.toBytes(family));

				ResultScanner scanner = hTable.getScanner(s);

				// Reading values from scan result
				for (Result r = scanner.next(); r != null; r = scanner.next())
					System.out.println("Row : " + r);
			} catch (NoSuchColumnFamilyException e) {
				System.out.println("Column family does not exist. "+ e.toString());
			}

			hTable.close();
		} else
			System.out.println("Table does not exist");
		sc.close();
		admin.close();

	}

}
