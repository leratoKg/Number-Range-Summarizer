package numberrangesummarizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lerato Kgomoeswana
 * */
public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

    /**
     * Parses a comma-separated string of numbers into a sorted collection of unique integers.
     * 
     * - Trims whitespace
     * - Validates input, only integers are allowed
     * - Removes any duplicates
     * - Sorts values in ascending order
     *
     * @param input comma-separated string of numbers
     * @return sorted collection of unique integers
     * @throws IllegalArgumentException if input contains invalid characters eg. letters, special characters 
     */
    @Override
    public Collection<Integer> collect(String input) {
        if (input == null || input.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .peek(inputToken -> {
                    if (!isValidInputToken(inputToken)) {
                        throw new IllegalArgumentException(
                            "Invalid input: '" + inputToken + "'. " +
                            "Input must be integers separated by commas only."
                        );
                    }
                })
                .map(Integer::parseInt)
                .distinct()              
                .sorted()               
                .collect(Collectors.toList());
    }

    /**
     * Validates whether a given token represents a valid integer.
     *
     * The token must:
     * - Contain only digits
     * - Optionally start with a minus sign for negative numbers
     * - Not contain decimals, letters, or special characters
     *
     * Examples:
     * "123"   → true
     * "abc"   → false
     *
     * @param token the string value to validate
     * @return true if the token is a valid integer, false otherwise
     */
    private boolean isValidInputToken(String token) {
        return token.matches("-?\\d+");
    }

    /**
     * Converts a collection of integers into a summarized string.
     * Sequential numbers are grouped into ranges.
     *
     * Example:
     * Input: [1,3,6,7,8,12,13,14,15,21,22,23,24,31]
     * Output: "1, 3, 6-8, 12-15, 21-24, 31"
     *
     * @param input collection of integers
     * @return summarized string representation
     */
    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        List<Integer> inputList = new ArrayList<>(input);
        StringBuilder summarizedResult = new StringBuilder();
        int startRange = inputList.get(0);
        int previousNumber = startRange;

        for (int i = 1; i < inputList.size(); i++) {
            int currentNumber = inputList.get(i);
            if (currentNumber == previousNumber + 1) {
                previousNumber = currentNumber;
            } else {
                appendToList(summarizedResult, startRange, previousNumber);
                summarizedResult.append(", ");

                startRange = currentNumber;
                previousNumber = currentNumber;
            }
        }
        appendToList(summarizedResult, startRange, previousNumber); // Add the last range
        return summarizedResult.toString();
    }

    /**
     * Appends a single number or a range of numbers to the provided StringBuilder.
     *
     * Examples:
     * startRange = 5, endRange = 5   → "5"
     * startRange = 6, endRange = 8   → "6-8"
     *
     * @param summarizedList the StringBuilder used to build the final summarized string
     * @param startRange the starting value of the range
     * @param endRange the ending value of the range
     */
    private void appendToList(StringBuilder summarizedResult, int startRange, int endRange) {
        if (startRange == endRange) {
            summarizedResult.append(startRange);
        } else {
            summarizedResult.append(startRange).append("-").append(endRange);
        }
    }


}
