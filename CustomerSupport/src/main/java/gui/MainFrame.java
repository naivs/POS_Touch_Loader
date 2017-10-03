package gui;

import data.Customer;
import engine.FileIO;
import engine.MyTableModel;
import java.awt.Color;
import java.awt.Point;
import java.util.Date;
import java.util.Locale;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainFrame extends javax.swing.JFrame {
    
    private MyTableModel tableModel = new MyTableModel(FileIO.readData());
  
    public MainFrame() {
        initComponents();
        setResizable(false);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ico/mainIco.png")).getImage());
        
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (!lse.getValueIsAdjusting() && (jTable1.getSelectedRow() != -1)) { // фильтр дубликации события
                    System.out.println("Table selection " + jTable1.getSelectedRow() + " was changed... ");
                    tableModel.setCurrentSelection(jTable1.getSelectedRow()); // запоминание номера выделенной строки

                    jLabel1.setText("Items: " + jTable1.getRowCount()); // вывод количества обращений
                    int waitingCount = 0;
                    for (Customer out : tableModel.getCustomers()) {
                        if (!out.getStatus())
                            waitingCount++;
                    }
                    jLabel1.setText(jLabel1.getText() + "/" + waitingCount + " is waiting"); // вывод количества незарегистрированных обращений

                    String out =
                            "                                               "
                                    + new Date(tableModel.getCustomer(tableModel.getCurrentSelection()).getDate()) + "\n" +
                                    "=============================================================\n" +
                                    "Customer's Name:\n" + tableModel.getCustomer(tableModel.getCurrentSelection()).getName() + "\n" +
                                    "=============================================================\n" +
                                    "Customer's Surname:\n" + tableModel.getCustomer(tableModel.getCurrentSelection()).getSurname() + "\n" +
                                    "=============================================================\n" +
                                    "Customer's Email:\n" + tableModel.getCustomer(tableModel.getCurrentSelection()).getEmail() + "\n" +
                                    "=============================================================\n" +
                                    "Company ITP_ID:\n" + tableModel.getCustomer(tableModel.getCurrentSelection()).getCompanyId() + "\n" +
                                    "=============================================================\n" +
                                    "Product:\n" + tableModel.getCustomer(tableModel.getCurrentSelection()).getProduct() + "\n" +
                                    "=============================================================\n" +
                                    "Problem:\n" + tableModel.getCustomer(tableModel.getCurrentSelection()).getProblem() + "\n" +
                                    "=============================================================\n" +
                                    "Solution:\n" + tableModel.getCustomer(tableModel.getCurrentSelection()).getSolution() + "";

                    jTextArea1.setText(out);
                    jTextArea1.setCaretPosition(0);
                }
            }
        });
        
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                onChange();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                onChange();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
            }
            
            private void onChange() {
                if (!jTextField1.getFont().isItalic())
                    search(jTextField1.getText());
                System.out.println("Hello change...");
            }
        });
        
        defaultTableView(jTable1.getRowCount());
    }

    private void search(String string) { // функция поиска записей
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (!string.equals("") && tableModel.getCustomer(i).getEmail().contains(string)) {
                jTable1.getSelectionModel().setSelectionInterval(i, i);
                jScrollPane1.getViewport().setViewPosition(new Point(0, i * 20));
                System.out.println("Finded in " + i + " record... ");
            }
        }
    }
    
    private void defaultTableView(int rowCount) { // установка точки обзора на последнюю строку
        jTable1.getSelectionModel().setSelectionInterval(tableModel.getCustomers().size() - 1, tableModel.getCustomers().size() - 1); // выделение последней строки таблицы
        //jTable1.getSelectionModel().setLeadSelectionIndex(tableModel.getCustomers().size() - 1); // установка индекса
        jTable1.requestFocus(); // фокус на таблицу
        
        Point point = new Point(0, rowCount * 20);
        jScrollPane1.getViewport().setViewPosition(point);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        doneButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jButton4.setToolTipText("");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Intel Customer Support");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("jLabel1");
        jLabel1.setFocusable(false);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTable1.setModel(tableModel);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        jTextField1.setForeground(java.awt.Color.gray);
        jTextField1.setText("search");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(null);
        jScrollPane2.setViewportView(jTextArea1);

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/addButton.png"))); // NOI18N
        addButton.setToolTipText("Add");
        addButton.setFocusPainted(false);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/editButton.png"))); // NOI18N
        editButton.setToolTipText("Change");
        editButton.setFocusPainted(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        doneButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/doneButton.png"))); // NOI18N
        doneButton.setToolTipText("Set as done");
        doneButton.setFocusPainted(false);
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/deleteButton.png"))); // NOI18N
        jButton1.setToolTipText("Delete record");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 320, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2)
                    .add(layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(addButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(editButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(doneButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton1)
                        .add(0, 25, Short.MAX_VALUE))))
        );

        layout.linkSize(new java.awt.Component[] {addButton, doneButton, editButton, jButton1}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jScrollPane2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(editButton)
                        .add(doneButton)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, addButton))
                    .add(jButton1))
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 236, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        layout.linkSize(new java.awt.Component[] {addButton, doneButton, editButton, jButton1}, org.jdesktop.layout.GroupLayout.VERTICAL);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        new OkCancelDialog(this, tableModel, OkCancelDialog.ADD_MODE).setVisible(true);
                tableModel.fireTableDataChanged();
                jTable1.getSelectionModel().setSelectionInterval(tableModel.getCustomers().size() - 1, tableModel.getCustomers().size() - 1); // выделение последней строки таблицы
                //jTable1.getSelectionModel().setLeadSelectionIndex(tableModel.getCustomers().size() - 1); // установка индекса
    }//GEN-LAST:event_addButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        new OkCancelDialog(this, tableModel, OkCancelDialog.EDIT_MODE).setVisible(true);
                tableModel.fireTableDataChanged();
                jTable1.getSelectionModel().setSelectionInterval(tableModel.getCurrentSelection(), tableModel.getCurrentSelection());
                //jTable1.getSelectionModel().setLeadSelectionIndex(tableModel.getCurrentSelection()); // установка фокуса
    }//GEN-LAST:event_editButtonActionPerformed

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        tableModel.getCustomer(tableModel.getCurrentSelection()).setStatus(true);
                tableModel.fireTableDataChanged();
                jTable1.getSelectionModel().setSelectionInterval(tableModel.getCurrentSelection(), tableModel.getCurrentSelection());

                System.out.println("Record #" + (tableModel.getCurrentSelection() + 1) + " checked as done...");
    }//GEN-LAST:event_doneButtonActionPerformed

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        if (jTextField1.getForeground().equals(Color.GRAY)) {
                    jTextField1.setText("");
                    jTextField1.setForeground(UIManager.getDefaults().getColor(JTextField.getDefaultLocale(), Locale.US));
                }
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        jTextField1.setText("search");
                jTextField1.setForeground(Color.GRAY);
    }//GEN-LAST:event_jTextField1FocusLost

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        tableModel.saveData();
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tableModel.getCustomers().remove(jTable1.getSelectedRow());
        tableModel.fireTableDataChanged();
        defaultTableView(tableModel.getRowCount());
    }//GEN-LAST:event_jButton1ActionPerformed

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
            javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");          
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton doneButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
