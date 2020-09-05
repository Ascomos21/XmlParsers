package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.Save;
import com.epam.rd.java.basic.practice7.Sorter;
import com.epam.rd.java.basic.practice7.entity.Gem;
import com.epam.rd.java.basic.practice7.entity.Reserve;
import com.epam.rd.java.basic.practice7.entity.VisualParameters;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SAXPars extends DefaultHandler {
    private final Logger logger = Logger.getLogger(SAXPars.class.getName());
    private Reserve reserve;
    String nameInputFile;

    public SAXPars(String nameInputFile) {
        this.nameInputFile = nameInputFile;
    }

    public static void main(String[] args) {
        informationInXml(args[0], "output.sax.xml");
    }

    public static String informationInXml(String nameFile, String nameOutputFile) {
        String output = "";
        SAXPars saxPars = new SAXPars(nameFile);
        saxPars.parse();
        Collections.sort(saxPars.reserve.getGemList(), Sorter.SORT_GEM_BY_COUNT_OF_FACES);
        try {
            Save save = new Save();
            output = save.saveToXML(saxPars.reserve, nameOutputFile);
        } catch (JAXBException e) {
            saxPars.logger.log(Level.WARNING, e.getMessage());
        }
        return output;
    }

    public String parse() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            SAXParser parser = factory.newSAXParser();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            parser.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, ""); // Compliant
            parser.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, ""); // compliant
            parser.parse(nameInputFile, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return reserve.getGemList().toString();
    }

    private String nameGem;
    private String originGem;
    private String visualParametersColorGem;

    private boolean name = false;
    private boolean origin = false;
    private boolean visualParametersColor = false;
    private boolean visualParametersCountOfFaces = false;

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {
        if ("reserve".equalsIgnoreCase(localName)) {
            reserve = new Reserve();
        }
        if (qName.equalsIgnoreCase("nameGem")) {
            name = true;
        }

        if (qName.equalsIgnoreCase("origin")) {
            origin = true;
        }
        if (qName.equalsIgnoreCase("color")) {
            visualParametersColor = true;
        }
        if (qName.equalsIgnoreCase("countOfFaces")) {
            visualParametersCountOfFaces = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        int visualParametersCountOfFacesGem;
        if (name) {
            nameGem = new String(ch, start, length);
            name = false;
        }
        if (origin) {
            originGem = new String(ch, start, length);
            origin = false;
        }
        if (visualParametersColor) {
            visualParametersColorGem = new String(ch, start, length);
            visualParametersColor = false;
        }
        if (visualParametersCountOfFaces) {
            visualParametersCountOfFacesGem = Integer.parseInt(new String(ch, start, length));
            visualParametersCountOfFaces = false;
            Gem gem = new Gem();
            gem.setNameGem(nameGem);
            gem.setOrigin(originGem);
            VisualParameters visualParameters = new VisualParameters();
            visualParameters.setCountOfFaces(visualParametersCountOfFacesGem);
            visualParameters.setColor(visualParametersColorGem);
            gem.setVisualParameters(visualParameters);
            reserve.getGemList().add(gem);
        }
    }
}