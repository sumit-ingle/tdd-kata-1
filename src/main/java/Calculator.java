import static java.util.Arrays.stream;

public class Calculator {
    public int add(String numbers) {
        if (numbers.equals(""))
            return 0;
        if (numbers.contains(",") || numbers.contains("\n")) {
            return stream(numbers.split("[\\n,]")).mapToInt(Integer::parseInt).sum();
        }
        return Integer.parseInt(numbers);
    }
}
