package other;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by user on 05/11/16.
 */
public class MySafeRunnable implements Runnable{

    private final String name;
    private final CyclicBarrier cb;
    private static ThreadLocal<MyTestClass> tl;

    public MySafeRunnable(String s, CyclicBarrier c) {
        name = s;
        cb = c;
        tl = new ThreadLocal<MyTestClass>() {
            @Override
            protected MyTestClass initialValue() {
                return new MyTestClass();
            }
        };

    }

    public void run() {
        try {
            cb.await();
        } catch (Exception e) {}
        MyTestClass s = tl.get();
        String r = RandomStringUtils.randomAlphanumeric(1024);
        s.setS(r);
        //System.out.println(name + " set " + s.getS());
        try{
            Thread.sleep(100);
        } catch (Exception e) {}
        System.out.println(name + " after " + s.getS());
    }
}
