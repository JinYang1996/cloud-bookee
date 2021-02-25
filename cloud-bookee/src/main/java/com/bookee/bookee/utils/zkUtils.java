package com.bookee.bookee.utils;

import com.bookee.bookee.watcher.ChildrenWatcher;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class zkUtils {

    @Autowired
    ZooKeeper zkClient;

    /**
     * 判断指定节点是否存在
     */
    public Stat exists(String path,boolean needWatch){
        try {
            return zkClient.exists(path,needWatch);
        } catch (Exception e) {
            log.error("【指定节点是否存在异常】{}，{}",path,e);
            return null;
        }
    }

    /**
     * 判断指定节点是否存在，并设置监听事件
     */
    public Stat exists(String path,Watcher watcher){
        try {
            return zkClient.exists(path,watcher);
        } catch (Exception e) {
            log.error("【指定节点是否存在异常】{}，{}",path,e);
            return null;
        }
    }

    /**
     * 创建持久化节点
     */
    public boolean createNode(String path,String data){
        try {
            zkClient.create(path,data.getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            return true;
        } catch (Exception e) {
            log.error("【创建持久化节点异常】{}，{}，{}",path,data,e);
            return false;
        }
    }

    /**
     * 创建临时顺序节点
     */
    public boolean createEphemeralNode(String path,String data){
        try {
            zkClient.create(path,data.getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
            return true;
        } catch (Exception e) {
            log.error("【创建持久化节点异常】{}，{}，{}",path,data,e);
            return false;
        }
    }

    /**
     * 更新持久化节点
     */
    public boolean updateNode(String path,String data){
        try {
            zkClient.setData(path,data.getBytes(),-1);
            return true;
        } catch (Exception e) {
            log.error("【更新持久化节点异常】{}，{}，{}",path,data,e);
            return false;
        }
    }

    /**
     * 删除持久化节点
     */
    public boolean deleteNode(String path){
        try {
            zkClient.delete(path,-1);
            return true;
        } catch (Exception e) {
            log.error("【删除持久化节点异常】{}，{}，{}",path,e);
            return false;
        }
    }

    /**
     * 获取子节点
     */
    public List<String> getChildren(String path) throws Exception{
        return zkClient.getChildren(path,new ChildrenWatcher());
    }

    /**
     * 获取节点内容
     */
    public String getData(String path,Watcher watcher){
        try {
            byte[] bytes = zkClient.getData(path,watcher,new Stat());
            return new String(bytes);
        } catch (Exception e) {
            log.error("【获取节点内容异常】{}，{}，{}",path,e);
            return null;
        }
    }
}
