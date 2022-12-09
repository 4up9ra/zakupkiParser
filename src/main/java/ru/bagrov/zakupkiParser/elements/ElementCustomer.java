package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class ElementCustomer {

    public static String getElementCustomer(Element element) {
        return element.getElementsByAttributeValue("target", "_blank").get(2).text();
    }

}
