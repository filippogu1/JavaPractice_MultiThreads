package MemoryAndGC;

public class JVMMemoryExample1 {

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    public static void main(String arg[])
    {
        Runtime gfg = Runtime.getRuntime();
        long memory1, memory2;
        Integer[] arr = new Integer[1000];

        // checking the total memory
        System.out.println("Total memory is: " + humanReadableByteCount(gfg.totalMemory(), true));

        // checking free memory
        memory1 = gfg.freeMemory();
        System.out.println("Initial free memory: " + humanReadableByteCount(memory1, true));

        // calling the garbage collector on demand
        gfg.gc();
        memory1 = gfg.freeMemory();
        System.out.println("Free memory after garbage collection: " + humanReadableByteCount(memory1, true));

        // allocating integers
        for (int i = 0; i < 1000; i++) {
            arr[i] = i;
        }

        memory2 = gfg.freeMemory();
        System.out.println("Free memory after allocation: " + humanReadableByteCount(memory2, true));
        System.out.println("Memory used by allocation: " + humanReadableByteCount(memory1 - memory2, true));

        // discard integers
        for (int i = 0; i < 1000; i++) {
            arr[i] = null;
        }

        gfg.gc();
        memory2 = gfg.freeMemory();
        System.out.println("Free memory after collecting discarded Integers: " + humanReadableByteCount(memory2, true));
    }
}
