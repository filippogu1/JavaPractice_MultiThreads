package Concurrency;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/*
    Concurrency: http://tutorials.jenkov.com/java-concurrency/non-blocking-algorithms.html

    ConcurrentHashMap vs HashMap
        1. ConcurrentHashMap and HashMap both are based on hash table.
        2. ConcurrentHashMap supports full concurrency of retrieval. HashMap can be synchronized using Collections.synchronizedMap() .
        3. ConcurrentHashMap provides concurrency level for updates that can be changed while instantiating.
        4. In concurrent modification HashMap throws ConcurrentModificationException whereas ConcurrentHashMap does not.

    "ConcurrentHashMap is a thread-safe object.
    No matter how many threads access it at the same time, it will keep the promises (1), (2), and (3) that I listed above.
    But if two of your program's threads each try put a different value into the map for the same key at the same time,
    that's a data race. When some other thread later looks up that key,
    the value that it gets will depend on which thread won the race."
*/
public class ConcurrentHashMapDemo {

    private final ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

    class WriteThreasOne implements Runnable {
        @Override
        public void run() {
            for(int i= 1; i<=10; i++) {
                System.out.println("Writing map entry - " + i + " : " + "A"+ i);
                map.putIfAbsent(i, "A"+ i);
            }
        }
    }
    class WriteThreasTwo implements Runnable {
        @Override
        public void run() {
            for(int i= 1; i<=5; i++) {
                System.out.println("Writing map entry - " + i + " : " + "B"+ i);
                map.put(i, "B"+ i);
            }
        }
    }
    class ReadThread implements Runnable {
        @Override
        public void run() {
            Iterator<Integer> ite = map.keySet().iterator();
            while(ite.hasNext()){
                Integer key = ite.next();
                System.out.println("Reading map entry - " + key + " : " + map.get(key));
            }
        }
    }

    public static void main(String[] args) {
        ConcurrentHashMapDemo c = new ConcurrentHashMapDemo();
        Thread t1 = new Thread(c.new WriteThreasOne());
        Thread t2 = new Thread(c.new WriteThreasTwo());
        Thread t3 = new Thread(c.new ReadThread());

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
