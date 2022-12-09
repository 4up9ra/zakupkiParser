package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class ElementPurchaseName {

    public static String getElementPurchaseName(Element element) {
        return element.getElementsByAttributeValue("class", "registry-entry__body-value").text();
    }

}
