package top.panson.rocketmq.order;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
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
        topic = OrderMessage.TOPIC,
        consumerGroup = "order-message-group-a-" + OrderMessage.TOPIC,
        consumeMode = ConsumeMode.ORDERLY
)
public class OrderMessageConsumer implements RocketMQListener<OrderMessage> {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void onMessage(OrderMessage message) {
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);

        // sleep 2 秒，用于查看顺序消费的效果
        try {
            Thread.sleep(2 * 1000L);
        } catch (InterruptedException ignore) {
        }
    }
}
