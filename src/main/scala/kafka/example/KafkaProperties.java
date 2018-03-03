package kafka.example;

public class KafkaProperties {
    public static final String TOPIC = "topic1";
    public static final String KAFKA_SERVER_URL = "10.1.18.84";
    public static final int KAFKA_SERVER_PORT = 6667;
    public static final int KAFAKA_PRODUCER_BUFFER_SIZE = 64 * 1024;
    public static final int CONECTION_TIMEOUT = 100000;
    public static final String TOPIC2 = "topic2";
    public static final String TOPIC3 = "topic3";
    public static final String CLIENT_ID = "SimpleConsumerDemoClient";

    private KafkaProperties(){}
}
