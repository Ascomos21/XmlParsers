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

public class StAXPars {
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
            save.saveToXML(stAXPars.reserve, "output.stax.xml");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public StAXPars(String nameInputFile) {
        this.nameInputFile = nameInputFile;
    }

    public void parse() {
        reserve = new Reserve();
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
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
                    }
                }

            }
            System.out.println(reserve.getGemList().toString());

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
