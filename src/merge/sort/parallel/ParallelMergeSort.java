package merge.sort.parallel;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

import merge.sort.sequential.SequentialMergeSort;

/**
 * The Class ParallelMergeSort.
 */
public class ParallelMergeSort extends RecursiveAction {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6761664019039931043L;
	
	/** The nums to be sorted */
	private int[] nums;
	
	/** The sequential merge sort. */
	private SequentialMergeSort sequencialMergeSort;

	/**
	 * Instantiates a new parallel merge sort.
	 *
	 * @param nums the nums
	 */
	public ParallelMergeSort(int[] nums) {
		this.nums = nums;
		sequencialMergeSort = new SequentialMergeSort();
	}

	/**
	 * Call the methods and execute its in parallel way.
	 * 
	 */
	@Override
	protected void compute() {
		if (nums.length <= 10) {
			sequencialMergeSort.mergeSort(nums);
			return;
		}
		int middleIndex = nums.length / 2;
		int[] leftSubarray = Arrays.copyOfRange(nums, 0, middleIndex);
		int[] rightSubarray = Arrays.copyOfRange(nums, middleIndex, nums.length);

		ParallelMergeSort leftTask = new ParallelMergeSort(leftSubarray);
		ParallelMergeSort rightTask = new ParallelMergeSort(rightSubarray);

		invokeAll(leftTask, rightTask);

		sequencialMergeSort.merge(leftSubarray, rightSubarray, nums);
	}

}
