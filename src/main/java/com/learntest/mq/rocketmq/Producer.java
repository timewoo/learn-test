package com.learntest.mq.rocketmq;

import org.apache.catalina.filters.RemoteHostFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.rmi.server.RemoteObject;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanglin
 * @date 2020/12/1 17:29
 */
public class Producer {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("please_rename_unique_group_name");
        defaultMQProducer.setNamesrvAddr("192.168.213.128:9876");
        defaultMQProducer.start();

//        syncProduce(defaultMQProducer);

//        defaultMQProducer.setRetryTimesWhenSendAsyncFailed(0);
//        asyncProduce(defaultMQProducer);

//        oneWayProduce(defaultMQProducer);

//        orderProduce(defaultMQProducer);

//        broadCastingProduce(defaultMQProducer);

        scheduleProduce(defaultMQProducer);

        defaultMQProducer.shutdown();
    }

    public static void syncProduce(DefaultMQProducer defaultMQProducer) throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {
        for (int i = 0; i < 100; i++) {
            Message message = new Message("TopicTest", "TagA", ("RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult send = defaultMQProducer.send(message);
            System.out.printf("%s%n", send);
        }
    }

    public static void asyncProduce(DefaultMQProducer defaultMQProducer) throws UnsupportedEncodingException, RemotingException, MQClientException, InterruptedException {
        int messageCount = 100;
        CountDownLatch countDownLatch = new CountDownLatch(messageCount);
        for (int i = 0; i < messageCount; i++) {
            int index = i;
            Message message = new Message("Jodie_topic_1023", "TagA", "OrderID188", "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            defaultMQProducer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable throwable) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d Exception %s %n", index, throwable);
                    throwable.printStackTrace();
                }
            });
        }
        System.out.println("等待-------------");
        countDownLatch.await(5, TimeUnit.SECONDS);
    }

    public static void oneWayProduce(DefaultMQProducer defaultMQProducer) throws UnsupportedEncodingException, RemotingException, MQClientException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("TopicTestOneWay",
                    "TagA",
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            defaultMQProducer.sendOneway(msg);
        }
        Thread.sleep(5000);
    }

    public static void orderProduce(DefaultMQProducer defaultMQProducer) throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {
        String[] tags = {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 100; i++) {
            // i       0  1  2  3  4  5  6  7  8  9
            //        10 11 12 13 14 15 16 17 18 19
            // ........
            //orderId  0  1  2  3  4  5  6  7  8  9
            int orderId = i % 10;
            Message message = new Message("TopicTestjjj1", tags[i % tags.length], "KEY" + i,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult send = defaultMQProducer.send(message, (mqs, msg, arg) -> {
                // id = orderId
                Integer id = (Integer) arg;
                // 同一批数据放在一个队列中,默认创建4个队列
                // queue   0   1   2    3
                //         0   -1   2    3
                //         -4   5   -6    7
                //         8   -9
                //        10   -11  12   13
                //        -14    15  -16  17
                int index = id % mqs.size();
                // 获取发送消息存储的队列，同一个队列内的消息有序
                return mqs.get(index);
            }, orderId);
            System.out.printf("First %s%n and then %s%n and then %s%n", send, message, StringUtils.toEncodedString(message.getBody(), Charset.defaultCharset()));
        }
    }

    public static void broadCastingProduce(DefaultMQProducer defaultMQProducer) throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {
        for (int i = 0; i < 100; i++) {
            Message message = new Message("TopicTest1", "TagA", "OrderID188", "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult send = defaultMQProducer.send(message);
            System.out.printf("%s%n", send);
        }
    }

    public static void scheduleProduce(DefaultMQProducer defaultMQProducer) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        for (int i = 0; i < 100; i++) {
            Message message = new Message("TestTopic2", ("Hello scheduled message " + i).getBytes());
            message.setDelayTimeLevel(3);
            defaultMQProducer.send(message);
        }
    }

    public static void transactionProduce() throws MQClientException, InterruptedException {
        TransactionMQProducer transactionMQProducer = new TransactionMQProducer("please_rename_unique_group_name");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });
        transactionMQProducer.setExecutorService(threadPoolExecutor);
        TransactionListenerImpl transactionListener = new TransactionListenerImpl();
        transactionMQProducer.setTransactionListener(transactionListener);
        transactionMQProducer.start();
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {
                Message msg =
                        new Message("TopicTest1234", tags[i % tags.length], "KEY" + i,
                                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = transactionMQProducer.sendMessageInTransaction(msg, null);
                System.out.printf("%s%n", sendResult);

                Thread.sleep(10);
            } catch (MQClientException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 100000; i++) {
            Thread.sleep(1000);
        }
        transactionMQProducer.shutdown();
    }

    static class TransactionListenerImpl implements TransactionListener{

        private AtomicInteger transactionIndex = new AtomicInteger(0);
        private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

        @Override
        public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
            int value = transactionIndex.getAndIncrement();
            int status = value % 3;
            localTrans.put(msg.getTransactionId(),status);
            return LocalTransactionState.UNKNOW;
        }

        @Override
        public LocalTransactionState checkLocalTransaction(MessageExt msg) {
            Integer status = localTrans.get(msg.getTransactionId());
            if (null!=status){
                switch (status){
                    case 0:
                        return LocalTransactionState.UNKNOW;
                    case 1:
                        return LocalTransactionState.COMMIT_MESSAGE;
                    case 2:
                        return LocalTransactionState.ROLLBACK_MESSAGE;
                }
            }
            return LocalTransactionState.COMMIT_MESSAGE;
        }
    }
}
