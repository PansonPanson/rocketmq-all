package top.panson.rocketmq.messageretry;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @create 2022-12-01 12:52
 * @Author: Panson
 */
@Component
public class RetryMessageProducer {

    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    public RetryMessageProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public SendResult syncSend(Integer id) {
        // 创建 Demo04Message 消息
        RetryMessage message = new RetryMessage(id);
        message.setId(id);
        // 同步发送消息
        return rocketMQTemplate.syncSend(RetryMessage.TOPIC, message);
    }
}
