package org.sujith;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class FindDuplicate {

    // Returns index of the first occurrence of target
    static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] < target)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    // Returns index just past the last occurrence of target
    static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= target)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    // Function to return elements that occur in arr more than once
    static List<Integer> findDuplicates(int[] arr) {
        Arrays.sort(arr);
        List<Integer> res = new ArrayList<>();
        Arrays.stream(arr).forEach(a -> System.out.print(a + " "));

        int i = 0;
        while (i < arr.length) {
            int first = lowerBound(arr, arr[i]);
            int last = upperBound(arr, arr[i]) - 1;

            // If the element occurs more than once, add it to res
            System.out.println(arr[i]+":"+first+":"+last);
            if (last > first) {

                res.add(arr[i]);
            }

            // Update i to the last index of the current element
            i = last + 1;
        }
        return res;
    }

    public static <T> Set<T>
    findDuplicateInStream(Stream<T> stream)
    {

        // Set to store the duplicate elements
        Set<T> items = new HashSet<>();

        // Return the set of duplicate elements
        return stream

                // Set.add() returns false
                // if the element was
                // already present in the set.
                // Hence filter such elements
                .filter(n -> !items.add(n))

                // Collect duplicate elements
                // in the set
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 5, 40, 12, 5, 6, 5, 12, 11};
        List<Integer> res = findDuplicates(arr);
        for (int x : res) {
            System.out.print(x + " ");
        }



        // Initial stream
        Stream<Integer> stream
                = Stream.of(5, 13, 4,
                21, 13, 27,
                2, 59, 59, 34);


        findDuplicateInStream(stream);

        // Print the found duplicate elements
//        System.out.println(
//                findDuplicateInStream(stream));
    }


//    public static <Integer> Set<Integer> find_duplicates(int[] arr){
//        IntStream<Integer> stream = Arrays.int(arr);
//        Set<T> duplicates = new HashSet<>();
//
//        stream.filter(n -> !duplicates.add(n)).collect(Collectors.toSet());
//        List<Integer> duplicateList =  stream.filter(i -> !duplicates.add(i)).collect(Collectors.toList());
//        return null;
//    }
//
//    public static <T> Set<T>
//    findDuplicateInStream(Stream<T> stream)
//    {
//
//        // Set to store the duplicate elements
//        Set<T> items = new HashSet<>();
//
//        // Return the set of duplicate elements
//        return stream
//
//                // Set.add() returns false
//                // if the element was
//                // already present in the set.
//                // Hence filter such elements
//                .filter(n -> !items.add(n))
//
//                // Collect duplicate elements
//                // in the set
//                .collect(Collectors.toSet());
//    }

}
