package com.epam.rd.java.basic.practice7;


public class Main {
    public static void main(String[] args) {
        SAXPars saxPars = new SAXPars(args[0]);
        DOMPars domPars = new DOMPars(args[0]);
        saxPars.main(new String[]{});
        domPars.main(new String[]{});

    }

}