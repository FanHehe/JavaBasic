import java.util.List;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		List<Sort<Integer>> sortList = new ArrayList<>(10);
		// 原始
		sortList.add(new Origin<Integer>()); 

		// 交换排序
		sortList.add(new QuickSort<Integer>());
		sortList.add(new BubbleSort<Integer>());
		
		// 选择排序
		sortList.add(new HeapSort<Integer>());
		sortList.add(new SelectSort<Integer>());

		// 插入排序
		sortList.add(new InsertSort<Integer>());
		sortList.add(new ShellSort<Integer>());
		// 归并排序
		sortList.add(new MergeSort<Integer>());
		// 基数排序
		sortList.add(new RadixSort<Integer>());
		// 计数排序
		sortList.add(new CountingSort<Integer>());
		// 桶排序
		sortList.add(new BucketSort<Integer>());

		for (Sort<Integer> sort: sortList) {
			List<Integer> list = makeArrayList();
			sort.sort(list);
			sort.print(list); // 5 1 -1 10 0 2 
		}
		
	}

	public static List<Integer> makeArrayList() {
		List<Integer> list = new ArrayList<>(6);

		list.add(5);
		list.add(1);
		list.add(-1);
		list.add(10);
		list.add(0);
		list.add(2);
		// 5 1 -1 10 0 2
		return list;
	}
}