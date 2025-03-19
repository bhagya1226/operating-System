import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

class LRU {
    public static void pageReplacement(int[] referenceString, int frameCount) {
        LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>(frameCount, 0.75f, true);
        int pageFaults = 0;
        int pageHits = 0;
        int pageMisses = 0;

        System.out.println("LRU Page Replacement");

        for (int page : referenceString) {
            if (!cache.containsKey(page)) {
                if (cache.size() == frameCount) {
                    Iterator<Map.Entry<Integer, Integer>> iterator = cache.entrySet().iterator();
                    iterator.next();
                    iterator.remove();
                }
                cache.put(page, 1); // Adding the page to cache
                pageFaults++; // Page miss
                pageMisses++; // Page miss
            } else {
                pageHits++; // Page hit
            }

            // Display the current state of frames
            System.out.print("Frames: ");
            for (int key : cache.keySet()) {
                System.out.print(key + " ");
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

// output
LRU Page Replacement
Frames: 5
Frames: 5 0
Frames: 5 0 2
Frames: 0 2 3
Frames: 0 2 3
Frames: 2 3 1
Frames: 2 3 1
Frames: 3 1 4
Frames: 1 4 5
Frames: 1 4 5
Frames: 4 5 2
Frames: 5 2 0
Frames: 2 0 3
Frames: 0 3 4
Frames: 0 3 4
Total Page Faults: 11
Total Page Hits: 4
Total Page Misses: 11
