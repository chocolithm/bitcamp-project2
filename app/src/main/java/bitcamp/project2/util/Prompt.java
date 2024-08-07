package bitcamp.project2.util;

import java.io.UnsupportedEncodingException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Prompt {

  static Scanner ans = new Scanner(System.in);
  static String[] mainMenus = new String[]{"내 일정", "약속추가", "사용자관리"};
  static String[][] subMenus = {
          {"등록", "수정", "삭제"},
          {},
          {"수정", "삭제"}
  };


  ///////////////////////////////////////////////////////////
  //////////////////////// print Menu ///////////////////////
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
  ////////////////////// print Calendar /////////////////////
  ///////////////////////////////////////////////////////////
  public static final String BOLD = "\033[1m";

  public static final String RED = "\033[31m";
  public static final String BLUE = "\033[34m";
  public static final String YAPINK = "\033[38;5;197m";
  public static final String CYANBLUE = "\033[38;5;51m";
  public static final String CYAN = "\033[96m";

  public static final String YAPINK_BACKGROUND = "\033[48;2;247;50;116m";

  public static final String RESET = "\033[0m";


  public static int printCalendar(int year, int month) {
    System.out.println(month + "월");
    System.out.println(RED+"일"+RESET+"  월  화  수  목  금  "+BLUE+"토"+RESET);

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
      switch ( date.getDayOfWeek() ){
        case DayOfWeek.SUNDAY :
          System.out.printf(RED+"%2d  "+RESET, date.getDayOfMonth());
          break;
        case DayOfWeek.SATURDAY:
          System.out.printf(BLUE+"%2d  "+RESET, date.getDayOfMonth());
          break;
        default:
          System.out.printf("%2d  ", date.getDayOfMonth());
          break;
      }
//      System.out.printf("%2d  ", date.getDayOfMonth());

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
    try{
      return Integer.parseInt(input(format, args));
    }catch (NumberFormatException e){
      printNumberFormatException();
      return -1;
    }
  }

  public static void close() {
    ans.close();
  }

  ///////////////////////////////////////////////////////////
  ///////////////////////// print ///////////////////////////
  ///////////////////////////////////////////////////////////

  // [ERROR message] if system.in doesn't get Number
  public static void printNumberFormatException() {
    System.out.println( printError()+"숫자로 입력해주세요.]");
  }

  // [ERROR message] if system.in get over Number
  public static void printNumberLimitException() {
    System.out.println( printError()+"유효한 숫자를 입력해주세요.]");
  }

  // Program Exit
  public static void printProgramExit() {
    System.out.println("[프로그램을 종료합니다...]");
//    loading(2000);
  }

  // Disaccord PassWord
  public static void printDisaccordPW() {
    System.out.println("[비밀번호가 잘못되었습니다.]");
    loading(1000);
  }

  // Disaccord ID
  public static void printDisaccordID() {
    System.out.println("[존재하지 않는 사용자입니다.]");
    loading(1000);
  }

  // Disaccord LogIn
  public static void printDisaccordLogin() {
    System.out.println("[ID PW를 다시 확인해주세요.]\n\n");
    loading(1000);
  }

  //Success LogIn
  public static void printSuccessLogin() {
    System.out.print("[로그인 되었습니다.]\n\n");
    loading(2000);
  }

  //[ERROR]
  private static String printError(){ return "[ERROR) "; }

  //Line
  public static String printLine(){ return YAPINK+BOLD+"++-----------------------------------------------------------------------++"+RESET+"\n"; }

//console clear
  public static void printBuff(){
    for(int i=0;i<35;i++) {
      System.out.print("\n");
    }
  }

  ///////////////////////////////////////////////////////////
  /////////////////////// print List ////////////////////////
  ///////////////////////////////////////////////////////////
  //dummy format(koean)
  public static String dummyFormat(String title, String start, String end, String repeat, String mem){
    int titleLen = getlengthWord(title);
    int repeatLen = getlengthWord(repeat);
    String str ="";

//    System.out.println(title+")"+"titleLen: "+titleLen+" repeatLen: "+repeatLen+"\n");
    if(end.equals(" ")){
      str = "%-"+ (15-titleLen/2) +"s %-5s   %-5s %-"+(14-repeatLen/2)+"s %-20s";
      return String.format(str, title, start, end, repeat, mem);
    }else{
      str = "%-"+ (15-titleLen/2) +"s %-5s ~ %-5s %-"+(14-repeatLen/2)+"s %-20s";
      return String.format(str, title, start, end, repeat, mem);
    }
  }

  //check byte(korean)
  public static int getlengthWord(String word){
    if(!getType(word)) {
      try {
        return word.getBytes("euc-kr").length;
      } catch (UnsupportedEncodingException e) {
        return 0;
      }
    }
    return 0;
  }

//get type(english?)
  public static boolean getType(String word){
    return !Pattern.matches("^[가-힣]*$", word);
  }







  //loading (...)
  public static void loading(long time){
    long sz = time/1000;
    try {
      for(int i=0;i<sz;i++) {
//        System.out.print(".");
        Thread.sleep(time/sz);
      }
      System.out.print("\n");
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      // e.printStackTrace();
    }
  }

  // 사용자가 메시지를 확인해야 할 때 => loading()을 통합
//  public static void printReturnToPrevious(String message) {
//    System.out.println(message);
//    System.out.println("이전으로 돌아갑니다. (엔터)");
//    getUserScanner();
//  }


}
