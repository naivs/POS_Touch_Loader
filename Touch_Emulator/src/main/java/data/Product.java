package data;

import java.io.Serializable;

/**
 *
 * @author ivan
 */
public class Product implements Serializable {

    private final String name;
    private final String plu;
    private final String picturePath;

    public Product(String name, String plu, String picturePath) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.plu = plu;
        this.picturePath = picturePath;
    }

    public String getPlu() {
        return plu;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
