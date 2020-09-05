package com.epam.rd.java.basic.practice7;


import com.epam.rd.java.basic.practice7.parsers.DOMPars;
import com.epam.rd.java.basic.practice7.parsers.SAXPars;
import com.epam.rd.java.basic.practice7.parsers.StAXPars;

public class Main {
    public static void main(String[] args) {
        SAXPars.main(new String[]{args[0],"output.sax.xml"});
        DOMPars.main(new String[]{args[0],"output.dom.xml"});
        StAXPars.main(new String[]{args[0],"output.stax.xml"});
    }

}