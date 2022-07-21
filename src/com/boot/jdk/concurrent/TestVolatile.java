package com.boot.jdk.concurrent;

/**
 * Volatile 可见性案例测试
 * @Author v_haimiyang
 * @Date 2022/7/21 15:28
 * @Version 1.0
 */
public class TestVolatile {
    private boolean flag = true;
    private int count = 0;
    public void refresh() {
        flag = false;
        System.out.println(Thread.currentThread().getName() + "修改flag:" + flag);
    }
    public void load() {
        System.out.println(Thread.currentThread().getName() + "开始执行...");
        while (flag) {
            count++;
        }
        System.out.println(Thread.currentThread().getName() + "跳出循环count:" + count);
    }
    public static void main(String[] args) throws InterruptedException {
        TestVolatile test = new TestVolatile();
        Thread thread = new Thread(()-> test.load(), "thradA");
        thread.start();
        Thread.sleep(1000);
        Thread threadB = new Thread(()-> test.refresh(), "thradB");
        threadB.start();
    }
}
