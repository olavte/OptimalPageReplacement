package no.hials.page.replacement;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Optimal page replacement algorithm
 * @author Girts Strazdins, 2016-03-11 (only a template)
 */
public class OptimalReplacementTest {
    /**
     * Test of process method, of class OptimalReplacement.
     */
    @Test
    public void testProcess() {
        System.out.println("Optimal Replacement process test");
        OptimalReplacement algo = new OptimalReplacement();

        // Use the book example with 3 frames and the given reference string
        algo.setup(3);
        String ref = "7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1"; //(7, E, E) -> (7, 0, E) -> (7, 0, 1) -> (2, 0, 1) -> ()
        int replacements = algo.process(ref);
        String frameStatus = algo.getFrameStatus();
        assertEquals(6, replacements);
        assertEquals("7, 0, 1", frameStatus);

        ref = "4,7,7,3,3,3,2,2,2,2,1,1,1,1,0,0,0,0,0,0"; // (4, E, E) -> (4, 7, E) -> (4, 7, 3) -> (2, 7, 3) -> (2, 1, 3) -> (2, 1, 0)
        replacements = algo.process(ref);
        frameStatus = algo.getFrameStatus();
        //assertEquals(6, replacements); // (0, E, E) -> (0, 1, E) -> (0, 1, 2) -> (3, 1, 2) -> (3, 4, 2) -> (3, 4, 7)
        //assertEquals("2, 1, 0", frameStatus);

        // TODO - add additional Unit tests here
    }
   
}
