package com.github.framework.util.algorithm;


import com.github.framework.util.json.FastJsonUtil;

/**
 * @author longhairen
 * @create 2017-08-04 22:53
 * @description
 **/
public class SortAlgorithm {

    public static void main(String[] args) {
        int[] arr = {3,1,5,7,2,4,9,6,10,8};
        System.out.println(FastJsonUtil.toJsonString(arr));
//        insertSort(arr);
//        System.out.println("插入排序："+FastJsonUtil.toJsonString(arr));
//        shellInsertSort(arr);
//        System.out.println("希尔排序："+FastJsonUtil.toJsonString(arr));
//        bubbleSort(arr);
//        System.out.println("冒泡排序："+FastJsonUtil.toJsonString(arr));
        quitSort(arr, 0, arr.length - 1);
        System.out.println("快速排序："+FastJsonUtil.toJsonString(arr));

    }

    /**
     * 快速排序
     * 选择一个基准元素,通常选择第一个元素或者最后一个元素,
     2）通过一趟排序讲待排序的记录分割成独立的两部分，其中一部分记录的元素值均比基准元素值小。另一部分记录的 元素值比基准值大。
     * @param array
     * @param low
     * @param high
     */
    public static void quitSort(int[] array, int low, int high){
        if (low < high) {
            int mid = getMiddle(array, low, high);
            quitSort(array, 0, mid - 1);
            quitSort(array, mid + 1, high);
        }
    }

    public static int getMiddle(int[] arr, int low, int high){
        int key = arr[low];
        while (low < high) {
            while(low < high && key <= arr[high]){
                high--;
            }
            arr[low] = arr[high];
            while(low < high && key >= arr[low]){
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = key;
        return low;
    }

    /**
     * 冒泡排序
     * 在要排序的一组数中，对当前还未排好序的范围内的全部数，
     * 自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。
     * @param array
     */
    public static void bubbleSort(int[] array){
        for (int i = 0, len = array.length; i < len; i++){
            for (int j = 0; j < len - i - 1; j++){
                if (array[j+1] < array[j]){
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 希尔排序
     * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，
     * 待整个序列中的记录“基本有序”时，再对全体记录进行依次直接插入排序。
     * @param array
     */
    public static void shellInsertSort(int[] array){
        int dk = array.length / 2;
        while(dk >= 1){
            for (int i = dk, len = array.length; i < len; i++){
                int j, curr = array[i];
                for (j = i - dk; j >= 0 && curr < array[j]; j = j - dk){
                    array[j + dk] = array[j];
                }
                array[j+dk] = curr;
            }

            dk = dk / 2;
        }
    }

    /**
     * 插入排序
     * 先将序列的第1个记录看成是一个有序的子序列，然后从第2个记录逐个进行插入，直至整个序列有序为止。
     * @param array
     */
    public static void insertSort(int[] array){
        for (int i = 1, len = array.length; i < len; i++){
            int indx, curr = array[i];
            for (indx = i -1; indx >= 0 && curr < array[indx]; indx--){
                array[indx+1] = array[indx];
            }
            array[indx+1] = curr;
        }
    }
}
