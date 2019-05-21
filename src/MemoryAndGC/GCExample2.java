package MemoryAndGC;

// Island of Isolation
public class GCExample2 {

    private GCExample2 i;
    public static void main(String[] args)
    {
        GCExample2 t1 = new GCExample2();
        GCExample2 t2 = new GCExample2();

        // Object of t1 gets a copy of t2
        t1.i = t2;

        // Object of t2 gets a copy of t1
        t2.i = t1;

        // Till now no object eligible
        // for garbage collection
        t1 = null;

        //now two objects are eligible for
        // garbage collection
        t2 = null;

        // calling garbage collector
        System.gc();

    }

    @Override
    protected void finalize() throws Throwable
    {
        System.out.println("Finalize method called");
    }
}
