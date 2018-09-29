package com.kafka;

import kafka.example.KafkaProperties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;
import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;
import java.util.Random;

public class KafkaTest01 extends Thread {
    public Properties properties = null;
    public KafkaTest01() {
        properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:6667,slave:6667");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");

    }

    @Override
    public void run() {
        while (true) {
            init();
        }
    }

    public void init() {
        String[] strings = new String[] {"hello world", "hello 1 world ", "wrewa"};
        KafkaProducer producer = new KafkaProducer(properties);
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("CustomerCountry",
                "precision Products", strings[new Random().nextInt(3)]);

        try {
            producer.send(record, new DemoProducerCallback()).get();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new KafkaTest01().start();
        }

    }
    static class DemoProducerCallback implements Callback {

        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
            System.out.println("partition " + metadata.partition() + " offset " + metadata.offset());
            if (exception != null) {
                exception.printStackTrace();
            }
        }
    }
}
