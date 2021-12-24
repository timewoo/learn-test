package com.learntest.mq.rocketmq;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.consumer.DefaultMQPushConsumerImpl;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author yanglin
 * @date 2020/12/2 11:36
 */
public class Consumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("push5");
        defaultMQPushConsumer.setNamesrvAddr("192.168.213.128:9876");
//        pushConsumer(defaultMQPushConsumer);

//        orderConsumer(defaultMQPushConsumer);

//        broadCastingConsumer(defaultMQPushConsumer);

        scheduledConsumer(defaultMQPushConsumer);
    }

    public static void pushConsumer(DefaultMQPushConsumer defaultMQPushConsumer) throws MQClientException {
        defaultMQPushConsumer.subscribe("TopicTest","*");
        defaultMQPushConsumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        defaultMQPushConsumer.start();
        System.out.printf("Consumer Started.%n");
    }

    public static void orderConsumer(DefaultMQPushConsumer defaultMQPushConsumer) throws MQClientException {
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        defaultMQPushConsumer.subscribe("TopicTestjjj1","TagA || TagC || TagD");
        defaultMQPushConsumer.registerMessageListener(new MessageListenerOrderly() {
//            AtomicLong consumerTime = new AtomicLong(0);
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                // 不自动提交
                context.setAutoCommit(false);
                System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs+ StringUtils.toEncodedString(msgs.get(0).getBody(), Charset.defaultCharset()) + "%n");
//                this.consumerTime.incrementAndGet();
//                if ((this.consumerTime.get()%2)==0){
//                    return ConsumeOrderlyStatus.SUCCESS;
//                }else if ((this.consumerTime.get() % 3) == 0){
//                    return ConsumeOrderlyStatus.ROLLBACK;
//                }else if ((this.consumerTime.get() % 4) == 0) {
//                    return ConsumeOrderlyStatus.COMMIT;
//                } else if ((this.consumerTime.get() % 5) == 0) {
//                    context.setSuspendCurrentQueueTimeMillis(3000);
//                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
//                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        defaultMQPushConsumer.start();
        System.out.printf("Consumer Started.%n");
    }

    public static void broadCastingConsumer(DefaultMQPushConsumer defaultMQPushConsumer) throws MQClientException {
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        defaultMQPushConsumer.setMessageModel(MessageModel.BROADCASTING);
        defaultMQPushConsumer.subscribe("TopicTest1", "TagA || TagC || TagD");
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        defaultMQPushConsumer.start();
        System.out.printf("Broadcast Consumer Started.%n");
    }

    public static void scheduledConsumer(DefaultMQPushConsumer defaultMQPushConsumer) throws MQClientException {
        defaultMQPushConsumer.subscribe("TestTopic2", "*");
        defaultMQPushConsumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt message : msgs) {
                // Print approximate delay time period
                System.out.println("Receive message[msgId=" + message.getMsgId() + "] "
                        + (System.currentTimeMillis() - message.getStoreTimestamp()) + "ms later");
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        defaultMQPushConsumer.start();
    }
}
