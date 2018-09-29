package com.Hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HbaseTest {
    private static HBaseAdmin admin = null;
    private static Configuration configuration;

    public static void main(String[] args) throws IOException {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "slave8,slave7,slave9");
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        admin = new HBaseAdmin(configuration);
        System.out.println(admin);
        List<String> tables = null;
        if (admin != null) {
            try {
                HTableDescriptor[] allTable = admin.listTables();
                if (allTable.length > 0) {
                    System.out.println(allTable.length);
                    tables = new ArrayList<>();
                }
                for (HTableDescriptor hTableDescriptor: allTable) {
                    tables.add(hTableDescriptor.getNameAsString());
                    System.out.println(hTableDescriptor.getNameAsString());
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
