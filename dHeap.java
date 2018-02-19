
package hw6;

@SuppressWarnings("unchecked")
class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T> {
	T[] array;
	int size = 0;
	int nb; // number of branches, d
	int idx = 0; // the empty index at the end of the array
	boolean mh; // is max heap

	/**
	 * O-argument constructor.
	 * Creates and empty dHeap with initial capacity = 5,
	 * and is a 2-min-heap
	 */
	public dHeap() {
		array = (T[]) new Comparable[5];
		mh = false;
		nb = 2;
	}

	/**
	 * Constructor to build a min or max dheap
	 * 
	 * @param isMaxHeap
	 *            if true, this is a 2-max-heap, else a 2-min-heap with initial
	 *            size = 'capacity'
	 */
	public dHeap(int capacity, boolean isMaxHeap) {
		array = (T[]) new Comparable[capacity];
		mh = isMaxHeap;
		nb = 2;
	}

	/**
	 * Constructor to build a with specified initial capacity and given number
	 * of children d.
	 * 
	 * @param capacity
	 *            initial capacity of the heap.
	 * @param isMaxHeap
	 *            if true, this is a max-heap, else a min-heap
	 * @param d
	 *            number of children,
	 * @exception if
	 *                d is less than one, throw IllegalArgumentException();
	 */
	public dHeap(int capacity, boolean isMaxHeap, int d) {
		array = (T[]) new Comparable[capacity];
		mh = isMaxHeap;
		nb = d;
	}
	
	/**
	 * @return teh number of elements in the heap
	 */
	public int size() {
		return size;
	}

	/**
	 * adds the new element to the heap appropriately, extends the array if
	 * necessary
	 * 
	 * @param data
	 *            the data of the element to be added
	 */
	public void add(T data) {
		if(data == null) throw new NullPointerException();
		array[idx] = data;
		bubbleUp(idx);

		size++;
		idx++;

		if (size > array.length - 1)
			resize();
	}
	
	/**
	 * removes the largest/smallest element of the heap and re-heapifies
	 * accordingly
	 * 
	 * @return the largest/smallest element of the heap
	 */
	public T remove() {
		if(size == 0) return null;
		T r = array[0];

		if(idx > 1){			
			array[0] = array[idx - 1];
			array[idx - 1] = null;
			trickleDown(0);
		}

		idx--;
		size--;
		return r;
	}
    
    public T findMaxinMin() {
        if(mh) return null;
        int max = getMaxChild(0);
        return array[max];
    }
    
    private int getMaxChild(int index){
        int max = index;
        int i = nb;
        
        while(i > 0 && (nb * index + i) < size){
            
            T child = array[nb * index + i];
            if(child.compareTo(array[max]) > 0)
                return getMaxChild(nb * index + i);
            
            i--;
        }
        
        return max;
    }

	/**
	 * used to reheapify after removing an element, it brings the
	 * greatest/smallest element to the top and arranges children appropriately
	 * recursively called such that the smallest/greatest elements "trickle
	 * down" to the bottom
	 * 
	 * @param index the location of the element that is being moved
	 */
	private void trickleDown(int index) {
		if (index == idx)
			return;
		else {
			int xcd = getExtremeChild(index);
			//saves location of "extreme" child
			
			T cld = array[xcd];
			T crt = array[index];
			if ((mh && crt.compareTo(cld) < 0) ||
					(!mh && crt.compareTo(cld) > 0)) {
				// swaps extreme child with element only if it is less
				// than/greater than the extreme child
				swap(xcd, index);
				trickleDown(xcd); // checks if needed to trickle the newly
									// swapped in child
			} else
				return; // trickle down not needed, current is in proper
						// location
		}
	}
	
	/**
	 * returns either the location of the greatest/least child of a parent node,
	 * or the location of the parent node itself
	 * 
	 * @param index the parent ndoe
	 * @return teh location of an extreme child or the parent itself
	 */
	private int getExtremeChild(int index) {
		int extIdx = index;
		T ext = array[index];
		// sets initial values of the extreme child to that of the parent
		
		for (int i = 1; i <= nb; i++) {
			//loops through the children of the parent
			
			int cldIdx = nb * index + i;
			T cld;
			
			if (cldIdx < array.length && array[cldIdx] != null)
				cld = array[cldIdx];
			else
				continue;
			
			if ((mh && cld.compareTo(ext) > 0) || 
				(!mh && cld.compareTo(ext) < 0)) {
				// saves the child as an extreme child if it's larger/smaller
				// than rest
				ext = array[cldIdx];
				extIdx = cldIdx;
			}
		}

		return extIdx;
	}
	
	/**
	 * moves a newly added element to its proper location in a the heap by
	 * recursively checking if it is greater/smaller than its parent element and
	 * "bubbling up" if necessary
	 * 
	 * @param index the element that needs bubblin'
	 */
	private void bubbleUp(int index) {
		if (index == 0)
			return;
		else {
			int pIdx = ((index - 1) / nb);
			//saves the location of the element's parent
			
			T prt = array[pIdx];
			T crt = array[index];
			if ((mh && crt.compareTo(prt) > 0) ||
				(!mh && crt.compareTo(prt) < 0)) {
				// swaps the element with the parent if it is greater/less than
				// the parent
				swap(pIdx, index);
				bubbleUp(pIdx); // checks if needed to bubble the newly swapped
								// in parent
			} else
				return; // bubble up not needed, element is in proper location
		}
	}

	/**
	 * swaps the values of two elements in the array
	 * 
	 * @param a
	 *            the location of value to be swapped
	 * @param b
	 *            the location of the value a is to be swapped with
	 */
	private void swap(int a, int b) {
		T t = array[a];
		array[a] = array[b];
		array[b] = t;
	}
	
	/**
	 * creates an array double the size of the initial array that stores the
	 * heap and copies the initial array's contents into the double array then
	 * it sets the array equal to the new dubble array
	 */
	private void resize() {
		T[] dubble = (T[]) new Comparable[array.length * 2];
		int i;

		for (i = 0; i < size; i++)
			dubble[i] = array[i];

		idx = i;
		array = dubble;
	}

}
