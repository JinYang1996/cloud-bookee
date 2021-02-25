package com.bookee.bookee.watcher;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

@Slf4j
public class ChildrenWatcher implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        log.error("ChildrenWatcher start============");
        log.error("ChildrenWatcher state={}",watchedEvent.getState());
        log.error("ChildrenWatcher type={}",watchedEvent.getType());
        log.error("ChildrenWatcher path={}",watchedEvent.getPath());
        log.error("ChildrenWatcher end============");
    }
}
