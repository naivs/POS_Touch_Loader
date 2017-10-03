package engine;

import data.Customer;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel {

    private ArrayList<Customer> customers; // массив с экземплярами клиентов
    private int currentSelection;

    public MyTableModel(Object customers) {
        if(customers != null)
            this.customers = (ArrayList<Customer>) customers;
        else
            this.customers = new ArrayList<>();
        //customers = FileIO.readData();

//        FileIO fileIo = new FileIO();
//        ArrayList<String> rowData = fileIo.getLines(); // строки из файла
//        String[] parsedDataRow;
//        
//        for(String out : rowData) {
//            parsedDataRow = out.split("Ã½"); // разбиение строки

//            customers.add(new Customer(
//                    Long.parseLong(parsedDataRow[0], 10),
//                    parsedDataRow[1],
//                    parsedDataRow[2],
//                    parsedDataRow[3],
//                    Integer.parseInt(parsedDataRow[4]),
//                    parsedDataRow[5],
//                    parsedDataRow[6],
//                    parsedDataRow[7],
//                    Boolean.valueOf(parsedDataRow[8])));
//        }

        //setCurrentSelection(0);
    }

    @Override
    public int getRowCount() {
        if(customers == null)
            return 0;
        return customers.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int column) { // заносим данные в модель из массива экземпляров клиентов

        String out = "";
        switch (column) {
            /*case 0:
                out = String.valueOf(new Date(customers.get(row).getDate()));
                break;*/
            case 0:
                out = customers.get(row).getName();
                break;
            /*case 2:
                out = customers.get(row).getSurname();
                break;*/
            case 1:
                out = customers.get(row).getEmail();
                break;
            case 2:
                out = String.valueOf(customers.get(row).getCompanyId());
                break;
            /*case 5:
                out = customers.get(row).getProduct();
                break;*/
            /*case 6:
                out = customers.get(row).getProblem();
                break;*/
            /*case 7:
                out = customers.get(row).getSolution();
                break;*/
            case 3:
                if((out = String.valueOf(customers.get(row).getStatus())).equals("false"))
                    out = "[WAITING]";
                else
                    out = "[DONE]";
                break;
        }
        return out;
    }

    @Override
    public String getColumnName(int i) {

        String out = "";
        switch (i) {
            case 0:
                out = "Name";
                break;
            case 1:
                out = "Email";
                break;
            case 2:
                out = "ITP_ID";
                break;
            case 3:
                out = "Status";
                break;
            /*case 5:
                out = "Product";
                break;
            case 6:
                out = "Problem";
                break;
            case 7:
                out = "Solution";
                break;
            case 8:
                out = "Status";
                break;*/
        }

        return out;
    }

    public Customer getCustomer(int index) {
        return customers.get(index);
    }

    public void addElement(Customer customer) {
        customers.add(customer);
    }
    
    public void saveData() {
        FileIO.saveData(customers);
//        FileIO fileIo = new FileIO();
//        fileIo.clearFile();
//        //ObjectOutputStream ois = new ObjectOutputStream()
//
//        String bufTextAreas = "";
//        
//        String fileIn = "";
//        for (Customer customer : customers) {
//            
//            fileIn = customer.getDate() + (char)253 +
//                    customer.getName() + (char)253 +
//                    customer.getSurname() + (char)253 +
//                    customer.getEmail() + (char)253 +
//                    customer.getCompanyId() + (char)253 +
//                    customer.getProduct() + (char)253 +
//                    customer.getProblem() + (char)253 +
//                    customer.getSolution() + (char)253 +
//                    customer.getStatus() + (char)126;
//            fileIo.addLine(fileIn);
//        }
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCurrentSelection(int i) {
        currentSelection = i;
    }

    public int getCurrentSelection() {
        return currentSelection;
    }
}
