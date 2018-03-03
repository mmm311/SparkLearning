package kafka.example;

/**
 * @author liu
 */
public class KafkaConsumerProduceDemo {
    public static void main(String[] args){
        boolean isAsync = args.length == 0 || !args[0].trim().equalsIgnoreCase("sync");
        Producer producerThread = new Producer(KafkaProperties.TOPIC, isAsync);
        producerThread.start();

        Consumer consumerThread = new Consumer(KafkaProperties.TOPIC);
        consumerThread.start();
        Consumer consumerThread2 = new Consumer(KafkaProperties.TOPIC2);
        consumerThread2.start();
    }
}
