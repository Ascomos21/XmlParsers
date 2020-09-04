package com.epam.rd.java.basic.practice7;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DemoTest {
    @Test
    public void testDemoMain() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PrintStream ps = new PrintStream(baos);

        PrintStream old = System.out;
        System.setOut(ps);
        Demo.main(new String[]{});
        String result = baos.toString();
        System.out.flush();
        System.setOut(old);
        Assert.assertEquals("BEFORE SORT[Gem{nameGem='Izumrud', origin='Mandalor', visualParameters=VisualParameters{color='Green', countOfFaces=8}}\n" +
                ", Gem{nameGem='Zhemchug', origin='Konoha', visualParameters=VisualParameters{color='Yellow', countOfFaces=5}}\n" +
                ", Gem{nameGem='Diamond', origin='Tatuin', visualParameters=VisualParameters{color='Yellow', countOfFaces=6}}\n" +
                "]\n" +
                "AFTER SORT[Gem{nameGem='Zhemchug', origin='Konoha', visualParameters=VisualParameters{color='Yellow', countOfFaces=5}}\n" +
                ", Gem{nameGem='Diamond', origin='Tatuin', visualParameters=VisualParameters{color='Yellow', countOfFaces=6}}\n" +
                ", Gem{nameGem='Izumrud', origin='Mandalor', visualParameters=VisualParameters{color='Green', countOfFaces=8}}\n" +
                "]\n" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
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
                "</reserve>\n" +
                "\n" +
                "root element reserve\n" +
                "Current elemgem\n" +
                "Gem{nameGem='Izumrud', origin='Mandalor', visualParameters=VisualParameters{color='Green', countOfFaces=8}}\n" +
                "\n" +
                "Current elemgem\n" +
                "Gem{nameGem='Zhemchug', origin='Konoha', visualParameters=VisualParameters{color='Yellow', countOfFaces=5}}\n" +
                "\n" +
                "Current elemgem\n" +
                "Gem{nameGem='Diamond', origin='Tatuin', visualParameters=VisualParameters{color='Yellow', countOfFaces=6}}\n" +
                "\n" +
                "BEFORE SOORt[Gem{nameGem='Izumrud', origin='Mandalor', visualParameters=VisualParameters{color='Green', countOfFaces=8}}\n" +
                ", Gem{nameGem='Zhemchug', origin='Konoha', visualParameters=VisualParameters{color='Yellow', countOfFaces=5}}\n" +
                ", Gem{nameGem='Diamond', origin='Tatuin', visualParameters=VisualParameters{color='Yellow', countOfFaces=6}}\n" +
                "]\n" +
                "AFTER SORT[Gem{nameGem='Diamond', origin='Tatuin', visualParameters=VisualParameters{color='Yellow', countOfFaces=6}}\n" +
                ", Gem{nameGem='Izumrud', origin='Mandalor', visualParameters=VisualParameters{color='Green', countOfFaces=8}}\n" +
                ", Gem{nameGem='Zhemchug', origin='Konoha', visualParameters=VisualParameters{color='Yellow', countOfFaces=5}}\n" +
                "]\n" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
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
                "</reserve>\n" +
                "\n" +
                "[Gem{nameGem='Izumrud', origin='Mandalor', visualParameters=VisualParameters{color='Green', countOfFaces=8}}\n" +
                ", Gem{nameGem='Zhemchug', origin='Konoha', visualParameters=VisualParameters{color='Yellow', countOfFaces=5}}\n" +
                ", Gem{nameGem='Diamond', origin='Tatuin', visualParameters=VisualParameters{color='Yellow', countOfFaces=6}}\n" +
                "]\n" +
                "BEFORE SORT[Gem{nameGem='Izumrud', origin='Mandalor', visualParameters=VisualParameters{color='Green', countOfFaces=8}}\n" +
                ", Gem{nameGem='Zhemchug', origin='Konoha', visualParameters=VisualParameters{color='Yellow', countOfFaces=5}}\n" +
                ", Gem{nameGem='Diamond', origin='Tatuin', visualParameters=VisualParameters{color='Yellow', countOfFaces=6}}\n" +
                "]\n" +
                "AFTER SORT[Gem{nameGem='Zhemchug', origin='Konoha', visualParameters=VisualParameters{color='Yellow', countOfFaces=5}}\n" +
                ", Gem{nameGem='Izumrud', origin='Mandalor', visualParameters=VisualParameters{color='Green', countOfFaces=8}}\n" +
                ", Gem{nameGem='Diamond', origin='Tatuin', visualParameters=VisualParameters{color='Yellow', countOfFaces=6}}\n" +
                "]\n" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
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
                "</reserve>\n" +
                "\n", result);
    }
}
