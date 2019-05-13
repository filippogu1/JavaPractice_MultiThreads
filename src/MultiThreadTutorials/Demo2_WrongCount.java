package MultiThreadTutorials;

// t1 and t2 will give unstable count value, because they did not sync the variable
public class Demo2_WrongCount {
    private int count = 0;

    public void doWork() {
        Thread t1 = new Thread(new Runnable(){
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    count++;
                }
            }
        });
        Thread t2 = new Thread(new Runnable(){
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    count++;
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
        Demo2_WrongCount d2 = new Demo2_WrongCount();
        d2.doWork();
    }
}
