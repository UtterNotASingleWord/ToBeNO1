import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by hfx on 2018/7/23.
 */
public class Consumer {
    public  static  void main(String args[]){
        Properties properties=PropertiesUtil.getProperties("consumer.properties");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList("test"));
        while (true){
            ConsumerRecords<String, String> consumerRecord=kafkaConsumer.poll(100);
            for(ConsumerRecord<String, String> consumerRecords:consumerRecord){
                System.out.println(consumerRecords.value());
            }

        }



    }
}
