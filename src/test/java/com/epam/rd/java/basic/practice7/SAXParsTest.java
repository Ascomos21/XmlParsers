package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.parsers.SAXPars;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class SAXParsTest {
    private static final String NAME_FILE = "input.xml";
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void testParsSAXP() {
        SAXPars saxPars = new SAXPars(NAME_FILE);
        String parsingFile = saxPars.parse();
        Assert.assertEquals("[Gem{nameGem='Izumrud', origin='Mandalor', visualParameters=VisualParameters{color='Green', countOfFaces=8}}\n" +
                ", Gem{nameGem='Zhemchug', origin='Konoha', visualParameters=VisualParameters{color='Yellow', countOfFaces=5}}\n" +
                ", Gem{nameGem='Diamond', origin='Tatuin', visualParameters=VisualParameters{color='Yellow', countOfFaces=6}}\n" +
                "]", parsingFile);
    }

    @Test
    public void testException(){
        thrown.expect(NullPointerException.class);
        SAXPars saxPars = new SAXPars("notCorrectName.xml ");
        String parsingFile = saxPars.parse();
    }
}
