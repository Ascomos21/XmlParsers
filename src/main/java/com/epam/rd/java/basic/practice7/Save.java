package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.entity.Reserve;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Save {
    private final Logger logger = Logger.getLogger(Save.class.getName());

    public String saveToXML(Reserve reserve, String nameFile)
            throws JAXBException {
        // Test -> DOM -> XML
        // create an instance of `JAXBContext`
        JAXBContext context = JAXBContext.newInstance(Reserve.class);

        // create an instance of `Marshaller`
        Marshaller marshaller = context.createMarshaller();

        // enable pretty-print XML output
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // write XML to `StringWriter`
        StringWriter sw = new StringWriter();

        // create `Book` object
        marshaller.marshal(reserve, sw);
        inputInFile(sw, nameFile);
        // print the XML
        return sw.toString();
    }

    private void inputInFile(StringWriter stringWriter, String nameFile) {
        try (FileWriter writer = new FileWriter(nameFile)) {
            writer.write(stringWriter.toString());
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}
