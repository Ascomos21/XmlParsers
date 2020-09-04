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
    private Reserve reserve;
    String nameGem;
    String origin;
    String color;
    int countOfFaces;
    String nameInputFile;

    public static void main(String[] args) {
        StAXPars stAXPars = new StAXPars(args[0]);
        stAXPars.parse();
        System.out.println("BEFORE SORT" + stAXPars.reserve.getGemList());
        Collections.sort(stAXPars.reserve.getGemList(), Sorter.SORT_GEM_BY_ORIGIN);
        System.out.println("AFTER SORT" + stAXPars.reserve.getGemList());
        Save save = new Save();
        try {
            System.out.println(save.saveToXML(stAXPars.reserve, "output.stax.xml"));
        } catch (JAXBException e) {
            stAXPars.logger.log(Level.WARNING, e.getMessage());
        }
    }

    public StAXPars(String nameInputFile) {
        this.nameInputFile = nameInputFile;
    }

    public String parse() {
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
