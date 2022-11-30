package top.panson.rocketmq.delayedmessage;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @create 2022-11-30 14:11
 * @Author: Panson
 */
@Component
@RocketMQMessageListener(
        topic = DelayedMessage.TOPIC,
        consumerGroup = "delayed-message-group-" + DelayedMessage.TOPIC
)
public class DelayedMessageConsumer implements RocketMQListener<DelayedMessage> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(DelayedMessage delayedMessage) {
        logger.info("[DelayedMessageConsumer-onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), JSON.toJSONString(delayedMessage));
    }
}
