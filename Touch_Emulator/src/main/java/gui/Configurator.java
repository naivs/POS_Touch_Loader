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
import io.ConfigurationReader;
import io.ConfigurationWriter;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.transform.TransformerException;
import models.DepartmentsTableModel;
import network.SMBClient;
import network.ServerCommunicator;
import org.xml.sax.SAXException;
import utils.IndexDispatcher;
import utils.ParGenerator;
import utils.PictureDrawer;
import utils.RefGenerator;

/**
 *
 * @author ivan
 */
public class Configurator extends javax.swing.JFrame {

    public static String SERVER_IP;
    public static int PORT;

    public static final int CLIENT_MESSAGE = 0;
    public static final int SERVER_MESSAGE = 1;

    public static final int PLAIN = 0;
    public static final int WARN = 1;
    public static final int CRIT = 2;

    private List<TerminalGroup> terminalGroups;

    private final IndexDispatcher idisp = new IndexDispatcher();

    public Configurator() {
        initComponents();
        
        try {
            terminalGroups = new ConfigurationReader().read();
            showMessage(CLIENT_MESSAGE, "Конфигурация открыта", PLAIN);
        } catch (SAXException e) {
            // empty or corrupted
            System.err.println(e.getMessage() + "\nФайл: \"resources/configuration.xml\" пуст или поврежден. Будет создана новая конфигурация");
            showMessage(CLIENT_MESSAGE, "\"configuration.xml\" пуст или поврежден", WARN);
            terminalGroups = new ArrayList<>();
        } catch (IOException e) {
            // no file
            System.err.println(e.getMessage() + "\nФайл: \"resources/configuration.xml\" не найден. Будет создана новая конфигурация");
            terminalGroups = new ArrayList<>();
        }
        update();
        showMessage(SERVER_MESSAGE, "Server IP: " + SERVER_IP + ":" + PORT, PLAIN);
    }

    private void openDepartment(java.awt.event.MouseEvent evt) {
        JTable table = (JTable) evt.getSource();
        Point p = evt.getPoint();
        int row = table.rowAtPoint(p);
        new Emulator(terminalGroups.get(row)).setVisible(true);
    }

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

    private boolean autorization() {
        String password = JOptionPane.showInputDialog(this, "Введите пароль:", "Разблокировка", JOptionPane.QUESTION_MESSAGE);
        if (password != null && password.equals("1234")) {
            showMessage(CLIENT_MESSAGE, "Доступ разрешен", PLAIN);
            return true;
        } else {
            showMessage(CLIENT_MESSAGE, "Неверный пароль!", WARN);
            return false;
        }
    }

    public final void showMessage(int TYPE, String message, int LEVEL) {
        switch (TYPE) {
            case CLIENT_MESSAGE:
                messageLabel.setText(message);
                switch (LEVEL) {
                    case PLAIN:
                        messageLabel.setBackground(Color.decode("5568405"));
                        break;

                    case WARN:
                        messageLabel.setBackground(Color.orange);
                        break;

                    case CRIT:
                        messageLabel.setBackground(Color.red);
                        break;
                }
                break;

            case SERVER_MESSAGE:
                serverStatusLabel.setText(message);
                switch (LEVEL) {
                    case PLAIN:
                        serverStatusLabel.setBackground(Color.decode("5568405"));
                        break;

                    case WARN:
                        serverStatusLabel.setBackground(Color.orange);
                        break;

                    case CRIT:
                        serverStatusLabel.setBackground(Color.red);
                        break;
                }
                break;
        }
    }

    private void update() {
        jTable1.setModel(new DepartmentsTableModel(terminalGroups));
        
        if(jTable1.getRowCount() < 1) {
            uploadButton.setEnabled(false);
        } else {
            jTable1.setRowSelectionInterval(0, 0);
            uploadButton.setEnabled(true);
        }
    }
    
    private void saveXMLConfiguration() {
        try {
            new ConfigurationWriter().write(terminalGroups);
            showMessage(CLIENT_MESSAGE, "Конфигурация обновлена!", PLAIN);
        } catch (TransformerException ex) {
            System.err.println("TransformerException occured while configuration saving! " + ex.getMessage());
            showMessage(CLIENT_MESSAGE, "Ошибка записи данных!", CRIT);
        }
    }

    private void generateFiles() {
        int groupsCount = 0;
        for (TerminalGroup tGrp : terminalGroups) {
            for (int i = 0; i < tGrp.getDaysOfWeek().length; i++) {
                groupsCount += tGrp.getDaysOfWeek()[i].getGroupCount();
            }
        }
        ProgressMonitor progressMonitor = new ProgressMonitor(this, "Подготовка файлов для выгрузки...", groupsCount);
        Thread generateThread = new Thread() {
            @Override
            public void run() {
                File picFolder = new File("resources/data");
                try {
                    delete(picFolder);
                } catch (IOException ex) {
                    System.out.println("IO Except " + ex.toString());
                }
                if (!picFolder.mkdir()) {
                    System.err.println("data folder doesn't created! trying again...");
                    if (!picFolder.mkdir()) {
                        System.err.println("data folder doesn't created again...");
                        //return false;
                    }
                }

                for (int i = 0; i < terminalGroups.size(); i++) {
                    TerminalGroup department = terminalGroups.get(i);
                    for (int j = 0; j < department.getDaysOfWeek().length; j++) {
                        DayOfWeek day = department.getDaysOfWeek()[j];
                        // clear pic folder                            
                        // creation day dirs
                        // saving all into it
                        File anotherDay;
                        if (department.getType() == TerminalGroup.TYPE_ALWAYS) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(new Date());
                            anotherDay = new File(picFolder.getPath() + "/day" + (calendar.get(Calendar.DAY_OF_WEEK) - 1));
                        } else {
                            anotherDay = new File(picFolder.getPath() + "/day" + j);
                        }
                        anotherDay.mkdir();

                        // saving .dat files
                        File regpar = new ParGenerator(day).getFile();
                        File pluref = new RefGenerator(day).getFile();

                        String terminals = "";
                        for (String num : terminalGroups.get(i).getTerminalsAsString().split(":")) {
                            terminals += num + "-";
                        }
                        terminals = terminals.substring(0, terminals.length() - 1);
                        try {
                            Files.copy(regpar.toPath(), new File(anotherDay.getPath() + "/P_REGPAR.DAT" + terminals).toPath(), StandardCopyOption.REPLACE_EXISTING);
                            Files.copy(pluref.toPath(), new File(anotherDay.getPath() + "/S_PLUREF.DAT" + terminals).toPath(), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException ex) {
                            System.err.println("IOException: " + ex.getMessage());
                        }

                        // saving images
                        File cafe = new File(anotherDay.getPath() + "/cafe");
                        cafe.mkdir();
                        for (int c = 0; c < day.getGroupCount(); c++) {
                            Group group = day.getGroup(c);
                            for (int d = 0; d < group.getSubgroupCount(); d++) {
                                Subgroup subgroup = group.getSubgroup(d);
                                try {
                                    File pic = new File(anotherDay.getAbsolutePath() + "/" + cafe.getName() + "/TCH_X" + subgroup.getIndex() + ".GIF");
                                    PictureDrawer.draw(pic, subgroup);
                                } catch (IOException ex) {
                                    System.err.println(ex.getMessage());
                                }
                            }
                            progressMonitor.stepUp();
                        }
                    }
                }

                JOptionPane.showMessageDialog(null, "Конфигурация успешно сохранена!", "Информация", JOptionPane.PLAIN_MESSAGE);
                progressMonitor.dispose();
                interrupt();
            }
        };

        generateThread.start();
        progressMonitor.setVisible(true);
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
        addButton = new javax.swing.JButton();
        uploadButton = new javax.swing.JButton();
        updateFromFileButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        statusPanel = new javax.swing.JPanel();
        messageLabel = new javax.swing.JLabel();
        serverStatusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Touch Configurator");
        setMaximumSize(new java.awt.Dimension(1280, 1024));
        setMinimumSize(new java.awt.Dimension(715, 364));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        addButton.setText("Новый отдел...");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jPanel1.add(addButton);

        uploadButton.setText("Загрузить все на сервер");
        uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadButtonActionPerformed(evt);
            }
        });
        jPanel1.add(uploadButton);

        updateFromFileButton.setText("Обновить данные из фала...");
        updateFromFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateFromFileButtonActionPerformed(evt);
            }
        });
        jPanel1.add(updateFromFileButton);

        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        statusPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        statusPanel.setFocusable(false);
        statusPanel.setMaximumSize(new java.awt.Dimension(32767, 24));
        statusPanel.setMinimumSize(new java.awt.Dimension(100, 24));

        messageLabel.setForeground(new java.awt.Color(0, 0, 0));
        messageLabel.setFocusable(false);
        messageLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        messageLabel.setOpaque(true);

        serverStatusLabel.setForeground(new java.awt.Color(0, 0, 0));
        serverStatusLabel.setFocusable(false);
        serverStatusLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        serverStatusLabel.setOpaque(true);

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serverStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serverStatusLabel))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        statusPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {messageLabel, serverStatusLabel});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        if (autorization()) {
            String holdedPOS = "";
            for (TerminalGroup buf : terminalGroups) {
                holdedPOS += ":" + buf.getTerminalsAsString();
            }
            DepartmentCreator dc = new DepartmentCreator(this, holdedPOS);
            TerminalGroup tg = dc.createDepartment();

            if (tg != null) {
                boolean contains = false;
                for (TerminalGroup element : terminalGroups) {
                    if (element.toString().equals(tg.toString())) {
                        contains = true;
                        showMessage(CLIENT_MESSAGE, "Отдел с таким имененм уже существует!", WARN);
                        break;
                    }
                }

                if (!contains) {
                    terminalGroups.add(tg);
                    saveXMLConfiguration();
                    update();
                }
            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadButtonActionPerformed
        ServerCommunicator communicator;
        SMBClient smbClient;

        try {
            communicator = new ServerCommunicator();
            smbClient = new SMBClient(SERVER_IP, communicator.getSmbAuth());
            System.out.println(smbClient.testConnection());
        } catch (UnknownHostException ex) {
            System.err.println("Unknown host " + Configurator.SERVER_IP);
            showMessage(SERVER_MESSAGE, "Некорректный сетевой адрес...", WARN);
            return;
        } catch (IOException ex) {
            System.err.println("I/O Socket error. " + ex.getMessage());
            showMessage(SERVER_MESSAGE, "Сервер офлайн...", WARN);
            return;
        }

        generateFiles();

        int days = 0;
        for (TerminalGroup tGrp : terminalGroups) {
            days += tGrp.getDaysOfWeek().length;
        }
        ProgressMonitor progressMonitor = new ProgressMonitor(this, "Загрузка данных на сервер...", days);
        Thread uploadingThread = new Thread() {
            @Override
            public void run() {
                smbClient.clearShare();

                for (int tGroupNum = 0; tGroupNum < terminalGroups.size(); tGroupNum++) {
                    for (int dayNum = 0; dayNum < terminalGroups.get(tGroupNum).getDaysOfWeek().length; dayNum++) {
                        try {
                            // create day folder
                            smbClient.createFolder("day" + dayNum + "/");
                            // create cafe folder
                            smbClient.createFolder("day" + dayNum + "/cafe/");
                            // copy .dat files
                            File datFiles = new File("resources/data/day" + dayNum);
                            for (File f : datFiles.listFiles((File directory, String fileName) -> fileName.contains(".DAT"))) {
                                smbClient.putFile(f, "day" + dayNum + "/" + f.getName());
                            }
                            // copy images
                            for (File img : new File("resources/data/day" + dayNum + "/cafe").listFiles()) {
                                smbClient.putFile(img, "day" + dayNum + "/cafe/" + img.getName());
                            }
                            progressMonitor.stepUp();
                        } catch (MalformedURLException ex) {
                            System.err.println("Wrong destenation URL. " + ex.getMessage());
                        } catch (IOException ex) {
                            System.err.println("I/O exception while P_REGPAR.DAT or S_PLUREF.DAT uploading. " + ex.getMessage());
                        }
                    }
                }
                communicator.shutDown();
                JOptionPane.showMessageDialog(null, "Выгрузка завершена!", "Информация", JOptionPane.PLAIN_MESSAGE);
                progressMonitor.dispose();
            }
        };

        uploadingThread.start();
    }//GEN-LAST:event_uploadButtonActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        int rowindex = jTable1.getSelectedRow();

        JMenuItem openMenuItem = new JMenuItem("Открыть");
        openMenuItem.addActionListener((ActionEvent e) -> {
            openDepartment(evt);
        });
        JMenuItem modifyMenuItem = new JMenuItem("Изменить");
        modifyMenuItem.addActionListener((ActionEvent e) -> {
            if (autorization()) {
                String holdedPOS = "";
                for (TerminalGroup buf : terminalGroups) {
                    holdedPOS += ":" + buf.getTerminalsAsString();
                }
                DepartmentCreator dc = new DepartmentCreator(this, holdedPOS);
                if (dc.editDepartment(terminalGroups.get(rowindex))) {
                    saveXMLConfiguration();
                    update();
                }
            }
        });
        JMenuItem removeMenuItem = new JMenuItem("Удалить");
        removeMenuItem.addActionListener((ActionEvent e) -> {
            if (autorization()) {
                int response = JOptionPane.showConfirmDialog(this,
                        terminalGroups.get(rowindex).toString() + "\nУдалить этот кассовый отдел?",
                        "Удаление отдела",
                        JOptionPane.OK_CANCEL_OPTION);
                if (response == JOptionPane.OK_OPTION) {
                    terminalGroups.remove(rowindex);
                    saveXMLConfiguration();
                    update();
                }
            }
        });

        if (rowindex < 0) {
            return;
        }
        if (evt.isPopupTrigger() && evt.getComponent() instanceof JTable) {
            JPopupMenu popup = new JPopupMenu();
            popup.add(openMenuItem);
            popup.add(modifyMenuItem);
            popup.add(new JSeparator());
            popup.add(removeMenuItem);
            popup.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jTable1MousePressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            openDepartment(evt);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void updateFromFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateFromFileButtonActionPerformed
        // choose file configuration
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("./"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Файлы Microsoft Excel 2007*", "xlsx"));

        if (fileChooser.showDialog(this, "Открыть файл Excel...") == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try {
                Parser parser = new Parser(file);

                // reading all products
                DayOfWeek[] days = terminalGroups.get(jTable1.getSelectedRow()).getDaysOfWeek();

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
                            products.add(new Product(name.split("::")[1], name.split("::")[0], ""));
                        }

                        Subgroup[] subgroups;
                        if (products.size() % 20 > 0) {
                            subgroups = new Subgroup[(products.size() / 20) + 1];
                        } else {
                            subgroups = new Subgroup[products.size() / 20];
                        }

                        String[] subgroupsNames = parser.getSubgroupNames(i, j);

                        for (int s = 0; s < subgroups.length; s++) {
                            subgroups[s] = new Subgroup(subgroupsNames[s],
                                    idisp.getNextFreeIndex(i, Integer.parseInt(terminalGroups.get(jTable1.getSelectedRow()).getStartIndex().substring(1))));
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

                terminalGroups.get(jTable1.getSelectedRow()).setModified(Calendar.getInstance().getTime().toString());
                showMessage(CLIENT_MESSAGE, "Загружен файл: " + file.getCanonicalPath(), PLAIN);
                saveXMLConfiguration();
                update();
            } catch (FileNotFoundException ex) {
                System.err.println("File not found");
                showMessage(CLIENT_MESSAGE, "Файл " + file.getName() + " не найден!", CRIT);
            } catch (IOException ex) {
                System.err.println("Other IO Exception");
                showMessage(CLIENT_MESSAGE, "Ошибка ввода/вывода!", CRIT);
            }
        }
    }//GEN-LAST:event_updateFromFileButtonActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Configurator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configurator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configurator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configurator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            //System.out.println("Server IP: " + SERVER_IP + ":" + PORT);
        } catch (FileNotFoundException e) {
            System.err.println("File touchDaemon.conf is not found! " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Other IO Exception." + e.getMessage());
            System.exit(1);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Can't close the stream!" + e.getMessage());
                System.exit(1);
            }
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Configurator().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel serverStatusLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JButton updateFromFileButton;
    private javax.swing.JButton uploadButton;
    // End of variables declaration//GEN-END:variables
}
