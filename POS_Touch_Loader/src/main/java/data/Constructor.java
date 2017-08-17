package data;

import gui.DayOfWeekConfig;
import gui.SubgroupConfig;
import gui.GroupConfig;
import gui.Uploader;
import io.ConfigurationWriter;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.transform.TransformerException;
import utils.IndexDispatcher;

/**
 *
 * @author ivan
 */
public class Constructor {

    private final Regular regular;

    private final ArrayList<TerminalGroup> terminalGroups; // <- конфигурация
    
    private final IndexDispatcher indexDispatcher;

    private ConfigurationWriter configurationWriter;
    private final utils.Validator validator;

    public Constructor(Regular regular) {

        this.regular = regular;
        indexDispatcher = new IndexDispatcher();

        validator = new utils.Validator(this.regular);
        terminalGroups = validator.validate();

//        // заполнение групп терминплов днями недели
//        for (TerminalGroup tg : terminalGroups) {
//            tg.createDaysOfWeek();
//        }
    }

    public DefaultTreeModel getConstrTreeModel(int terminalGroup, int dayOfWeek) {

        DayOfWeek dow = terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek];
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(dow);

        for (int i = 0; i < 8; i++) {
            Group group = dow.getGroup(i);//terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek].getGroup(i);

            if (group != null) {

                DefaultMutableTreeNode grpNode = new DefaultMutableTreeNode(group);

                for (int j = 0; j < 8; j++) {
                    Subgroup subgroup = group.getSubgroup(j);//terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek].getGroup(i).getSubgroup(j);

                    if (subgroup != null) {
                        
                        DefaultMutableTreeNode sgrpNode = new DefaultMutableTreeNode(subgroup);
                        
                        for(int k = 0; k < 20; k++) {
                            Product product = subgroup.getProduct(k);//terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek].getGroup(i).getSubgroup(j).getProduct(k);
                            
                            if(product != null) {
                                DefaultMutableTreeNode prodNode = new DefaultMutableTreeNode(product);
                                sgrpNode.add(prodNode);
                            }
                        }
                        
                        
                        grpNode.add(sgrpNode);
                    }
                }

                root.add(grpNode);
            }
            //else
            //    root.add(new DefaultMutableTreeNode());
        }

        DefaultTreeModel dtm = new DefaultTreeModel(root);

        return dtm;
    }

    public DefaultComboBoxModel getAllTerminalGroupsAsComboBoxModel() {

        DefaultComboBoxModel dcm = new DefaultComboBoxModel();

        for (TerminalGroup tg : terminalGroups) {
            dcm.addElement(tg);
        }

        return dcm;
    }

    public String[] getAddedGroupsAsString(int terminalGroup, int dayOfWeek) {

        DayOfWeek dow = terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek];

        return dow.getGroupsAsStringArray();
    }

    public DefaultTableModel getAddedGroupsAsTableModel(int terminalGroup, int dayOfWeek) {

        DefaultTableModel dtm = new DefaultTableModel(8, 1);

        int i = 0;
        for (String grp : terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek].getGroupsAsStringArray()) {

            dtm.setValueAt(grp, i, 0);
            i++;
        }

        return dtm;
    }

    public DefaultTableModel getAddedSubgroupsAsTableModel(int terminalGroup, int dayOfWeek, int group) {

        DefaultTableModel dtm = new DefaultTableModel(8, 1);

        int i = 0;
        for (String sgrp : terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek].getGroup(group).getSubgroupsAsStringArray()) {

            dtm.setValueAt(sgrp, i, 0);
            i++;
        }

        return dtm;
    }

    public void uploadConfiguration(javax.swing.JFrame parent) {
        
        Uploader uploader = new Uploader(parent,
                true,
                terminalGroups
        );

        uploader.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                
            }
        });

        uploader.setVisible(true);
    }
    
//    public String[] getAddedSubgroups(int terminalGroup, int dayOfWeek, int group) {
//        return terminalGroups.get(terminalGroup).getDaysOfWeek().get(dayOfWeek).getGroup(group).getSubgroupsAsStringArray();
//    }

//    public DefaultListModel getAllGroupsAsListModel(String[] exclude) {
//
//        DefaultListModel dcm = new DefaultListModel();
//
//        for (int i = 0; i < regular.getGroupsAsListModel().getSize(); i++) {
//
//            //boolean isAdded = false;
//            for (String exclude1 : exclude) {
//                if (regular.getGroupsAsStringArray()[i].equals(exclude1)) {
//                    //isAdded = true;
//                    break;
//                }
//            }
//
//            dcm.addElement(regular.getGroupsAsStringArray()[i]);
//        }
//
//        return dcm;
//    }

//    public DefaultListModel getAllSubgroupsAsListModel(String group, String[] exclude) {
//
//        // исключая не совместимые
//        // исключая добавленные
//        DefaultListModel dcm = new DefaultListModel();
//
//        // получаю все подгруппы
//        ArrayList<Subgroup> subgroups = regular.getSubgroups();
//
//        for (int i = 0; i < subgroups.size(); i++) {
//
//            if (subgroups.get(i).isAccessible(group)) {
//                for (String exclude1 : exclude) {
//                    if (subgroups.get(i).toString().equals(exclude1)) {
//                        break;
//                    }
//                }
//
//                dcm.addElement(regular.getSubgroupsAsStringArray()[i]);
//            }
//        }
//
//        return dcm;
//    }

//    public void addGroup(int terminalGroup, int dayOfWeek, Group group) {
//
//        DayOfWeek dow = terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek];
//        dow.addGroup(group);
//    }

//    public void addSubgroup(int terminalGroup, int dayOfWeek, int group, Subgroup subgroup) {
//
//        Group grp = terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek].getGroup(group);
//        grp.addSubgroup(subgroup);
//    }

//    public void removeGroup(int terminalGroup, int dayOfWeek, int group) {
//
//        DayOfWeek dow = terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek];
//        dow.removeGroup(group);
//    }
//
//    public void upGroup(int group, int terminalGroup, int dayOfWeek) {
//        DayOfWeek dow = terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek];
//        dow.upGroup(group);
//    }
//
//    public void downGroup(int group, int terminalGroup, int dayOfWeek) {
//        DayOfWeek dow = terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek];
//        dow.downGroup(group);
//    }

//    public ArrayList<TerminalGroup> getTerminalGroups() {
//        return terminalGroups;
//    }

//    public int getGroupCount(int terminalGroup, int dayOfWeek) {
//        return terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek].getGroupCount();
//    }
//
//    public int getSubgroupCount(int terminalGroup, int dayOfWeek, int group) {
//        return terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek].getGroup(group).getSubgroupCount();
//    }

    public void configureDayOfWeek(gui.MainFrame parent, int terminalGroup, int dayOfWeek) {

        DayOfWeekConfig dialog = new DayOfWeekConfig(parent,
                true,
                dayOfWeek,
                terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek],
                regular.getGroupsAsListModel()
        );

        dialog.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {

                configurationWriter = new ConfigurationWriter();

                try {
                    configurationWriter.write(terminalGroups);
                } catch (TransformerException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });

        dialog.setVisible(true);
    }

    public void configureGroup(gui.MainFrame parent, int terminalGroup, int dayOfWeek, int group) {

        GroupConfig dialog = new GroupConfig(parent,
                true,
                dayOfWeek,
                indexDispatcher,
                terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek].getGroup(group),
                regular.getSubgroupsAsListModel(terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek].getGroup(group))
        );

        dialog.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                configurationWriter = new ConfigurationWriter();

                try {
                    configurationWriter.write(terminalGroups);
                } catch (TransformerException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });

        dialog.setVisible(true);
    }

    public void configureSubgroup(gui.MainFrame parent, int terminalGroup, int dayOfWeek, int group, int subgroup) {

        System.out.println("Group index: " + group);
        System.out.println("Subgroup index: " + subgroup);

        SubgroupConfig dialog = new SubgroupConfig(parent,
                true,
                dayOfWeek,
                terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek].getGroup(group).getSubgroup(subgroup),
                regular.getProductsAsListModel(terminalGroups.get(terminalGroup).getDaysOfWeek()[dayOfWeek].getGroup(group).getSubgroup(subgroup))
        );

        dialog.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                configurationWriter = new ConfigurationWriter();

                try {
                    configurationWriter.write(terminalGroups);
                } catch (TransformerException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });

        dialog.setVisible(true);
    }
}
