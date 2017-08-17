package io;

import data.Group;
import data.Product;
import data.Subgroup;
import data.TerminalGroup;
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
 * @author ivan
 */
public class CatalogReader {

    private DocumentBuilderFactory factory;
    private DocumentBuilder documentBuilder;
    private Document doc;

    public CatalogReader() {
        try {
            factory = DocumentBuilderFactory.newInstance();
            documentBuilder = factory.newDocumentBuilder();
            doc = documentBuilder.parse("res/catalog.xml");
        } catch (ParserConfigurationException e) {
            System.err.println(e.getMessage());
        } catch (SAXException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public ArrayList<Group> readGroups() {
        
        ArrayList<Group> groups = new ArrayList();
        NodeList grp = doc.getElementsByTagName("Group");
        
        for(int i = 0; i < grp.getLength(); i++) {
            Element element = (Element) grp.item(i);
            groups.add(new Group(element.getAttribute("name"), element.getAttribute("creationDate"), element.getAttribute("ModifiedDate")));
        }
        
        return groups;
    }
    
    public ArrayList<Subgroup> readSubgroups() {
        
        ArrayList<Subgroup> subgroups = new ArrayList();
        NodeList sgrp = doc.getElementsByTagName("Subgroup");
        
        for(int i = 0; i < sgrp.getLength(); i++) {
            
            Element subgroup = (Element) sgrp.item(i);
            NodeList ag = subgroup.getElementsByTagName("AGroup");
            
            ArrayList<String> accessGrps = new ArrayList();
            
            for(int j = 0; j < ag.getLength(); j++) {
                accessGrps.add(ag.item(j).getTextContent());
            }
            
            Subgroup s = new Subgroup(subgroup.getAttribute("name"), 0, accessGrps, subgroup.getAttribute("creationDate"), subgroup.getAttribute("modifiedDate"));
            //s.setNumber(Integer.parseInt(subgroup.getAttribute("number")));
            subgroups.add(s);
        }
        
        return subgroups;
    }
    
    public ArrayList<Product> readProducts() {
        
        ArrayList<Product> products = new ArrayList();
        NodeList prod = doc.getElementsByTagName("Product");
        
        for(int i = 0; i < prod.getLength(); i++) {
            
            Element product = (Element) prod.item(i);
            NodeList asg = product.getElementsByTagName("ASubgroup");
            
            ArrayList<String> accessSgrp = new ArrayList();
            
            for(int j = 0; j < asg.getLength(); j++) {
                accessSgrp.add(asg.item(j).getTextContent());
            }
            
            products.add(new Product(product.getAttribute("name"), product.getAttribute("plu"), product.getAttribute("picturePath"), accessSgrp, product.getAttribute("creationDate")));
        }
        
        return products;
    }
    
    public ArrayList<TerminalGroup> readTerminalGroups() {
        
        ArrayList<TerminalGroup> terminalGroups = new ArrayList();
        NodeList termGrp = doc.getElementsByTagName("TerminalGroup");
        
        for(int i = 0; i < termGrp.getLength(); i++) {
            
            Element terminalGroup = (Element) termGrp.item(i);
            NodeList terminals = terminalGroup.getElementsByTagName("Terminal");
            
            ArrayList<String> terms = new ArrayList();
            
            for(int j = 0; j < terminals.getLength(); j++) {
                terms.add(((Element) terminals.item(j)).getAttribute("id"));
            }
            
            terminalGroups.add(new TerminalGroup(terminalGroup.getAttribute("name"), terms));
        }
        
        return terminalGroups;
    }
}
