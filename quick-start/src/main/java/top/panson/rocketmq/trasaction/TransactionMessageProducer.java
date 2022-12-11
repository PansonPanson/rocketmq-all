package top.panson.rocketmq.trasaction;

import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


/**
 * 在 RocketMQ 中，Producer 可以根据定义 MessageQueueSelector 消息队列选择策略，选择 Topic 下的队列。目前提供三种策略：
 *
 * SelectMessageQueueByHash ，【默认】，基于 hashKey 的哈希值取余，选择对应的队列。
 * SelectMessageQueueByRandom ，基于随机的策略，选择队列。
 * SelectMessageQueueByMachineRoom：空实现
 * 未使用 MessageQueueSelector 时，采用轮询的策略选择队列。
 * @create 2022-11-30 14:11
 * @Author: Panson
 */

@Component
public class TransactionMessageProducer {

    private final RocketMQTemplate rocketMQTemplate;

    @Autowired
    public TransactionMessageProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public TransactionSendResult sendMessageInTransaction(Integer id) {
        // <1> 创建 Demo07Message 消息
        Message<TransactionMessage> message = MessageBuilder.withPayload(new TransactionMessage(id)).build();
        // <2> 发送事务消息
        return rocketMQTemplate.sendMessageInTransaction(TransactionMessage.TOPIC, message, id);
    }

    @RocketMQTransactionListener()
    public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

        private Logger logger = LoggerFactory.getLogger(getClass());

        @Override
        public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            // ... local transaction process, return rollback, commit or unknown
            logger.info("[executeLocalTransaction][执行本地事务，消息：{} arg：{}]", msg, arg);
            return RocketMQLocalTransactionState.UNKNOWN;
        }

        @Override
        public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
            // ... check transaction status and return rollback, commit or unknown
            logger.info("[checkLocalTransaction][回查消息：{}]", msg);
            return RocketMQLocalTransactionState.COMMIT;
        }

    }


}
