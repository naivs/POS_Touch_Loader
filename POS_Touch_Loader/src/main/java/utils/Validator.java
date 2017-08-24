package utils;

import data.Group;
import data.Product;
import data.Regular;
import data.Subgroup;
import data.TerminalGroup;
import io.ConfigurationReader;
import java.util.ArrayList;

/**
 *
 * @author Ivan
 */
public class Validator {

    private Regular catalog;

    private ConfigurationReader configurationReader;
    private ArrayList<TerminalGroup> configuration;

    public Validator(Regular regular) {

        catalog = regular;

        //configurationReader = new ConfigurationReader();
        configuration = new ArrayList();
    }

    public ArrayList<TerminalGroup> validate() {

        configuration = configurationReader.read();

        // terminal groups validation
        for (TerminalGroup termGrp : configuration) {

            boolean isValid = false;

            for (TerminalGroup tg : catalog.getTerminalGroups()) {
                if (termGrp.toString().equals(tg.toString())) {
                    isValid = true;
                }
            }

            termGrp.setRemoved(!isValid);
        }

        // groups validation
        for (int a = 0; a < configuration.size(); a++) {
            for (int b = 0; b < configuration.get(a).getDaysOfWeek().length; b++) {
                for (int c = 0; c < configuration.get(a).getDaysOfWeek()[b].getGroupCount(); c++) {

                    Group bufGroup = configuration.get(a).getDaysOfWeek()[b].getGroup(c);
                    if (bufGroup != null) {
                        boolean isValid = false;

                        for (Group gr : catalog.getGroups()) {

                            // subgroups validation
                            for (int d = 0; d < bufGroup.getSubgroupCount(); d++) {

                                Subgroup bufSubgroup = bufGroup.getSubgroup(d);
                                if (bufSubgroup != null) {
                                    boolean isVal = false;

                                    for (Subgroup sgrp : catalog.getSubgroups()) {

                                        // product validation
                                        for (int e = 0; e < bufSubgroup.getProductCount(); e++) {

                                            Product bufProduct = bufSubgroup.getProduct(e);
                                            if (bufProduct != null) {
                                                boolean isV = false;

                                                for (Product prod : catalog.getProducts()) {
                                                    if (bufProduct.toString().equals(prod.toString())) {
                                                        isV = true;
                                                        bufProduct.setAccessibleSubgroups(prod.getAccessibleSubgroups());
                                                        break;
                                                    }
                                                }

                                                bufProduct.setRemoved(!isV);
                                            }
                                        }
                                        // =====================

                                        if (bufSubgroup.toString().equals(sgrp.toString())) {
                                            isVal = true;
                                            bufSubgroup.setAccessibleGroups(sgrp.getAccessibleGroups());
                                            break;
                                        }
                                    }

                                    bufSubgroup.setRemoved(!isVal);
                                }
                            }
                            // ====================

                            if (bufGroup.toString().equals(gr.toString())) {
                                isValid = true;
                                break;
                            }
                        }

                        bufGroup.setRemoved(!isValid);
                    }
                }
            }
        }

        return configuration;
    }
}
