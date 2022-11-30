package top.panson.rocketmq.quickstart;

import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.panson.rocketmq.Application;
import top.panson.rocketmq.delayedmessage.DelayedMessageProducer;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @create 2022-11-30 14:39
 * @Author: Panson
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DelayedMessageProducerTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private DelayedMessageProducer producer;

    @Test
    public void testSyncSendDelay() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSendDelay(id, 3); // 延迟级别 3 ，即 10 秒后消费
        logger.info("[testSyncSendDelay][发送编号：[{}] 发送结果：[{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}
