package com.lyj.dec.thread;

/**
 *
 * @Author liuyanjun
 * @Date 2025/12/4 15:31
 **/
public class Interrupted {

}
//某些阻塞方法在检测到中断时会抛出 InterruptedException，例如：
//Thread.sleep(...)
//Object.wait(...)
//Thread.join(...)
//阻塞队列的 put/take（java.util.concurrent）
//Selector.select（NIO，取决于实现）