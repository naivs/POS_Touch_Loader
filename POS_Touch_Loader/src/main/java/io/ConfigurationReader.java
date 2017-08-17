package io;

import data.Group;
import data.Product;
import data.Subgroup;
import data.TerminalGroup;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

    public ConfigurationReader() {
        try {
            factory = DocumentBuilderFactory.newInstance();
            documentBuilder = factory.newDocumentBuilder();
            doc = documentBuilder.parse("res/configuration.xml");
        } catch (ParserConfigurationException e) {
            System.err.println(e.getMessage());
        } catch (SAXException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<TerminalGroup> read() {

        ArrayList<TerminalGroup> terminalGroups = new ArrayList();
        NodeList termGrps = doc.getElementsByTagName("TerminalGroup");

        for (int a = 0; a < termGrps.getLength(); a++) {

            // terminalGroup
            Element elementA = (Element) termGrps.item(a);
            ArrayList<String> terminals = new ArrayList();
            terminals.addAll(Arrays.asList(elementA.getAttribute("terminals").split(":")));
            terminalGroups.add(new TerminalGroup(elementA.getAttribute("name"), terminals));

            // daysOfWeek
            NodeList dow = elementA.getElementsByTagName("DayOfWeek");

            // groups
            for (int b = 0; b < terminalGroups.get(a).getDaysOfWeek().length; b++) {
                terminalGroups.get(a).getDaysOfWeek()[b].setGroups(readGroups(termGrps, a, b));
                terminalGroups.get(a).getDaysOfWeek()[b].setModifiedDate(((Element) dow.item(b)).getAttribute("lastConfigured"));
            }
        }

        return terminalGroups;
    }

    private Group[] readGroups(NodeList termGrps, int terminalGroup, int dayOfWeek) {
        
        NodeList groupsList = ((Element) ((Element) termGrps.item(terminalGroup)).getElementsByTagName("DayOfWeek").item(dayOfWeek)).getElementsByTagName("Group");

        Group[] groups = new Group[8];
        
        for(int i = 0; i < groupsList.getLength(); i++) {
            Element grp = (Element) groupsList.item(i);
            Group group = new Group(grp.getAttribute("name"), grp.getAttribute("creationDate"), grp.getAttribute("modifiedDate"));
            //group.setNumber(Integer.parseInt(grp.getAttribute("number")));
            group.setSubgroups(readSubgroups(groupsList, i));
            
            groups[Integer.parseInt(grp.getAttribute("number"))] = group;
        }

        return groups;
    }
    
    private Subgroup[] readSubgroups(NodeList groupsList, int group) {
        
        NodeList subgroupsList = ((Element) groupsList.item(group)).getElementsByTagName("Subgroup");
        Subgroup[] subgroups = new Subgroup[8];
        
        for(int i = 0; i < subgroupsList.getLength(); i++) {
            Element sgrp = (Element) subgroupsList.item(i);
            Subgroup subgroup = new Subgroup(sgrp.getAttribute("name"), Integer.parseInt(sgrp.getAttribute("index")), null, sgrp.getAttribute("creationDate"), sgrp.getAttribute("modifiedDate"));
            subgroup.setPicturePath(sgrp.getAttribute("picPath"));
            subgroup.setProducts(readProducts(subgroupsList, i));
            
            subgroups[Integer.parseInt(sgrp.getAttribute("number"))] = subgroup; //subgroup.getNumber()) - 1] = subgroup;
        }
        
        return subgroups;
    }
    
    private Product[] readProducts(NodeList productList, int subgroup) {
        
        NodeList productsList = ((Element) productList.item(subgroup)).getElementsByTagName("Product");
        Product[] products = new Product[20];
        
        for(int i = 0; i < productsList.getLength(); i++) {
            Element prod = (Element) productsList.item(i);
            Product product = new Product(prod.getAttribute("name"), prod.getAttribute("plu"), prod.getAttribute("picPath"), null, prod.getAttribute("creationDate"));
            //product.setNumber(Integer.parseInt(prod.getAttribute("number")));
            
            products[Integer.parseInt(prod.getAttribute("number"))] = product;
        }
        
        return products;
    }
}
