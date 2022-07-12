package org.example.synchronization;

public class One2HundredDemoV3 {
    public volatile int count = 0;
    public Object obj = new Object();

    class Printer implements Runnable{
        public int threadId;

        public Printer(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (count < 100) {
                // 所以其实问题的关键在于：在竞争的时候，让线程自己有能力判别是否轮到自己执行【在go中一般是让别人来通知自己】
                // 这里的逻辑是让他们随机竞争，如果竞争到了但没轮到他，就什么也不执行，否则执行然后参与下一轮竞争
                synchronized (obj) {
                    if (count % 10 == threadId) {
                        System.out.printf("thread %d: %d", threadId, count++);
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        One2HundredDemoV2 one2HundredDemoV2 = new One2HundredDemoV2();
        Thread[] ts = new Thread[10];
        for (int i = 0; i < 10; i++) {
            ts[i] = new Thread(one2HundredDemoV2.new Printer(i));
            ts[i].start();
        }
        for (int i = 0; i < 10; i++) {
            ts[i].join();
        }
    }
}
