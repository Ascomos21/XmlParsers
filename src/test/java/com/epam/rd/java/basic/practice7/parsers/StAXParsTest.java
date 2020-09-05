package com.epam.rd.java.basic.practice7.parsers;

import org.junit.Assert;
import org.junit.Test;

public class StAXParsTest {
    private static final String NAME_FILE = "input.xml";
    @Test
    public void testStAXPars() {
        StAXPars stAXPars = new StAXPars(NAME_FILE);
        String parsingFile = stAXPars.parse();
        Assert.assertEquals("[Gem{nameGem='Izumrud', origin='Mandalor', visualParameters=VisualParameters{color='Green', countOfFaces=8}}\n" +
                ", Gem{nameGem='Zhemchug', origin='Konoha', visualParameters=VisualParameters{color='Yellow', countOfFaces=5}}\n" +
                ", Gem{nameGem='Diamond', origin='Tatuin', visualParameters=VisualParameters{color='Yellow', countOfFaces=6}}\n" +
                "]", parsingFile);
    }

    @Test
    public void createPars(){
        StAXPars stAXPars = StAXPars.createParser(NAME_FILE);
        Assert.assertEquals("[Gem{nameGem='Zhemchug', origin='Konoha', visualParameters=VisualParameters{color='Yellow', countOfFaces=5}}\n" +
                ", Gem{nameGem='Izumrud', origin='Mandalor', visualParameters=VisualParameters{color='Green', countOfFaces=8}}\n" +
                ", Gem{nameGem='Diamond', origin='Tatuin', visualParameters=VisualParameters{color='Yellow', countOfFaces=6}}\n" +
                "]", stAXPars.reserve.getGemList().toString());
    }

    @Test
    public void testInputInFile(){
        String information = StAXPars.informationToXml(NAME_FILE,"test.stax.xml");
        Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<reserve>\n" +
                "    <gem>\n" +
                "        <nameGem>Zhemchug</nameGem>\n" +
                "        <origin>Konoha</origin>\n" +
                "        <visualParameters>\n" +
                "            <color>Yellow</color>\n" +
                "            <countOfFaces>5</countOfFaces>\n" +
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
                "        <nameGem>Diamond</nameGem>\n" +
                "        <origin>Tatuin</origin>\n" +
                "        <visualParameters>\n" +
                "            <color>Yellow</color>\n" +
                "            <countOfFaces>6</countOfFaces>\n" +
                "        </visualParameters>\n" +
                "    </gem>\n" +
                "</reserve>\n", information);

    }

}
