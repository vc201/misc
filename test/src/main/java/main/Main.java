package main;

import other.ListNode;
import other.MySafeRunnable;
import other.MyUnsafeRunnable;
import other.Solution;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by user on 05/11/16.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        int[] a = {1, 3};
        int[] b = {2, 3};

        System.out.println(new Solution().findMedianSortedArrays(a, b));
    }

}
