package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.entity.Gem;
import com.epam.rd.java.basic.practice7.entity.Reserve;
import com.epam.rd.java.basic.practice7.entity.VisualParameters;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXPars extends DefaultHandler {
    private Reserve reserve;
    String nameInputFile;

    SAXPars(String nameInputFile) {
        this.nameInputFile = nameInputFile;
    }

    public static void main(String[] args) {
        SAXPars saxPars = new SAXPars(args[0]);
        try {
            saxPars.parse();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(saxPars.getReserve().getGemList().toString());
        try {
            Save save = new Save();

            save.saveToXML(saxPars.reserve, "output.sax.xml");
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public Reserve getReserve() {
        return reserve;
    }

    public void parse() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // XML document contains namespaces
        factory.setNamespaceAware(true);
        // set validation
        SAXParser parser = factory.newSAXParser();
        parser.parse(nameInputFile, this);
    }


    private String nameGem;
    private String originGem;
    private String visualParametersColorGem;
    private int visualParametersCountOfFacesGem;

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
    public void endElement(String uri, String localName,
                           String qName) {
        if (name)
            System.out.println("End Element :" + qName);

    }

    @Override
    public void characters(char ch[], int start, int length) {

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
            reserve.getGemList().add(new Gem(nameGem, originGem, new VisualParameters(visualParametersColorGem, visualParametersCountOfFacesGem)));
        }
    }


}
