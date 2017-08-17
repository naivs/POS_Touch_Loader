package data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author ivan
 */
public class Product implements Serializable {

    private final String name;
    private final String plu;
    private final String picturePath;
    private List<String> accessibleSubgroups;
    private boolean isValid;
    private final String creationDate;

    public Product(String name, String plu, String picturePath, List<String> accessibleSubgroups) {
        
        this(name, plu, picturePath, accessibleSubgroups, Calendar.getInstance().getTime().toString());
    }

    public Product(String name, String plu, String picturePath, List<String> accessibleSubgroups, String creationDate) {
        
        this.name = name;
        this.plu = plu;
        this.picturePath = picturePath;
        this.accessibleSubgroups = accessibleSubgroups;
        this.creationDate = creationDate;
    }

    public String getPlu() {
        return plu;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public List<String> getAccessibleSubgroups() {
        return accessibleSubgroups;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setAccessibleSubgroups(List<String> accessibleSubgroups) {
        this.accessibleSubgroups = accessibleSubgroups;
    }

    public void setRemoved(boolean statement) {
        isValid = statement;
    }

    public String getName() {
        return name;
    }

    public boolean isValid() {
        return isValid;
    }

    @Override
    public String toString() {
        return name;
    }
}
