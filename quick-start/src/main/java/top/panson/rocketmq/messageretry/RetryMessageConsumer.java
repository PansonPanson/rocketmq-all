package top.panson.rocketmq.messageretry;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @create 2022-12-01 12:53
 * @Author: Panson
 */
@Component
@RocketMQMessageListener(
        topic = RetryMessage.TOPIC,
        consumerGroup = "retry-message-consumer-group-" + RetryMessage.TOPIC
)
public class RetryMessageConsumer implements RocketMQListener<RetryMessage> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private AtomicInteger count = new AtomicInteger();

    @Override
    public void onMessage(RetryMessage message) {
        count.incrementAndGet();
        logger.info("[onMessage][线程编号:{} 消息内容：{}]，第 {} 次消费消息", Thread.currentThread().getId(), message, count.get());
        // <X> 注意，此处抛出一个 RuntimeException 异常，模拟消费失败
        throw new RuntimeException("故意抛出一个异常");
    }
}
