package class08;

import java.util.Stack;

/**
 * 快速排序
 **/
public class Code03_PartitionAndQuickSort {

    /**
     * 用数组最后一个值P为基准，小于等于P的值集中到左区域，大于P的值集中到右区域，而且P是“小于等于”区域的最后一个数
     * 要求O(N)
     * 32144)7687
     * 思路：
     * 当前数<=P，当前数和“小于等于”区域的下一个数交换，“小于等于”区域向右扩一个位置，当前数跳到下一个
     * 当前数>P，当前数跳到下一个
     **/
    public static void splitNum1(int[] arr) {
        //“小于等于”区域的起始边界
        int lessEqualR = -1;
        //当前下标
        int cur = 0;
        int N = arr.length;
        while (cur < N) {
            if (arr[cur] <= arr[N - 1]) {
//                //当前数和“小于等于”区域的下一个数交换
//                swap(arr, lessEqualR+1,cur);
//                //“小于等于”区域向右扩一个位置
//                lessEqualR++;
//                //当前数跳到下一个
//                cur++;

                //简化成下面一行
                swap(arr, ++lessEqualR, cur++);
            } else {
                cur++;
            }
        }
    }

    /**
     * 用数组最后一个值P为基准，小于P的值集中到左区域，等于P的数集中到中间区域，大于P的值集中到右区域
     * 2323)444(8679
     * 思路：
     * 右区域先固定在N-1的位置，把P隔离开
     * 当前数<P，当前数和“小于”区域的下一个数交换，“小于”区域向右扩一个位置，当前数跳到下一个
     * 当前数>P，当前数和“大于”区域的前一个数交换，“大于”区域往左扩一个位置，当前数不动
     * 当前数==P，当前数直接跳下一个
     * 当前数撞上“大于”区域的左边界，“大于”区域的第一个数和P交换
     **/
    public static void splitNum2(int[] arr) {
        int N = arr.length;
        //小于区域的起始边界
        int lessR = -1;
        //大于区域的边界在N-1
        int moreL = N - 1;
        int cur = 0;
        while (cur < moreL) {
            //当前数<P
            if (arr[cur] < arr[N - 1]) {
                swap(arr, ++lessR, cur++);
            }
            //当前数>P
            else if (arr[cur] > arr[N - 1]) {
                swap(arr, --moreL, cur);
            }
            //当前数=P
            else {
                cur++;
            }
        }
        //当前数来到“大于”区域的左边界
        swap(arr, moreL, N - 1);
    }

    /**
     * arr[L...R]范围上，拿arr[R]做划分值
     * L~R范围上，小于P的放左，等于的放中间，大于的放右
     * 返回等于区域的左边界和右边界两个数组成的数组 [eqLeft, eqRight]
     * 参考 splitNum2 方法
     */
    public static int[] partition(int[] arr, int L, int R) {
        //小于区域的右边界
        int lessR = L - 1;
        //大于区域的左边界
        int moreL = R;
        int cur = L;
        while (cur < moreL) {
            if (arr[cur] < arr[R]) {
                swap(arr, ++lessR, cur++);
            } else if (arr[cur] > arr[R]) {
                swap(arr, --moreL, cur);
            } else {
                cur++;
            }
        }
        swap(arr, moreL, R);
        //等于区域的左边界是lessR+1
        //右边界是moreL，因为最后一步划分至值和大于区域的第一个做交换了
        return new int[]{lessR + 1, moreL};
    }

    /**
     * 快速排序 递归
     **/
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        //等于区域的左右边界
        int[] equalE = partition(arr, L, R);
        //小于区域：L~等于区域左边界-1
        process(arr, L, equalE[0] - 1);
        //大于区域：等于区域右边界+1~R
        process(arr, equalE[1] + 1, R);
    }


    /**
     * 快速排序 非递归
     **/
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Stack<Job> stack = new Stack<>();
        stack.push(new Job(0, arr.length - 1));
        while (!stack.isEmpty()) {
            Job cur = stack.pop();
            int[] equals = partition(arr, cur.L, cur.R);
            if (equals[0] > cur.L) { // 有< 区域
                stack.push(new Job(cur.L, equals[0] - 1));
            }
            if (equals[1] < cur.R) { // 有 > 区域
                stack.push(new Job(equals[1] + 1, cur.R));
            }
        }
    }

    public static class Job {
        public int L;
        public int R;

        public Job(int left, int right) {
            L = left;
            R = right;
        }
    }

    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R); // <[R] =[R] >[R]
        return new int[]{less + 1, more};
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
//		int[] arr = { 7, 1, 3, 5, 4, 5, 1, 4, 2, 4, 2, 4 };
//
//		splitNum2(arr);
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i] + " ");
//		}

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr1, arr3)) {
                System.out.println("Oops!");
                succeed = false;
                break;
            }
        }
        System.out.println("test end");
        System.out.println(succeed ? "Nice!" : "Oops!");

    }

}
