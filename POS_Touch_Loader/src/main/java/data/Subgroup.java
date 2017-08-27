package data;

import java.util.Calendar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ivan
 */
public class Subgroup {
    
    private final String name;
    private int index;
    private Product[] products;
    private String picturePath;
    private java.util.List<String> accessibleGroups;
    private boolean isValid;
    private String modifiedDate;
    private final String creationDate;
    
    public Subgroup(String name, int index, java.util.List<String> accessibleGroups) {
        this(name, index, accessibleGroups, Calendar.getInstance().getTime().toString(), Calendar.getInstance().getTime().toString());
    }
    
    public Subgroup(String name, int index, java.util.List<String> accessibleGroups, String creationDate, String modifiedDate) {
        this.name = name;
        this.index = index;
        products = new Product[20];
        this.accessibleGroups = accessibleGroups;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
    }
    
    public java.util.List<String> getAccessibleGroups() {
        return accessibleGroups;
    }
    
    public boolean isAccessible(String group) {
        
        for(int i = 0; i < accessibleGroups.size(); i++) {
            if(accessibleGroups.get(i).equals(group)) {
                return true;
            }
        }
        
        return false;
    }
    
    public void addProduct(Product product) {
        
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = product;
                updateTime();
                break;
            }
        }
    }
    
    public void setPicturePath(String picurePath) {
        this.picturePath = picurePath;
    }
    
    public String getPicturePath() {
        return picturePath;
    }
    
    public void removeProduct(int index) {
        
        products[index] = null;
        updateTime();
    }
    
    public boolean isValid() {
        return isValid;
    }
    
    public void setProducts(Product[] products) {
        this.products = products;
    }
    
    public String getCreationDate() {
        return creationDate;
    }
    
    public DefaultTableModel getProductsAsTableModel() {
        
        DefaultTableModel dtm = new DefaultTableModel(20, 1);

        int i = 0;
        for (Product prd : products) {

            if (prd != null) {
                dtm.setValueAt(prd.toString(), i, 0);
            }
            i++;
        }

        return dtm;
    }
    
    public Product[] getProducts() {
        return products;
    }
    
    public String[] getProductsAsStringArray() {

        String[] prod = new String[products.length];

        for (int i = 0; i < prod.length; i++) {
            if (products[i] != null) {
                prod[i] = products[i].toString();
            }
        }

        return prod;
    }
    
    public String getName() {
        return name;
    }
    
    public Product getProduct(int index) {
        return products[index];
    }
    
    public int getProductCount() {
        
        int count = 0;
        
        for(Product prod : products) {
            if(prod != null) {
                count++;
            }
        }
        
        return count;
    }
    
    public int getProductNumber(String product) {
        
        int number = -1;
        
        for(int i = 0; i < products.length; i++) {
            if(products[i] != null) {
                if(product.equals(products[i].getName())) {
                    number = i;
                }
            }
        }
        
        return number;
    }
    
    public void upProduct(int product) {
        int first = product, second = product - 1;

        //if (first > 0) {
            Product buf = products[second];
            products[second] = products[first];
            products[first] = buf;
            
            updateTime();
            
//            products[second].setNumber(second);
//            
//            if(products[first] != null) {
//                products[first].setNumber(first);
//            }
        //}
    }

    public void downProduct(int product) {
        int first = product, second = product + 1;

        //if (first < 19) {
            Product buf = products[second];
            products[second] = products[first];
            products[first] = buf;
            
            updateTime();
            
//            products[second].setNumber(second);
//            
//            if(products[first] != null) {
//                products[first].setNumber(first);
//            }
        //}
    }
    
    public void show(utils.Monitor monitor) {
        monitor.display(products);
    }
    
    public void setRemoved(boolean statement) {
        isValid = statement;
    }
    
    public void updateTime() {
        modifiedDate = Calendar.getInstance().getTime().toString();
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public void setAccessibleGroups(java.util.List<String> accessibleGroups) {
        this.accessibleGroups = accessibleGroups;
    }
    
    public String getIndex() {
        if(index < 10) {
            return "00" + String.valueOf(index);
        } else if(index < 100) {
            return "0" + String.valueOf(index);
        }
        
        return String.valueOf(index);
    }
    
    public String getModifiedDate() {
        return modifiedDate;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
