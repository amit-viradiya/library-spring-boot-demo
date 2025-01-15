package com.example.library;

import java.util.*;
import java.util.stream.Collectors;

public class CodingTask3 {


    public static void main(String[] args) {
        int[] array = {2, 4, 3, 7, 1, 5};
        int target = 6;

        List<List<Integer>> pairs = getSubArraySum_Optimal(array, target);

        // Print the pairs
        System.out.println(pairs);
    }

    private static List<List<Integer>> getSubArraySum_Better(int[] array, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();

        for (int num : array) {
            int complement = target - num;

            // If the complement is in the set, we found a valid pair
            if (seen.contains(complement)) {
                ans.add(Arrays.asList(complement, num));
            }

            // Add the current number to the set
            seen.add(num);
        }

        return ans;
    }

    private static List<List<Integer>> getSubArraySum_Optimal(int [] array, int target) {
        //Step 1 Sort the Array
        Arrays.sort(array);
        List<List<Integer>> ans = new ArrayList<>();
        int right = array.length-1;
        int left = 0;
        while (left <= right) {
            int sum = array[left] + array[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                List<Integer> temp = Arrays.asList(array[left], array[right]);
                ans.add(temp);
                left++;
                right--;
            }

        }
        return ans;
    }

    public static List<int[]> findPairsWithSum(int[] array, int target) {
        Set<Integer> seen = new HashSet<>();
        List<int[]> result = new ArrayList<>();

        for (int num : array) {
            int complement = target - num;

            // Check if the complement exists in the 'seen' set
            if (seen.contains(complement)) {
                result.add(new int[]{complement, num}); // Add pair to the result
            }

            seen.add(num); // Add the current number to 'seen' set
        }

        return result;
    }

    public static List<int[]> findPairsWithSumAvoidDuplicate(int[] array, int target) {
        Set<Integer> seen = new HashSet<>();
        Set<String> uniquePairs = new HashSet<>();
        List<int[]> result = new ArrayList<>();

        for (int num : array) {
            int complement = target - num;

            // Check if the complement exists in 'seen' set
            if (seen.contains(complement)) {
                // Create a sorted pair to avoid duplicates like (3, 3) and (3, 3)
                int[] pair = new int[]{Math.min(num, complement), Math.max(num, complement)};
                String pairKey = pair[0] + "," + pair[1]; // A string to track unique pairs

                // If the pair hasn't been seen before, add it to the result
                if (!uniquePairs.contains(pairKey)) {
                    result.add(pair);
                    uniquePairs.add(pairKey); // Mark the pair as seen
                }
            }

            seen.add(num); // Add current number to 'seen' set
        }

        return result;
    }

    public static List<int[]> findPairsWithSumHashMap(int[] array, int target) {
        Map<Integer, Integer> countMap = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        for (int num : array) {
            int complement = target - num;

            // If the complement exists in the map and we have not yet used it as a pair
            if (countMap.containsKey(complement) && countMap.get(complement) > 0) {
                result.add(new int[] {complement, num});
                countMap.put(complement, countMap.get(complement) - 1);  // Decrement count for complement
            } else {
                countMap.put(num, countMap.getOrDefault(num, 0) + 1);  // Add or update the count of num
            }
        }

        return result;
    }


}


