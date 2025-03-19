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
        int[] referenceString = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3};
        int frameCount = 3;
        pageReplacement(referenceString, frameCount);
    }
}
