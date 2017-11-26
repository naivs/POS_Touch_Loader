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
package io;

import data.Group;
import data.Product;
import data.Subgroup;
import data.Department;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Ivan
 */
public class ConfigurationReader {

    private DocumentBuilderFactory factory;
    private DocumentBuilder documentBuilder;
    private Document doc;

    public ConfigurationReader() throws SAXException, IOException {
        try {
            factory = DocumentBuilderFactory.newInstance();
            documentBuilder = factory.newDocumentBuilder();
            doc = documentBuilder.parse("resources/configuration.xml");
        } catch (ParserConfigurationException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Department> read() {
        ArrayList<Department> terminalGroups = new ArrayList();
        NodeList termGrps = doc.getElementsByTagName("TerminalGroup");

        for (int a = 0; a < termGrps.getLength(); a++) {
            // terminalGroup
            Element elementA = (Element) termGrps.item(a);
            Department tg = new Department(Integer.parseInt(elementA.getAttribute("type")), elementA.getAttribute("name"), elementA.getAttribute("terminals"), elementA.getAttribute("startIndex"));
            tg.setModified(elementA.getAttribute("modified"));
            terminalGroups.add(tg);
//            // daysOfWeek
//            NodeList dow = elementA.getElementsByTagName("DayOfWeek");
            // groups
            for (int b = 0; b < terminalGroups.get(a).getDaysOfWeek().length; b++) {
                terminalGroups.get(a).getDaysOfWeek()[b].addAllGroups(readGroups(termGrps, a, b));
            }
        }

        return terminalGroups;
    }

    private Group[] readGroups(NodeList termGrps, int terminalGroup, int dayOfWeek) {
        NodeList groupsList = ((Element) ((Element) termGrps.item(terminalGroup)).getElementsByTagName("DayOfWeek").item(dayOfWeek)).getElementsByTagName("Group");

        Group[] groups = new Group[groupsList.getLength()];
        
        for(int i = 0; i < groupsList.getLength(); i++) {
            Element grp = (Element) groupsList.item(i);
            Group group = new Group(grp.getAttribute("name"));
            //group.setNumber(Integer.parseInt(grp.getAttribute("number")));
            group.setSubgroups(readSubgroups(groupsList, i));
            groups[Integer.parseInt(grp.getAttribute("number"))] = group;
        }

        return groups;
    }
    
    private Subgroup[] readSubgroups(NodeList groupsList, int group) {
        NodeList subgroupsList = ((Element) groupsList.item(group)).getElementsByTagName("Subgroup");
        Subgroup[] subgroups = new Subgroup[subgroupsList.getLength()];
        
        for(int i = 0; i < subgroupsList.getLength(); i++) {
            Element sgrp = (Element) subgroupsList.item(i);
            Subgroup subgroup = new Subgroup(sgrp.getAttribute("name"), Integer.parseInt(sgrp.getAttribute("index")));
            subgroup.setProducts(readProducts(subgroupsList, i));
            subgroups[Integer.parseInt(sgrp.getAttribute("number"))] = subgroup; //subgroup.getNumber()) - 1] = subgroup;
        }
        
        return subgroups;
    }
    
    private Product[] readProducts(NodeList productList, int subgroup) {
        NodeList productsList = ((Element) productList.item(subgroup)).getElementsByTagName("Product");
        Product[] products = new Product[productsList.getLength()];
        
        for(int i = 0; i < productsList.getLength(); i++) {
            Element prod = (Element) productsList.item(i);
            Product product = new Product(prod.getAttribute("name"), prod.getAttribute("plu"), prod.getAttribute("picPath"));
            //product.setNumber(Integer.parseInt(prod.getAttribute("number")));
            products[Integer.parseInt(prod.getAttribute("number"))] = product;
        }
        
        return products;
    }
}
