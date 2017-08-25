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
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import utils.IndexDispatcher;

/**
 *
 * @author Ivan
 */
public class TGManager extends javax.swing.JDialog {

    private final ArrayList<TerminalGroup> terminalGroups;
    private String[] termGrpsNames;
    
    private final IndexDispatcher idisp = new IndexDispatcher();
    //private final TerminalNumberDispatcher tndisp = new TerminalNumberDispatcher(terminalGroups);

    /**
     * Creates new form TGManager
     *
     * @param parent
     * @param modal
     * @param terminalGroups
     */
    public TGManager(java.awt.Frame parent, boolean modal, ArrayList<TerminalGroup> terminalGroups) {
        super(parent, modal);
        this.terminalGroups = terminalGroups;
        initComponents();
        update();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 314));
        setSize(new java.awt.Dimension(470, 314));

        jButton1.setText("файл..");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("файл..");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("файл..");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("файл..");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("файл..");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("файл..");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("файл..");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setToolTipText("");
        jTextField1.setFocusable(false);
        jTextField1.setPreferredSize(new java.awt.Dimension(150, 24));
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setFocusable(false);
        jTextField2.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField3.setEditable(false);
        jTextField3.setFocusable(false);
        jTextField3.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField4.setEditable(false);
        jTextField4.setFocusable(false);
        jTextField4.setPreferredSize(new java.awt.Dimension(150, 24));
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4MouseClicked(evt);
            }
        });

        jTextField5.setEditable(false);
        jTextField5.setFocusable(false);
        jTextField5.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField6.setEditable(false);
        jTextField6.setFocusable(false);
        jTextField6.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField7.setEditable(false);
        jTextField7.setFocusable(false);
        jTextField7.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField8.setEditable(false);
        jTextField8.setFocusable(false);
        jTextField8.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField9.setEditable(false);
        jTextField9.setFocusable(false);
        jTextField9.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField10.setEditable(false);
        jTextField10.setFocusable(false);
        jTextField10.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField11.setEditable(false);
        jTextField11.setFocusable(false);
        jTextField11.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField12.setEditable(false);
        jTextField12.setFocusable(false);
        jTextField12.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField13.setEditable(false);
        jTextField13.setFocusable(false);
        jTextField13.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField14.setEditable(false);
        jTextField14.setFocusable(false);
        jTextField14.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField15.setEditable(false);
        jTextField15.setFocusable(false);
        jTextField15.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField16.setEditable(false);
        jTextField16.setFocusable(false);
        jTextField16.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField17.setEditable(false);
        jTextField17.setFocusable(false);
        jTextField17.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField18.setEditable(false);
        jTextField18.setFocusable(false);
        jTextField18.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField19.setEditable(false);
        jTextField19.setFocusable(false);
        jTextField19.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField20.setEditable(false);
        jTextField20.setFocusable(false);
        jTextField20.setPreferredSize(new java.awt.Dimension(150, 24));

        jTextField21.setEditable(false);
        jTextField21.setFocusable(false);
        jTextField21.setPreferredSize(new java.awt.Dimension(150, 24));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jCheckBox1.setText("Разблокировать");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void update() {
        termGrpsNames = new String[7];
        Arrays.fill(termGrpsNames, "");
        
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        jTextField16.setText("");
        jTextField17.setText("");
        jTextField19.setText("");
        jTextField20.setText("");
        
        for (int i = 0; i < this.terminalGroups.size(); i++) {
            termGrpsNames[i] = this.terminalGroups.get(i).toString();
            
            switch (i) {
                case 0:
                    jTextField1.setText(termGrpsNames[i]);
                    jTextField2.setText(this.terminalGroups.get(i).getTerminalsAsString());
                    break;
                case 1:
                    jTextField4.setText(termGrpsNames[i]);
                    jTextField5.setText(this.terminalGroups.get(i).getTerminalsAsString());
                    break;
                case 2:
                    jTextField7.setText(termGrpsNames[i]);
                    jTextField8.setText(this.terminalGroups.get(i).getTerminalsAsString());
                    break;
                case 3:
                    jTextField10.setText(termGrpsNames[i]);
                    jTextField11.setText(this.terminalGroups.get(i).getTerminalsAsString());
                    break;
                case 4:
                    jTextField13.setText(termGrpsNames[i]);
                    jTextField14.setText(this.terminalGroups.get(i).getTerminalsAsString());
                    break;
                case 5:
                    jTextField16.setText(termGrpsNames[i]);
                    jTextField17.setText(this.terminalGroups.get(i).getTerminalsAsString());
                    break;
                case 6:
                    jTextField19.setText(termGrpsNames[i]);
                    jTextField20.setText(this.terminalGroups.get(i).getTerminalsAsString());
                    break;
            }
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTextField1.getText().isEmpty() || jTextField2.getText().isEmpty()) {
            jLabel1.setText("Отдел №1 не создан!");
        } else {
            jTextField3.setText(openExcelFile(0));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jTextField4.getText().isEmpty() || jTextField5.getText().isEmpty()) {
            jLabel1.setText("Отдел №2 не создан!");
        } else {
            jTextField6.setText(openExcelFile(1));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTextField7.getText().isEmpty() || jTextField8.getText().isEmpty()) {
            jLabel1.setText("Отдел №3 не создан!");
        } else {
            jTextField9.setText(openExcelFile(2));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (jTextField10.getText().isEmpty() || jTextField11.getText().isEmpty()) {
            jLabel1.setText("Отдел №4 не создан!");
        } else {
            jTextField12.setText(openExcelFile(3));
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (jTextField13.getText().isEmpty() || jTextField14.getText().isEmpty()) {
            jLabel1.setText("Отдел №5 не создан!");
        } else {
            jTextField15.setText(openExcelFile(4));
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (jTextField16.getText().isEmpty() || jTextField17.getText().isEmpty()) {
            jLabel1.setText("Отдел №6 не создан!");
        } else {
            jTextField18.setText(openExcelFile(5));
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (jTextField19.getText().isEmpty() || jTextField20.getText().isEmpty()) {
            jLabel1.setText("Отдел №7 не создан!");
        } else {
            jTextField21.setText(openExcelFile(6));
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if(jCheckBox1.isSelected()) {
            String password = JOptionPane.showInputDialog(this,  "Введите пароль:", "Разблокировка", JOptionPane.QUESTION_MESSAGE);
            if(password != null && password.equals("1234")) {
                enableEditable(true);
            } else {
                jCheckBox1.setSelected(false);
                jLabel1.setText("Неверный пароль");
            }
        } else {
            enableEditable(false);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        if (jTextField1.isFocusable()) {
            if (!jTextField1.getText().isEmpty()) {
                deleteTerminalGroup(0);
            } else {
                addTerminalGroup();
            }
        }
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        if (jTextField4.isFocusable()) {
            if (!jTextField4.getText().isEmpty()) {
                deleteTerminalGroup(1);
            } else {
                addTerminalGroup();
            }
        }
    }//GEN-LAST:event_jTextField4MouseClicked

    private void addTerminalGroup() {
        String name = JOptionPane.showInputDialog(this, "Название:", "Название кассового отдела", JOptionPane.QUESTION_MESSAGE);
        if (Arrays.binarySearch(termGrpsNames, name) >= 0) {
            jLabel1.setText("Отдел с таким именем существует");
        } else {
            String terminals = AddingTerminals.showAddTerminalsDialog();
            if(terminals.isEmpty()) {
                jLabel1.setText("Нельзя создать отдел без касс");
            } else {
                terminalGroups.add(new TerminalGroup(name, terminals));
                update();
            }
        }
    }
    
    private void deleteTerminalGroup(int tGrp) {
        int response = JOptionPane.showConfirmDialog(this, "Удалить этот кассовый отдел?", "Кассовый отдел", JOptionPane.OK_CANCEL_OPTION);
        if (response == JOptionPane.OK_OPTION) {
            terminalGroups.remove(tGrp);
            update();
        }
    }
    
    private void enableEditable(boolean focusable) {
        jTextField1.setFocusable(focusable);
        jTextField2.setFocusable(focusable);
        jTextField4.setFocusable(focusable);
        jTextField5.setFocusable(focusable);
        jTextField7.setFocusable(focusable);
        jTextField8.setFocusable(focusable);
        jTextField10.setFocusable(focusable);
        jTextField11.setFocusable(focusable);
        jTextField13.setFocusable(focusable);
        jTextField14.setFocusable(focusable);
        jTextField16.setFocusable(focusable);
        jTextField17.setFocusable(focusable);
        jTextField19.setFocusable(focusable);
        jTextField20.setFocusable(focusable);
    }
    
    private String openExcelFile(int tGrpNum) {     
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

                DayOfWeek[] days = terminalGroups.get(tGrpNum).getDaysOfWeek();

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
                            subgroups[s] = new Subgroup(subgroupsNames[s], idisp.getNextFreeIndex(i), Collections.EMPTY_LIST);
                        }

                        for (int g = 0; g < products.size(); g++) {
                            subgroups[g / 20].addProduct(products.get(g));
                        }

//                        // subgroup picture saving
//                        for (int sg = 0; sg < subgroups.length; sg++) {
//
//                            ((Monitor) jPanel2).display(subgroups[sg].getProducts());
//                            BufferedImage img = new BufferedImage(jPanel2.getWidth(), jPanel2.getHeight(), BufferedImage.TYPE_INT_ARGB);
//                            jPanel2.paint(img.getGraphics());
//
//                            try {
//                                ImageIO.write(img, "gif", new File("resources/pic/" + i + "TCH_X" + subgroups[sg].getIndex() + ".GIF"));
//                            } catch (IOException ex) {
//
//                            }
//                        }
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

                return file.getCanonicalPath();

            } catch (FileNotFoundException ex) {
                System.err.println("File not found");
            } catch (IOException ex) {
                System.err.println("Other IO Exception");
            }
        }

        return "";
    } 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
