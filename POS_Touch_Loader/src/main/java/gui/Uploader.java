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
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
        SMBAuthentication in = communicator.getSmbAuth();
        jLabel1.setText(communicator.getLoadTime());
        smbClient = new SMBClient(Emulator.SERVER_IP, communicator.getSmbAuth());
        System.out.println(smbClient.testConnection());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(63, 63, 63)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // upload to server
        smbClient.clearShare();

        for (int a = 0; a < 7; a++) {
            if (la.dayIsLoad(a)) {
                // -> create day folder
                smbClient.createFolder("day" + a + "/");
                // -> create cafe folder
                smbClient.createFolder("day" + a + "/cafe/");
                // -> copy images
                try {
                    for (File img : new File("resources/pic/day" + a).listFiles()) {
                        smbClient.putFile(img, "day" + a + "/cafe/" + img.getName());
                    }
                    // copying PLUREF.DAT
                    File ref = new RefGenerator(configuration, a).getFile();
                    smbClient.putFile(ref, "day" + a + "/S_PLUREF.DAT");

                    for (int b = 0; b < configuration.size(); b++) {
                        File par = new ParGenerator(configuration, a, b).getFile();

                        String terminals = "";
                        for (String num : configuration.get(b).getTerminalsAsString().split(":")) {
                            terminals += num + "-";
                        }
                        terminals = terminals.substring(0, terminals.length() - 1);
                        smbClient.putFile(par, "day" + a + "/P_REGPAR.DAT" + terminals);
                    }
                } catch (MalformedURLException ex) {
                    System.err.println("Wrong destenation URL. " + ex.getMessage());
                } catch (IOException ex) {
                    System.err.println("I/O exception while P_REGPAR.DAT or S_PLUREF.DAT uploading. " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        communicator.shutDown();
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables
}
