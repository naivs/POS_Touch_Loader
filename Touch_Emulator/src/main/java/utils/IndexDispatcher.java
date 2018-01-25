/*
 * Copyright (C) 2017 Ivan Naumov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
    
    public int getNextFreeIndex(int day, int start) {
        for(int i = start; i < start + 100; i++) {
            if(!indexPool[day][i]) {
                indexPool[day][i] = true;
                return i;
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
