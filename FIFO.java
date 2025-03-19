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
        int[] referenceString = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3};
        int frameCount = 3;
        pageReplacement(referenceString, frameCount);
    }
}
//Output

FIFO Page Replacement
Frames: 7
Frames: 7 0
Frames: 7 0 1
Frames: 2 0 1
Frames: 2 0 1
Frames: 2 3 1
Frames: 2 3 0
Frames: 4 3 0
Frames: 4 2 0 
Frames: 4 2 3
Frames: 0 2 3
Frames: 0 2 3
Total Page Faults: 10
Total Page Hits: 2
Total Page Misses: 10
