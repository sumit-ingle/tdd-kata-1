import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;

public class CalculatorTest {
    @Nested
    @DisplayName("Addition")
    class Addition {
        @Test
        void returns_0_for_empty_string() {
            Calculator calculator = new Calculator();
            int additionResult = calculator.add("");
            Assertions.assertEquals(0, additionResult);
        }

        @Nested
        @DisplayName("Single Number")
        class SingleNumber {
            @Test
            void returns_1_for_input_1() {
                Calculator calculator = new Calculator();
                int additionResult = calculator.add("1");
                Assertions.assertEquals(1, additionResult);
            }

            @Test
            void returns_2_for_input_2() {
                Calculator calculator = new Calculator();
                int additionResult = calculator.add("2");
                Assertions.assertEquals(2, additionResult);
            }

            @ParameterizedTest
            @ValueSource(strings = {"10", "200", "3333", "1234567890"})
            void returns_n_for_input_n(String number) {
                Calculator calculator = new Calculator();
                int additionResult = calculator.add(number);
                Assertions.assertEquals(Integer.parseInt(number), additionResult);
            }
        }

        @Nested
        @DisplayName("Two Numbers")
        class TwoNumbers {
            @Test
            void returns_2_for_1comma1() {
                Calculator calculator = new Calculator();
                int additionResult = calculator.add("1,1");
                Assertions.assertEquals(2, additionResult);
            }

            @Test
            void returns_3_for_1comma2() {
                Calculator calculator = new Calculator();
                int additionResult = calculator.add("1,2");
                Assertions.assertEquals(3, additionResult);
            }
        }

        @Nested
        @DisplayName("More than two numbers")
        class MoreThanTwoNumbers {
            @Test
            void returns_6_for_1comma2comma3() {
                Calculator calculator = new Calculator();
                int additionResult = calculator.add("1,2,3");
                Assertions.assertEquals(6, additionResult);
            }

            @Test
            void returns_sum_for_4_numbers_delimited_by_comma() {
                Calculator calculator = new Calculator();
                int additionResult = calculator.add("5,5,5,5");
                Assertions.assertEquals(20, additionResult);
            }

            @Test
            void returns_sum_for_5_numbers_delimited_by_comma() {
                Calculator calculator = new Calculator();
                int additionResult = calculator.add("5,5,5,5,5");
                Assertions.assertEquals(25, additionResult);
            }

            @Test
            void returns_sum_for_10_numbers_delimited_by_comma() {
                Calculator calculator = new Calculator();
                int additionResult = calculator.add("5,5,5,5,5,5,5,5,5,5");
                Assertions.assertEquals(50, additionResult);
            }

            @Test
            void returns_sum_for_100_numbers_delimited_by_comma() {
                Calculator calculator = new Calculator();
                var hundredNumbers = Collections.nCopies(100, "10");
                String commaSeparatedHundredNumbers = String.join(",", hundredNumbers);

                int additionResult = calculator.add(commaSeparatedHundredNumbers);

                Assertions.assertEquals(1000, additionResult);
            }
        }

        @Nested
        @DisplayName("Supports new line delimiter")
        class NewLineDelimiter {
            @Test
            void returns_sum_for_numbers_delimited_by_new_line() {
                Calculator calculator = new Calculator();
                int additionResult = calculator.add("5\n5");
                Assertions.assertEquals(10, additionResult);
            }

            @Test
            void supports_new_line_and_comma_delimiter_in_same_input() {
                Calculator calculator = new Calculator();
                int additionResult = calculator.add("5\n5,5");
                Assertions.assertEquals(15, additionResult);
            }
        }
    }
}
