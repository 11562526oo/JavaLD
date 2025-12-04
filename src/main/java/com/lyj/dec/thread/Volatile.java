package com.lyj.dec.thread;

/**
 * 保证变量的可见性与有序性（禁止特定指令重排），但不保证复合操作的原子性。
 * 适用场景：一个线程写，多个线程读；状态标志、发布对象引用等。
 *
 * 可见性：线程 A 对变量的写入，线程 B 能“立刻”看到（通过内存屏障实现）。
 * 有序性：volatile 写之前的操作不会被重排到写之后；读取之后的操作不会被重排到读取之前。
 *          sychonize 不能禁止重排序 ，组合使用
 *
 * join原理 wait JVM内部notifyAll
 * @Author liuyanjun
 * @Date 2025/12/4 15:48
 **/
public class Volatile {
    public static void main(String[] args) {
    }
}
