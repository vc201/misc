package other;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by user on 05/11/16.
 */
public class MyUnsafeRunnable implements Runnable{

    private final String name;
    private final MyTestClass tc;
    private final CyclicBarrier cb;

    public MyUnsafeRunnable(String s, CyclicBarrier c) {
        name = s;
        cb = c;
        tc = new MyTestClass();
    }

    public void run() {
        try {
            cb.await();
        } catch (Exception e) {}
        String r = RandomStringUtils.randomAlphanumeric(1024);
        tc.setS(r);
        //System.out.println(name + " set " + tc.getS());
        try{
            Thread.sleep(100);
        } catch (Exception e) {}
        System.out.println(name + " after " + tc.getS());
    }
}
