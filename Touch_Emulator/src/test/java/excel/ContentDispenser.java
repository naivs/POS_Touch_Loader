/*
 * Copyright (C) 2018 Ivan Naumov
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
package excel;

import java.util.Random;

/**
 *
 * @author Ivan Naumov
 */
public class ContentDispenser {

    /*
        the first part must be have max 14 symbols
        the second part must be have max 18 symbols
        divides by "::"
     */
    private static final String[] groups_1 = {
        "Горячие",
        "Холодные",
        "WOK",
        "Отличные",
        "Дешевые"
    };
    private static final String[] groups_2 = {
        "Блюда",
        "Напитки",
        "Акции",
        "Обеды",
        "Игрушки"
    };

    /*
        the first part must be have max 12 symbols
        the second part must be have max 18 symbols
        divides by "::"
     */
    private static final String[] subGroups_1 = {
        "Скрытные",
        "Молочные",
        "Твердые",
        "Жидкие",
        "Газообразные"
    };
    private static final String[] subGroups_2 = {
        "Котлеты",
        "Салаты",
        "Напитки",
        "Маффины",
        "Соусы",
        "Пельмени"
    };

    private static final String[] products_1 = {
        "БУРИТТО",
        "ЖАРКОЕ",
        "ЛАЗАНЬЯ",
        "ежевика",
        "клубника",
        "малина",
        "кисель",
        "клубничный крем",
        "крем-брюле",
        "круассан",
        "лакомка",
        "мусс",
        "ПИРОЖОК",
        "КАША",
        "СОСИСКИ",
        "САРДЕЛЬКА",
        "блинчик"
    };
    private static final String[] products_2 = {
        "ИЗ ИНДЕЙКИ",
        "С ГРИБАМИ",
        "МЯСНАЯ",
        "со сливками",
        "с пудингом",
        "вишневый",
        "шоколадный",
        "со сливками и карамелью",
        "с вишней",
        "С КАПУСТОЙ И ЯЙЦОМ",
        "С КАРТОФЕЛЕИ И ГРИБАМИ",
        "С КУРИЦЕЙ И ГРИБАМИ",
        "С МЯСОМ И РИСОМ",
        "с творогом",
        "с БАНАНОМ",
        "с КУРИЦЕЙ",
        "с МЯСОМ",
        "с картофелем и грибами",
        "с капустой и яйцом"
    };

    private Random random;

    public ContentDispenser() {
        random = new Random();
    }

    public String[] getGroupNames() {
        String[] names = new String[8];
        for (int i = 0; i < names.length; i++) {
            names[i] = groups_1[random.nextInt(groups_1.length)] + "::";
            names[i] += groups_2[random.nextInt(groups_2.length)];
        }
        return names;
    }

    public String[] getSubgroupNames() {
        String[] names = new String[8];
        for (int i = 0; i < names.length; i++) {
            names[i] = subGroups_1[random.nextInt(subGroups_1.length)] + "::";
            names[i] += subGroups_2[random.nextInt(subGroups_2.length)];
        }
        return names;
    }

    public String[] getProductNames() {
        String[] names = new String[20];
        for (int i = 0; i < names.length; i++) {
            names[i] = products_1[random.nextInt(products_1.length)] + " ";
            names[i] += products_2[random.nextInt(products_2.length)];
        }
        return names;
    }

    public int[] getProductPlus() {
        int[] plus = new int[20];
        for (int i = 0; i < plus.length; i++) {
            plus[i] = random.nextInt(999999);
        }
        return plus;
    }
}
