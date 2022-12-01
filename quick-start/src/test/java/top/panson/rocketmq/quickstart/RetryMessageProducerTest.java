package top.panson.rocketmq.quickstart;

import org.apache.rocketmq.client.producer.SendResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.panson.rocketmq.Application;
import top.panson.rocketmq.messageretry.RetryMessageProducer;

import java.util.concurrent.CountDownLatch;

/**
 * @create 2022-12-01 19:18
 * @Author: Panson
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RetryMessageProducerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RetryMessageProducer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        logger.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
