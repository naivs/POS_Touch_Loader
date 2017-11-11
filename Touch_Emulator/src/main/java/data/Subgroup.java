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
package data;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ivan
 */
public class Subgroup {
    
    private final String name;
    private int index;
    private Product[] products;
    
    public Subgroup(String name, int index) {
        this.name = name;
        this.index = index;
        products = new Product[20];
    }
    
    public void addProduct(Product product) {
        
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = product;
                break;
            }
        }
    }
    
    public void removeProduct(int index) {
        products[index] = null;
    }
    
    public void setProducts(Product[] products) {
        this.products = products;
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
            
//            products[second].setNumber(second);
//            
//            if(products[first] != null) {
//                products[first].setNumber(first);
//            }
        //}
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public String getIndex() {
        if(index < 10) {
            return "00" + String.valueOf(index);
        } else if(index < 100) {
            return "0" + String.valueOf(index);
        }
        
        return String.valueOf(index);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
