package ru.liga.utils;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

@Component
public class XpathQueryAnalyzer {
    private final XPath xpath;

    public XpathQueryAnalyzer() {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        this.xpath = xPathFactory.newXPath();
    }

    public String getQueryStringResult(String expression, Document document) {
        try {
            return xpath.evaluate(expression, document);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Node getQueryNodeResult(String expression, Document document) {
        try {
            return (Node) xpath.compile(expression).evaluate(document, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }
    public XPath getXpath() {
        return xpath;
    }
}
