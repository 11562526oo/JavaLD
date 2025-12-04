package com.lyj.dec.thread;

/**
 * @Author liuyanjun
 * @Date 2025/12/4 14:28
 **/
public class WaitNotifyExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);

        producer.start();
        consumer.start();
    }
}

class SharedResource {
    private String message;
    private boolean isAvailable = false;

    public synchronized void produce(String msg) {
        while (isAvailable) {
            try {
                wait(); // 等待直到有空间  线程会进入“等待（Waiting）”状态
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.message = msg;
        isAvailable = true;
        System.out.println("生产者生产: " + msg);
        notify(); // 唤醒消费者
    }

    public synchronized String consume() {
        while (!isAvailable) {
            try {
                wait(); // 等待直到有产品
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        isAvailable = false;
        System.out.println("消费者消费: " + message);
        notify(); // 唤醒生产者
        return message;
    }
}

class Producer extends Thread {
    private SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            resource.produce("产品 " + i);
        }
    }
}

class Consumer extends Thread {
    private SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            resource.consume();
        }
    }
}


