import java.util.*;

class Optimal {
    public static void pageReplacement(int[] referenceString, int frameCount) {
        int[] frames = new int[frameCount];
        Arrays.fill(frames, -1); // Initially, all frames are empty
        int pageFaults = 0;
        int pageHits = 0;
        int pageMisses = 0;

        System.out.println("Optimal Page Replacement");

        for (int i = 0; i < referenceString.length; i++) {
            int page = referenceString[i];
            boolean pageFound = false;

            // Check if the page is already in the frames
            for (int j = 0; j < frameCount; j++) {
                if (frames[j] == page) {
                    pageFound = true;
                    break;
                }
            }

            if (pageFound) {
                pageHits++; // Page hit
            } else {
                // If there is an empty frame, put the page in it
                boolean placed = false;
                for (int j = 0; j < frameCount; j++) {
                    if (frames[j] == -1) {
                        frames[j] = page;
                        pageFaults++; // Page miss
                        placed = true;
                        pageMisses++; // Page miss
                        break;
                    }
                }

                // If no empty frame, replace the page that will not be used for the longest time
                if (!placed) {
                    int farthest = -1, indexToReplace = -1;
                    for (int j = 0; j < frameCount; j++) {
                        int nextUse = findNextUse(referenceString, i, frames[j]);
                        if (nextUse == -1) {
                            indexToReplace = j;
                            break;
                        } else if (nextUse > farthest) {
                            farthest = nextUse;
                            indexToReplace = j;
                        }
                    }
                    frames[indexToReplace] = page;
                    pageFaults++; // Page miss
                    pageMisses++; // Page miss
                }
            }

            // Display the current state of frames
            System.out.print("Frames: ");
            for (int f : frames) {
                if (f != -1) {
                    System.out.print(f + " ");
                }
            }
            System.out.println();
        }
        System.out.println("Total Page Faults: " + pageFaults);
        System.out.println("Total Page Hits: " + pageHits);
        System.out.println("Total Page Misses: " + pageMisses);
    }

    private static int findNextUse(int[] referenceString, int start, int page) {
        for (int i = start + 1; i < referenceString.length; i++) {
            if (referenceString[i] == page) {
                return i;
            }
        }
        return -1; // Return -1 if the page is not found in the future
    }

    public static void main(String[] args) {
        int[] referenceString = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3};
        int frameCount = 3;
        pageReplacement(referenceString, frameCount);
    }
}
