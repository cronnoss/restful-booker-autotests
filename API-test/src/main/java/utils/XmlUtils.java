package utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

public class XmlUtils {

    public static final String XPATH_PREFIX = "//*[local-name()=";

    public static Document getDocumentFromString(String string) {
        return getDocumentFromByteArray(string.getBytes(StandardCharsets.UTF_8));
    }

    @SneakyThrows
    public static Document getDocumentFromByteArray(byte[] byteArray) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        return documentBuilder.parse(byteArrayInputStream);
    }

    @SneakyThrows
    public static Document getDocumentWithoutNode(Document document, String xpathExpression) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node = (Node) xPath.compile(xpathExpression).evaluate(document, XPathConstants.NODE);
        node.getParentNode().removeChild(node);
        return node.getOwnerDocument();
    }

    @SneakyThrows
    public static Document getDocumentWithUpdatedNodeAttribute(Document document, String xpathExpression, String attributeName, String newValue) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        Element node = (Element) xPath.evaluate(xpathExpression, document, XPathConstants.NODE);
        node.setAttribute(attributeName, newValue);
        return node.getOwnerDocument();
    }

    @SneakyThrows
    public static Document getDocumentWithoutNodeAttribute(Document document, String xpathExpression, String attributeName) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        Node node = (Node) xPath.compile(xpathExpression).evaluate(document, XPathConstants.NODE);
        Element element = (Element) node;
        element.removeAttribute(attributeName);
        node = element;
        return node.getOwnerDocument();
    }

    @SneakyThrows
    public static boolean isNodeExists(Document document, String xpathExpression) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        return xPath.compile(xpathExpression).evaluate(document, XPathConstants.NODE) != null;
    }

    @SneakyThrows
    public static String getNodeValue(Document document, String xpathExpression) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        return Optional.ofNullable((Node) xPath.compile(xpathExpression).evaluate(document, XPathConstants.NODE))
                .map(Node::getNodeValue).orElse(null);
    }

    @SneakyThrows
    public static Document getDocumentWithUpdatedNodeValue(Document document, String xpathExpression, String newValue) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        Element node = (Element) xPath.evaluate(xpathExpression, document, XPathConstants.NODE);
        node.setTextContent(newValue);
        return node.getOwnerDocument();
    }

    @SneakyThrows
    public static String getStringFromDocument(Document document) {
        DOMSource domSource = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);
        return writer.toString();
    }

    @SneakyThrows
    public static void updateDocument(Document updatedDoc, Map<String, String> data) {
        XPath xPath = XPathFactory.newInstance().newXPath();
        for (var entry : data.entrySet()) {
            var evaluate = xPath.evaluate(entry.getKey(), updatedDoc, XPathConstants.NODE);
            var node = (Node) evaluate;
            node.setTextContent(entry.getValue());
        }
    }

    public static String pojoToXml(Object data) {
        String xml = "";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(data.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(data, stringWriter);
            xml = stringWriter.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
