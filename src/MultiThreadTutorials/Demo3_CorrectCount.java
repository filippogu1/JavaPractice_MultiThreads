package MultiThreadTutorials;

// This will provide the correct count value of 2000
public class Demo3_CorrectCount {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public void doWork() {
        Thread t1 = new Thread(new Runnable(){
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    increment();
                }
            }
        });
        Thread t2 = new Thread(new Runnable(){
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    increment();
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
        System.out.println("Count is " + count);
    }

    public static void main(String[] args) {
        Demo3_CorrectCount d3 = new Demo3_CorrectCount();
        d3.doWork();
    }
}
