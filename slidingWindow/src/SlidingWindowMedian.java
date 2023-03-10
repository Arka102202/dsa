import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SlidingWindowMedian {

    public static double[] medianSlidingWindow0(int[] nums, int k) {

        LinkedList<Integer> ls = new LinkedList<>(),
                rs = new LinkedList<>(),
                stack = new LinkedList<>();
        double[] r = new double[(nums.length - k) + 1];
        boolean flag;
        int j = 0, l = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (j % 2 == 0) {
                if (ls.size() == 0) {
                    ls.add(num);
                    flag = true;
                } else {
                    if (rs.size() > 0 && num <= rs.getFirst()) {
                        if (num <= ls.getLast()) ls.addLast(num);
                        else {
                            while (num < ls.getFirst()) stack.addFirst(ls.removeFirst());
                            ls.addFirst(num);
                            while (stack.size() > 0) ls.addFirst(stack.removeFirst());
                        }
                        flag = true;
                    } else {
                        if (rs.size() > 0 && num >= rs.getLast()) rs.addLast(num);
                        else {
                            while (rs.size() > 0 && num > rs.getFirst()) stack.addFirst(rs.removeFirst());
                            rs.addFirst(num);
                            while (stack.size() > 0) rs.addFirst(stack.removeFirst());
                        }
                        ls.addFirst(rs.removeFirst());
                        flag = false;
                    }
                }
            } else {
                if (rs.size() == 0) {
                    if (ls.size() == 1) {
                        if (num < ls.getFirst()) {
                            rs.addFirst(ls.removeFirst());
                            ls.addFirst(num);
                            flag = true;
                        } else {
                            rs.add(num);
                            flag = false;
                        }
                    } else {
                        rs.add(num);
                        flag = false;
                    }
                } else {
                    if (num >= ls.getFirst()) {
                        if (num >= rs.getLast()) rs.addLast(num);
                        else {
                            while (num > rs.getFirst()) stack.addFirst(rs.removeFirst());
                            rs.addFirst(num);
                            while (stack.size() > 0) rs.addFirst(stack.removeFirst());
                        }
                        flag = false;
                    } else {
                        if (num <= ls.getLast()) ls.addLast(num);
                        else {
                            while (num < ls.getFirst()) stack.addFirst(ls.removeFirst());
                            ls.addFirst(num);
                            while (stack.size() > 0) ls.addFirst(stack.removeFirst());
                        }
                        rs.addFirst(ls.removeFirst());
                        flag = true;
                    }
                }
            }
            j++;
            if (j == k) {
                if (k % 2 == 0) {
                    long val = (long) rs.getFirst() + (long) ls.getFirst();
                    r[l] = val / 2.0;
                } else r[l] = ls.getFirst();
                num = nums[l];
                if (flag && !ls.remove(Integer.valueOf(num))) rs.remove(Integer.valueOf(num));
                else if (!flag && !rs.remove(Integer.valueOf(num))) ls.remove(Integer.valueOf(num));

                if ((ls.size() - rs.size()) < 0) ls.addFirst(rs.removeFirst());
                else if ((ls.size() - rs.size()) > 1) rs.addFirst(ls.removeFirst());

                l++;
                j--;


            }


        }
        return r;
    }

    public static double[] medianSlidingWindow1(int[] nums, int k) {

        int[] arr = new int[k];
        int j = 0, l = 0;
        double[] r = new double[nums.length - k + 1];

        for (int num : nums) {
            arr[j] = num;
            j++;
            if (j == k) {
                Arrays.sort(arr);
                if (k % 2 == 0) {
                    int p1 = k / 2, p2 = p1 - 1;
                    long val = (long) arr[p1] + (long) arr[p2];
                    r[l] = val / 2.0;
                } else r[l] = arr[k / 2];

                int pos = Arrays.binarySearch(arr, nums[l]);
                arr[pos] = arr[k - 1];
                j--;
                l++;
            }
        }
        return r;

    }
    public static double[] medianSlidingWindow(int[] nums, int k) {

        PriorityQueue<Integer> lh = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> rh = new PriorityQueue<>();
        double[] r = new double[nums.length - k + 1];
        int j = 0, l = 0;

        for (int num : nums) {
            if (j % 2 == 0) {
                if(lh.size() == 0) lh.add(num);
                else {
                    if(rh.size() > 0 && num <= rh.peek()) lh.add(num);
                    else {
                        rh.add(num);
                        lh.add(rh.poll());
                    }
                }
            }else {
                if (rh.size() == 0) {
                    if (lh.size() == 1 && num < lh.peek()) {
                        rh.add(lh.poll());
                        lh.add(num);
                    }
                    else rh.add(num);
                }
                else {
                    if (lh.size() > 0 && num >= lh.peek()) rh.add(num);
                    else {
                        lh.add(num);
                        rh.add(lh.poll());
                    }
                }
            }
            j++;

            if (j == k) {
                if (k%2 == 0) {
                    long val = (long) rh.peek()+(long)lh.peek();
                    r[l] = val/2.0;
                }else r[l] = lh.peek();

                if(lh.contains(nums[l])) lh.remove(nums[l]);
                else rh.remove(nums[l]);

                if ((lh.size() - rh.size()) < 0) lh.add(rh.poll());
                else if ((lh.size() - rh.size()) > 1) rh.add(lh.poll());

                j--;
                l++;
            }

        }

        return r;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{7, 0, 3, 9, 9, 9, 1, 7, 2, 3}, 6)));

        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{5, 2, 2, 7, 3, 7, 9, 0, 2, 3}, 9)));

        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{2147483647, 2147483647}, 2)));

        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{1, 2, 3, 4, 2, 3, 1, 4, 2}, 3)));

        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }


}
