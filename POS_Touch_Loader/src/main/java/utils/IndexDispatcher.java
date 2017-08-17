package utils;

/**
 *
 * @author ivan
 */
public class IndexDispatcher {
    
//    public IndexDispatcher() {
//        System.out.println(getFreeIndexPercent()[0]);
//    }
    
    // indexPool[dayOfWeek][index]
    private final boolean[][] indexPool = new boolean[7][999];
    
    public int getNextFreeIndex(int day) {
        for(int i = 0; i < indexPool[day].length; i++) {
            if(!indexPool[day][i]) {
                indexPool[day][i] = true;
                return i + 1;
            }
        }
        
        return -1;
    }
    
    public void releaseIndex(int day, int index) {
        indexPool[day][index - 1] = false;
    }
    
    public float[] getFreeIndexPercent() {
        float[] free = new float[7];
        
        
        for(int i = 0; i < indexPool.length; i++) {
            
            int holdedCount = 0;
            
            for(int j = 0; j < indexPool[i].length; j++) {
                if(indexPool[i][j]) {
                    holdedCount++;
                }
            }
            
            free[i] = holdedCount / (999f / 100);
        }
        
        return free;
    }
}
