package Zookeeper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockExample {
    public static String getData(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYYMMDDHHMMSS");
        return simpleDateFormat.format(new Date());
    }
    public static void main(String args[]){
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        ExecutorService executorService= Executors.newCachedThreadPool();//
        for(int i=0;i<5;i++){
            executorService.submit(new Runnable() {
                public void run() {
                    try {
                        countDownLatch.await();
                        ZkLock lock=new ZkLock("192.168.237.128:2181","lock");
                        lock.lock();
                        System.out.println(Thread.currentThread().getName()+getData());
                        lock.unlock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            });
        }
        countDownLatch.countDown();
        executorService.shutdown();

    }

}
