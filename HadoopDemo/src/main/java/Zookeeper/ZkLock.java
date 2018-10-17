package Zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZkLock implements Watcher {
    private ZooKeeper zk ;
    private String path;
    private String currentNode;
    private String waitNode;
    private CountDownLatch countDownLatch;
    // 根节点
    private String ROOT = "/bit";
    public ZkLock(String host,String path){
        this.path=path;
        try {
            zk = new ZooKeeper(host,50000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Stat stat = zk.exists(ROOT, false);
            if (stat == null) {
                // 如果根节点不存在，则创建根节点
                zk.create(ROOT, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void lock(){
        try {
            currentNode=zk.create(ROOT + "/" + path, new byte[0],
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            List<String> lockObjNode=zk.getChildren(ROOT,false);
            Collections.sort(lockObjNode);
            if (currentNode.equals(ROOT + "/" + lockObjNode.get(0))) {
                return ;
            }else{
                String childZnode = currentNode.substring(currentNode.lastIndexOf("/") + 1);
                int num=Collections.binarySearch(lockObjNode,childZnode);
                if(num==0){
                    num=1;
                }
                waitNode=lockObjNode.get(num-1);
                Stat stat = zk.exists(ROOT + "/" + waitNode, true);
                if (stat != null) {
                    countDownLatch = new CountDownLatch(1);
                   this.countDownLatch.await();
                }
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void unlock(){
        try {
            zk.delete(currentNode, -1);
            currentNode = null;
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }
    public void process(WatchedEvent watchedEvent) {
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }

    }
}
