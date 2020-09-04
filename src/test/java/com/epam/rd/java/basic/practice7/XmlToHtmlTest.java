package com.epam.rd.java.basic.practice7;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class XmlToHtmlTest {
    @Test
    public void testConvert() {
        XmlToHtml xmlToHtml = new XmlToHtml();
        xmlToHtml.convert();
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader("report.html")) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        File file = new File("report.html");
        file.delete();
        Assert.assertEquals("<html>\n" +
                "<body>\n" +
                "<h1>Test Case Results</h1>\n" +
                "<table border=\"1\">\n" +
                "<tr>\n" +
                "<th>nameGem</th><th>origin</th><th>color</th><th>countOfFaces</th>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>Izumrud</td><td>Mandalor</td><td>Green</td><td>8</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>Zhemchug</td><td>Konoha</td><td>Yellow</td><td>5</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td>Diamond</td><td>Tatuin</td><td>Yellow</td><td>6</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>\n", stringBuilder.toString());

    }
}
