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
import top.panson.rocketmq.trasaction.TransactionMessageProducer;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TransactionMessageProducerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TransactionMessageProducer producer;

    @Test
    public void testSendMessageInTransaction() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.sendMessageInTransaction(id);
        logger.info("[testSendMessageInTransaction][发送编号：[{}] 发送结果：[{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }


}
