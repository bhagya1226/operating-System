import java.util.Arrays;

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
        int[] referenceString = {5,0,2,3,0,1,3,4,5,4,2,0,3,4,3};
        int frameCount = 3;
        pageReplacement(referenceString, frameCount);
    }
}
//Output
Optimal Page Replacement
Frames: 5
Frames: 5 0
Frames: 5 0 2
Frames: 5 0 3
Frames: 5 0 3
Frames: 5 1 3
Frames: 5 1 3
Frames: 5 4 3
Frames: 5 4 3
Frames: 5 4 3 
Frames: 2 4 3
Frames: 0 4 3
Frames: 0 4 3
Frames: 0 4 3
Frames: 0 4 3
Total Page Faults: 8
Total Page Hits: 7
Total Page Misses: 8
