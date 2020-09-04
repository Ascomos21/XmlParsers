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
        DOMPars pars = new DOMPars(args[0]);
        pars.parse();
        System.out.println("BEFORE SOORt" + pars.reserve.getGemList());

        Collections.sort(pars.reserve.getGemList(), Sorter.SORT_GEM_BY_NAME);
        Save save = new Save();
        System.out.println("AFTER SORT" + pars.reserve.getGemList());
        try {
            System.out.println(save.saveToXML(pars.reserve, "output.dom.xml"));
        } catch (JAXBException e) {
            pars.logger.log(Level.WARNING, e.getMessage());
        }

    }

    public String parse() {
        String FEATURE = null;
        String parsingGems = null;
        File file = new File(nameInputFile);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
        try {
            factory.setFeature(FEATURE, true);
            FEATURE = "http://xml.org/sax/features/external-general-entities";
            factory.setFeature(FEATURE, false);

            FEATURE = "http://xml.org/sax/features/external-parameter-entities";
            factory.setFeature(FEATURE, false);

            FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
            factory.setFeature(FEATURE, false);
        } catch (ParserConfigurationException e) {
            logger.log(Level.WARNING, e.getMessage());
        }


        reserve = new Reserve();
        try {
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
