package bitcamp.project2.util;

import java.util.Scanner;

public class Prompt {

  static Scanner ans = new Scanner(System.in);
  static String[] mainMenus = new String[]{"내 일정", "약속추가", "사용자관리", "종료"};
  static String[][] subMenus = {
          {"등록", "수정", "삭제"},
          {},
          {"수정", "삭제"}
  };


  ///////////////////////////////////////////////////////////
  //////////////////////// printMenu ////////////////////////
  ///////////////////////////////////////////////////////////

  public static String printSubMenu(String menuTitle, int ans){
    String[] menus = subMenus[ans - 1];
    for (int i = 0; i < menus.length; i++) {
      System.out.printf("[%d] %s\t", (i + 1), menus[i]);
    }
    System.out.println("[0] 이전");

    return Prompt.input(String.format("메인/%s>", menuTitle));
  }



  ///////////////////////////////////////////////////////////
  //////////////////////// Scanner //////////////////////////
  ///////////////////////////////////////////////////////////

  public static String getUserScanner(){ return ans.nextLine(); }

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

  ///////////////////////////////////////////////////////////
  ///////////////////////// print ///////////////////////////
  ///////////////////////////////////////////////////////////
  // [ERROR message] if system.in doesn't get Number
  public static void printNumberFormatException() {
    System.out.println( printError()+"숫자로 입력해주세요.");
  }

  // [ERROR message] if system.in get over Number
  public static void printNumberLimitException() {
    System.out.println( printError()+"유효한 숫자를 입력해주세요.");
  }

  // Program Exit
  public static void printProgramExit() { System.out.println("프로그램을 종료합니다...");}

  // Disaccord PassWord
  public static void printDisaccordPW() { System.out.println("비밀번호가 잘못되었습니다."); }

  //[ERROR]
  private static String printError(){ return "[ERROR] "; }
}
