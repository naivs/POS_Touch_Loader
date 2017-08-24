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

import data.TerminalGroup;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import models.UploaderTableModel;
import network.SMBAuthentication;
import network.SMBClient;
import network.ServerCommunicator;
import utils.LoadAnalyzer;
import utils.ParGenerator;
import utils.RefGenerator;

/**
 *
 * @author Ivan
 */
public class Uploader extends javax.swing.JDialog {

    private final ArrayList<TerminalGroup> configuration;
    LoadAnalyzer la;
    ServerCommunicator communicator;
    SMBClient smbClient;

    /**
     * Creates new form Uploader
     *
     * @param parent
     * @param modal
     * @param configuration
     */
    public Uploader(java.awt.Frame parent, boolean modal, ArrayList<TerminalGroup> configuration) {
        super(parent, modal);
        initComponents();

        this.configuration = configuration;

        String[] colNames = new String[configuration.size()];
        for (int i = 0; i < colNames.length; i++) {
            colNames[i] = configuration.get(i).toString();
        }
        la = new LoadAnalyzer(configuration);
        jTable1.setModel(new UploaderTableModel(colNames, la.getDaysLoad()));
        
        /*
        -> test connection to server demon
        -> get server parameters
        -> get SMB auth data
        -> restrict uploading if "fire time" close.
        -> restrict uploading if other client connected (optional)
        -> restrict uploading if any day is overloaded
        -> test SMB share available
        */
        
        communicator = new ServerCommunicator();
//        try {
//            Thread.sleep(1000L);
//        } catch (InterruptedException ex) {
//            System.out.println("interrupted" + ex.getMessage());
//        }
        SMBAuthentication in = communicator.getSmbAuth();
        jLabel1.setText(communicator.getLoadTime());
        smbClient = new SMBClient(Emulator.SERVER_IP, communicator.getSmbAuth());
        System.out.println(smbClient.testConnection());
        try {
            System.out.println(smbClient.listFiles());
            smbClient.createFolder("test/");
            System.out.println("\n");
            System.out.println(smbClient.listFiles());
        } catch (Exception e) {
            System.out.println("fuckfuckfuck");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Загрузка параметров на сервер");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("Адрес:");

        jLabel2.setText("Пользователь:");

        jLabel3.setText("Пароль:");

        jButton1.setText("Соединение");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Выгрузить");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable1);

        jTextPane2.setEditable(false);
        jTextPane2.setBorder(null);
        jTextPane2.setText("* В таблице представлена информация о заполненности кассовых групп продуктами в каждый день недели. Информация выводится в процентном эквиваленте.");
        jTextPane2.setFocusable(false);
        jScrollPane3.setViewportView(jTextPane2);

        jLabel6.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(80, 80, 80))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPasswordField1, jTextField1, jTextField2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPasswordField1, jTextField1, jTextField2});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        char[] pass = jPasswordField1.getPassword();
//        smb = new SMBClient(jTextField1.getText(), jTextField2.getText(), new String(pass, 0, pass.length));
//
//        try {
//            smb.listFiles();
//            jLabel6.setForeground(Color.green);
//            jLabel6.setText("[OK]");
//        } catch (IOException e) {
//            jLabel6.setForeground(Color.red);
//            jLabel6.setText("[FALSE]");
//        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        // upload to server
//        smb.clearFolder();
//
//        for (int a = 0; a < 7; a++) {
//            if (la.dayIsLoad(a)) {
//
//                smb.createFolder("d_" + String.valueOf(a));
//                smb.createFolder("d_" + String.valueOf(a) + "/cafe");
//
//                // put images
//                File[] list = new File("res/pic").listFiles();
//
//                for (File file : list) {
//                    if (file.getName().startsWith(String.valueOf(a) + "TCH")) {
//                        try {
//                            smb.putImage("d_" + String.valueOf(a) + "/cafe", file);
//                        } catch (IOException e) {
//                            System.err.println("Can't upload file: " + file.getPath());
//                        }
//                    }
//                }
//
//                // put pluref
//                RefGenerator rgen = new RefGenerator(configuration, a);
//
//                BufferedWriter bw;
//                try {
//                    bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ref.tmp"), "Cp866"));
//
//                    for (String line : rgen.getData()) {
//                        bw.append(line + "\r\n");
//                    }
//
//                    bw.flush();
//                    bw.close();
//
//                    File ref = new File("ref.tmp");
//                    smb.putFile("d_" + String.valueOf(a) + "/S_PLUREF.DAT", ref);
//
//                    ref.delete();
//
//                } catch (UnsupportedEncodingException e) {
//                    System.out.println(e.getMessage());
//                } catch (FileNotFoundException e) {
//                    System.out.println(e.getMessage());
//                } catch (IOException e) {
//                    System.out.println(e.getMessage());
//                }
//
//                for (int b = 0; b < configuration.size(); b++) {
//
//                    if (la.isLoad(b, a)) {
//                        //smb.createFolder("d_" + String.valueOf(a) + "/" + configuration.get(b).toString());
//
//                        // put pardat's
//                        ParGenerator pgen = new ParGenerator(configuration, a, b);
//
//                        BufferedWriter bw1;
//                        try {
//                            bw1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("par.tmp"), "Cp866"));
//
//                            for (String line : pgen.getData()) {
//                                bw1.append(line + "\r\n");
//                            }
//
//                            bw1.flush();
//                            bw1.close();
//
//                            File par = new File("par.tmp");
//                            smb.putFile("d_" + String.valueOf(a) + "/P_REGPAR.DAT" + configuration.get(b).getTerminalsAsString(), par);
//
//                            par.delete();
//
//                        } catch (UnsupportedEncodingException e) {
//                            System.out.println(e.getMessage());
//                        } catch (FileNotFoundException e) {
//                            System.out.println(e.getMessage());
//                        } catch (IOException e) {
//                            System.out.println(e.getMessage());
//                        }
//
//                    }
//                }
//
//            }
//        }
//
////            //create termGroups
////            if (la.isLoad(a)) {
////                smb.createFolder(configuration.get(a).toString());
////
////                for (int b = 0; b < configuration.get(a).getDaysOfWeek().length; b++) {
////
////                    //create dayOfWeeks
////                    if (la.isLoad(a, b)) {
////                        smb.createFolder(configuration.get(a).toString() + "/" + configuration.get(a).getDaysOfWeek()[b].toString());
////
////                        // put pluref
////                        // put par
////                        
////                        
////                        smb.createFolder(configuration.get(a).toString() + "/" + configuration.get(a).getDaysOfWeek()[b].toString() + "/cafe");
////                        // copy img to cafe
////                        File cafe = new File("res/pic");
////                        File[] list = cafe.listFiles();
////
////                        for (int i = 0; i < list.length; i++) {
////                            if (list[i].getName().startsWith(String.valueOf(b) + "TCH")) {
////                                try {
////                                    smb.putFile(configuration.get(a).toString() + "/" + configuration.get(a).getDaysOfWeek()[b].toString() + "/cafe", list[i]);
////                                } catch (IOException e) {
////                                    System.err.println("Can't upload file: " + list[i].getPath());
////                                }
////                            }
////                        }
////                    }
////                }
////            }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        communicator.shutDown();
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables
}
