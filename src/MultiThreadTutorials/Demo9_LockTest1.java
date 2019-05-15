package MultiThreadTutorials;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo9_LockTest1 {

    private Lock lock;

    public Demo9_LockTest1() {
        lock = new ReentrantLock();
    }

    private void method(Thread thread){
        lock.lock();
        try {
            System.out.println("Thread "+thread.getName() + " acquired lock");
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            System.out.println("Thread "+thread.getName() + " released lock");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Demo9_LockTest1 lockTest = new Demo9_LockTest1();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockTest.method(Thread.currentThread());
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockTest.method(Thread.currentThread());
            }
        }, "t2");

        t1.start();
        t2.start();
    }

}
