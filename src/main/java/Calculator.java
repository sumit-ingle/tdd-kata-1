import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

import static java.util.Arrays.stream;

public class Calculator {
    public int add(String numbers) {
        if (numbers.equals(""))
            return 0;
        if (containsCustomDelimiterSyntax(numbers)) {
            return addUsingCustomDelimiter(numbers);
        }
        if (containsDefaultDelimiters(numbers)) {
            return getSum(numbers, "[\\n,]");
        }
        return Integer.parseInt(numbers);
    }

    private boolean containsDefaultDelimiters(String numbers) {
        return numbers.contains(",") || numbers.contains("\n");
    }

    private boolean containsCustomDelimiterSyntax(String numbers) {
        return numbers.matches("//.\\n.*");
    }

    private int addUsingCustomDelimiter(String numbers) {
        String customDelimiter = StringUtils.substringBetween(numbers, "//", "\n");
        String delimitedNumbers = StringUtils.substringAfter(numbers, "\n");
        return getSum(delimitedNumbers, Pattern.quote(customDelimiter));
    }

    private int getSum(String delimitedNumbers, String delimiterRegex) {
        return stream(delimitedNumbers.split(delimiterRegex)).mapToInt(Integer::parseInt).sum();
    }
}
