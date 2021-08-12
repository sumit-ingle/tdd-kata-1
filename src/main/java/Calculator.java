import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Calculator {
    public int add(String numbers) {
        if (numbers.equals(""))
            return 0;
        if (containsCustomDelimiterSyntax(numbers)) {
            return addUsingCustomDelimiter(numbers);
        }
        return calculateSum(numbers, "[\\n,]");
    }

    private boolean containsCustomDelimiterSyntax(String numbers) {
        return numbers.matches("//.\\n.*");
    }

    private int addUsingCustomDelimiter(String numbers) {
        String customDelimiter = StringUtils.substringBetween(numbers, "//", "\n");
        String delimitedNumbers = StringUtils.substringAfter(numbers, "\n");
        return calculateSum(delimitedNumbers, Pattern.quote(customDelimiter));
    }

    private int calculateSum(String delimitedNumbers, String delimiterRegex) {
        List<Integer> parsedNumbers = stream(delimitedNumbers.split(delimiterRegex))
                .map(Integer::parseInt)
                .filter(number -> number <= 1000)
                .collect(Collectors.toList());
        validateNegativeNumbers(parsedNumbers);
        return parsedNumbers.stream().mapToInt(number -> number).sum();
    }

    private void validateNegativeNumbers(List<Integer> numbers) {
        List<Integer> negativeNumbers = numbers.stream().filter((number) -> number < 0).collect(Collectors.toList());
        if (negativeNumbers.size() > 0) {
            throw new IllegalArgumentException("negatives not allowed: " + negativeNumbers);
        }
    }
}
