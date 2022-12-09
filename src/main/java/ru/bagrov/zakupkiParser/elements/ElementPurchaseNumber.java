package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class ElementPurchaseNumber {

    public static String getElementPurchaseNumber(Element element) {
        return element.getElementsByAttributeValue("class", "registry-entry__header-mid__number").text();
    }
}
