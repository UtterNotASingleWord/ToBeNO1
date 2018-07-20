package ActiveMq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by hfx on 2018/7/19.
 */
@Repository
public class Consumer {
    @Autowired
    private JmsTemplate jmsTemplate;


    public void receiveMessage() {
        System.out.println("消费方接收消息:"+jmsTemplate.receive());
    }
}
