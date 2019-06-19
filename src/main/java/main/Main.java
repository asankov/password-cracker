package main;

import java.util.List;

public class Main {

  public static void main(String... args) {

    final List<String> combinations = new PasswordCracker().crack("password");

    System.out.println(String.format("Combinations count: %d", combinations.size()));
    System.out.println(String.format("Combinations: %s",combinations));
  }
}
