package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.entity.Gem;

import java.util.Comparator;

public class Sorter {
    public static final Comparator<Gem> SORT_GEM_BY_NAME = new Comparator<Gem>() {
        @Override
        public int compare(Gem o1, Gem o2) {
            return o1.getNameGem().compareTo(o2.getNameGem());
        }
    };
    public static final Comparator<Gem> SORT_GEM_BY_ORIGIN = new Comparator<Gem>() {
        @Override
        public int compare(Gem o1, Gem o2) {
            return o1.getOrigin().compareTo(o2.getOrigin());
        }
    };
    public static final Comparator<Gem> SORT_GEM_BY_COUNT_OF_FACES = new Comparator<Gem>() {
        @Override
        public int compare(Gem o1, Gem o2) {
            System.out.println(Integer.valueOf(o1.getVisualParameters().getCountOfFaces()));
            return Integer.valueOf(o1.getVisualParameters().getCountOfFaces()).compareTo(Integer.valueOf(o2.getVisualParameters().getCountOfFaces()));
        }
    };
}
