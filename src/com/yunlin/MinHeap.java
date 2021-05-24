package com.yunlin;

public class MinHeap  {
    public int[] data;
    private int index;
    private int size;
    private static final int FRONT = 0;

    public MinHeap (int size) {
        this.size = size;
        this.index = -1;
        data = new int[size];
    }

    private int getParent(int i) {
        return (i-1)/2;
    }

    private int getLeftChild(int i) {
        return i*2+1;
    }

    private int getRightChild(int i) {
        return i*2+2;
    }

    private void swap(int i, int j) {
        int t;
        t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    private boolean isLeaf(int i) {
        if (getLeftChild(i)>index && getRightChild(i)>index) {
            return true;
        }
        return false;
    }

    private void siftUp(int i) {
        while (data[i] < data[getParent(i)]) {
            swap(i, getParent(i));
            i = getParent(i);
        }
    }

    private void siftDown(int i) {
        if (!isLeaf(i)) {
            if (data[i] > data[getLeftChild(i)] || data[i] > data[getRightChild(i)]) {
                // Swap with the left child and heapify the left child
                if (data[getLeftChild(i)] < data[getRightChild(i)]) {
                    swap(i, getLeftChild(i));
                    siftDown(getLeftChild(i));
                }
                // Swap with the right child and heapify the right child
                else {
                    swap(i, getRightChild(i));
                    siftDown(getRightChild(i));
                }
            }
        }
    }

    public void insert(int e) { // insert at the bottom and shift up
        if (index >= size) return;
        data[++index] = e;
        siftUp(index);
    }

    public int peek() {
        return data[FRONT];
    }

    public int pop() { // put leaf on the top and shift down
        int top = data[FRONT];
        if (index == FRONT) {
            index--;
            return top;
        }
        data[FRONT] = data[index--];
        siftDown(FRONT);
        return top;
    }

    public void buildMinHeap() { // use helper function shiftdown
        for (int i=(index-1)/2; i>=1; i--) {
            siftDown(i);
        }
    }

    public void print() {
        for (int i = 0; i <= index; i++) {
            System.out.print(data[i] + ": ");
        }
        System.out.println();
    }

    ////////////////////////////////////////////////////////////////
    public static void main(String[] arg) {
        System.out.println("The Min Heap test1 is ");
        MinHeap h1 = new MinHeap(15);
        h1.insert(5);
        h1.print();
        h1.insert(3);
        h1.print();
        h1.insert(2);
        h1.print();
//        h1.insert(10);
//        h1.insert(19);
//        h1.insert(6);
//        h1.insert(22);
//        h1.insert(9);
//
        System.out.println("The Min val is " + h1.pop());
        h1.print();

        System.out.println("The Min val is " + h1.pop());
        h1.print();

        System.out.println("The Min val is " + h1.pop());
        h1.print();

        System.out.println("The Min val is " + h1.pop());
        h1.print();

    }

}
