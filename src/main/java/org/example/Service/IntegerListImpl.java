package org.example.Service;

import org.example.Exception.ElementNotFountException;
import org.example.Exception.InvalidIndexException;
import org.example.Exception.NullItemException;
import org.example.Exception.StorageIsFullException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] strong;
    private int size;

    public IntegerListImpl() {
        strong = new Integer[10];
    }

    public IntegerListImpl(int initSize) {
        strong = new Integer[initSize];
    }

    private void grow() {
        int newCapacity = strong.length + strong.length / 2;
        strong = Arrays.copyOf(strong, newCapacity);
    }

    @Override
    public Integer add(Integer item) {
        if (size == strong.length) {
            grow();
        }
        validateItem(item);
        strong[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (size == strong.length) {
            grow();
        }
        validateItem(item);
        validateIndex(index);
        if (index == size) {
            strong[size++] = item;
        } else {
            System.arraycopy(strong, index, strong, index + 1, size - index);
            strong[index] = item;
            size++;
        }
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        strong[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFountException();
        }
        if (index != size) {
            System.arraycopy(strong, index + 1, strong, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = strong[index];
        if (index != size) {
            System.arraycopy(strong, index + 1, strong, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (strong[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (strong[i].equals(item)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return strong[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(strong, size);
    }

    private void quickSort(Integer[] arr, int low, int high) {
        if (arr == null || arr.length == 0 || low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                Integer temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j) {
            quickSort(arr, low, j);
        }
        if (high > i) {
            quickSort(arr, i, high);
        }
    }

    public void sort() {
        quickSort(strong, 0, size - 1);
    }

    public int binarySearch(Integer item) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Integer midVal = strong[mid];

            if (midVal < item) {
                low = mid + 1;
            } else if (midVal > item) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -(low + 1);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateSize() {
        if (size >= strong.length) {
            throw new StorageIsFullException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new InvalidIndexException();
        }
    }
}
