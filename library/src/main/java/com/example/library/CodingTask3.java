package com.example.library;

import java.util.*;
import java.util.stream.Collectors;

public class CodingTask3 {

    public static void main(String[] args) {
        int[] array = {2, 4, 3, 7, 1, 5};
        int target = 6;
        List<int[]> result = findPairsWithSum(array, target);

        result.forEach(pair -> System.out.println(Arrays.toString(pair)));
    }

    public static List<int[]> findPairsWithSum(int[] array, int target) {
        Set<Integer> seen = new HashSet<>();
        Set<List<Integer>> uniquePairs = new HashSet<>();

        return Arrays.stream(array)
                .mapToObj(num -> {
                    int complement = target - num;
                    if (seen.contains(complement)) {
                        List<Integer> pair = Arrays.asList(Math.min(num, complement), Math.max(num, complement));
                        if (uniquePairs.add(pair)) {
                            return new int[]{pair.get(0), pair.get(1)};
                        }
                    }
                    seen.add(num);
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
