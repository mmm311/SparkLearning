//package kafka.example;
//
//import kafka.api.FetchRequestBuilder;
//import kafka.javaapi.FetchRequest;
//import kafka.javaapi.consumer.SimpleConsumer;
//import kafka.javaapi.message.ByteBufferMessageSet;
//
//import java.io.UnsupportedEncodingException;
//import kafka.message.MessageAndOffset;
//
//
//import java.nio.ByteBuffer;
//
//public class SimpleConsumerDemo {
//    private static void printMessage(ByteBufferMessageSet messageSet) throws UnsupportedEncodingException {
//        for (MessageAndOffset messageAndOffset: messageSet){
//            ByteBuffer payload = messageAndOffset.message().payload();
//            byte[] bytes = new byte[payload.limit()];
//            payload.get(bytes);
//            System.out.println(new String(bytes, "UTF-8"));
//
//        }
//    }
//
//    private static void generateData(){
//        Producer producer2 = new Producer(KafkaProperties.TOPIC2, false);
//        producer2.start();
//        Producer producer3 = new Producer(KafkaProperties.TOPIC3, false);
//        producer3.start();
//        try{
//            Thread.sleep(1000);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) throws Exception{
//        generateData();
//
//        SimpleConsumer simpleConsumer = new SimpleConsumer(KafkaProperties.KAFKA_SERVER_URL,
//                KafkaProperties.KAFKA_SERVER_PORT,
//                KafkaProperties.CONECTION_TIMEOUT,
//                KafkaProperties.KAFAKA_PRODUCER_BUFFER_SIZE,
//                KafkaProperties.CLIENT_ID);
//
//        System.out.println("Testing single fetch");
//        FetchRequest req = new FetchRequestBuilder()
//                .clientId(KafkaProperties.CLIENT_ID)
//                .addFetch(KafkaProperties.TOPIC2, 0, 0L, 100)
//                .build();
//
//    }
//}
