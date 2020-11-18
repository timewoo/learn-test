package com.learntest.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yanglin
 * @date 2020/10/22 15:23
 */
public class ZkClientTest {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString("118.190.37.175:2181").retryPolicy(retryPolicy).build();
        curatorFramework.start();
//        curatorFramework.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/node1/000001");
        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/node1");
        curatorFramework.close();
    }
}
