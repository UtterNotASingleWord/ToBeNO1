package ActiveMq;

import org.springframework.stereotype.Repository;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by hfx on 2018/7/19.
 */

public class ListenerConfig  implements MessageListener  {
    public void onMessage(Message message) {
        try {
            System.out.println(((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
