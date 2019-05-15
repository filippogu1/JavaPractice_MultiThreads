package MultiThreadTutorials;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo10_LockTest2 {

    private Lock lock = new ReentrantLock();

    private void method(Thread thread){
        /*
            lock.lock();
            try {
                System.out.println("线程名"+thread.getName() + "获得了锁");
            }catch(Exception e){
                e.printStackTrace();
            } finally {
                System.out.println("线程名"+thread.getName() + "释放了锁");
                lock.unlock();
            }
        */

        if(lock.tryLock()){
            try {
                System.out.println("Thread "+thread.getName() + " acquired lock");
            }catch(Exception e){
                e.printStackTrace();
            } finally {
                System.out.println("Thread "+thread.getName() + " released lock");
                lock.unlock();
            }
        }else{
            System.out.println("I am "+Thread.currentThread().getName()+". Lock is other's so I give up!!");
        }
    }

    public static void main(String[] args) {
        Demo10_LockTest2 lockTest = new Demo10_LockTest2();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
