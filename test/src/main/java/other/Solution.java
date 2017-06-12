package other;

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1Start = 0;
        int n2Start = 0;
        int n1End = nums1.length - 1;
        int n2End = nums2.length - 1;

        int nthSmallest = (n1End + n2End) / 2;

//        if ((n1End + n2End) % 2 == 0) {
//            nthSmallest++;
//        }

        int counter = 0;

        while (true && counter < 10) {
            System.out.println("nth smallest is " + nthSmallest);

            int[] p1 = pivot(nums1, n1Start, n1End);
            int[] p2 = pivot(nums2, n2Start, n2End);

            if (p1[1] <= p2[1]) {
                int elems = (p1[0] - n1Start + p2[0] - n2Start + 1);
                System.out.println("num elems is " + elems);
                // we've got enough elems in both left side of pivots
                if (nthSmallest < elems) {
                    n1End = p1[0];
                    n2End = p2[0];

                    System.out.println("Enough elems in left side");
                }
                // use other side
                else {
                    n1Start = p1[0];
                    n2Start = p2[0];
                    nthSmallest -= elems;

                    System.out.println("Enough elems in right other side");
                }
            } else { // do opposite
                int elems = (n1End - p1[0] + n2End - p2[0]);
                System.out.println("num elems is " + elems);
                // we've got enough elems in both right side of pivots
                if (nthSmallest >= elems) {
                    n1Start = p1[0];
                    n2Start = p2[0];

                    System.out.println("Enough elems in right side");
                }
                // use other side
                else {
                    n1End = p1[0];
                    n2End = p2[0];
                    nthSmallest -= elems;

                    System.out.println("Enough elems in left other side");
                }
            }

            System.out.println("new nums1 " + n1Start + " " + n1End);
            System.out.println("new nums2 " + n2Start + " " + n2End);

            if ((n1End - n1Start + n2End - n2Start) <= 2 ) {
                System.out.println(nums1[n1Start] + " " + nums1[n1End]);
                System.out.println(nums2[n2Start] + " " + nums2[n2End]);
                System.out.println("nth smallest is " + nthSmallest);
                break;
            }

            counter++;
        }

        return 0;
    }

    // index, val
    public int[] pivot(int[] arr, int start, int end) {
        int index = (end - start) / 2 + start;
        return new int[]{index, arr[index]};
    }
}