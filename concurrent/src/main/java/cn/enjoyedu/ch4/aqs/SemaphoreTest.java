package cn.enjoyedu.ch4.aqs;

import java.util.concurrent.*;

/**
 * @className: SemaphoreTest
 * @description: TODO 类描述
 * @author 情似皓月
 * @date: 2020/12/26
 **/
public class SemaphoreTest {
    private static ExecutorService executor = new ThreadPoolExecutor(10, 10,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue(10));

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(10);
        for (int i = 0; i < 40; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("处理数据中......");
                    Thread.sleep(3000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        executor.shutdown();
    }
}
