package com.epam.rd.java.basic.practice7;


import com.epam.rd.java.basic.practice7.parsers.DOMPars;
import com.epam.rd.java.basic.practice7.parsers.SAXPars;
import com.epam.rd.java.basic.practice7.parsers.StAXPars;

public class Main {
    public static void main(String[] args) {
        SAXPars saxPars = new SAXPars(args[0]);
        StAXPars stAXPars = new StAXPars(args[0]);
        DOMPars domPars = new DOMPars(args[0]);
        saxPars.main(new String[]{args[0]});
        domPars.main(new String[]{args[0]});
        stAXPars.main(new String[]{args[0]});

    }

}