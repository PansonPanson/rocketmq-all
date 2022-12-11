package top.panson.rocketmq.broadcast;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import top.panson.rocketmq.delayedmessage.DelayedMessage;


/**
 * @create 2022-11-30 14:11
 * @Author: Panson
 */

@Component
public class BroadcastMessageProducer {

    private final RocketMQTemplate rocketMQTemplate;

    @Autowired
    public BroadcastMessageProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public SendResult syncSend(Integer id) {
        Message<BroadcastMessage> message = MessageBuilder.withPayload(new BroadcastMessage(id)).build();
        return rocketMQTemplate.syncSend(BroadcastMessage.TOPIC, message);
    }

}
