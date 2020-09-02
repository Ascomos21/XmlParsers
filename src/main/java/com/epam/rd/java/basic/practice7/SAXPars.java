package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.entity.Gem;
import com.epam.rd.java.basic.practice7.entity.Reserve;
import com.epam.rd.java.basic.practice7.entity.VisualParameters;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class SAXPars extends DefaultHandler {
    private Reserve reserve;

    public static void main(String[] args) {
        SAXPars saxPars = new SAXPars();
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
            saveToXML(saxPars.reserve);
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
        parser.parse("input.xml", this);
    }

    int count = 0;

    private String nameGem;
    private String originGem;
    private String visualParametersColorGem;
    private int visualParametersCountOfFacesGem;

    private boolean name = false;
    private boolean origin = false;
    private boolean visualParametersColor = false;
    private boolean visualParametersCountOfFaces = false;

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
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

    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        if (name)
            System.out.println("End Element :" + qName);

    }

    public void characters(char ch[], int start, int length) throws SAXException {

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

    /*public static Document getDocument(Reserve reserve) throws ParserConfigurationException {

        // obtain DOM parser
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // set properties for Factory

        // XML document contains namespaces
        dbf.setNamespaceAware(true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        // create root element
        Element tElement = document.createElement("reserve");

        // add root element
        document.appendChild(tElement);

        // add questions elements
        for (Gem gem : reserve.getGemList()) {

            // add question
            Element qElement = document.createElement("gem");
            tElement.appendChild(qElement);

            // add question text
            Element qtElement =
                    document.createElement("XML.QUESTION_TEXT.value()");
            qtElement.setTextContent(question.getQuestionText());
            qElement.appendChild(qtElement);

            // add answers
            for (Answer answer : question.getAnswers()) {
                Element aElement = document.createElement(XML.ANSWER.value());
                aElement.setTextContent(answer.getContent());

                // set attribute
                if (answer.isCorrect()) {
                    aElement.setAttribute(XML.CORRECT.value(), "true");
                }
                qElement.appendChild(aElement);
            }
        }

        return document;
    }
    public static void saveToXML(Document document, String xmlFileName)
            throws TransformerException {

        StreamResult result = new StreamResult(new File(xmlFileName));

        // set up transformation
        TransformerFactory tf = TransformerFactory.newInstance();
        javax.xml.transform.Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");

        // run transformation
        t.transform(new DOMSource(document), result);
    }*/
    public static void saveToXML(Reserve reserve)
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
        inputInFile(sw);
        // print the XML
        System.out.println(sw.toString());
    }

    private static void inputInFile(StringWriter stringWriter) {
        try {
            File myObj = new File("output.stax.xml");
            FileWriter writer = new FileWriter("output.stax.xml");
            writer.write(stringWriter.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
