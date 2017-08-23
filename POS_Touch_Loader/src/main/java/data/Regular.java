package data;

import models.ConstructorTableModel;
import exceptions.EmptyDataException;
import io.CatalogReader;
import io.CatalogWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.table.TableModel;
import javax.xml.transform.TransformerException;

/**
 *
 * @author ivan
 */
public class Regular {

    // all of this objects is serializable
    private final ArrayList<Group> groups;
    private final ArrayList<Subgroup> subgroups;
    private final ArrayList<Product> products;
    private final ArrayList<TerminalGroup> terminalGroups;

    private final CatalogReader input;
    private CatalogWriter output;

    public Regular() throws EmptyDataException {

        input = new CatalogReader();
        groups = input.readGroups();
        subgroups = input.readSubgroups();
        products = input.readProducts();
        terminalGroups = input.readTerminalGroups();
    }

    public DefaultListModel getGroupsAsListModel() {
        DefaultListModel dlm = new DefaultListModel();

        for (Group group : groups) {
            dlm.addElement(group);
        }

        return dlm;
    }

//    public String[] getGroupsAsStringArray() {
//
//        String[] stringGroups = new String[groups.size()];
//
//        for (int i = 0; i < groups.size(); i++) {
//            stringGroups[i] = groups.get(i).toString();
//        }
//
//        return stringGroups;
//    }

    public Group getGroup(int index) {
        return groups.get(index);
    }
    
    public ArrayList<Group> getGroups() {
        return groups;
    }
    
    public int getGroupsCount() {
        return groups.size();
    }

    public ArrayList<Subgroup> getSubgroups() {
        return subgroups;
    }
    
    public DefaultListModel getSubgroupsAsListModel() {

        DefaultListModel dlm = new DefaultListModel();

        for (int i = 0; i < subgroups.size(); i++) {
            dlm.addElement(subgroups.get(i));
        }

        return dlm;
    }

    public DefaultListModel getSubgroupsAsListModel(Group group) {

        DefaultListModel dlm = new DefaultListModel();

        for (int i = 0; i < subgroups.size(); i++) {
            for (int j = 0; j < subgroups.get(i).getAccessibleGroups().size(); j++) {
                if (group.toString().equals(subgroups.get(i).getAccessibleGroups().get(j))) {
                    dlm.addElement(subgroups.get(i));
                }
            }
        }

        return dlm;
    }

    public String[] getSubgroupsAsStringArray() {

        String[] stringSubgroups = new String[subgroups.size()];

        for (int i = 0; i < subgroups.size(); i++) {
            stringSubgroups[i] = subgroups.get(i).toString();
        }

        return stringSubgroups;
    }

    public TableModel getGroupsAsTableModel(int subgroup) {

        String[] data = new String[groups.size()];
        boolean[] selectedData = new boolean[groups.size()];

        for (int i = 0; i < data.length; i++) {
            data[i] = groups.get(i).toString();
        }

        if (subgroup != -1) {
            for (int i = 0; i < groups.size(); i++) {
                for (int j = 0; j < subgroups.get(subgroup).getAccessibleGroups().size(); j++) {
                    if (groups.get(i).toString().equals(subgroups.get(subgroup).getAccessibleGroups().get(j))) {
                        selectedData[i] = true;
                        break;
                    }
                }
            }
        }

        return new ConstructorTableModel(data, selectedData);
    }

    public TableModel getSubgroupsAsTableModel(int product) {

        String[] data = new String[subgroups.size()];
        boolean[] selectedData = new boolean[data.length];

        for (int i = 0; i < data.length; i++) {
            data[i] = subgroups.get(i).toString();
        }

        if (product != -1) {
            for (int i = 0; i < subgroups.size(); i++) {
                for (int j = 0; j < products.get(product).getAccessibleSubgroups().size(); j++) {
                    if (subgroups.get(i).toString().equals(products.get(product).getAccessibleSubgroups().get(j))) {
                        selectedData[i] = true;
                        break;
                    }
                }
            }
        }

        return new ConstructorTableModel(data, selectedData);
    }

//    public ArrayList<Subgroup> getSubgroups() {
//
//        return subgroups;
//    }
    
    public int getSubgroupsCount() {
        return subgroups.size();
    }
    
    public Subgroup getSubgroup(int index) {
        return subgroups.get(index);
    }

    public DefaultListModel getProductsAsListModel() {

        DefaultListModel dlm = new DefaultListModel();

        for (int i = 0; i < products.size(); i++) {
            dlm.addElement(products.get(i));
        }

        return dlm;
    }

    public DefaultListModel getProductsAsListModel(Subgroup subgroup) {

        DefaultListModel dlm = new DefaultListModel();

        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < products.get(i).getAccessibleSubgroups().size(); j++) {
                if (subgroup.toString().equals(products.get(i).getAccessibleSubgroups().get(j))) {
                    dlm.addElement(products.get(i));
                }
            }
        }

        return dlm;
    }

    public ArrayList<TerminalGroup> getTerminalGroups() {
        return terminalGroups;
    }
    
    public TerminalGroup getTerminalGroup(int index) {
        return terminalGroups.get(index);
    }

    public String getTerminalGroupName(int terminalGroup) {
        return terminalGroups.get(terminalGroup).toString();
    }

    public DefaultListModel getTerminalGroupsAsListModel() {

        DefaultListModel dlm = new DefaultListModel();

        for (TerminalGroup terminalGroup : terminalGroups) {
            dlm.addElement(terminalGroup);
        }

        return dlm;
    }

    public DefaultListModel getTerminalsAsListModel(int terminalGroup) {

        DefaultListModel dlm = new DefaultListModel();

//        for (String terminal : terminalGroups.get(terminalGroup).getTerminals()) {
//            dlm.addElement(terminal);
//        }

        return dlm;
    }

    public void addTerminalsInTermGroup(int terminalGroup, String[] terminals) {
        //terminalGroups.get(terminalGroup).addAllTerminals(terminals);
    }

    public String getProductName(int product) {
        return products.get(product).toString();
    }
    
    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getProductPlu(int product) {
        return products.get(product).getPlu();
    }

    public String getProductPicturePath(int product) {
        return products.get(product).getPicturePath();
    }

    public void addGroup(String name) {
        groups.add(new Group(name));
        //update(groups, "res/rglGr.dat");
        updateXml();
    }

    public void removeGroup(int group) {
        groups.remove(group);
        updateXml();
    }

    public void addSubgroup(String name, List<String> accessibleGroups) {
        subgroups.add(new Subgroup(name, 0, accessibleGroups));
        updateXml();
    }

    public void removeSubgroup(int subgroup) {
        subgroups.remove(subgroup);
        updateXml();
    }

    public void addProduct(String name, String plu, String picturePath, List<String> accessibleSubgroups) {
        products.add(new Product(name, plu, picturePath, accessibleSubgroups));
        updateXml();
    }

    public void removeProduct(int product) {
        products.remove(product);
        updateXml();
    }

    public void addTerminalGroup(String name, ArrayList<String> terminals) {

        //terminalGroups.add(new TerminalGroup(name, terminals));
        //terminalGroups.get(terminalGroups.size() - 1).createDaysOfWeek();
        updateXml();
    }

    public void removeTreminalGroup(int terminalGroup) {

        terminalGroups.remove(terminalGroup);
        updateXml();
    }

    private void updateXml() {

        output = new CatalogWriter();

        try {
            output.write(terminalGroups, groups, subgroups, products);
        } catch (TransformerException e) {
            System.err.println(e.getMessage());
        }
    }
}
