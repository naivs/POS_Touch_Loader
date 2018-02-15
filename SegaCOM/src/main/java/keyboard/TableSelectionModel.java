/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keyboard;

import javax.swing.DefaultListSelectionModel;

/**
 *
 * @author Ivan Naumov
 */
public class TableSelectionModel extends DefaultListSelectionModel {
    
    @Override
    public void setSelectionInterval(int index0, int index1) {
        System.out.println(String.format("setSelectionInterval: from %s to %s", index0, index1));
        System.out.println("AnchorSelectedIndex is: " + getAnchorSelectionIndex());
        System.out.println("LeadSelectedIndex is: " + getLeadSelectionIndex());
        
//        if (index0 != 0) {
            super.setSelectionInterval(index0, index1);
//        }
        
        
    }
    
    @Override
    public void addSelectionInterval(int index0, int index1) {
        System.out.println(String.format("addSelectionInterval: from %s to %s", index0, index1));
        super.addSelectionInterval(index0, index1);
    }
}
