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

import data.*;
import io.ConfigurationReader;
import io.ConfigurationWriter;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import utils.Monitor;

/**
 *
 * @author Ivan
 */
public class Emulator extends javax.swing.JFrame {
    public static String SERVER_IP;
    public static int PORT;
    
    private JButton[] touch = new JButton[8];
    private int level = 2;
    private List<JMenu> groupsMenu = new ArrayList<>();
    private List<JRadioButtonMenuItem> daysButtons = new ArrayList<>();
    private final ButtonGroup dayButtonsGroup = new ButtonGroup();
    
    private List<TerminalGroup> terminalGroups;
    private int selectedTermGroup;
    private int selectedDay;
    private int selectedGroup;

    /**
     * Creates new form Emulator
     */
    public Emulator() {
        terminalGroups = new ArrayList<>();
        try {
            terminalGroups = new ConfigurationReader().read();
        } catch (SAXException e) {
            // empty or corrupted
            System.err.println(e.getMessage() + "\nФайл: \"resources/configuration.xml\" пуст или поврежден. Будет создана новая конфигурация");
            openPosDepartmentManager();
        } catch (IOException e) {
            // no file
            System.err.println(e.getMessage() + "\nФайл: \"resources/configuration.xml\" не найден. Будет создана новая конфигурация");
            openPosDepartmentManager();
        }
        
        initComponents();
        
        touch[0] = jButton1;
        touch[1] = jButton2;
        touch[2] = jButton3;
        touch[3] = jButton4;
        touch[4] = jButton5;
        touch[5] = jButton6;
        touch[6] = jButton7;
        touch[7] = jButton8;
        
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
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new Monitor();
        jButton9 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuDepartments = new javax.swing.JMenuItem();
        menuUpload = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        menuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Emulator");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        setSize(new java.awt.Dimension(600, 500));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Меню"));

        jButton1.setBackground(new java.awt.Color(0, 102, 0));
        jButton1.setFocusable(false);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 102, 0));
        jButton2.setFocusable(false);
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 0));
        jButton3.setFocusable(false);
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 0));
        jButton4.setFocusable(false);
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 102, 0));
        jButton5.setFocusable(false);
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 102, 0));
        jButton6.setFocusable(false);
        jButton6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 102, 0));
        jButton7.setFocusable(false);
        jButton7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(0, 102, 0));
        jButton8.setFocusable(false);
        jButton8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6)
                    .addComponent(jButton5)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton8)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Ассортимент"));
        jPanel3.setToolTipText("");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.setPreferredSize(new java.awt.Dimension(555, 384));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        jButton9.setBackground(new java.awt.Color(255, 51, 51));
        jButton9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton9.setText("Назад");
        jButton9.setFocusable(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(23, 15));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(23, 15));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(23, 15));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4});

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jMenu1.setText("Главное меню");

        menuDepartments.setText("Кассовые отделы");
        menuDepartments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDepartmentsActionPerformed(evt);
            }
        });
        jMenu1.add(menuDepartments);

        menuUpload.setText("Выгрузить на сервер...");
        menuUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUploadActionPerformed(evt);
            }
        });
        jMenu1.add(menuUpload);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Кассовый отдел...");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Справка");

        menuAbout.setText("О программе");
        jMenu3.add(menuAbout);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(256, 256, 256))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(826, 626));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void update() {
        lockButtons(true);
        jMenu2.removeAll();
        for(int i = 0; i < terminalGroups.size(); i++) {
            groupsMenu.add(new JMenu(terminalGroups.get(i).toString()));
            jMenu2.add(groupsMenu.get(i));
            
            for (DayOfWeek daysOfWeek : terminalGroups.get(i).getDaysOfWeek()) {
                JRadioButtonMenuItem jrmi = new JRadioButtonMenuItem(daysOfWeek.toString());
                daysButtons.add(jrmi);
                groupsMenu.get(i).add(jrmi);
                dayButtonsGroup.add(jrmi);
                jrmi.addActionListener((ActionEvent e) -> {
                    int number = -1;
                    for (int j = 0; j < daysButtons.size(); j++) {
                        if (e.getSource().equals(daysButtons.get(j))) {
                            number = j;
                        }
                    }
                    selectedTermGroup = number / 7;
                    selectedDay = number % 7;
                    level = 2;
                    setButtonsLabels();
                    ((Monitor) jPanel2).clear();
                    lockButtons(false);
                });
            }
        }
    }
    
    private void lockButtons(boolean isLock) {
        for(JButton btn : touch) {
            btn.setEnabled(!isLock);
        }
        jButton9.setEnabled(!isLock);
    }
    
    private void setButtonsLabels() {
        if (level == 2) {
            jLabel2.setText("Кассовый отдел: " + terminalGroups.get(selectedTermGroup).toString());
            jLabel3.setText("День: " + terminalGroups.get(selectedTermGroup).getDaysOfWeek()[selectedDay].toString());
            String[] groupNames = terminalGroups.get(selectedTermGroup).getDaysOfWeek()[selectedDay].getGroupsAsStringArray();
            for (int i = 0; i < touch.length; i++) {
                touch[i].setText("<html>" + groupNames[i].replaceAll("::", "<br>") + "</html>");
            }
        } else {
            String[] subgroupNames = terminalGroups.get(selectedTermGroup).getDaysOfWeek()[selectedDay].getGroup(selectedGroup).getSubgroupsAsStringArray();
            for (int i = 0; i < subgroupNames.length; i++) {
                touch[i].setText("<html>" + subgroupNames[i].replaceAll("::", "<br>") + "</html>");
            }
        }
    }
    
    private void menuUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUploadActionPerformed
        Uploader uploader = new Uploader(this, true, (ArrayList) terminalGroups);
            uploader.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    
                }
            });
            uploader.setVisible(true);
    }//GEN-LAST:event_menuUploadActionPerformed

    private void menuDepartmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDepartmentsActionPerformed
        openPosDepartmentManager();
    }//GEN-LAST:event_menuDepartmentsActionPerformed

    private void touchActionHub(int button) {
        switch (button) {
            case 1:
                touchAction(button);
                break;
            case 2:
                touchAction(button);
                break;
            case 3:
                touchAction(button);
                break;
            case 4:
                touchAction(button);
                break;
            case 5:
                touchAction(button);
                break;
            case 6:
                touchAction(button);
                break;
            case 7:
                touchAction(button);
                break;
            case 8:
                touchAction(button);
                break;
        }
    }
    
    private void touchAction(int button) {
        if (level == 2) {
            System.out.println("Button GROUP " + button + " fired...");
            level = 1;
            selectedGroup = button-1;
            setButtonsLabels();
        } else if(level == 1) {
            System.out.println("Button SUB-GROUP " + button + " fired...");
            Product[] products = terminalGroups.get(selectedTermGroup).getDaysOfWeek()[selectedDay].getGroup(selectedGroup).getSubgroup(button-1).getProducts();
            ((Monitor) jPanel2).display(products);

            System.out.println("\n====================");
            for (int i = 0; i < products.length; i++) {
                if (products[i] != null) {
                    System.out.println(i + 1 + ") " + products[i].getName() + " - " + products[i].getPlu());
                }
            }
            System.out.println("====================");
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        touchActionHub(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        touchActionHub(2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        touchActionHub(3);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        touchActionHub(4);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        touchActionHub(5);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        touchActionHub(6);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        touchActionHub(7);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        touchActionHub(8);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        ((Monitor) jPanel2).clear();
        level = 2;
        setButtonsLabels();
    }//GEN-LAST:event_jButton9ActionPerformed

    void delete(File f) throws IOException {
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                delete(c);
            }
        }
        if (!f.delete()) {
            throw new FileNotFoundException("Failed to delete file: " + f);
        }
    }
    
    private void openPosDepartmentManager() {
        PosDepartmentManager manager = new PosDepartmentManager(this, true, (ArrayList) terminalGroups);
        manager.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                if (manager.isModified() == PosDepartmentManager.CONTENT_CHANGED) {
                    Thread t = new Thread() {
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(null, "", "Идет сохранение...", JOptionPane.PLAIN_MESSAGE);
                        }
                    };
                    t.start();
                    // configuration writing
                    try {
                        new ConfigurationWriter().write(terminalGroups);
                    } catch (TransformerException ex) {
                        System.err.println("TransformerException occured while configuration saving! " + ex.getMessage());
                    }

                    File picFolder = new File("resources/pic");
                    try {
                        delete(picFolder);
                    } catch (IOException ex) {
                        System.out.println("IO Except " + ex.toString());
                    }
                    picFolder.mkdir();

                    for (int b = 0; b < terminalGroups.size(); b++) {
                        for (int a = 0; a < terminalGroups.get(b).getDaysOfWeek().length; a++) {
                            if (terminalGroups.get(b).getDaysOfWeek()[a].getGroupCount() > 0) {
                                // clear pic folder                            
                                // creation day dirs
                                // saving all images into it
                                File anotherDay = new File("resources/pic/day" + a);
                                anotherDay.mkdir();
                                for (int c = 0; c < terminalGroups.get(b).getDaysOfWeek()[a].getGroupCount(); c++) {
                                    for (int d = 0; d < terminalGroups.get(b).getDaysOfWeek()[a].getGroup(c).getSubgroupCount(); d++) {
                                        Product[] products = terminalGroups.get(b).getDaysOfWeek()[a].getGroup(c).getSubgroup(d).getProducts();
                                        ((Monitor) jPanel2).display(products);

                                        BufferedImage bi = new BufferedImage(jPanel2.getSize().width, jPanel2.getSize().height, BufferedImage.TYPE_INT_ARGB);
                                        Graphics g = bi.createGraphics();
                                        jPanel2.paint(g);
                                        g.dispose();
                                        try {
                                            File pic = new File(anotherDay.getAbsolutePath() + "/TCH_X" + terminalGroups.get(b).getDaysOfWeek()[a].getGroup(c).getSubgroup(d).getIndex() + ".GIF");
                                            ImageIO.write(bi, "GIF", pic);
                                        } catch (IOException ex) {
                                            System.out.println(ex.getMessage());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    t.interrupt();
                } else if(manager.isModified() == PosDepartmentManager.DEPARTMENTS_CHANGED) {
                    try {
                        new ConfigurationWriter().write(terminalGroups);
                    } catch (TransformerException ex) {
                        System.err.println("TransformerException occured while configuration saving!");
                    }
                }
            }
        });
        manager.setVisible(true);
    }

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Emulator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        Properties properties = new Properties();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("touchLoader.conf")));
            properties.load(reader);
            SERVER_IP = properties.getProperty("serverIP");
            PORT = Integer.parseInt(properties.getProperty("port"));
            System.out.println("Server IP: " + SERVER_IP + ":" + PORT);
        } catch (FileNotFoundException e) {
            System.err.println("File touchDaemon.conf is not found! " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Other IO Exception." + e.getMessage());
            System.exit(1);
        } finally {
            try {
                if(reader != null) reader.close();
            } catch (IOException e) {
                System.err.println("Can't close the stream!" + e.getMessage());
                System.exit(1);
            }
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Emulator().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JMenuItem menuAbout;
    private javax.swing.JMenuItem menuDepartments;
    private javax.swing.JMenuItem menuUpload;
    // End of variables declaration//GEN-END:variables
}
