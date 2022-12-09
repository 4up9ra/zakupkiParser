package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class Customer {

    public static String getCustomer(Element element) {
        return element.getElementsByAttributeValue("target", "_blank").get(2).text();
    }

}
