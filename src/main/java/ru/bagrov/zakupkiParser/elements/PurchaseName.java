package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class PurchaseName {

    public static String getPurchaseName(Element element) {
        return element.getElementsByAttributeValue("class", "registry-entry__body-value").text();
    }
}
