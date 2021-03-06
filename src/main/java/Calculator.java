import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Calculator {
    public int add(String numbers) {
        if (numbers.isEmpty())
            return 0;
        var parsedNumbers = parseNumbers(numbers);
        validateNegativeNumbers(parsedNumbers);
        return parsedNumbers.stream().mapToInt(number -> number).sum();
    }

    private List<Integer> parseNumbers(String numbers) {
        String delimiterPattern = "[\\n|,]";
        String delimitedNumbers = numbers;
        if (containsCustomDelimiterSyntax(numbers)) {
            String delimiters = StringUtils.substringBetween(numbers, "//", "\n");
            delimiterPattern = getDelimiterPattern(delimiters);
            delimitedNumbers = StringUtils.substringAfter(numbers, "\n");
        }
        return stream(delimitedNumbers.split(delimiterPattern)).map(Integer::parseInt)
                .filter(number -> number <= 1000)
                .collect(Collectors.toList());
    }

    private String getDelimiterPattern(String delimiters) {
        return stream(delimiters
                .replace("][", "|")
                .replace("[", "")
                .replace("]", "")
                .split("\\|"))
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
    }

    private boolean containsCustomDelimiterSyntax(String numbers) {
        return numbers.matches("//.*\\n.*");
    }

    private void validateNegativeNumbers(List<Integer> numbers) {
        List<Integer> negativeNumbers = numbers.stream().filter((number) -> number < 0).collect(Collectors.toList());
        if (negativeNumbers.size() > 0) {
            throw new IllegalArgumentException("negatives not allowed: " + negativeNumbers);
        }
    }
}
