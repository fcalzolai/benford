package org.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) {
    List<String> strings = List.of(
            "str1",
            "str2",
            "str3",
            "str4",
            "str5"
    );

    Stream<String> stringStream = strings.stream()
            .map(s -> "Stream 1 - " + s + " ")
            .peek(System.err::println);

    Stream<String> stringStream1 = stringStream
            .map(s -> "Stream 2 - " + s)
            .peek(System.err::println);

    List<String> collect = stringStream1.collect(Collectors.toList());
    System.out.println(collect);
  }

}
