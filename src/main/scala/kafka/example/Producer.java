package kafka.example;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Producer extends Thread{

    private final KafkaProducer<Integer, String> producer;
    private final String topic;
    private final Boolean isAsync;

    public Producer(String topic, Boolean isAsync){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
        properties.put("client.id", "DemoProducer");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(properties);
        this.topic = topic;
        this.isAsync = isAsync;
    }

    @Override
    public void run() {
        int messageNo = 1;
        
        while (true){
            String messageStr = "Message_" + messageNo;
            long startTime = System.currentTimeMillis();
            if (isAsync){
                producer.send(new ProducerRecord<>(topic, messageNo, messageStr),
                        new DemoCallBack(startTime, messageNo, messageStr));
            } else {
                try{
                    producer.send(new ProducerRecord<>(topic, messageNo, messageStr)).get();
                    System.out.println("Sent message:( " + messageNo + "," + messageStr + ")");

                }catch (InterruptedException | ExecutionException e){
                    e.printStackTrace();
                }
            }
        }                                                                   
       
    }

}
class DemoCallBack implements Callback{

    private final long startTime;
    private final int key;
    private final String message;

    public DemoCallBack(long startTime, int key, String message){
        this.startTime = startTime;
        this.key = key;
        this.message = message;
    }

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (recordMetadata != null){
            System.out.println(
                    "message(" + key + "," + message + ") sent to paritition(" + recordMetadata.partition()+
                            "), " + "offset(" + recordMetadata.offset() + ") in " + elapsedTime + " ms"
            );
        }else {
            e.printStackTrace();
        }

    }
}
