package core.java.test.memory;

/**
 * Created by chenyuanjun on 3/27/17.
 */
public class VolatileTest {

    private volatile int number = 0;
    public int getNumber() {
        return this.number;
    }
    public void increaseNumber() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ++this.number; // ++不是原子性
    }



    public static void main(String[] args) {

        VolatileTest test = new VolatileTest();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.increaseNumber();
                }
            }).start();
        }
        // wait for sub-threads finish, then main thread continue to run
        while(Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println("number value: " + test.getNumber());
    }

}
