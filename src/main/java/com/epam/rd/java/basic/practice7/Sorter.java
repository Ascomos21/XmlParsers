package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.entity.Gem;

import java.util.Comparator;

public class Sorter {
    private Sorter() {
    }

    public static final Comparator<Gem> SORT_GEM_BY_NAME = Comparator.comparing(Gem::getNameGem);
    public static final Comparator<Gem> SORT_GEM_BY_ORIGIN = Comparator.comparing(Gem::getOrigin);
    public static final Comparator<Gem> SORT_GEM_BY_COUNT_OF_FACES = Comparator.comparing(o -> o.getVisualParameters().getCountOfFaces());
}
