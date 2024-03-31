package org.example;

import java.util.Arrays;
import java.util.Random;

public class SortingTest {

    public static void main(String[] args) {

        Integer[] originalArray = new Integer[100000];
        Random rand = new Random();
        for (int i = 0; i < originalArray.length; i++) {
            originalArray[i] = rand.nextInt();
        }


        Integer[] arrayForQuickSort = originalArray.clone();
        Integer[] arrayForMergeSort = originalArray.clone();
        Integer[] arrayForStandardSort = originalArray.clone();


        long start = System.currentTimeMillis();
        quickSort(arrayForQuickSort, 0, arrayForQuickSort.length - 1);
        long quickSortTime = System.currentTimeMillis() - start;
        System.out.println("Время быстрой сортировки: " + quickSortTime + "Мс");


        start = System.currentTimeMillis();
        mergeSort(arrayForMergeSort, 0, arrayForMergeSort.length - 1);
        long mergeSortTime = System.currentTimeMillis() - start;
        System.out.println("Время сортировки слиянием: " + mergeSortTime + "Мс");


        start = System.currentTimeMillis();
        Arrays.sort(arrayForStandardSort);
        long standardSortTime = System.currentTimeMillis() - start;
        System.out.println("Стандартные Arrays.sort время: " + standardSortTime + "Мс");
    }

    private static void quickSort(Integer[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Integer[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    private static void mergeSort(Integer[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(Integer[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Integer[] L = new Integer[n1];
        Integer[] R = new Integer[n2];

        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}