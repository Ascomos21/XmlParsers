package com.epam.rd.java.basic.practice7;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class XmlToHtml {
    public static void main(String[] args) {
        XmlToHtml xmlToHtml = new XmlToHtml();
        xmlToHtml.convert();
    }

    public void convert() {
        try {


            TransformerFactory tFactory = TransformerFactory.newInstance();

            Source xslDoc = new StreamSource("stylesheet.xsl");

            Source xmlDoc = new StreamSource("input.xml");

            String outputFileName = "report.html";

            OutputStream htmlFile = new FileOutputStream(outputFileName);

            Transformer trasform = tFactory.newTransformer(xslDoc);

            trasform.transform(xmlDoc, new StreamResult(htmlFile));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
