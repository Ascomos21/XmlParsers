package com.epam.rd.java.basic.practice7;

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

public class DOMPars {
    private Reserve reserve;
    String nameInputFile;

    DOMPars(String nameInputFile) {
        this.nameInputFile = nameInputFile;
    }
    public static void main(String[] args) {
        DOMPars pars = new DOMPars(args[0]);
        pars.parse(pars.nameInputFile);
        System.out.println("BEFORE SOORt" + pars.reserve.getGemList());

        Collections.sort(pars.reserve.getGemList(), Sorter.SORT_QUESTIONS_BY_QUESTION_TEXT);
        Save save = new Save();
        System.out.println("AFTER SORT" + pars.reserve.getGemList());
        try {
            save.saveToXML(pars.reserve, "output.dom.xml");
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public void parse(String nameFile) {

        File file = new File(nameFile);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
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
                    int countOfFaces = Integer.valueOf(element.getElementsByTagName("countOfFaces").item(0).getTextContent());
                    Gem gem = new Gem(nameGem, origin, new VisualParameters(color, countOfFaces));
                    System.out.println(gem);
                    reserve.getGemList().add(gem);
                }
            }
            System.out.println(reserve.getGemList());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
