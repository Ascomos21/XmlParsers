package com.epam.rd.java.basic.practice7;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlToHtml {
    Logger logger = Logger.getLogger(XmlToHtml.class.getName());

    public static void main(String[] args) {
        XmlToHtml xmlToHtml = new XmlToHtml();
        xmlToHtml.convert();
    }

    public void convert() {
        String outputFileName = "report.html";
        try (OutputStream htmlFile = new FileOutputStream(outputFileName);) {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            tFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            tFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            Source xslDoc = new StreamSource("stylesheet.xsl");
            Source xmlDoc = new StreamSource("input.xml");
            Transformer trasform = tFactory.newTransformer(xslDoc);
            trasform.transform(xmlDoc, new StreamResult(htmlFile));

        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}
