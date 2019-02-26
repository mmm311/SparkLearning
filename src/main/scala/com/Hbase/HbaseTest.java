package com.Hbase;

import java.io.IOException;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.compress.Compression.Algorithm;
import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;
import org.apache.hadoop.hbase.util.Bytes;


public class HbaseTest {
    private static final String TABLE_NAME = "MY_TABLE_NAME_TOO";
    private static final String CF_DEFAULT = "DEFAULT_COLUMN_FAMILY";

    public static void createOrOverwrite(Admin admin, TableDescriptor table) throws IOException{
        if (admin.tableExists(table.getTableName())) {
            admin.disableTable(table.getTableName());
            admin.deleteTable(table.getTableName());
        }
        admin.createTable(table);
    }

    public static void createSchemaTables(Configuration config) throws IOException{
        try (Connection connection = ConnectionFactory.createConnection(config);
        Admin admin = connection.getAdmin()) {
            TableName tableName = TableName.valueOf(TABLE_NAME);
            TableDescriptorBuilder tableDescBuilder = TableDescriptorBuilder.newBuilder(tableName);

            ColumnFamilyDescriptorBuilder columnDescBuilder = ColumnFamilyDescriptorBuilder
                    .newBuilder(Bytes.toBytes(CF_DEFAULT))
                    .setBlocksize(32 * 1024)
                    .setCompactionCompressionType(Algorithm.NONE)
                    .setDataBlockEncoding(DataBlockEncoding.NONE);
            tableDescBuilder.setColumnFamily(columnDescBuilder.build());
            TableDescriptor table = tableDescBuilder.build();
            System.out.print("Creating table. ");
            createOrOverwrite(admin, table);
            System.out.println(" Done.");
        }
    }

    public static void modifySchema (Configuration config) throws IOException {
        try (Connection connection = ConnectionFactory.createConnection(config);
        Admin admin = connection.getAdmin()) {
            TableName tableName = TableName.valueOf(TABLE_NAME);
            if (!admin.tableExists(tableName)) {
                System.out.println("Table does not exist.");
                System.exit(-1);
            }

            ColumnFamilyDescriptorBuilder columnDescBuilder = ColumnFamilyDescriptorBuilder
                    .newBuilder(Bytes.toBytes("NEWCF"))
                    .setCompactionCompressionType(Algorithm.GZ)
                    .setMaxVersions(HConstants.ALL_VERSIONS);
            admin.addColumnFamily(tableName, columnDescBuilder.build());

        }
    }

    public static void main(String[] args)  {
        try {
            Configuration configuration = HBaseConfiguration.create();
            configuration.set("zookeeper.znode.parent", "/hbase-unsecure");
            configuration.set("hbase.zookeeper.quorum", "master,slave");

            createSchemaTables(configuration);
            modifySchema(configuration);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
