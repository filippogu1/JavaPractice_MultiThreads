package MemoryAndGC;

public class GCExample1 {

    // These two gc calls are effectively the same
    public static void main(String[] args) throws InterruptedException
    {
        GCExample1 t1 = new GCExample1();
        GCExample1 t2 = new GCExample1();

        // Nullifying the reference variable
        t1 = null;

        // requesting JVM for running Garbage Collector
        System.gc();

        // Nullifying the reference variable
        t2 = null;

        // requesting JVM for running Garbage Collector
        Runtime.getRuntime().gc();

    }


    // NOTE: Just before destroying an object, Garbage Collector calls finalize() method
    // on the object to perform cleanup activities.
    // Once finalize() method completes, Garbage Collector destroys that object

    // finalize method is called on object once
    // before garbage collecting it
    @Override
    protected void finalize() throws Throwable
    {
        System.out.println("Garbage collector called");
        System.out.println("Object garbage collected : " + this);
    }
}
