package io;

import data.DayOfWeek;
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
        terminalGroups.stream().map((termGroup) -> {
            Element termGrp = doc.createElement("TerminalGroup");
            termGrp.setAttribute("name", termGroup.toString());
            termGrp.setAttribute("terminals", termGroup.getTerminalsAsString());
            termGrp.setAttribute("startIndex", termGroup.getStartIndex());
            // daysOfWeek
            for (DayOfWeek dayOfWeek : termGroup.getDaysOfWeek()) {
                Element dayElement = doc.createElement("DayOfWeek");
                dayElement.setAttribute("name", dayOfWeek.toString());
                dayElement.setAttribute("modifiedDate", dayOfWeek.getModifiedDate());
                // groups
                for (int c = 0; c < 8; c++) {
                    if (dayOfWeek.getGroup(c) != null) {
                        Element group = doc.createElement("Group");
                        group.setAttribute("name", dayOfWeek.getGroup(c).getName());
                        group.setAttribute("number", String.valueOf(c)); //terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getNumber());
                        group.setAttribute("creationDate", dayOfWeek.getGroup(c).getCreationDate());
                        group.setAttribute("modifiedDate", dayOfWeek.getGroup(c).getModifiedDate());
                        // subgroups
                        for (int d = 0; d < 8; d++) {
                            if (dayOfWeek.getGroup(c).getSubgroup(d) != null) {
                                Element subgroup = doc.createElement("Subgroup");
                                subgroup.setAttribute("name", dayOfWeek.getGroup(c).getSubgroup(d).getName());
                                subgroup.setAttribute("index", dayOfWeek.getGroup(c).getSubgroup(d).getIndex());
                                subgroup.setAttribute("picPath", dayOfWeek.getGroup(c).getSubgroup(d).getPicturePath());
                                subgroup.setAttribute("number", String.valueOf(d));//terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getSubgroup(d).getNumber());
                                subgroup.setAttribute("creationDate", dayOfWeek.getGroup(c).getSubgroup(d).getCreationDate());
                                subgroup.setAttribute("modifiedDate", dayOfWeek.getGroup(c).getSubgroup(d).getModifiedDate());
                                // products
                                for (int e = 0; e < 20; e++) {
                                    if (dayOfWeek.getGroup(c).getSubgroup(d).getProduct(e) != null) {
                                        Element product = doc.createElement("Product");
                                        product.setAttribute("name", dayOfWeek.getGroup(c).getSubgroup(d).getProduct(e).toString());
                                        product.setAttribute("plu", dayOfWeek.getGroup(c).getSubgroup(d).getProduct(e).getPlu());
                                        product.setAttribute("picPath", dayOfWeek.getGroup(c).getSubgroup(d).getProduct(e).getPicturePath());
                                        product.setAttribute("number", String.valueOf(e));//terminalGroups.get(a).getDaysOfWeek()[b].getGroup(c).getSubgroup(d).getProduct(e).getNumber());
                                        product.setAttribute("creationDate", dayOfWeek.getGroup(c).getSubgroup(d).getProduct(e).getCreationDate());
                                        subgroup.appendChild(product);
                                    }
                                }
                                group.appendChild(subgroup);
                            }
                        }
                        dayElement.appendChild(group);
                    }
                }
                termGrp.appendChild(dayElement);
            }
            return termGrp;
        }).forEachOrdered((termGrp) -> {
            root.appendChild(termGrp);
        });

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
