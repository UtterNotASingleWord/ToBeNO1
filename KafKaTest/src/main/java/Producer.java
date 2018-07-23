import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by hfx on 2018/7/23.
 */
public class Producer {
    public  static  void main(String args[]){
        Properties properties=PropertiesUtil.getProperties("producer.properties");
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        kafkaProducer.send(new ProducerRecord<String, String>("test","hello"));
        kafkaProducer.close();

    }





}
