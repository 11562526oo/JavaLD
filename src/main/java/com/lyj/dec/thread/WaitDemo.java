package com.lyj.dec.thread;

/**
 * 查看线程状态，wait / wait(time)  notify()
 * @Author liuyanjun
 * @Date 2025/12/4 14:48
 **/
public class WaitDemo {
    private static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("t1 start");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 end");
            }
        },"T");
//        Thread.currentThread().isDaemon()  是否是守护线程  用于回收，检测
        t1.start();
        Thread.sleep(100); // TIMED_WAITING
        System.out.println("Main: T state (outside) = " + t1.getState());

        synchronized (lock) {
            lock.notify();
        }
        System.out.println("Main: T final state = " + t1.getState());
        System.out.println("Main: 0 final state = " + Thread.currentThread().getState());

        // 等待T结束
        t1.join();
        System.out.println("Main: 0 final state = " + Thread.currentThread().getState());
        System.out.println("Main: T final state = " + t1.getState());
    }
}

// 新建（New）
//线程被创建但尚未开始执行。
//状态表示为 Thread.State.NEW。

//2. 可运行（Runnable）
//线程可以运行或正在运行。此状态包括正在等待 CPU 时间片的线程。
//状态表示为 Thread.State.RUNNABLE。

//3. 阻塞（Blocked）
//线程在等待获取一个锁（monitor），即被阻塞。
//状态表示为 Thread.State.BLOCKED。

//4. 等待（Waiting）
//线程在等待另一个线程执行特定操作（如调用 Object.wait()、Thread.join() 或 LockSupport.park()）。
//状态表示为 Thread.State.WAITING。

//5. 超时等待（Timed Waiting）
//线程在等待另一个线程执行特定操作，但会在指定时间后自动返回（如调用 Thread.sleep(millis)、Object.wait(millis) 或 Thread.join(millis)）。
//状态表示为 Thread.State.TIMED_WAITING。

//6. 终止（Terminated）
//线程已完成执行，正常退出或由于异常终止。
//状态表示为 Thread.State.TERMINATED。
