package com.epam.rd.java.basic.practice7;


import com.epam.rd.java.basic.practice7.parsers.DOMPars;
import com.epam.rd.java.basic.practice7.parsers.SAXPars;
import com.epam.rd.java.basic.practice7.parsers.StAXPars;

public class Main {
    public static void main(String[] args) {
        SAXPars.main(new String[]{args[0]});
        DOMPars.main(new String[]{args[0]});
        StAXPars.main(new String[]{args[0]});
    }

}