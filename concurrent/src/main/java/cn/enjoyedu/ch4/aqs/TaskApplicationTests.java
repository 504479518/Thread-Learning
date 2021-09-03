package cn.enjoyedu.ch4.aqs;

import java.util.concurrent.*;


class TaskApplicationTests {

    private static ExecutorService executor = new ThreadPoolExecutor(10, 10,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue(10));

    //目标:炒菜
    //1.洗菜 5秒
    //2.买盐 3秒
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        long now = System.currentTimeMillis();
        //洗菜5秒
        executor.execute(() -> {
            try {
                Thread.sleep(5000);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
        });
        //买盐3秒
        executor.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();
        System.out.println("可以炒菜了" + (System.currentTimeMillis() - now));
    }
}
