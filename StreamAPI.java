Here's how you can use the Stream API in Java to solve these problems:

1. **Фильтрация четных чисел:**
   ```java
   import java.util.Arrays;
   import java.util.List;
   import java.util.stream.Collectors;

   public class EvenNumbersFilter {
       public static void main(String[] args) {
           List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
           List<Integer> evenNumbers = numbers.stream()
                                               .filter(n -> n % 2 == 0)
                                               .collect(Collectors.toList());
           evenNumbers.forEach(System.out::println);
       }
   }
   ```

2. **Преобразование строк в их длины:**
   ```java
   import java.util.Arrays;
   import java.util.List;
   import java.util.stream.Collectors;

   public class StringLengths {
       public static void main(String[] args) {
           List<String> strings = Arrays.asList("hello", "world", "java", "stream");
           List<Integer> lengths = strings.stream()
                                          .map(String::length)
                                          .collect(Collectors.toList());
           lengths.forEach(System.out::println);
       }
   }
   ```

3. **Получение уникальных символов:**
   ```java
   import java.util.stream.Collectors;

   public class UniqueCharacters {
       public static void main(String[] args) {
           String input = "hello world";
           String uniqueChars = input.chars()
                                     .distinct()
                                     .sorted()
                                     .mapToObj(c -> String.valueOf((char) c))
                                     .collect(Collectors.joining());
           System.out.println(uniqueChars);
       }
   }
   ```

4. **Вычислите сумму квадратов всех чисел:**
   ```java
   import java.util.Arrays;
   import java.util.List;

   public class SumOfSquares {
       public static void main(String[] args) {
           List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
           int sumOfSquares = numbers.stream()
                                     .mapToInt(n -> n * n)
                                     .sum();
           System.out.println(sumOfSquares);
       }
   }
   ```

5. **Преобразование списка в строку:**
   ```java
   import java.util.Arrays;
   import java.util.List;
   import java.util.stream.Collectors;

   public class JoinStrings {
       public static void main(String[] args) {
           List<String> strings = Arrays.asList("apple", "banana", "cherry");
           String result = strings.stream()
                                  .collect(Collectors.joining(", "));
           System.out.println(result);
       }
   }
   ```

6. **Поиск первого положительного числа:**
   ```java
   import java.util.Arrays;
   import java.util.List;
   import java.util.Optional;

   public class FirstPositiveNumber {
       public static void main(String[] args) {
           List<Integer> numbers = Arrays.asList(-1, -2, -3, 4, 5);
           Optional<Integer> firstPositive = numbers.stream()
                                                    .filter(n -> n > 0)
                                                    .findFirst();
           firstPositive.ifPresent(System.out::println);
       }
   }
   ```

7. **Группировка по длине строки:**
   ```java
   import java.util.Arrays;
   import java.util.List;
   import java.util.Map;
   import java.util.stream.Collectors;

   public class GroupByLength {
       public static void main(String[] args) {
           List<String> strings = Arrays.asList("one", "two", "three", "four", "five");
           Map<Integer, List<String>> groupedByLength = strings.stream()
                                                               .collect(Collectors.groupingBy(String::length));
           groupedByLength.forEach((length, group) -> {
               System.out.println("Length " + length + ": " + group);
           });
       }
   }
   ```

8. **Преобразование строк в верхний регистр:**
   ```java
   import java.util.Arrays;
   import java.util.List;
   import java.util.stream.Collectors;

   public class ToUpperCase {
       public static void main(String[] args) {
           List<String> strings = Arrays.asList("hello", "world", "java", "stream");
           List<String> uppercased = strings.stream()
                                            .map(String::toUpperCase)
                                            .collect(Collectors.toList());
           uppercased.forEach(System.out::println);
       }
   }
   ```

9. **Отфильтруйте четные числа и возьмите первые 3:**
   ```java
   import java.util.Arrays;
   import java.util.List;
   import java.util.stream.Collectors;

   public class FirstThreeEvenNumbers {
       public static void main(String[] args) {
           List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
           List<Integer> firstThreeEvenNumbers = numbers.stream()
                                                        .filter(n -> n % 2 == 0)
                                                        .limit(3)
                                                        .collect(Collectors.toList());
           firstThreeEvenNumbers.forEach(System.out::println);
       }
   }
   ```