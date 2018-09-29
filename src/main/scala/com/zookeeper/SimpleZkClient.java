package com.zookeeper;


import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

public class SimpleZkClient {


    public static void main(String[] args) throws Exception{
        ZooKeeper zk = new ZooKeeper("10.1.18.218:2181", 3000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {

            }
        });
        System.out.println("=========创建节点===========");
        if (zk.exists("/test2", false) == null) {
            zk.create("/test2", "znode1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT );
        }

        System.out.println("========= 查看节点是否安装成功 ==========");
        System.out.println(new String(zk.getData("/test2",false, null)));
    }


}

