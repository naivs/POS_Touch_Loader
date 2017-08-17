package gui;

import data.Product;
import data.Subgroup;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import utils.Monitor;

/**
 *
 * @author Ivan
 */
public class SubgroupConfig extends javax.swing.JDialog {

    private final int day;
    private final Subgroup subgroup;
    private DefaultListModel allProducts; // <- Objects inside
    private final DefaultListModel allProductsOriginal;

    public SubgroupConfig(java.awt.Frame parent,
            boolean modal,
            int day,
            Subgroup subgroup,
            DefaultListModel allProducts) {

        super(parent, modal);
        initComponents();

        this.day = day;
        this.subgroup = subgroup;
        allProductsOriginal = allProducts;

        this.allProducts = refreshAllProducts();

        allProd.setModel(this.allProducts);
        addedProd.setModel(this.subgroup.getProductsAsTableModel());

        subgroup.show((Monitor) jPanel2);
    }

    private DefaultListModel refreshAllProducts() {

        DefaultListModel dlm = new DefaultListModel();

        // vv копирование всех продуктов исключая добавленные vv
        for (int i = 0; i < allProductsOriginal.size(); i++) {
            boolean isPresence = false;

            for (int j = 0; j < subgroup.getProductsAsStringArray().length; j++) {
                if (allProductsOriginal.get(i).toString().equals(subgroup.getProductsAsStringArray()[j])) {
                    isPresence = true;
                    break;
                }
            }

            if (!isPresence) {
                dlm.addElement(allProductsOriginal.get(i));
            }
        }

        return dlm;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new Monitor();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        allProd = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        addedProd = new javax.swing.JTable();
        addProd = new javax.swing.JButton();
        delProd = new javax.swing.JButton();
        upProd = new javax.swing.JButton();
        downProd = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        pluLabel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        picPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Настройка подгруппы");
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

        jLabel23.setText("Добавленные товары:");

        jScrollPane2.setPreferredSize(new java.awt.Dimension(554, 385));

        allProd.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        allProd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                allProdFocusLost(evt);
            }
        });
        allProd.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                allProdValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(allProd);
        allProd.setSelectedIndex(0);

        addedProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "A"
            }
        ));
        addedProd.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(addedProd);

        addProd.setText("<");
        addProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProdActionPerformed(evt);
            }
        });

        delProd.setText(">");
        delProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delProdActionPerformed(evt);
            }
        });

        upProd.setText("^");
        upProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upProdActionPerformed(evt);
            }
        });

        downProd.setText("v");
        downProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downProdActionPerformed(evt);
            }
        });

        nameLabel.setText("Название:");

        pluLabel.setText("PLU:");

        jLabel21.setText("Изображение:");

        picPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        picPanel.setPreferredSize(new java.awt.Dimension(200, 112));

        javax.swing.GroupLayout picPanelLayout = new javax.swing.GroupLayout(picPanel);
        picPanel.setLayout(picPanelLayout);
        picPanelLayout.setHorizontalGroup(
            picPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );
        picPanelLayout.setVerticalGroup(
            picPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        jLabel1.setText("Все товары:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(addProd)
                                        .addComponent(delProd, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(upProd))
                                    .addComponent(downProd, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(nameLabel)
                            .addComponent(pluLabel)
                            .addComponent(jLabel21)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel1))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(addProd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(delProd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(upProd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(downProd)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pluLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 68, Short.MAX_VALUE)))
                .addComponent(picPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void allProdValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_allProdValueChanged

        String[] labels = {"Название: ", "PLU: "};

        if (!evt.getValueIsAdjusting() && allProd.getSelectedIndex() != -1) {
            /*
            1. получить экземпляр Product
            2. вывести о нем информацию в поля
             */

            // 1
            Product prod = (Product) allProducts.get(allProd.getSelectedIndex());
            // 2
            System.out.println("Product name: " + prod.toString());
            System.out.println("Product plu: " + prod.getPlu());
            System.out.println("Product pic_path: " + prod.getPicturePath());

            nameLabel.setText(labels[0] + prod.toString());
            pluLabel.setText(labels[1] + prod.getPlu());

            try {
                ImageIcon picture = new ImageIcon(ImageIO.read(new File(prod.getPicturePath())));
                picPanel.getGraphics().drawImage(picture.getImage(), 1, 1, 198, 110, picPanel);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_allProdValueChanged

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

        BufferedImage bi = new BufferedImage(jPanel2.getSize().width, jPanel2.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        jPanel2.paint(g);
        g.dispose();
        try {
            File pic = new File("res/pic/" + day + "TCH_X" + subgroup.getIndex() + ".GIF");
            ImageIO.write(bi, "GIF", pic);
            subgroup.setPicturePath(pic.getPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
//        if (isModified) {
//            JOptionPane jop = new JOptionPane("Сохранить изменения?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
//            JDialog dialog = jop.createDialog(this, "Сохранить");
//            dialog.show();
//            /*return values 0 - ok
//            *               1 - no
//            *               2 - cancel
//             */
//
//            int returnValue = Integer.parseInt(jop.getValue().toString());
//
//            switch (returnValue) {
//                case 0:
//                    regWriter.write(groupNames, subgroupNames, subgroupIndexes);
//                    pluWriter.writeData(plurefData);
//
//                    for (int i = 0; i < 8; i++) {
//                        categoryComboBox.setSelectedIndex(i);
//
//                        for (int j = 0; j < 8; j++) {
//                            allProd.setSelectedIndex(j);
//
//                            BufferedImage bi = new BufferedImage(jPanel2.getSize().width, jPanel2.getSize().height, BufferedImage.TYPE_INT_ARGB);
//                            Graphics g = bi.createGraphics();
//                            jPanel2.paint(g);
//                            g.dispose();
//                            try {
//                                ImageIO.write(bi, "gif", new SmbFileOutputStream(new SmbFile(SMB_URL + IMAGE_PATH + "TCH_X" + subgroupIndexes[categoryComboBox.getSelectedIndex()][allProd.getSelectedIndex()] + ".GIF", SMB_AUTH)));
//                            } catch (IOException e) {
//                                System.out.println(e.getMessage());
//                            }
//                        }
//                    }
//
//                    System.exit(0);
//
//                case 1:
//                    System.exit(0);
//
//                case 2:
//
//            }
//        }
    }//GEN-LAST:event_formWindowClosed

    private void addProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProdActionPerformed

        int selectedProduct = allProd.getSelectedIndex();

        if (selectedProduct != -1 && subgroup.getProductCount() < 20) {
            // 1
            Product product = (Product) allProducts.get(selectedProduct);
            subgroup.addProduct(new Product(product.toString(), product.getPlu(), product.getPicturePath(), null, product.getCreationDate()));
            // 2
            DefaultListModel dlm = new DefaultListModel();

            for (int i = 0; i < allProd.getModel().getSize(); i++) {
                dlm.addElement(allProd.getModel().getElementAt(i));
            }

            dlm.remove(selectedProduct);
            allProd.setModel(dlm);

            allProducts = refreshAllProducts(); // <- пересчитываем модель с объектами
            // 3
            addedProd.setModel(subgroup.getProductsAsTableModel());

            // show on monitor
            subgroup.show((Monitor) jPanel2);
        }
    }//GEN-LAST:event_addProdActionPerformed

    private void delProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delProdActionPerformed

        if (addedProd.getSelectedColumn() != -1 && subgroup.getProductCount() > 0) {
            DefaultListModel dlm = new DefaultListModel();

            for (int i = 0; i < allProd.getModel().getSize(); i++) {
                dlm.addElement(allProd.getModel().getElementAt(i));
            }

            dlm.addElement(addedProd.getModel().getValueAt(addedProd.getSelectedRow(), 0));

            allProd.setModel(dlm);

            subgroup.removeProduct(addedProd.getSelectedRow());

            allProducts = refreshAllProducts(); // <- пересчитываем модель с объектами

            addedProd.setModel(subgroup.getProductsAsTableModel());

            // show on monitor
            subgroup.show((Monitor) jPanel2);
        }
    }//GEN-LAST:event_delProdActionPerformed

    private void upProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upProdActionPerformed

        int selectedIndex = addedProd.getSelectedRow();

        if (selectedIndex > 0) {
            subgroup.upProduct(addedProd.getSelectedRow());
            addedProd.setModel(subgroup.getProductsAsTableModel());

            addedProd.setRowSelectionInterval(selectedIndex - 1, selectedIndex - 1);

            // show on monitor
            subgroup.show((Monitor) jPanel2);
        }
    }//GEN-LAST:event_upProdActionPerformed

    private void downProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downProdActionPerformed

        int selectedIndex = addedProd.getSelectedRow();

        if (selectedIndex < 19) {
            subgroup.downProduct(addedProd.getSelectedRow());
            addedProd.setModel(subgroup.getProductsAsTableModel());

            addedProd.setRowSelectionInterval(selectedIndex + 1, selectedIndex + 1);

            // show on monitor
            subgroup.show((Monitor) jPanel2);
        }
    }//GEN-LAST:event_downProdActionPerformed

    private void allProdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_allProdFocusLost

        String[] labels = {"Название: ", "PLU: "};

        nameLabel.setText(labels[0]);
        pluLabel.setText(labels[1]);

        try {
            ImageIcon picture = new ImageIcon(ImageIO.read(getClass().getResource("/resources/blank.png")));
            picPanel.getGraphics().drawImage(picture.getImage(), 1, 1, 198, 110, picPanel);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_allProdFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProd;
    private javax.swing.JTable addedProd;
    private javax.swing.JList<String> allProd;
    private javax.swing.JButton delProd;
    private javax.swing.JButton downProd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JPanel picPanel;
    private javax.swing.JLabel pluLabel;
    private javax.swing.JButton upProd;
    // End of variables declaration//GEN-END:variables
}
