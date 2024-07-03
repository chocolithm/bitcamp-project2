package bitcamp.project2.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
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

  public static String printMenu(){
    String[] menus = mainMenus;
    for (int i = 0; i < menus.length; i++) {
      System.out.printf("[%1d] %-8s\t", (i + 1), menus[i]);
    }
    System.out.println("[0] 이전");

    return Prompt.input(String.format("메인>"));
  }


  public static String printSubMenu(String menuTitle, int ans){
    String[] menus = subMenus[ans - 1];
    for (int i = 0; i < menus.length; i++) {
      System.out.printf("[%1d] %-8s\t", (i + 1), menus[i]);
    }
    System.out.println("[0] 이전");

    return Prompt.input(String.format("메인/%s>", menuTitle));
  }


  ///////////////////////////////////////////////////////////
  ////////////////////// printCalendar //////////////////////
  ///////////////////////////////////////////////////////////
  public static int printCalendar(int year, int month) {
    System.out.println(month + "월");
    System.out.println("월  화  수  목  금  토  일");

    // 해당 월의 첫 날짜 계산
    LocalDate date = LocalDate.of(year, month, 1);

    // 첫 날짜 이전의 공백 출력
    int emptyDays = date.getDayOfWeek().getValue() % 7;
    for (int i = 0; i < emptyDays; i++) {
      System.out.print("    ");
    }

    int lastDay = 0;
    // 해당 월의 모든 날짜 출력
    while (date.getMonthValue() == month) {
      // 날짜 출력
      System.out.printf("%2d  ", date.getDayOfMonth());

      // 다음 날짜로 이동
      date = date.plusDays(1);

      // 토요일마다 줄 바꿈
      if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
        System.out.println();
      }

      lastDay++;
    }

    System.out.println();
    return lastDay;
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
  public static void printProgramExit() { System.out.println("[프로그램을 종료합니다...]"); }

  // Disaccord PassWord
  public static void printDisaccordPW() { System.out.println("[비밀번호가 잘못되었습니다.]"); }

  // Disaccord ID
  public static void printDisaccordID() { System.out.println("[존재하지 않는 사용자입니다.]"); }

  // Disaccord LogIn
  public static void printDisaccordLogin() { System.out.println("[ID PW를 다시 확인해주세요.]\n\n"); }

  //Success LogIn
  public static void printSuccessLogin() {System.out.print("[로그인 되었습니다.]\n\n");}

  //[ERROR]
  private static String printError(){ return "[ERROR] "; }

  //Line
  public static String printLine(){ return "++-------------------------------------------------------------++\n"; }

  public static void printBuff(){
    for(int i=0;i<30;i++) {
      System.out.print("\n");
    }
  }

  public static void printReturnToMain(String message) {
    System.out.println(message);
    System.out.println("메인으로 돌아갑니다. (엔터)");
    getUserScanner();
  }


}
