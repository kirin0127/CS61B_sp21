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

}