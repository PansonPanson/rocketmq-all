package top.panson.rocketmq.delayedmessage;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


/**
 * @create 2022-11-30 14:11
 * @Author: Panson
 */

@Component
public class DelayedMessageProducer {

    private final RocketMQTemplate rocketMQTemplate;

    @Autowired
    public DelayedMessageProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public SendResult syncSendDelay(Integer id, int  delayLevel) {
        Message<DelayedMessage> message = MessageBuilder.withPayload(new DelayedMessage(id)).build();
        return rocketMQTemplate.syncSend(DelayedMessage.TOPIC, message, 30 * 1000, delayLevel);
    }

}
