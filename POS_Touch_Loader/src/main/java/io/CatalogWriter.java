package io;

import data.*;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author ivan
 */
public class CatalogWriter {
    
    private DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder icBuilder;
    private Document doc;

    public CatalogWriter() {
        try {
            icBuilder = icFactory.newDocumentBuilder();
            doc = icBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            System.err.println(e.getMessage());
        }
    }

    public void write(ArrayList<TerminalGroup> terminalGroups, ArrayList<Group> groups, ArrayList<Subgroup> subgroups, ArrayList<Product> products) throws TransformerException {

        Element root = doc.createElement("Catalog");

        Element rootTermGrp = doc.createElement("TerminalGroups");
        for (TerminalGroup terminalGroup : terminalGroups) {
            Element termGroupElement = doc.createElement("TerminalGroup");
            termGroupElement.setAttribute("name", terminalGroup.toString());
            Element terminalsElement = doc.createElement("Terminals");
//            for(String terminal : terminalGroup.getTerminals()) {
//                Element term = doc.createElement("Terminal");
//                term.setAttribute("id", terminal);
//                terminalsElement.appendChild(term);
//            }
            termGroupElement.appendChild(terminalsElement);
            rootTermGrp.appendChild(termGroupElement);
        }
        root.appendChild(rootTermGrp);

        Element rootGroups = doc.createElement("Groups");
        for (Group group : groups) {
            Element groupElement = doc.createElement("Group");
            groupElement.setAttribute("name", group.getName());
            groupElement.setAttribute("creationDate", group.getCreationDate());
            
            rootGroups.appendChild(groupElement);
        }
        root.appendChild(rootGroups);
        
        Element rootSubgroups = doc.createElement("Subgroups");
        for (Subgroup subgroup : subgroups) {
            Element subgroupElement = doc.createElement("Subgroup");
            subgroupElement.setAttribute("name", subgroup.getName());
            subgroupElement.setAttribute("creationDate", subgroup.getCreationDate());
            Element accessibleGroupsElement = doc.createElement("AccessibleGroups");
            subgroupElement.appendChild(accessibleGroupsElement);
            
            for(String accessibleGroup : subgroup.getAccessibleGroups()) {
                Element accessGrp = doc.createElement("AGroup");
                accessGrp.setTextContent(accessibleGroup);
                accessibleGroupsElement.appendChild(accessGrp);
            }
            
            rootSubgroups.appendChild(subgroupElement);
        }
        root.appendChild(rootSubgroups);
        
        Element rootProducts = doc.createElement("Products");
        for (Product product : products) {
            Element productElement = doc.createElement("Product");
            productElement.setAttribute("name", product.toString());
            productElement.setAttribute("plu", product.getPlu());
            productElement.setAttribute("picturePath", product.getPicturePath());
            productElement.setAttribute("creationDate", product.getCreationDate());
            Element accessibleSubgroupsElement = doc.createElement("AccessibleSubgroups");
            for(String accessibleSubgroup : product.getAccessibleSubgroups()) {
                Element accessSgrp = doc.createElement("ASubgroup");
                accessSgrp.setTextContent(accessibleSubgroup);
                accessibleSubgroupsElement.appendChild(accessSgrp);
            }
            productElement.appendChild(accessibleSubgroupsElement);
            rootProducts.appendChild(productElement);
        }
        root.appendChild(rootProducts);

        doc.appendChild(root);

        // output DOM XML to console 
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult console = new StreamResult(new File("res/catalog.xml"));
        transformer.transform(source, console);

        System.out.println("\nXML DOM Created Successfully..");
    }
}
