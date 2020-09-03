package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.entity.Gem;

import java.util.Comparator;

public class Sorter {
    public static final Comparator<Gem> SORT_QUESTIONS_BY_QUESTION_TEXT = new Comparator<Gem>() {
        @Override
        public int compare(Gem o1, Gem o2) {
            return o1.getNameGem().compareTo(o2.getNameGem());
        }
    };
}
