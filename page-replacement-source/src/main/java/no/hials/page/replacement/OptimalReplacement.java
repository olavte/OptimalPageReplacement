package no.hials.page.replacement;

import java.util.Collections;
import java.util.List;

/**
 * Optimal Replacement algorithm
 * Fill in your code in this class!
 */
public class OptimalReplacement extends ReplacementAlgorithm {
    
    // TODO - add some state variables here, if you need any
    private int currentFrame = 0;

    @Override
    protected void reset() {
        currentFrame = 0;
        // TODO - do preparation/initilization here, if needed
    }
    
    @Override
    public int process(String referenceString) {

        // Get the reference list as an array
        List<Integer> pageReferences = Tools.stringToArray(referenceString);
        if (pageReferences == null) return 0;

        Collections.sort(pageReferences);

        int replacements = 0; // How many page replacements made

        // Check all virtual page accesses
        for (int page: pageReferences) {
            for(int i = 0; i > frames.length; i++) {
                if(frames[i] == EMPTY) {
                    pageIn(currentFrame, page);
                }
            }
            if (!isLoaded(page)) {
                int a = 0;
                int b = 0;
                int c = 0;
                for (int p: pageReferences) {
                    if (p == frames[0]) {
                        a = p;
                    } else if (p == frames[1]) {
                        b = p;
                    } else if (p == frames[2]) {
                        c = p;
                    }
                }
                if (a > b && a > c) {
                    pageIn(frames[0], page);
                    replacements++;
                }
                if (b > a && b > c) {
                    pageIn(frames[1], page);
                    replacements++;
                }
                if (c > b && c > a) {
                    pageIn(frames[2], page);
                    replacements++;
                }
            }
        }
       
        return replacements;
    }

    /**
     * Move the current circular FIFO pointer - next page to be replaced
     */
    private void moveCurrentPointer() {
        currentFrame++;
        // If it overflows, go to first frame again
        if (currentFrame == frames.length) {
            currentFrame = 0;
        }
    }

    // TODO - create any helper methods here if you need any
}
