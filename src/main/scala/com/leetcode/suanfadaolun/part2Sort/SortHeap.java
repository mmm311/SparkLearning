package com.leetcode.suanfadaolun.part2Sort;

public class SortHeap {
    public static int parent(int i ){
        return (i + 1) / 2 - 1;
    }

    public static int left(int i){
        return  2 * (i + 1) - 1;
    }

    public static int right(int i){
        return 2 * (i + 1);
    }

    public static  void max_heapify(int A[], int i, int heap_size){
            int left = left(i);
            int right = right(i);

            int largest = 0;
            if (left <= heap_size && A[left] > A[i]) {
                largest = left;
            } else {
                largest = i;
            }

            if (right <= heap_size && A[right] > A[largest]) {
                largest = right;
            }
            if (largest == i || largest > heap_size)
                return;
            int temp = A[i];
            A[i] = A[largest];
            A[largest] = temp;
            max_heapify(A, largest, heap_size);

    }

    public static void max_heapify01(int A[], int start, int heap_size){
        int child = start * 2 + 1;

        while (child < heap_size){
            if (A[child + 1] > A[child] && (child < heap_size)){
                child = child + 1;
            }

            if (A[child] < A[start]){
                break;
            }else{
                int temp = A[start];
                A[start] = A[child];
                A[child] = temp;
            }

            start = child;
            child = 2 * start + 1;

        }
    }

    public static void build_max_heap(int[] A){
        int heap_size = A.length - 1;
        for (int i = A.length / 2 - 1 ; i >= 0; i--){
            max_heapify01(A, i, heap_size);
        }

    }

    public static void heapSort(int[] A){
        int heap_size = A.length - 1;
        for (int i = heap_size; i >= 0; i--){
            int temp = A[0];
            A[0] = A[i];
            A[i] = temp;
            heap_size = heap_size - 1;
            max_heapify(A, 0, heap_size);

        }
    }

    public static void main(String[] args){
        int []A = {0, 1, 3, 2, 4 ,5, 6, 8, 7, 9, 12, 1, 1, 1, 1, 8};
        build_max_heap(A);
        heapSort(A);
        for (int i = 0; i < A.length; i++){
            System.out.print(A[i] + " ");
        }
    }


}
