package ActiveMq;

import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import javax.jms.JMSException;
import javax.jms.Session;

/**
 * Created by hfx on 2018/7/19.
 */
@Service
public class Producer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(final String message) {
        jmsTemplate.send(new MessageCreator() {

            public Message createMessage(Session session) throws JMSException {
                return (Message) session.createTextMessage(message);
            }
        });
    }


    /*public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("JmsTemplate");
        jmsTemplate.send( new MessageCreator() {

            public Message createMessage(Session session) throws JMSException {
                return (Message) session.createTextMessage("队列1");
            }
        });
    }*/
}
