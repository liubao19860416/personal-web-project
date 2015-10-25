package work_day06_xml.test;

import java.util.ArrayList;

//代码测试程序；
import org.junit.Assert;
import org.junit.Test;

public class test02 {

	// @Test
	public void test() {
		System.out.println("");

		int a = 10;
		// System.out.println(a);

		ArrayList list = new ArrayList<>();
		list.add(100);
		list.add("str");
		for (int i = 0; i < list.size(); i++) {
			// System.out.println("集合长度为："+list.size());
			// System.out.println(list.get(i));
		}

	}

	// @Test
	public void test03() {
		int i = 30;
		Assert.assertEquals(30, i);// 便已通过，不会报错；
	}

	@Test
	public void test1() {
		int i = 30;
		int[] arr = { 1, 2, 3, 4, 5 };
		int[] arr2 = { 1, 2 };
		System.out.println(getSum2(1, 2, 3, 4));
		// System.out.println(getSum(arr));
		// System.out.println(getSum(arr, arr2));
	}

	public long getSum(int[]... arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++)
				sum += arr[i][j];
		}
		return sum;
	}

	//这个需要理解一下；
	public long getSum2(int... arr) {
		int sum = 0;
		for (int arr1 : arr) {
			sum += arr1;
		}
		return sum;
	}
}
