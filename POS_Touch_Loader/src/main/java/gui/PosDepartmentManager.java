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
package gui;

import data.DayOfWeek;
import data.Group;
import data.Product;
import data.Subgroup;
import data.TerminalGroup;
import excel.Parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import utils.IndexDispatcher;

/**
 *
 * @author ivan
 */
public class PosDepartmentManager extends javax.swing.JDialog {

    private final ArrayList<TerminalGroup> configuration;
    private final DefaultListModel listModel = new DefaultListModel<>();
    
    private final IndexDispatcher idisp = new IndexDispatcher();
    
    private static final int NORMAL = 1;
    private static final int ADD = 2;
    private static final int EDIT = 3;
    
    public static final int NO_CHANGES = 0;
    public static final int DEPARTMENTS_CHANGED = 1;
    public static final int CONTENT_CHANGED = 2;
    
    private int isModified;
    
    /**
     * Creates new form NewDept
     * @param parent
     * @param modal
     * @param configuration
     */
    public PosDepartmentManager(java.awt.Frame parent, boolean modal, ArrayList<TerminalGroup> configuration) {
        super(parent, modal);
        this.configuration = configuration;
        configuration.forEach((tGrp) -> {
            listModel.addElement(tGrp.toString());
        });
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        btnAdd = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        boxName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        boxTerminals = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        boxFile = new javax.swing.JTextField();
        btnFile = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Кассовые отделы");
        setResizable(false);

        jList1.setModel(listModel);
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setFocusable(false);
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        btnAdd.setText("+");
        btnAdd.setFocusable(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDel.setText("-");
        btnDel.setFocusable(false);
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ---", "*001", "*101", "*201", "*311", "*401", "*501", "*601" }));
        jComboBox1.setEnabled(false);
        jComboBox1.setFocusable(false);

        jLabel1.setText("Название");

        boxName.setEditable(false);
        boxName.setFocusable(false);

        jLabel2.setText("Кассы");

        boxTerminals.setEditable(false);
        boxTerminals.setFocusable(false);
        boxTerminals.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boxTerminalsMouseClicked(evt);
            }
        });

        jLabel3.setText("Служебный стартовый индекс");

        btnOk.setText("ОК");
        btnOk.setEnabled(false);
        btnOk.setFocusable(false);
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnCancel.setText("Отмена");
        btnCancel.setEnabled(false);
        btnCancel.setFocusable(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel4.setText("Файл данных");

        boxFile.setEditable(false);
        boxFile.setFocusable(false);

        btnFile.setText("...");
        btnFile.setEnabled(false);
        btnFile.setFocusable(false);
        btnFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(boxName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(boxTerminals, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(boxFile, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFile, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnOk)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancel))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxTerminals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnOk))
                .addContainerGap())
        );

        btnEdit.setText("/");
        btnEdit.setFocusable(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnDel)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnDel)
                            .addComponent(btnEdit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private boolean autorization() {
        String password = JOptionPane.showInputDialog(this, "Введите пароль:", "Разблокировка", JOptionPane.QUESTION_MESSAGE);
        if (password != null && password.equals("1234")) {
            return true;
        } else {
            jLabel5.setText("Неверный пароль");
            return false;
        }
    }
    
    public int isModified() {
        return isModified;
    }
    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(autorization()) turnState(ADD);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        turnState(NORMAL);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileActionPerformed
        // choose file configuration
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("./"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Файлы Microsoft Excel 2007*", "xlsx"));

        if (fileChooser.showDialog(this, "Открыть файл Excel...") == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            // -> need check repeats file
            try {
                Parser parser = new Parser(file);

                // reading all products
//                ArrayList<String> terminals = new ArrayList();
//                terminals.addAll(Arrays.asList(termGrpsTerminals[tGrpNum].split("-")));
//                terminalGroup = new TerminalGroup("default", terminals);

                DayOfWeek[] days = configuration.get(jList1.getSelectedIndex()).getDaysOfWeek();

                for (int i = 0; i < days.length; i++) {

                    // groups name reading. It same for any day.
                    Group[] groups = new Group[8];

                    int k = 0;
                    for (String name : parser.getGroupsNames()) {
                        groups[k] = new Group(name);
                        k++;
                    }

                    for (int j = 0; j < groups.length; j++) {
                        ArrayList<Product> products = new ArrayList();

                        for (String name : parser.getProducts(i, j)) {
                            products.add(new Product(name.split("::")[1], name.split("::")[0], "", Collections.EMPTY_LIST));
                        }

                        Subgroup[] subgroups;
                        if (products.size() % 20 > 0) {
                            subgroups = new Subgroup[(products.size() / 20) + 1];
                        } else {
                            subgroups = new Subgroup[products.size() / 20];
                        }

                        String[] subgroupsNames = parser.getSubgroupNames(i, j);
                        
                        for (int s = 0; s < subgroups.length; s++) {
                            //subgroups[s] = new Subgroup(String.valueOf(s + 1), idisp.getNextFreeIndex(i), Collections.EMPTY_LIST);
                            subgroups[s] = new Subgroup(subgroupsNames[s], 
                                    idisp.getNextFreeIndex(i, Integer.parseInt(String.valueOf(jComboBox1.getSelectedItem()).substring(1, 4))), 
                                    Collections.EMPTY_LIST);
                        }

                        for (int g = 0; g < products.size(); g++) {
                            subgroups[g / 20].addProduct(products.get(g));
                        }
                        // =====================

                        for (Subgroup sgrp : subgroups) {
                            groups[j].addSubgroup(sgrp);
                        }
                    }

                    days[i].deleteAllGroups();
                    for (Group grp : groups) {
                        days[i].addGroup(grp);
                    }
                }

                boxFile.setText(file.getCanonicalPath());

            } catch (FileNotFoundException ex) {
                System.err.println("File not found");
            } catch (IOException ex) {
                System.err.println("Other IO Exception");
            }
            isModified = CONTENT_CHANGED;
        }
    }//GEN-LAST:event_btnFileActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        clear();
        if (jList1.getSelectedIndex() != -1) {
            TerminalGroup tGrp = configuration.get(jList1.getSelectedIndex());
            boxName.setText(tGrp.toString());
            boxTerminals.setText(tGrp.getTerminalsAsString());
            jComboBox1.setSelectedItem(tGrp.getStartIndex());
            btnFile.setEnabled(true);
        }
    }//GEN-LAST:event_jList1ValueChanged

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        if (listModel.contains(boxName.getText())) {
            jLabel5.setText("Отдел с таким именем существует");
        } else if (boxName.getText().isEmpty() || boxTerminals.getText().isEmpty() || jComboBox1.getSelectedIndex() == 0) {
            jLabel5.setText("Заполните все поля");
        } else {
            TerminalGroup tGrp = new TerminalGroup(boxName.getText(), boxTerminals.getText(), jComboBox1.getSelectedItem().toString());
            configuration.add(tGrp);
            listModel.addElement(tGrp);
            turnState(NORMAL);
            isModified = isModified == 0 ? DEPARTMENTS_CHANGED : CONTENT_CHANGED;
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        if (autorization()) {
            int selectedIndex = jList1.getSelectedIndex();
            int response = JOptionPane.showConfirmDialog(this,
                    listModel.get(selectedIndex).toString() + "\nУдалить этот кассовый отдел?",
                    "Удаление отдела",
                    JOptionPane.OK_CANCEL_OPTION);
            if (response == JOptionPane.OK_OPTION) {
                configuration.remove(selectedIndex);
                listModel.remove(selectedIndex);
            }
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void boxTerminalsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boxTerminalsMouseClicked
        if(boxTerminals.isFocusable())
            boxTerminals.setText(AddingTerminals.showAddTerminalsDialog());
    }//GEN-LAST:event_boxTerminalsMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        //if(autorization()) return;
    }//GEN-LAST:event_btnEditActionPerformed

    private void clear() {
        boxName.setText("");
        boxTerminals.setText("");
        boxFile.setText("");
        jComboBox1.setSelectedIndex(0);
        jLabel5.setText("");
        btnFile.setEnabled(false);
    }
    
    private void turnState(int STATE) {
        switch(STATE) {
            case NORMAL:
                clear();
                jList1.setEnabled(true);
                
                btnAdd.setEnabled(true);
                btnDel.setEnabled(true);
                btnEdit.setEnabled(true);
                btnOk.setEnabled(false);
                btnCancel.setEnabled(false);
                btnFile.setEnabled(true);
                
                boxName.setFocusable(false);
                boxName.setEditable(false);
                boxTerminals.setFocusable(false);
                
                jComboBox1.setEnabled(false);
                break;
                
            case ADD:
                clear();
                jList1.setEnabled(false);
                
                btnAdd.setEnabled(false);
                btnDel.setEnabled(false);
                btnEdit.setEnabled(false);
                btnOk.setEnabled(true);
                btnCancel.setEnabled(true);
                btnFile.setEnabled(false);
                
                boxName.setFocusable(true);
                boxName.setEditable(true);
                boxTerminals.setFocusable(true);
                
                jComboBox1.setEnabled(true);
                break;
                
            case EDIT:
                break;
        }
    }
    
    /**
     * @param args the command line arguments
     */
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField boxFile;
    private javax.swing.JTextField boxName;
    private javax.swing.JTextField boxTerminals;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFile;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
