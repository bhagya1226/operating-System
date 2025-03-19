import java.util.Arrays;

class FIFO {
    public static void pageReplacement(int[] referenceString, int frameCount) {
        int[] frames = new int[frameCount];
        Arrays.fill(frames, -1); // Initially, all frames are empty
        int pageFaults = 0;
        int pageHits = 0;
        int pageMisses = 0;
        int index = 0;

        System.out.println("FIFO Page Replacement");

        for (int page : referenceString) {
            boolean pageFound = false;
            // Check if page is already in the frames
            for (int i = 0; i < frameCount; i++) {
                if (frames[i] == page) {
                    pageFound = true;
                    break;
                }
            }

            if (pageFound) {
                pageHits++; // Increment page hits
            } else {
                frames[index] = page;
                index = (index + 1) % frameCount; // Move to the next frame in a circular manner
                pageFaults++; // Increment page faults
                pageMisses++; // Increment page misses
            }

            // Display the current state of frames
            System.out.print("Frames: ");
            for (int i = 0; i < frameCount; i++) {
                if (frames[i] != -1) {
                    System.out.print(frames[i] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("Total Page Faults: " + pageFaults);
        System.out.println("Total Page Hits: " + pageHits);
        System.out.println("Total Page Misses: " + pageMisses);
    }

    public static void main(String[] args) {
        int[] referenceString = {5,0,2,3,0,1,3,4,5,4,2,0,3,4,3};
        int frameCount = 3;
        pageReplacement(referenceString, frameCount);
    }
}
//Output

FIFO Page Replacement
Frames: 5
Frames: 5 0
Frames: 5 0 2
Frames: 3 0 2
Frames: 3 0 2
Frames: 3 1 2
Frames: 3 1 2
Frames: 3 1 4
Frames: 5 1 4
Frames: 5 1 4
Frames: 5 2 4
Frames: 5 2 0
Frames: 3 2 0
Frames: 3 4 0
Frames: 3 4 0
Total Page Faults: 11
Total Page Hits: 4
Total Page Misses: 11
