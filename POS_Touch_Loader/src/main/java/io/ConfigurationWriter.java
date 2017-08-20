package io;

import java.io.File;
import java.util.List;
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
public class ConfigurationWriter {

    private DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder icBuilder;
    private Document doc;

    public ConfigurationWriter() {
        try {
            icBuilder = icFactory.newDocumentBuilder();
            doc = icBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            System.err.println(e.getMessage());
        }
    }

    public void write(List<data.TerminalGroup> terminalGroups) throws TransformerException {

        Element root = doc.createElement("Configuration");
        // terminalGroups
        for (int a = 0; a < terminalGroups.size(); a++) {
            Element termGrp = doc.createElement("TerminalGroup");
            termGrp.setAttribute("name", terminalGroups.get(a).toString());
            termGrp.setAttribute("terminals", terminalGroups.get(a).getTerminalsAsString());

            // daysOfWeek
            for (int b = 0; b < terminalGroups.get(a).getDaysOfWeek().length; b++) {
                Element dayOfWeek = doc.createElement("DayOfWeek");
                dayOfWeek.setAttribute("name", terminalGroups.get(a).getDaysOfWeek()[b].toString());
                dayOfWeek.setAttribute("modifiedDate", terminalGroups.get(a).getDaysOfWeek()[b].getModifiedDate());

                // groups
                for (int c = 0; c < 8; c++) {
                    if (terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c) != null) {
                        Element group = doc.createElement("Group");
                        group.setAttribute("name", terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getName());
                        group.setAttribute("number", String.valueOf(c)); //terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getNumber());
                        group.setAttribute("creationDate", terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getCreationDate());
                        group.setAttribute("modifiedDate", terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getModifiedDate());

                        // subgroups
                        for (int d = 0; d < 8; d++) {
                            if (terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getSubgroup(d) != null) {
                                Element subgroup = doc.createElement("Subgroup");
                                subgroup.setAttribute("name", terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getSubgroup(d).getName());
                                subgroup.setAttribute("index", terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getSubgroup(d).getIndex());
                                subgroup.setAttribute("picPath", terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getSubgroup(d).getPicturePath());
                                subgroup.setAttribute("number", String.valueOf(d));//terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getSubgroup(d).getNumber());
                                subgroup.setAttribute("creationDate", terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getSubgroup(d).getCreationDate());
                                subgroup.setAttribute("modifiedDate", terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getSubgroup(d).getModifiedDate());

                                // products
                                for (int e = 0; e < 20; e++) {
                                    if (terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getSubgroup(d).getProduct(e) != null) {
                                        Element product = doc.createElement("Product");
                                        product.setAttribute("name", terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getSubgroup(d).getProduct(e).toString());
                                        product.setAttribute("plu", terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getSubgroup(d).getProduct(e).getPlu());
                                        product.setAttribute("picPath", terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getSubgroup(d).getProduct(e).getPicturePath());
                                        product.setAttribute("number", String.valueOf(e));//terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getSubgroup(d).getProduct(e).getNumber());
                                        product.setAttribute("creationDate", terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getSubgroup(d).getProduct(e).getCreationDate());
                                        
                                        subgroup.appendChild(product);
                                    }
                                }
                                group.appendChild(subgroup);
                            }
                        }
                        dayOfWeek.appendChild(group);
                    }
                }
                termGrp.appendChild(dayOfWeek);
            }
            root.appendChild(termGrp);
        }

        doc.appendChild(root);

        // output DOM XML to console 
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult console = new StreamResult(new File("resources/configuration.xml"));
        transformer.transform(source, console);

        System.out.println("\nXML DOM Created Successfully..");
    }
}
