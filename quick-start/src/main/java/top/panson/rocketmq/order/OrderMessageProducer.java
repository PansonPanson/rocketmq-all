package top.panson.rocketmq.order;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
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
public class OrderMessageProducer {

    private final RocketMQTemplate rocketMQTemplate;

    @Autowired
    public OrderMessageProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public SendResult syncSendOrderly(Integer id) {
        OrderMessage message = new OrderMessage();
        message.setId(id);
        // 同步发送消息
        return rocketMQTemplate.syncSendOrderly(OrderMessage.TOPIC, message, String.valueOf(id));
    }

    public void asyncSendOrderly(Integer id, SendCallback callback) {
        OrderMessage message = new OrderMessage();
        message.setId(id);
        // 异步发送消息
        rocketMQTemplate.asyncSendOrderly(OrderMessage.TOPIC, message, String.valueOf(id), callback);
    }

    public void onewaySendOrderly(Integer id) {
        OrderMessage message = new OrderMessage();
        message.setId(id);
        // 异步发送消息
        rocketMQTemplate.sendOneWayOrderly(OrderMessage.TOPIC, message, String.valueOf(id));
    }


}
