package gui;

import data.DayOfWeek;
import data.Group;
import javax.swing.DefaultListModel;

/**
 *
 * @author ivan
 */
public class DayOfWeekConfig extends javax.swing.JDialog {

    private final int day;
    private final DayOfWeek dayOfWeek;
    private final DefaultListModel allGroups;

    /**
     * Creates new form GroupConfig
     *
     * @param parent
     * @param modal
     * @param day
     * @param dayOfWeek
     * @param allGroups
     */
    public DayOfWeekConfig(java.awt.Frame parent, boolean modal, int day, DayOfWeek dayOfWeek, DefaultListModel allGroups) {
        super(parent, modal);
        initComponents();

        this.day = day;
        this.dayOfWeek = dayOfWeek;

        this.allGroups = new DefaultListModel();
        for (int i = 0; i < allGroups.size(); i++) {
            boolean isPresence = false;

            for (int j = 0; j < dayOfWeek.getGroupsAsStringArray().length; j++) {
                if (allGroups.get(i).toString().equals(dayOfWeek.getGroupsAsStringArray()[j])) {
                    isPresence = true;
                    break;
                }
            }

            if (!isPresence) {
                this.allGroups.addElement(allGroups.get(i));
            }
        }

        allGrp.setModel(this.allGroups);
        addedGroups.setModel(dayOfWeek.getGroupsAsTableModel());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        delGrpBtn = new javax.swing.JButton();
        addGrpBtn = new javax.swing.JButton();
        upBtn = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        allGrp = new javax.swing.JList();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        addedGroups = new javax.swing.JTable();
        downBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel14.setText("Добавлены:");

        delGrpBtn.setText(">");
        delGrpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delGrpBtnActionPerformed(evt);
            }
        });

        addGrpBtn.setText("<");
        addGrpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGrpBtnActionPerformed(evt);
            }
        });

        upBtn.setText("^");
        upBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upBtnActionPerformed(evt);
            }
        });

        allGrp.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane9.setViewportView(allGrp);

        jLabel15.setText("Все группы:");

        addedGroups.getTableHeader().setResizingAllowed(false);
        addedGroups.getTableHeader().setReorderingAllowed(false);
        addedGroups.setUpdateSelectionOnSort(false);
        addedGroups.setVerifyInputWhenFocusTarget(false);
        jScrollPane15.setViewportView(addedGroups);

        downBtn.setText("v");
        downBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(delGrpBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addGrpBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(upBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(downBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addGrpBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(delGrpBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(upBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(downBtn)
                                .addGap(76, 76, 76)))))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void delGrpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delGrpBtnActionPerformed
        // remove group from dayOfWeek

        /*
        1) добавить выбранный элемент в allListGroups
        2) удалить выбранный элемент из addedGroupsTable (не сдвигая остальные)
        3) подгрузить измененную модель для addedGroupsTable
         */
        if (addedGroups.getSelectedColumn() != -1 && dayOfWeek.getGroupCount() > 0) {
            // 1
//            DefaultListModel dlm = new DefaultListModel();
//
//            for (int i = 0; i < allGrp.getModel().getSize(); i++) {
//                dlm.addElement(allGrp.getModel().getElementAt(i));
//            }

            allGroups.addElement(addedGroups.getModel().getValueAt(addedGroups.getSelectedRow(), 0));
            //allGrp.setModel(dlm);
            // 2
            dayOfWeek.removeGroup(addedGroups.getSelectedRow());
            // 3
            addedGroups.setModel(dayOfWeek.getGroupsAsTableModel());
        }
    }//GEN-LAST:event_delGrpBtnActionPerformed

    private void addGrpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGrpBtnActionPerformed
        // add group to dayOfWeek

        /*
        1) добавить выбранный элемент в addedGroupsTable
        2) удалить выбранный элемент из allListGroups
        3) подгрузить измененную модель для addedGroupsTable
         */
        int selectedGroup = allGrp.getSelectedIndex();

        if (selectedGroup != -1 && dayOfWeek.getGroupCount() < 8) {
            // 1
            //Group group = new Group(allGrp.getSelectedValue());
            dayOfWeek.addGroup(new Group(((Group) allGroups.get(selectedGroup)).getName()));
            // 2
//            DefaultListModel dlm = new DefaultListModel();
//
//            for (int i = 0; i < allGrp.getModel().getSize(); i++) {
//                dlm.addElement(allGrp.getModel().getElementAt(i));
//            }

            allGroups.remove(selectedGroup);
            //allGrp.setModel(dlm);
            // 3
            addedGroups.setModel(dayOfWeek.getGroupsAsTableModel());
        }
    }//GEN-LAST:event_addGrpBtnActionPerformed

    private void upBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upBtnActionPerformed

        int selectedRow = addedGroups.getSelectedRow();

        if (selectedRow > 0) {

            dayOfWeek.upGroup(selectedRow);
            addedGroups.setModel(dayOfWeek.getGroupsAsTableModel());

            addedGroups.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
        }
    }//GEN-LAST:event_upBtnActionPerformed

    private void downBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downBtnActionPerformed

        int selectedRow = addedGroups.getSelectedRow();

        if (selectedRow < 7) {
            dayOfWeek.downGroup(addedGroups.getSelectedRow());
            addedGroups.setModel(dayOfWeek.getGroupsAsTableModel());

            addedGroups.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);
        }
    }//GEN-LAST:event_downBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addGrpBtn;
    private javax.swing.JTable addedGroups;
    private javax.swing.JList<String> allGrp;
    private javax.swing.JButton delGrpBtn;
    private javax.swing.JButton downBtn;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JButton upBtn;
    // End of variables declaration//GEN-END:variables
}
