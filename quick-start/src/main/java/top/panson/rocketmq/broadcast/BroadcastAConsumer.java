package top.panson.rocketmq.broadcast;

import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.panson.rocketmq.delayedmessage.DelayedMessage;

/**
 * @create 2022-12-11 15:42
 * @Author: Panson
 */

@Component
@RocketMQMessageListener(
        topic = BroadcastMessage.TOPIC,
        consumerGroup = "broadcast-message-group-a-" + BroadcastMessage.TOPIC,
        messageModel = MessageModel.BROADCASTING // 设置为广播消费
)
public class BroadcastAConsumer implements RocketMQListener<BroadcastMessage> {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void onMessage(BroadcastMessage message) {
        logger.info("[BroadcastAConsumer-onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
