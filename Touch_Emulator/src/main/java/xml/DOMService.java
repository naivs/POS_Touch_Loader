/*
 * Copyright (C) 2018 ivan
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
package xml;

import data.Day;
import data.Department;
import data.Group;
import data.Product;
import data.Subgroup;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ivan
 */
public class DOMService implements SerializationService {

    private DocumentBuilderFactory documentFactory;
    private DocumentBuilder documentBuilder;
    private DOMImplementation dom;
    private Document document;

    private enum Attributes {
        configuration, department, name, index, day, terminals,
        number, subgroup, group, product, plu, modified
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public DOMService() {
        documentFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentFactory.newDocumentBuilder();
            dom = documentBuilder.getDOMImplementation();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void serialize(List<Department> data, File file) {
        document = dom.createDocument(null, null, null);
        Element root = document.createElement(Attributes.configuration.name());
        document.appendChild(root);

        //departments
        data.forEach((container) -> {
            Element departmentElement = document.createElement(Attributes.department.name());
            root.appendChild(departmentElement);

            Department department = (Department) container;
            departmentElement.setAttribute(Attributes.name.name(), department.getName());
            departmentElement.setAttribute(Attributes.terminals.name(), department.getTerminalsAsString());
            departmentElement.setAttribute(Attributes.index.name(), 
                    department.getIndex() < 10 ? "00" + String.valueOf(department.getIndex()) : String.valueOf(department.getIndex()));
            departmentElement.setAttribute(Attributes.modified.name(), department.getModified());
            departmentElement.setAttribute(Attributes.number.name(), String.valueOf(department.getNumber()));

            //days
            for (int b = 0; b < department.getComponentsCount(); b++) {
                Element dayElement = document.createElement(Attributes.day.name());
                departmentElement.appendChild(dayElement);

                Day day = (Day) department.getComponent(b);
                dayElement.setAttribute(Attributes.name.name(), day.getName());
                dayElement.setAttribute(Attributes.number.name(), String.valueOf(day.getNumber()));

                //groups
                for (int c = 0; c < day.getComponentsCount(); c++) {
                    Element groupElement = document.createElement(Attributes.group.name());
                    dayElement.appendChild(groupElement);

                    Group group = (Group) day.getComponent(c);
                    groupElement.setAttribute(Attributes.name.name(), group.getName());
                    groupElement.setAttribute(Attributes.number.name(), String.valueOf(group.getNumber()));

                    //subgroups
                    for (int d = 0; d < group.getComponentsCount(); d++) {
                        Element subgroupElement = document.createElement(Attributes.subgroup.name());
                        groupElement.appendChild(subgroupElement);

                        Subgroup subgroup = (Subgroup) group.getComponent(d);
                        subgroupElement.setAttribute(Attributes.name.name(), subgroup.getName());
                        subgroupElement.setAttribute(Attributes.index.name(), 
                                subgroup.getIndex() < 10 ? "00" + String.valueOf(subgroup.getIndex()) : String.valueOf(subgroup.getIndex()));
                        subgroupElement.setAttribute(Attributes.number.name(), String.valueOf(subgroup.getNumber()));

                        //products
                        for (int e = 0; e < subgroup.getComponentsCount(); e++) {
                            Element productElement = document.createElement(Attributes.product.name());
                            subgroupElement.appendChild(productElement);

                            Product product = (Product) subgroup.getComponent(e);
                            productElement.setAttribute(Attributes.name.name(), product.getName());
                            productElement.setAttribute(Attributes.plu.name(), String.valueOf(product.getPlu()));
                            productElement.setAttribute(Attributes.number.name(), String.valueOf(product.getNumber()));
                        }
                    }
                }
            }
        });

        try {
            DOMSource domSource = new DOMSource(document);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result result = new StreamResult(file);
            transformer.transform(domSource, result);
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public List<Department> deserialize(File file) throws IOException {
        try {
            document = documentBuilder.parse(file);
        } catch (SAXException ex) {
            ex.printStackTrace();
        }

        ArrayList<Department> departments = new ArrayList<>();

        //departments
        NodeList departmentList = document.getElementsByTagName(Attributes.department.name());
        for (int a = 0; a < departmentList.getLength(); a++) {
            Element departmentElement = (Element) departmentList.item(a);
            Department department = new Department(
                    departmentElement.getAttribute(Attributes.name.name()),
                    departmentElement.getAttribute(Attributes.terminals.name()),
                    Integer.parseInt(departmentElement.getAttribute(Attributes.index.name())),
                    Integer.parseInt(departmentElement.getAttribute(Attributes.number.name()))
            );
            department.setModified(departmentElement.getAttribute(Attributes.modified.name()));
            departments.add(department);

            //days
            NodeList dayList = departmentElement.getElementsByTagName(Attributes.day.name());
            for (int b = 0; b < dayList.getLength(); b++) {
                Element dayElement = (Element) dayList.item(b);
                Day day = new Day(
                        dayElement.getAttribute(Attributes.name.name()),
                        Integer.parseInt(dayElement.getAttribute(Attributes.number.name()))
                );
                department.addComponent(day);

                //groups
                NodeList groupList = dayElement.getElementsByTagName(Attributes.group.name());
                for (int c = 0; c < groupList.getLength(); c++) {
                    Element groupElement = (Element) groupList.item(c);
                    Group group = new Group(
                            groupElement.getAttribute(Attributes.name.name()),
                            Integer.parseInt(groupElement.getAttribute(Attributes.number.name()))
                    );
                    day.addComponent(group);

                    //subgroups
                    NodeList subgroupList = groupElement.getElementsByTagName(Attributes.subgroup.name());
                    for (int d = 0; d < subgroupList.getLength(); d++) {
                        Element subgroupElement = (Element) subgroupList.item(d);
                        Subgroup subgroup = new Subgroup(
                                subgroupElement.getAttribute(Attributes.name.name()),
                                Integer.parseInt(subgroupElement.getAttribute(Attributes.index.name())),
                                Integer.parseInt(subgroupElement.getAttribute(Attributes.number.name()))
                        );
                        group.addComponent(subgroup);

                        //products
                        NodeList productList = subgroupElement.getElementsByTagName(Attributes.product.name());
                        for (int e = 0; e < productList.getLength(); e++) {
                            Element productElement = (Element) productList.item(e);
                            Product product = new Product(
                                    productElement.getAttribute(Attributes.name.name()),
                                    Long.parseLong(productElement.getAttribute(Attributes.plu.name())),
                                    Integer.parseInt(productElement.getAttribute(Attributes.number.name()))
                            );
                            subgroup.addComponent(product);
                        }
                    }
                }
            }
        }

        return departments;
    }

}
