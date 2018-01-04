/*
 * Copyright (C) 2017 ivan
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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivan
 */
public abstract class Container extends Component {

    private final List<Component> components;

    Container(String name, int number) {
        super(name, number);
        components = new ArrayList<>();
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public Component getComponent(Component component) {
        for(Component comp : components) {
            if(component.equals(comp)) {
                return comp;
            }
        }
        throw new IllegalArgumentException(String.format("%s not found...", component.toString()));
    }

    public Component getComponent(int component) {
        return components.get(component);
    }
    
    public List<Component> getComponents() {
        return components;
    }
    
    public void removeComponent(Component component) {
        components.remove(component);
    }

    public void removeComponent(int component) {
        components.remove(component);
    }

    public void removeAll() {
        components.removeAll(components);
    }
    
    public int getComponentsCount() {
        return components.size();
    }
}
