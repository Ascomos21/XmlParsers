package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.entity.Gem;
import com.epam.rd.java.basic.practice7.entity.Reserve;
import com.epam.rd.java.basic.practice7.entity.VisualParameters;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveTest {
    Logger logger = Logger.getLogger(SaveTest.class.getName());
    Gem gem1 = new Gem("Izumrud", "Tatuin", new VisualParameters("Green", 5));
    Gem gem2 = new Gem("Zhemchug", "Korusant", new VisualParameters("Yellow", 8));
    Gem gem3 = new Gem("Almaz", "Konoha", new VisualParameters("Blue", 6));

    List<Gem> gemArrayList = new ArrayList<>(Arrays.asList(gem1, gem2, gem3));

    @Test
    public void testSave() {
        Reserve reserve = new Reserve();
        reserve.setGemList(gemArrayList);
        System.out.println(reserve.getGemList());
        Save save = new Save();
        String informationInFile = null;
        try {
            informationInFile = save.saveToXML(reserve, "test.xml");
        } catch (JAXBException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<reserve>\n" +
                "    <gem>\n" +
                "        <nameGem>Izumrud</nameGem>\n" +
                "        <origin>Tatuin</origin>\n" +
                "        <visualParameters>\n" +
                "            <color>Green</color>\n" +
                "            <countOfFaces>5</countOfFaces>\n" +
                "        </visualParameters>\n" +
                "    </gem>\n" +
                "    <gem>\n" +
                "        <nameGem>Zhemchug</nameGem>\n" +
                "        <origin>Korusant</origin>\n" +
                "        <visualParameters>\n" +
                "            <color>Yellow</color>\n" +
                "            <countOfFaces>8</countOfFaces>\n" +
                "        </visualParameters>\n" +
                "    </gem>\n" +
                "    <gem>\n" +
                "        <nameGem>Almaz</nameGem>\n" +
                "        <origin>Konoha</origin>\n" +
                "        <visualParameters>\n" +
                "            <color>Blue</color>\n" +
                "            <countOfFaces>6</countOfFaces>\n" +
                "        </visualParameters>\n" +
                "    </gem>\n" +
                "</reserve>\n", informationInFile);
    }
}
