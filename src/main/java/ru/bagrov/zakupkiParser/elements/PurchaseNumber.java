package ru.bagrov.zakupkiParser.elements;

import org.jsoup.nodes.Element;

public class PurchaseNumber {

    public static String getPurchaseNumber(Element element) {
        return element.getElementsByAttributeValue("class", "registry-entry__header-mid__number").text();
    }
}
