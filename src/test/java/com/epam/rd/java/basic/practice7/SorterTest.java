package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.entity.Gem;
import com.epam.rd.java.basic.practice7.entity.VisualParameters;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SorterTest {
    Gem gem1 = new Gem("Izumrud", "Tatuin", new VisualParameters("Green", 5));
    Gem gem2 = new Gem("Zhemchug", "Korusant", new VisualParameters("Yellow", 8));
    Gem gem3 = new Gem("Almaz", "Konoha", new VisualParameters("Blue", 6));

    ArrayList<Gem> gemArrayList = new ArrayList<>(Arrays.asList(gem1, gem2, gem3));

    @Test
    public void testSortingByName() {
        System.out.println("list before sorting" + gemArrayList);
        Collections.sort(gemArrayList, Sorter.SORT_GEM_BY_NAME);
        System.out.println("list after sorting" + gemArrayList);
        Assert.assertEquals(Stream.of(gem3, gem1, gem2).collect(Collectors.toList()), gemArrayList);
    }

    @Test
    public void testSortingByCountOfFaces() {
        System.out.println("list before sorting" + gemArrayList);
        Collections.sort(gemArrayList, Sorter.SORT_GEM_BY_COUNT_OF_FACES);
        System.out.println("list after sorting" + gemArrayList);
        Assert.assertEquals(Stream.of(gem1, gem3, gem2).collect(Collectors.toList()), gemArrayList);
    }

    @Test
    public void testSortingByOrigin() {
        System.out.println("list before sorting" + gemArrayList);
        Collections.sort(gemArrayList, Sorter.SORT_GEM_BY_ORIGIN);
        System.out.println("list after sorting" + gemArrayList);
        Assert.assertEquals(Stream.of(gem3, gem2, gem1).collect(Collectors.toList()), gemArrayList);
    }
}
