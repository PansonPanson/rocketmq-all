package top.panson.rocketmq.trasaction;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @create 2022-12-11 15:42
 * @Author: Panson
 */

@Component
@RocketMQMessageListener(
        topic = TransactionMessage.TOPIC,
        consumerGroup = "order-message-group-a-" + TransactionMessage.TOPIC,
        consumeMode = ConsumeMode.ORDERLY
)
public class TransactionMessageConsumer implements RocketMQListener<TransactionMessage> {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void onMessage(TransactionMessage message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
