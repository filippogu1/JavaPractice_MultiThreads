package MultiThreadTutorials;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Starting: " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed: " + id);
    }
}

public class Demo5_ThreadPool {

    public static void main(String[] args) {
        Demo5_ThreadPool d5 = new Demo5_ThreadPool();
        ExecutorService es = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            es.submit(new Processor(i));
        }

        es.shutdown();

        try {
            es.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks submitted!!");
    }
}
