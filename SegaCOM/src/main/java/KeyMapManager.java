import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ivan
 */
public class KeyMapManager implements Serializable {
    
    private final Map<String, KeyMap> layouts;
    
    public KeyMapManager() {
        layouts = new HashMap();
        layouts.put("Default", new KeyMap(KeyMap.DEFAULT));
    }
    
    public void addLayout(String name, int[][] keys) {
        
    }
    
    public int[][] getLayout(String name) {
        return layouts.get(name).getKeyMap();
    }
}
