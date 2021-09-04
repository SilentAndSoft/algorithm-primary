package class08;

/**
 * 归并排序
 **/
public class Code02_MergeSort {

    /**
     * 递归方法实现
     */
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // arr[L...R]范围上，请让这个范围上的数，有序！
    public static void process(int[] arr, int L, int R) {
        //只有一个数，返回
        if (L == R) {
            return;
        }
        // int mid = (L + R) / 2
        //这种写法能防止溢出
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    /**
     * 数组左右两部分merge
     **/
    public static void merge(int[] arr, int L, int M, int R) {
        //拷贝L~R范围排序的数
        int[] help = new int[R - L + 1];
        int i = 0;
        //两个指针分别从L和M+1出发
        int p1 = L;
        int p2 = M + 1;
        //保证左右两部分不越界，小的数拷贝进辅助数组
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //到这里：要么p1越界，要么p2越界，不可能同时越界
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        //到这里就已经排好了，将辅助数组原样拷贝进目标数组
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }


    /**
     * 非递归方法实现
     * O(N*logN)
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //规定步长：1 2 4 8 16....
        int step = 1;
        int N = arr.length;
        //步长超过总长度，终止循环
        //步长等于总长度左组的时候就是全部的元素，右组没了，所以不需要==N
        while (step < N) {
            //左组起始位置
            int L = 0;
            while (L < N) {
                //M的位置是左组最后一个
                int M = 0;
                //N到数组末尾一共有N-1-L+1个数，>=step说明能凑出步长，也就能找到M的位置L+step-1
                if (N - L >= step) {
                    M = L + step - 1;
                }
                //最后步长凑不够，就剩一个左组了，M就是数组末尾
                else {
                    M = N - 1;
                }
                //M到最后，说明后面没了
                if (M == N - 1) {
                    break;
                }
                //右组起始位置为M+1
                int R = 0;
                //R到数组末尾一共有(N-1)-(M+1)+1个数，>=step，说明能凑出右组，R=M+1+step-1
                if (N - 1 - M >= step) {
                    R = M + step;
                }
                //凑不出右组
                else {
                    R = N - 1;
                }
                //到这里，L~M就是左组，M+1~R就是右组
                merge(arr, L, M, R);
                /*调整下一个左组*/
                //右组来到数组最后
                if (R == N - 1) {
                    break;
                }
                //否则左组来到右组后面的位置
                else {
                    L = R + 1;
                }
            }
            /*调整步长*/
            //防止step溢出，比如N逼近2^31-1
            if (step > N / 2) {
                break;
            }
            //步长每次乘2
            step *= 2;
        }

    }

    // 非递归方法实现（精简）
//	public static void mergeSort2(int[] arr) {
//		if (arr == null || arr.length < 2) {
//			return;
//		}
//		int N = arr.length;
//		int mergeSize = 1;
//		while (mergeSize < N) {
//			int L = 0;
//			while (L < N) {
//				if (mergeSize >= N - L) {
//					break;
//				}
//				int M = L + mergeSize - 1;
//				int R = M + Math.min(mergeSize, N - M - 1);
//				merge(arr, L, M, R);
//				L = R + 1;
//			}
//			if (mergeSize > N / 2) {
//				break;
//			}
//			mergeSize <<= 1;
//		}
//	}

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
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
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
