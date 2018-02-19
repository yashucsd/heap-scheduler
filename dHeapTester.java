

package hw6;

import org.junit.*;
import static org.junit.Assert.*;

public class dHeapTester {
    dHeap<Integer> heapOne;
    dHeap<Integer> heapTwo;
    dHeap<Integer> heapThree;
    dHeap<Integer> heapFour;
    dHeap<Integer> heapFive;
    dHeap<Integer> heapSix;
    
    //creates min and max bi, tri, and quadary heaps
    @Before
    public void setUp() {
        heapOne = new dHeap<>();
        heapTwo = new dHeap<>(3, true);
        heapThree = new dHeap<>(4, false, 3);
        heapFour = new dHeap<>(6, true, 3);
        heapFive = new dHeap<>(2, false, 4);
        heapSix = new dHeap<>(1, true, 4);
    }
    
    //min heap, binary
    
        //tests adding and retrieving a single element from a binary minheap
        @Test
        public void testMinSingleBi() {
            heapOne.add(1);
            assertEquals("Checks single element add & remove",
                         new Integer(1), heapOne.remove());
        }
    
		// tests adding multiple elements and retrieving a single element from a
		// binary minheap
        @Test
        public void testMinMultiBiAdd() {
            addElms(heapOne);
            assertEquals("Checks adding multiple elements",
                         new Integer(1), heapOne.remove());
        }
    
        //tests removing multiple elements from a binary minheap
        @Test
        public void testMinMultiBiRem() {
            addElms(heapOne);
            assertEquals("Checks minimum element removal",
                         new Integer(1), heapOne.remove());
            assertEquals("Checks minimum element removal",
                         new Integer(2), heapOne.remove());
            assertEquals("Checks minimum element removal",
                         new Integer(3), heapOne.remove());
        }
    
    //max heap, binary, small capacity
    
        @Test
        public void testMaxSingleBi() {
            heapTwo.add(1);
            assertEquals("Checks single element add & remove",
                         new Integer(1), heapTwo.remove());
        }
    
        @Test
        public void testMaxMultiBiAdd() {
            addElms(heapTwo);
            assertEquals("Checks adding multiple elements",
                         new Integer(3), heapTwo.remove());
        }
    
        @Test
        public void testMaxMultiBiRem() {
            addElms(heapTwo);
            assertEquals("Checks minimum element removal",
                         new Integer(3), heapTwo.remove());
            assertEquals("Checks minimum element removal",
                         new Integer(2), heapTwo.remove());
            assertEquals("Checks minimum element removal",
                         new Integer(1), heapTwo.remove());
        }
    
    //min heap, trinary
    
        @Test
        public void testMinSingleTri() {
            heapThree.add(1);
            assertEquals("Checks single element add & remove",
                         new Integer(1), heapThree.remove());
        }
    
        @Test
        public void testMinMultiTriAdd() {
            addElms(heapThree);
            assertEquals("Checks adding multiple elements",
                         new Integer(1), heapThree.remove());
        }
    
        @Test
        public void testMinMultiTriRem() {
            addElms(heapThree);
            assertEquals("Checks minimum element removal",
                         new Integer(1), heapThree.remove());
            assertEquals("Checks minimum element removal",
                         new Integer(2), heapThree.remove());
            assertEquals("Checks minimum element removal",
                         new Integer(3), heapThree.remove());
        }
    
    //max heap, trinary, large capacity
    
        @Test
        public void testMaxSingleTri() {
            heapFour.add(1);
            assertEquals("Checks single element add & remove",
                         new Integer(1), heapFour.remove());
        }
        
        @Test
        public void testMaxMultiTriAdd() {
            addElms(heapFour);
            assertEquals("Checks adding multiple elements",
                         new Integer(3), heapFour.remove());
        }
        
        @Test
        public void testMaxMultiTriRem() {
            addElms(heapFour);
            assertEquals("Checks minimum element removal",
                         new Integer(3), heapFour.remove());
            assertEquals("Checks minimum element removal",
                         new Integer(2), heapFour.remove());
            assertEquals("Checks minimum element removal",
                         new Integer(1), heapFour.remove());
        }
    
    //min heap, quadnary
    
        @Test
        public void testMinSingleQuad() {
            heapFive.add(1);
            assertEquals("Checks single element add & remove",
                         new Integer(1), heapFive.remove());
        }
        
        @Test
        public void testMinMultiQuadAdd() {
            addElms(heapFive);
            assertEquals("Checks adding multiple elements",
                         new Integer(1), heapFive.remove());
        }
        
        @Test
        public void testMinMultiQuadRem() {
            addElms(heapFive);
            assertEquals("Checks minimum element removal",
                         new Integer(1), heapFive.remove());
            assertEquals("Checks minimum element removal",
                         new Integer(2), heapFive.remove());
            assertEquals("Checks minimum element removal",
                         new Integer(3), heapFive.remove());
        }
    
    //max heap, quadnary, small capacity
    
        @Test
        public void testMaxSingleQuad() {
            heapSix.add(1);
            assertEquals("Checks single element add & remove",
                         new Integer(1), heapSix.remove());
        }
        
        @Test
        public void testMaxMultiQuadAdd() {
            addElms(heapSix);
            assertEquals("Checks adding multiple elements",
                         new Integer(3), heapSix.remove());
        }
        
        @Test
        public void testMaxMultiQuadRem() {
            addElms(heapSix);
            assertEquals("Checks minimum element removal",
                         new Integer(3), heapSix.remove());
            assertEquals("Checks minimum element removal",
                         new Integer(2), heapSix.remove());
            assertEquals("Checks minimum element removal",
                         new Integer(1), heapSix.remove());
        }


    //tests the size function of the heap, assures it returns the right size
    @Test
    public void testSize() {
        addElms(heapOne);
        assertEquals("Checks size after adding", 3, heapOne.size());
    }
    
    //tests adding a null element to the heap
	@Test
	public void testAddNull() {
		try {
			heapOne.add(null);
			fail();
		} catch (NullPointerException e) {
			// pass
		}
	}
	
    //tests removing an element from an empty heap
	@Test
	public void remFrmNone(){
		assertEquals("Checks null returned when removing",
				null, heapOne.remove());
	}
	
	@Test
	public void testFindMaxinMin(){
		addElms(heapOne);
		
	}
	
    //adds three elements to the heap h
    private void addElms(dHeap<Integer> h) {
        h.add(1);
        h.add(2);
        h.add(3);
    }
}
