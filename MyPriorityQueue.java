package hw6;

public class MyPriorityQueue<T extends Comparable<? super T>> {
    private dHeap<T> heap;
    
    public MyPriorityQueue(int size){
        heap = new dHeap<>(size, false);
    }
    
    public void add(T e){
        heap.add(e);
    }
    
    public T poll(){
        return heap.remove();
    }
}
