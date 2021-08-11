import java.util.Arrays;

public class Calculator {
    public int add(String numbers) {
        if (numbers.equals(""))
            return 0;
        if (numbers.contains(",")) {
            return Arrays.stream(numbers.split(",")).mapToInt(Integer::parseInt).sum();
        }
        return Integer.parseInt(numbers);
    }
}
