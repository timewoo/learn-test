package com.learntest.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author yanglin
 * @date 2020/11/18 13:44
 */
public class ZookeeperWatcher implements Watcher {


    @Override
    public void process(WatchedEvent event) {
        Event.KeeperState state = event.getState();
        String path = event.getPath();
        Event.EventType type = event.getType();
        if (state.equals(Event.KeeperState.ConnectedReadOnly)){
            if (type.equals(Event.EventType.NodeCreated)){

            }
        }
    }
}
