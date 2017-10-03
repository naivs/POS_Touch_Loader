package engine;

import data.Customer;
import java.io.*;
import java.util.ArrayList;

public class FileIO {
    //private final static String FILE_PATH = "base.cus"; // файл данных по умолчанию
    private final static String OBJECT_SAVE_FILE = "baseObj.bs";
    //new javax.swing.ImageIcon(getClass().getResource("/ico/mainIco.png")).getImage()
    
    //private static String FILE_SIGN = "Property_of_CYBERDYNE-Inc.||cid:04081997"; // подпись файлов программы
    //private static boolean LOCK = true;

    public ArrayList<String> getLines() // считать строку из файла. numStr - номер строки
    {
//        ArrayList<String> lines = new ArrayList<>();
//            try {
//                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(FILE_PATH));
//                String buf = "";
//                int readBytes = 0;
//                
//                while ((readBytes = inputStream.read()) != -1) {
//                    
//                    if(readBytes != 126) // ~ character separator
//                        buf += (char) readBytes;
//                    else {
//                        lines.add(buf); // add field
//                        buf = ""; // clearing buffer
//                    }
//                }
//                    //lines.add(buf); // add field
//                    //else if(readBytes == stopByte)
//                
//                    //out.add(buf);
//                
//                inputStream.close();
//                return lines;
//            } catch (IOException ex) {
//                System.out.print(ex.getMessage());
//            }
//
              return null;
    }

//    public void addLine(String line)
//    {
//            try {
//                BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
//                bw.write(line);
//                bw.newLine();
//                bw.flush();
//                bw.close();
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//            }
//    }
//
//    public void clearFile() {
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
//            bw.write("");
//            bw.flush();
//            bw.close();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    
    public static void saveData(ArrayList<Customer> records) { // записать файл обьектов Customer
        try {
            FileOutputStream fos = new FileOutputStream(OBJECT_SAVE_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(records);
            oos.flush();
            oos.close();
            fos.close();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static Object readData() { // считать файл обьектов Customer
        try {
        FileInputStream fis = new FileInputStream(OBJECT_SAVE_FILE);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object customers = ois.readObject();
        ois.close();
        fis.close();
        return customers;
        }
        catch(ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*private void addSign()
    {
        addLine(FILE_SIGN);
    }*/

    /*public boolean checkSign() // проверка фала
    {
        String buf = getLine(-1);
        if(buf != null && buf.equals(FILE_SIGN)) {
            System.out.println("Data file confirmed...");
            return true; // exit with confirmation
        }
        else {
            System.out.println("Data file corrupted...");
            File file = new File(FILE_PATH);
            try {
                if(file.createNewFile()) {
                    System.out.println("New data file created...");
                    LOCK = true; // file unlocking
                    addSign();
                    return true; // exit with confirmation
                }
                else
                    System.out.println("New data file created is failed...");
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return false; // exit without confirmation
    }*/
}
