package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.Save;
import com.epam.rd.java.basic.practice7.Sorter;
import com.epam.rd.java.basic.practice7.entity.Gem;
import com.epam.rd.java.basic.practice7.entity.Reserve;
import com.epam.rd.java.basic.practice7.entity.VisualParameters;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DOMPars {
    private final Logger logger = Logger.getLogger(DOMPars.class.getName());
    private Reserve reserve;
    String nameInputFile;

    public DOMPars(String nameInputFile) {
        this.nameInputFile = nameInputFile;
    }

    public static void main(String[] args) {
        System.out.println(informationToXml(args[0],"output.dom.xml"));
    }

    public static String informationToXml(String nameInputFile, String nameOutputFile) {
        DOMPars pars = new DOMPars(nameInputFile);
        pars.parse();
        String output = "";
        Collections.sort(pars.reserve.getGemList(), Sorter.SORT_GEM_BY_NAME);
        Save save = new Save();
        try {
            output = save.saveToXML(pars.reserve, nameOutputFile);
        } catch (JAXBException e) {
            pars.logger.log(Level.WARNING, e.getMessage());
        }
        return output;
    }

    public String parse() {
        reserve = new Reserve();

        String parsingGems = null;
        File file = new File(nameInputFile);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, ""); // Compliant
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, ""); // compliant
        try {
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            NodeList nodeList = document.getElementsByTagName("gem");
            System.out.println("root element " + document.getDocumentElement().getNodeName());
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("Current elem" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String nameGem = element.getElementsByTagName("nameGem").item(0).getTextContent();
                    String origin = element.getElementsByTagName("origin").item(0).getTextContent();
                    String color = element.getElementsByTagName("color").item(0).getTextContent();
                    int countOfFaces = Integer.parseInt(element.getElementsByTagName("countOfFaces").item(0).getTextContent());
                    Gem gem = new Gem(nameGem, origin, new VisualParameters(color, countOfFaces));
                    System.out.println(gem);
                    reserve.getGemList().add(gem);
                }
            }
            parsingGems = reserve.getGemList().toString();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return parsingGems;
    }
}
