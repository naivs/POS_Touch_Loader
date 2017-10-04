package data;

import java.io.Serializable;
import javax.swing.ImageIcon;

public class Button implements Serializable {
    
    private String number;
    private String plu;
    private String text;
    private ImageIcon image;
    
    //CONSTRUCTORS
    public Button(String number, String plu, String text, ImageIcon image) {
        
        this.number = number;
        this.plu = plu;
        this.text = text;
        this.image = image;
    }
    
    //GETTERS
    public String getNumber() {
        
        return number;
    }
    
    public String getPlu() {
        return plu;
    }
    
    public String getText() {
        return text;
    }
    
    public ImageIcon getImage() {
        return image;
    }
    
    //SETTERS
    public void setNumber(String number) {
        
        this.number = number;
    }
    
    public void setPlu(String plu) {
        
        this.plu = plu;
    }
    
    public void setText(String text) {
        
        this.text = text;
    }
    
    public void setImage(ImageIcon image) {
        
        this.image = image;
    }
}
