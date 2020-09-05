package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.Save;
import com.epam.rd.java.basic.practice7.Sorter;
import com.epam.rd.java.basic.practice7.entity.Gem;
import com.epam.rd.java.basic.practice7.entity.Reserve;
import com.epam.rd.java.basic.practice7.entity.VisualParameters;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StAXPars {
    private final Logger logger = Logger.getLogger(StAXPars.class.getName());
    Reserve reserve;
    private String nameGem;
    private String origin;
    private String color;
    private String nameInputFile;

    public static void main(String[] args) {
        informationToXml(args[0], "output.stax.xml");
    }

    public static String informationToXml(String nameFile, String nameOutputFile) {
        String output = "";
        StAXPars stAXPars = createParser(nameFile);
        Save save = new Save();
        try {
            output = save.saveToXML(stAXPars.reserve, nameOutputFile);
        } catch (JAXBException e) {
            stAXPars.logger.log(Level.WARNING, e.getMessage());
        }
        return output;
    }

    public static StAXPars createParser(String nameFile) {
        StAXPars stAXPars = new StAXPars(nameFile);
        stAXPars.parse();
        System.out.println("BEFORE SORT" + stAXPars.reserve.getGemList());
        Collections.sort(stAXPars.reserve.getGemList(), Sorter.SORT_GEM_BY_ORIGIN);
        System.out.println("AFTER SORT" + stAXPars.reserve.getGemList());
        return stAXPars;
    }

    public StAXPars(String nameInputFile) {
        this.nameInputFile = nameInputFile;
    }

    public String parse() {
        int countOfFaces;
        reserve = new Reserve();
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            factory.setProperty("javax.xml.stream.isSupportingExternalEntities", false);
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader(nameInputFile));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String currentElement = startElement.getName().getLocalPart();
                    switch (currentElement) {
                        case "nameGem":
                            event = eventReader.nextEvent();
                            nameGem = event.asCharacters().getData();
                            break;
                        case "origin":
                            event = eventReader.nextEvent();
                            origin = event.asCharacters().getData();
                            break;
                        case "color":
                            event = eventReader.nextEvent();
                            color = event.asCharacters().getData();
                            break;
                        case "countOfFaces":
                            event = eventReader.nextEvent();
                            countOfFaces = Integer.valueOf(event.asCharacters().getData());
                            reserve.getGemList().add(new Gem(nameGem, origin, new VisualParameters(color, countOfFaces)));
                            break;
                        default:
                            // will NOT execute because of the line preceding the switch.
                    }
                }
            }
            System.out.println(reserve.getGemList().toString());

        } catch (FileNotFoundException | XMLStreamException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return reserve.getGemList().toString();
    }
}
