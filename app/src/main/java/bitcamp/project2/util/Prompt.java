package bitcamp.project2.util;

import java.util.Scanner;

public class Prompt {

  static Scanner ans = new Scanner(System.in);

  public static String input(String format, Object... args) {
    System.out.printf(format + " ", args);
    return ans.nextLine();
  }

  public static int inputInt(String format, Object... args) {
    return Integer.parseInt(input(format, args));
  }

  public static void close() {
    ans.close();
  }
}
