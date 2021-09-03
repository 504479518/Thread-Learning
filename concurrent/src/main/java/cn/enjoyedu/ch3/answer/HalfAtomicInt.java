package cn.enjoyedu.ch3.answer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类说明：有一个残缺A类实现了线程安全的：
 * get方法和compareAndSet()方法
 * 自行实现它的递增方法
 */
public class HalfAtomicInt {
    private AtomicInteger atomicI = new AtomicInteger(0);

    /**
     * 请完成这个递增方法
     */
    public void increament() {
        for (; ; ) {
            int i = getCount();
            boolean suc = compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    public int getCount() {
        return atomicI.get();
    }

    public boolean compareAndSet(int oldValue, int newValue) {
        return atomicI.compareAndSet(oldValue, newValue);
    }

    public static void main(String[] args) throws InterruptedException {
        HalfAtomicInt halfAtomicInt = new HalfAtomicInt();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        halfAtomicInt.increament();
                    }
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(halfAtomicInt.getCount());
    }
}

//import java.util.ArrayList;import java.util.Collection;import java.util.Collections;import java.util.List;import java.util.concurrent.CountDownLatch;import java.util.concurrent.locks.Lock;import java.util.concurrent.locks.ReentrantLock;
//public class FairAndUnfairTest {  private static CountDownLatch start;    private static class MyReentrantLock extends ReentrantLock {        public MyReentrantLock(boolean fair) {            super(fair);        }        public Collection<Thread> getQueuedThreads() {            List<Thread> arrayList = new ArrayList<Thread>(super.getQueuedThreads());            Collections.reverse(arrayList);            return arrayList;        }    }    private static class Worker extends Thread {        private Lock lock;        public Worker(Lock lock) {            this.lock = lock;        }        @Override        public void run() {            try {                start.await();            } catch (InterruptedException e) {              e.printStackTrace();            }            // 连续两次打印当前的Thread和等待队列中的Thread            for (int i = 0; i < 2; i++) {                lock.lock();                try {                    System.out.println("Lock by [" + getName() + "], Waiting by " + ((MyReentrantLock) lock).getQueuedThreads());                } finally {                    lock.unlock();                }            }        }        public String toString() {            return getName();        }    }    public static void main(String[] args) {    Lock fairLock = new MyReentrantLock(true);    //Lock unfairLock = new MyReentrantLock(false);    testLock(fairLock);        //testLock(unfairLock);  }    private static void testLock(Lock lock) {        start = new CountDownLatch(1);        for (int i = 0; i < 5; i++) {            Thread thread = new Worker(lock);            thread.setName("" + i);            thread.start();        }        start.countDown();    }}
