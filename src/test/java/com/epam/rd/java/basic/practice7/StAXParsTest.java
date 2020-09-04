package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.parsers.StAXPars;
import org.junit.Assert;
import org.junit.Test;

public class StAXParsTest {
    @Test
    public void testStAXPars() {
        StAXPars stAXPars = new StAXPars("input.xml");
        String parsingFile = stAXPars.parse();
        Assert.assertEquals("[Gem{nameGem='Izumrud', origin='Mandalor', visualParameters=VisualParameters{color='Green', countOfFaces=8}}\n" +
                ", Gem{nameGem='Zhemchug', origin='Konoha', visualParameters=VisualParameters{color='Yellow', countOfFaces=5}}\n" +
                ", Gem{nameGem='Diamond', origin='Tatuin', visualParameters=VisualParameters{color='Yellow', countOfFaces=6}}\n" +
                "]", parsingFile);
    }
}
