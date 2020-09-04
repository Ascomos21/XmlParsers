package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.entity.Gem;
import com.epam.rd.java.basic.practice7.entity.VisualParameters;
import org.junit.Assert;
import org.junit.Test;

public class GemTest {
    @Test
    public void testGemConstructor() {
        Gem gem = new Gem();
        gem.setNameGem("Almaz");
        gem.setOrigin("Korusant");
        VisualParameters visualParameters = new VisualParameters();
        visualParameters.setColor("Blue");
        visualParameters.setCountOfFaces(10);
        gem.setVisualParameters(visualParameters);
        Assert.assertEquals("Gem{nameGem='Almaz', origin='Korusant', visualParameters=VisualParameters{color='Blue', countOfFaces=10}}\n",
                gem.toString());
    }
}
