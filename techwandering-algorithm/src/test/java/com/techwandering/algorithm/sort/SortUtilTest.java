package com.techwandering.algorithm.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author wanshao
 * create time is  2023/3/22
 **/
class SortUtilTest {
    
    @Test
    void mergeSort() {
        int[] actual = {5, 1, 6, 2, 3, 4};
        int[] expected = {1, 2, 3, 4, 5, 6};
        SortUtil.mergeSort(actual, actual.length);
        assertArrayEquals(expected, actual);
    }
    
}