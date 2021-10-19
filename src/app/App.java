package app;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import merge.sort.parallel.ParallelMergeSort;
import merge.sort.sequential.SequentialMergeSort;

public class App {
	public static void main(String[] args) {
		SequentialMergeSort sequencialMergeSort = new SequentialMergeSort();

		int[] largeNumsS = generateArraysOfNumbers();
		int[] largeNumsP = generateArraysOfNumbers();

		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		ParallelMergeSort parallelMergeSort = new ParallelMergeSort(largeNumsP);

		long start = System.currentTimeMillis();
		sequencialMergeSort.mergeSort(largeNumsS);
		System.out.println("time for sequencial algorithm " + (System.currentTimeMillis() - start));
		long start2 = System.currentTimeMillis();
		pool.invoke(parallelMergeSort);
		System.out.println("time for parallel algorithm " + (System.currentTimeMillis() - start2));

		printValues(largeNumsP);
	}

	public static int[] generateArraysOfNumbers() {
		Random random = new Random();
		int[] nums = new int[1000000];
		for (int i = 0; i < 1000000; i++) {
			nums[i] = random.nextInt(100000);
		}

		return nums;
	}

	public static void printValues(int[] nums) {
		for (int i : nums) {
			System.out.println(i);
		}
	}
}
