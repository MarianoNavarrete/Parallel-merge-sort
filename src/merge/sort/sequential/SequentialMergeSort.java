package merge.sort.sequential;

import java.util.Arrays;


/**
 * The Class SequentialMergeSort.
 */
public class SequentialMergeSort {

	/**
	 * Merge sort.
	 *
	 * @param nums the nums
	 */
	public void mergeSort(int[] nums) {
		if (nums.length <= 1) {
			return;
		}

		int middleIndex = nums.length / 2;

		int[] left = Arrays.copyOfRange(nums, 0, middleIndex);
		int[] rigth = Arrays.copyOfRange(nums, middleIndex, nums.length);

		mergeSort(left);
		mergeSort(rigth);

		merge(left, rigth, nums);
	}

	/**
	 * Merge.
	 *
	 * @param left the left
	 * @param rigth the rigth
	 * @param nums the nums
	 */
	public void merge(int[] left, int[] rigth, int[] nums) {
		int i = 0;
		int j = 0;
		int k = 0;

		while (i < left.length && j < rigth.length) {
			if (left[i] < rigth[j]) {
				nums[k++] = left[i++];
			} else {
				nums[k++] = rigth[j++];
			}
		}
		//if there is data in the left side
		while (i < left.length) {
			nums[k++] = left[i++];
		}
		//if there is data in the right side
		while (j < rigth.length) {
			nums[k++] = rigth[j++];
		}
	}
}
