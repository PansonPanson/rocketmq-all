package top.panson.rocketmq.simplemessage;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public SendResult batchSyncSend(Collection<Integer> ids) {
        List<Message<SimpleMessage>> messages = new ArrayList<>(ids.size());
        for (Integer id : ids) {
            SimpleMessage simpleMessage = new SimpleMessage(id);
            // 构建 Spring Messaging 定义的 Message 消息
            Message<SimpleMessage> message = MessageBuilder.withPayload(simpleMessage).build();
            messages.add(message);
        }
        // 同步批量发送消息
        return rocketMQTemplate.syncSend(SimpleMessage.TOPIC, messages, 30 * 1000L);
    }

}
