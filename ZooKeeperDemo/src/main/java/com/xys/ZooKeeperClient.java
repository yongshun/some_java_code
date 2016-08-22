package com.xys;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @author xiongyongshun
 * @version 1.0
 * @created 16/8/22 18:03
 * <p>
 * 基本了 ZK 使用例子, 展示了如何连接到一个指定的 ZK 服务器上.
 */
public class ZooKeeperClient implements Watcher {
    private static Logger logger = LoggerFactory.getLogger("ZooKeeperClient");
    private static CountDownLatch countDownLatch = new CountDownLatch(4);
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception {
        // 在初始化 ZK 客户端时, 传递了一个 Watcher 接口的实例, 即 ZooKeeperClient,
        // ZK 通过 Watcher 的 process 回调方法来通知客户端 ZK 的状态改变.
        zooKeeper =
                new ZooKeeper("127.0.0.1:2181", 5000,
                        new ZooKeeperClient());

        logger.info(zooKeeper.getState().toString());

        nodeOperationTest();

        countDownLatch.await();
    }

    private static void nodeOperationTest() throws Exception {

        String path = "/zk_test";

        // 判断节点是否存在
        Stat stat = zooKeeper.exists(path, false);
        if (stat == null) {
            logger.info("Node {} }does not exist", path);
        } else {
            // 删除指定的节点.
            // 第二个参数是节点的版本, 当它的值不为-1, 且和需要删除节点的版本相同时, 此操作才成功.
            // 如果它的值为 -1, 则表示忽略节点版本号.
            zooKeeper.delete(path, -1);
        }

        // 创建临时节点
        // CreateMode 设置为 EPHEMERAL, 表示创建临时节点.
        String resultPath = zooKeeper.create(path, "aaa".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        logger.info("Path: " + resultPath);

        // 创建临时顺序节点.
        // CreateMode 设置为 EPHEMERAL_SEQUENTIAL, 表示创建临时顺序节点.
        resultPath = zooKeeper.create(path, "aaa".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        logger.info("Path: " + resultPath);


        // 删除节点, 然后使用异步方式创建节点
        zooKeeper.delete(path, -1);

        zooKeeper.create(path, "aaa".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new IStringCallback(), "第一个节点, 临时节点");

        zooKeeper.create(path, "aaa".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, new IStringCallback(), "第二个节点, 临时顺序节点");

        countDownLatch.countDown();
    }

    public void process(WatchedEvent watchedEvent) {
        logger.info("Receive watched event: " + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }

    private static class IStringCallback implements AsyncCallback.StringCallback {
        public void processResult(int rc, String path, Object ctx, String name) {
            logger.info("Create path result: [ {}, {}, {}, real path name: {} ]", rc, path, ctx, name);

            countDownLatch.countDown();
        }
    }
}