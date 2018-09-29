package com.kafka;

import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

class DemoCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        System.out.println("partition: " + metadata.partition()+" , offset: "+ metadata.offset());
    }
}

public class AvroKafkaProducter {
    Logger logger = LoggerFactory.getLogger("AvroKafkaProducter");

    public static final String USER_SCHEMA = "{"
            + "\"type\":\"record\","
            + "\"name\":\"Iteblog\","
            + "\"fields\":["
            + "  { \"name\":\"str1\", \"type\":\"string\" },"
            + "  { \"name\":\"str2\", \"type\":\"string\" },"
            + "  { \"name\":\"int1\", \"type\":\"int\" }"
            + "]}";

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:6667,slave:6667");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");

        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(USER_SCHEMA);
        Injection<GenericRecord, byte[]> recordInjection = GenericAvroCodecs.toBinary(schema);
        KafkaProducer<String, byte[]> producer = new KafkaProducer<String, byte[]>(props);
        System.out.println("start ...");
        for (int i = 0; i < 1000; i++) {
            GenericData.Record avroRecord = new GenericData.Record(schema);
            avroRecord.put("str1", "Str 1-" + i);
            avroRecord.put("str2", "Str 2-" + i);
            avroRecord.put("int1", i);
            byte[] bytes = recordInjection.apply(avroRecord);

            ProducerRecord<String, byte[]> record = new ProducerRecord<String, byte[]>("itblog", "" + i, bytes);
            producer.send(record, new DemoCallback());
        }
        producer.close();
    }


}
