package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> noResizing = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();
        noResizing.addLast(4);
        noResizing.addLast(5);
        noResizing.addLast(6);
        buggyAList.addLast(4);
        buggyAList.addLast(5);
        buggyAList.addLast(6);
        assertEquals(noResizing.removeLast(), buggyAList.removeLast());
        assertEquals(noResizing.removeLast(), buggyAList.removeLast());
        assertEquals(noResizing.removeLast(), buggyAList.removeLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int sizeB = B.size();
                assertEquals(size, sizeB);
            } else if(operationNumber == 2){
                // getLast
                if(L.size() != 0){
                    int val = L.getLast();
                    int valB = B.getLast();
                    assertEquals(val, valB);
                }
            } else if(operationNumber == 3){
                // removeLast
                if(L.size() != 0){
                    int val = L.removeLast();
                    int valB = B.removeLast();
                    assertEquals(val, valB);
                }
            }
        }
    }

}