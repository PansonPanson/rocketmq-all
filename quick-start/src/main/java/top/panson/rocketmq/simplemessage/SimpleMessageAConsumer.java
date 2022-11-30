package top.panson.rocketmq.simplemessage;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @create 2022-11-29 15:49
 * @Author: Panson
 */

@Component
@RocketMQMessageListener(
        topic = SimpleMessage.TOPIC,
        consumerGroup = "simple-message-group-a" + SimpleMessage.TOPIC
)
public class SimpleMessageAConsumer implements RocketMQListener<SimpleMessage> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(SimpleMessage simpleMessage) {
        logger.info("[SimpleMessageAConsumer-onMessage][线程编号:{} 消息内容:{}]", Thread.currentThread().getId(), JSON.toJSONString(simpleMessage));
    }
}
