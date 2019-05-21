package MultiThreadTutorials;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Demo7_ProducerAndConsumer {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void producer() throws InterruptedException {
        Random rand = new Random();
        while (true) {
            int num = rand.nextInt(100);
            System.out.println("Putting value " + num + ". Queue size is " + queue.size());
            queue.put(num);
        }
    }

    // The take() method is used to retrieve and remove the head of this queue.
    // If queue is empty then it will wait until an element becomes available
    public static void consumer() throws InterruptedException {
        Random rand = new Random();
        while (true) {
            Thread.sleep(100);
            if (rand.nextInt(10) == 0) {
                Integer value = queue.take();
                System.out.println("Taking value " + value + ". Queue size is " + queue.size());
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable(){
           public void run() {
               try {
                   producer();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        Thread t2 = new Thread(new Runnable(){
            public void run() {
                try {
                    consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
    }

}
