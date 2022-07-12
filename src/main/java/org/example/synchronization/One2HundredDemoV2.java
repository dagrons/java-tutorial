package org.example.synchronization;

public class One2HundredDemoV2 {

    public volatile Integer count = 0;
    private Object obj = new Object();

    /*
    inner class的好处：所谓的inner class，其实是提供了一个嵌套的上下文，inner class可以直接访问outer class中的属性，并且多个inner
    class的instance共享同一个outer class instance的属性，
    然后我们引入的anonymous class也是一种闭包的手段，在anonymous class内部可以直接wrap当前上下文的变量
    毕竟在java中, everything is an object
     */
    public class Printer implements Runnable {
        private int threadId;

        public Printer(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (count < 100) {
                synchronized (obj) {
                    if (count % 10 == threadId) { // 轮到自己，执行完后，通知其他线程参与竞争
                        System.out.printf("thread %d: %d\n", threadId, count++);
                        obj.notifyAll(); // 这里notifyAll并不是让出控制权，而是唤醒在wait的线程，让他们参与竞争，但并没有指定谁来拿锁，包括自己也会参与竞争
                    } else {
                        try { // 没轮到自己，主动放弃，然后等别人执行完后在参与竞争
                            obj.wait(); // 放弃锁，然后陷入沉睡，当被其他线程唤醒时，需要重新参与锁的竞争才能进步
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        One2HundredDemoV2 one2HundredDemoV2 = new One2HundredDemoV2();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            Printer p = one2HundredDemoV2.new Printer(i);
            threads[i] = new Thread(p);
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
    }
}
