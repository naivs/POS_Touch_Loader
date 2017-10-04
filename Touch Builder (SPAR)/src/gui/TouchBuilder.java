package gui;

import data.Primary;
import graphics.Monitor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan
 */
public class TouchBuilder extends javax.swing.JFrame {

// FIELDS
    //private static final Long START_TIME = 1490076286210L;//eqd fe
    //ddaf owrgpwqrgj ewporijew rpoijewoiewp
    private static final Long CURRENT_TIME = System.currentTimeMillis();

    private final Primary primary;
    private final DefaultListModel listModel = new DefaultListModel();

    // FLAGS
    protected boolean isModified;
    //private boolean isSaved;
    /*
    * 0 - только текст
    * 1 - только картинки
    * 2 - все
     */
    private int cellPattern = 1;
    private int displayMode = 2;

// CONSTRUCTOR
    public TouchBuilder() {

        primary = new Primary();
        initComponents();
        categoryComboBox.setSelectedIndex(0);
        subCategoryList.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        textsRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        picturesRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        bothRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        patternMenuItem2 = new javax.swing.JMenuItem();
        jPanel2 = new Monitor();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        subCategoryList = new javax.swing.JList();
        categoryComboBox = new javax.swing.JComboBox();
        modifyButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        saveMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem5 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenu3.setText("Параметры");

        textsRadioButtonMenuItem.setText("Подписи");
        textsRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textsRadioButtonMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(textsRadioButtonMenuItem);

        picturesRadioButtonMenuItem.setText("Изображения");
        picturesRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                picturesRadioButtonMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(picturesRadioButtonMenuItem);

        bothRadioButtonMenuItem.setSelected(true);
        bothRadioButtonMenuItem.setText("Все");
        bothRadioButtonMenuItem.setToolTipText("");
        bothRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bothRadioButtonMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(bothRadioButtonMenuItem);
        jMenu3.add(jSeparator2);

        patternMenuItem2.setText("Выбор шаблона");
        patternMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patternMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(patternMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Touch Builder v1.0");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel2.setPreferredSize(new java.awt.Dimension(555, 384));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 384, Short.MAX_VALUE)
        );

        jLabel23.setText("ПОД-КАТЕГОРИЯ:");

        jScrollPane2.setPreferredSize(new java.awt.Dimension(554, 385));

        subCategoryList.setModel(listModel);
        subCategoryList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                subCategoryListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(subCategoryList);

        categoryComboBox.setModel(new DefaultComboBoxModel(primary.getDynaButtonsNames()));
        categoryComboBox.setPreferredSize(new java.awt.Dimension(554, 26));
        categoryComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryComboBoxActionPerformed(evt);
            }
        });

        modifyButton.setText("Изменить");
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("КАТЕГОРИЯ:");

        jMenu2.setText("Файл");

        saveMenuItem.setText("Сохранить");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(saveMenuItem);
        jMenu2.add(jSeparator1);

        exitMenuItem5.setText("Выход");
        exitMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(exitMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Справка");

        jMenuItem3.setText("О программе");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                        .addComponent(categoryComboBox, 0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel23))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(modifyButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void categoryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryComboBoxActionPerformed

        listModel.clear();

        String[] pDButtonsIndex = new String[8];

        for (int i = 0; i < 8; i++) {
            pDButtonsIndex[i] = primary.getPdButtonsNumbers()[(i) + 8 * (categoryComboBox.getSelectedIndex())];
        }

        int[] free = primary.getFreePlu(pDButtonsIndex);

        String text;

        for (int i = 0; i < pDButtonsIndex.length; i++) {

            text = primary.getPdButtonsNames()[((categoryComboBox.getSelectedIndex()) * 8) + i];

            listModel.addElement("*" + pDButtonsIndex[i] + ": " + text + " (" + (20 - free[i]) + "/20)");
        }

        subCategoryList.setSelectedIndex(0);
    }//GEN-LAST:event_categoryComboBoxActionPerformed

    private void subCategoryListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_subCategoryListValueChanged
        // TODO add your handling code here:

        //jTextField41.setText(jList2.getSelectedValue());
        if (!subCategoryList.isSelectionEmpty()) {

            ((Monitor) jPanel2).display(primary.getPdButtons().get(subCategoryList.getSelectedValue().substring(1, 4)), cellPattern, displayMode);
        }
    }//GEN-LAST:event_subCategoryListValueChanged

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        primary.writeData();

        int categorySelectedIndex = categoryComboBox.getSelectedIndex();
        int subCategorySelectedIndex = subCategoryList.getSelectedIndex();

        for (int i = 0; i < 8; i++) {
            categoryComboBox.setSelectedIndex(i);

            for (int j = 0; j < 8; j++) {
                subCategoryList.setSelectedIndex(j);

                BufferedImage bi = new BufferedImage(jPanel2.getSize().width, jPanel2.getSize().height, BufferedImage.TYPE_INT_ARGB);
                Graphics g = bi.createGraphics();
                jPanel2.paint(g);
                g.dispose();
                try {
                    ImageIO.write(bi, "gif", new File("D:/TOUCH_SERVER/cafe/TCH_X" + subCategoryList.getSelectedValue().substring(1, 4) + ".GIF"));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        categoryComboBox.setSelectedIndex(categorySelectedIndex);
        subCategoryList.setSelectedIndex(subCategorySelectedIndex);

        ((Monitor) jPanel2).display(primary.getPdButtons().get(subCategoryList.getSelectedValue().substring(1, 4)), cellPattern, displayMode);

        //isSaved = true;
        isModified = false;
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void exitMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItem5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_exitMenuItem5ActionPerformed

    private void textsRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textsRadioButtonMenuItemActionPerformed
        if (picturesRadioButtonMenuItem.isSelected() || bothRadioButtonMenuItem.isSelected()) {
            picturesRadioButtonMenuItem.setSelected(false);
            bothRadioButtonMenuItem.setSelected(false);
            displayMode = 0;
            ((Monitor) jPanel2).display(primary.getPdButtons().get(subCategoryList.getSelectedValue().substring(1, 4)), cellPattern, displayMode);
        }
    }//GEN-LAST:event_textsRadioButtonMenuItemActionPerformed

    private void bothRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bothRadioButtonMenuItemActionPerformed
        if (picturesRadioButtonMenuItem.isSelected() || textsRadioButtonMenuItem.isSelected()) {
            picturesRadioButtonMenuItem.setSelected(false);
            textsRadioButtonMenuItem.setSelected(false);
            displayMode = 2;
            ((Monitor) jPanel2).display(primary.getPdButtons().get(subCategoryList.getSelectedValue().substring(1, 4)), cellPattern, displayMode);
        }
    }//GEN-LAST:event_bothRadioButtonMenuItemActionPerformed

    private void picturesRadioButtonMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_picturesRadioButtonMenuItemActionPerformed
        if (bothRadioButtonMenuItem.isSelected() || textsRadioButtonMenuItem.isSelected()) {
            bothRadioButtonMenuItem.setSelected(false);
            textsRadioButtonMenuItem.setSelected(false);
            displayMode = 1;
            ((Monitor) jPanel2).display(primary.getPdButtons().get(subCategoryList.getSelectedValue().substring(1, 4)), cellPattern, displayMode);
        }
    }//GEN-LAST:event_picturesRadioButtonMenuItemActionPerformed

    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed

        Edit dialog = new Edit(this, true, primary.getPdButton(subCategoryList.getSelectedValue().substring(1, 4)));
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                //((Monitor) jPanel2).display(primary.getPdButtons().get(subCategoryList.getSelectedValue().substring(1, 4)));
                //System.out.println("cancel...");
            }

            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (isModified == true) {
                    primary.sortButtonsNames(subCategoryList.getSelectedValue().substring(1, 4));
                    ((Monitor) jPanel2).display(primary.getPdButtons().get(subCategoryList.getSelectedValue().substring(1, 4)), cellPattern, displayMode);
                }

                //isModified = true;
                //isSaved = false;
                //System.out.println("ok...");
            }
        });

        dialog.setVisible(true);
    }//GEN-LAST:event_modifyButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (isModified) {
            JOptionPane jop = new JOptionPane("Сохранить изменения?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
            JDialog dialog = jop.createDialog(this, "Сохранить");
            dialog.show();
            /*return values 0 - ok
            *               1 - no
            *               2 - cancel
             */

            int returnValue = Integer.parseInt(jop.getValue().toString());

            switch (returnValue) {
                case 0:
                    primary.writeData();

                    for (int i = 0; i < 8; i++) {
                        categoryComboBox.setSelectedIndex(i);

                        for (int j = 0; j < 8; j++) {
                            subCategoryList.setSelectedIndex(j);

                            BufferedImage bi = new BufferedImage(jPanel2.getSize().width, jPanel2.getSize().height, BufferedImage.TYPE_INT_ARGB);
                            Graphics g = bi.createGraphics();
                            jPanel2.paint(g);
                            g.dispose();
                            try {
                                ImageIO.write(bi, "gif", new File("D:/TOUCH_SERVER/cafe/TCH_X" + subCategoryList.getSelectedValue().substring(1, 4) + ".GIF"));
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    System.exit(0);

                case 1:
                    System.exit(0);

                case 2:

            }
        }
    }//GEN-LAST:event_formWindowClosed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JOptionPane jop = new JOptionPane("Разработчик: компания \"ЦТО ВЕСЫ\" \nНаумов Иван\nnaumov@vesynn.ru", JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = jop.createDialog(this, "О программе");
        dialog.show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void patternMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patternMenuItem2ActionPerformed

        final SetPattern dialog = new SetPattern(this, true, cellPattern);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                //((Monitor) jPanel2).display(primary.getPdButtons().get(subCategoryList.getSelectedValue().substring(1, 4)));
                //System.out.println("cancel...");
            }

            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {

                cellPattern = dialog.getCellPattern();
                ((Monitor) jPanel2).display(primary.getPdButtons().get(subCategoryList.getSelectedValue().substring(1, 4)), cellPattern, displayMode);

                System.out.println("ok... " + cellPattern);
            }
        });

        dialog.setVisible(true);
    }//GEN-LAST:event_patternMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            java.util.logging.Logger.getLogger(TouchBuilder.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        } catch (InstantiationException e1) {
            java.util.logging.Logger.getLogger(TouchBuilder.class.getName()).log(java.util.logging.Level.SEVERE, null, e1);
        } catch (IllegalAccessException e2) {
            java.util.logging.Logger.getLogger(TouchBuilder.class.getName()).log(java.util.logging.Level.SEVERE, null, e2);
        } catch (javax.swing.UnsupportedLookAndFeelException e3) {
            java.util.logging.Logger.getLogger(TouchBuilder.class.getName()).log(java.util.logging.Level.SEVERE, null, e3);
        }
        //</editor-fold>

        //</editor-fold>
        //System.out.println(System.currentTimeMillis());
        // lisence (one week)
//        if ((CURRENT_TIME - START_TIME) > 2678400000L) {
//            JOptionPane.showMessageDialog(null, "System needs to update...");
//            //System.out.println("Истёк пробный период использования");
//            System.exit(1);
//        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TouchBuilder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButtonMenuItem bothRadioButtonMenuItem;
    private javax.swing.JComboBox<String> categoryComboBox;
    private javax.swing.JMenuItem exitMenuItem5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JButton modifyButton;
    private javax.swing.JMenuItem patternMenuItem2;
    private javax.swing.JRadioButtonMenuItem picturesRadioButtonMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JList<String> subCategoryList;
    private javax.swing.JRadioButtonMenuItem textsRadioButtonMenuItem;
    // End of variables declaration//GEN-END:variables
}
