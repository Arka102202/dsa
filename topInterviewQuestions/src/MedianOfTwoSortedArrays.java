public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len1 = nums1.length, len2 = nums2.length;

        if(nums1[len1-1] <= nums2[0]){
            return 0.0;
        }
        else if (nums1[0] >= nums2[len2-1]){
            return 1.0;
        } else{


        }


        return 0.0;

    }



    public static void search(int s1, int e1, int s2, int e2,int[] nums1, int[] nums2, int len1, int len2){

        int mid1 = (s1+e1)>>>1;

        if(nums1[s1] <= nums2[s2]){

            if(nums1[mid1] <= nums2[s1] &&
                    mid1 < len1 && nums1[mid1+1] >= nums2[e2]){

                System.out.println("something");
            }



        }










    }


}
