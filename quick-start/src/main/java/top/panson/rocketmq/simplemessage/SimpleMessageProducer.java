package top.panson.rocketmq.simplemessage;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @create 2022-11-29 15:40
 * @Author: Panson
 */

@Component
public class SimpleMessageProducer {

    private RocketMQTemplate rocketMQTemplate;

    public SimpleMessageProducer() {}

    @Autowired
    public SimpleMessageProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    /**
     * 同步发送消息
     * @param id
     * @return
     */
    public SendResult syncSend(Integer id) {
        SimpleMessage message = new SimpleMessage();
        message.setId(id);
        return rocketMQTemplate.syncSend(SimpleMessage.TOPIC, message);
    }

    /**
     * 异步发送消息
     * @param id
     * @param callback
     */
    public void asyncSend(Integer id, SendCallback callback) {
        SimpleMessage message = new SimpleMessage();
        message.setId(id);
        rocketMQTemplate.asyncSend(SimpleMessage.TOPIC, message, callback);
    }

    /**
     * oneway 发送消息
     * @param id
     */
    public void onewaySend(Integer id) {
        SimpleMessage message = new SimpleMessage();
        message.setId(id);
        rocketMQTemplate.sendOneWay(SimpleMessage.TOPIC, message);
    }

}
