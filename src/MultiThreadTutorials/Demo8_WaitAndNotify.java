package MultiThreadTutorials;

import java.util.Scanner;

public class Demo8_WaitAndNotify {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread running...");
            wait();
            System.out.println("Resumed!");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this) {
            System.out.println("Waiting for return key...");
            scanner.nextLine();
            System.out.println("Return key pressed!");
            notify();
            Thread.sleep(5000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Demo8_WaitAndNotify d8 = new Demo8_WaitAndNotify();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    d8.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    d8.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
