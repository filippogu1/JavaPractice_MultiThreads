package MultiThreadTutorials;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Demo4_MultipleLocks {

    private Random rand = new Random();

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void stageOne() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(rand.nextInt(100));
        }
    }

    public void stageTwo() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(rand.nextInt(100));
        }
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting......");
        Demo4_MultipleLocks d4 = new Demo4_MultipleLocks();
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(new Runnable(){
           public void run() {
               d4.process();
           }
        });
        Thread t2 = new Thread(new Runnable(){
            public void run() {
                d4.process();
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time taken is " + (end - start));
        System.out.println("list1 size is " + d4.list1.size());
        System.out.println("list1 size is " + d4.list2.size());
    }
}
