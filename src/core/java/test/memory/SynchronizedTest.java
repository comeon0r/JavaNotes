package core.java.test.memory;

/**
 * Created by chenyuanjun on 3/27/17.
 */
public class SynchronizedTest {

    private boolean ready = false;
    private int result = 0;
    private int number = 1;

    public void write() {
        ready = true;
        number = 2;
    }
    public void read() {
        if(ready) {
            result = number * 3;
        }
        System.out.println("result value: " + result);
    }

    private class ReadWriteThread extends Thread {

        private boolean flag;

        public ReadWriteThread(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            if(flag) {
                write();
            }
            else {
                read();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            SynchronizedTest test = new SynchronizedTest();
            test.new ReadWriteThread(true).start();
            test.new ReadWriteThread(false).start();
        }
    }

}
