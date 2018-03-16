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
    private int currentPageProgress = 0;

    @Override
    protected void reset() {
        currentFrame = 0;
        currentPageProgress = 0;
        // TODO - do preparation/initilization here, if needed
    }
    
    @Override
    public int process(String referenceString) {
        // Get the reference list as an array
        List<Integer> pageReferences = Tools.stringToArray(referenceString);
        if (pageReferences == null) return 0;

        int replacements = 0; // How many page replacements made

        // Check all virtual page accesses
        for (int page: pageReferences) {
            // If the page is not loaded into physical frame
            if (!isLoaded(page) && !isFramesFull()) {
                // Page it in (load it)
                if (pageIn(currentFrame, page)) {
                    // If there was a page replacement, register it
                    replacements++;
                }
                moveCurrentPointer();
                System.out.println(getFrameStatus());
            } else if (!isLoaded(page)) {
                System.out.println(getFrameStatus() + " Will check distance on " + page);
                if(pageInOptimal(page, pageReferences, currentPageProgress)) {
                    replacements++;
                }
            } else {
                System.out.println("Page "+ page +" is already loaded");
            }
            System.out.println("Current replacement = " + replacements);
            currentPageProgress++;
            System.out.println();
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
