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
package excel;

/**
 *
 * @author Ivan Naumov
 */
public interface XLSXService {
    
    boolean isDayEmpty(int day);
    
    boolean isGroupEmpty(int day, int group);
    
    boolean isSubgroupEmpty(int day, int group, int subgroup);
    
    boolean isProductEmpty(int day, int group, int subgroup, int product);
    
    String[] getDayNames();
    
    String[] getGroupNames(int day);
    
    String[] getSubgroupNames(int day, int group);
    
    String[] getProductNames(int day, int group, int subgroup);
    
    int[] getProductPlu(int day, int group, int subgroup);
    
    String readProductName(int day, int group, int subgroup, int product);
    
    int readProductPlu(int day, int group, int subgroup, int product);
    
    void close();
}
