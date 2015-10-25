package array;

/**
 * 可以逆序并打印任意类型的数组的函数； 这时候需要定义引用数据类型过的数据； 
 * 这里的数据类型需要时刻注意，不要写基本数据类型；
 */
public class ArrayUtil {

    public static void main(String[] args) {
        Integer[] arr1 = { 1, 4, 7, 22, 7, 99, 22, 77 };
        Character[] arr2 = { '1', 'e', '4', '8' };
        String[] arr3 = { "iii", "9999", "7iydsfu77w63r87" };
        // 可以交换任意类型的数组的指定位置的元素的函数；
        printArr(arr1);
        reverseAny(arr1);
        printArr(arr1);
        
        printArr(arr2);
        reverseAny(arr2);
        printArr(arr2);
        
        printArr(arr3);
        reverseAny(arr3);
        printArr(arr3);
    }

    /**
     * 打印任意数组快捷类
     */
    public static <T> void printArr(T[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + ",");
            }
        }
        System.out.println("]");
    }

    /**
     * 反转任意数组快捷类
     */
    public static <T> void reverseAny(T[] arr) {
        T temp = null;
        for (int i = 0, j = arr.length - 1; j >= i; i++, j--) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
