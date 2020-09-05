package com.epam.rd.java.basic.practice7.parsers;

import org.junit.Assert;
import org.junit.Test;

public class DOMParsTest {
    private static final String NAME_FILE = "input.xml";

    @Test
    public void testParse() {
        DOMPars domPars = new DOMPars(NAME_FILE);
        String parsingFile = domPars.parse();
        Assert.assertEquals("[Gem{nameGem='Izumrud', origin='Mandalor', visualParameters=VisualParameters{color='Green', countOfFaces=8}}\n" +
                ", Gem{nameGem='Zhemchug', origin='Konoha', visualParameters=VisualParameters{color='Yellow', countOfFaces=5}}\n" +
                ", Gem{nameGem='Diamond', origin='Tatuin', visualParameters=VisualParameters{color='Yellow', countOfFaces=6}}\n" +
                "]", parsingFile);
    }

    @Test
    public void testInputInFIle() {
        String informationInFIle = DOMPars.informationToXml("input.xml", "test.dom.xml");
        Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<reserve>\n" +
                "    <gem>\n" +
                "        <nameGem>Diamond</nameGem>\n" +
                "        <origin>Tatuin</origin>\n" +
                "        <visualParameters>\n" +
                "            <color>Yellow</color>\n" +
                "            <countOfFaces>6</countOfFaces>\n" +
                "        </visualParameters>\n" +
                "    </gem>\n" +
                "    <gem>\n" +
                "        <nameGem>Izumrud</nameGem>\n" +
                "        <origin>Mandalor</origin>\n" +
                "        <visualParameters>\n" +
                "            <color>Green</color>\n" +
                "            <countOfFaces>8</countOfFaces>\n" +
                "        </visualParameters>\n" +
                "    </gem>\n" +
                "    <gem>\n" +
                "        <nameGem>Zhemchug</nameGem>\n" +
                "        <origin>Konoha</origin>\n" +
                "        <visualParameters>\n" +
                "            <color>Yellow</color>\n" +
                "            <countOfFaces>5</countOfFaces>\n" +
                "        </visualParameters>\n" +
                "    </gem>\n" +
                "</reserve>\n", informationInFIle);
    }

}
