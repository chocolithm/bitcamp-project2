package bitcamp.project2.util;

import bitcamp.project2.controller.*;
import bitcamp.project2.vo.User;

import java.util.LinkedList;

import static bitcamp.project2.util.Prompt.*;

public class Menu {

    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    Menu(){

    }





    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static Menu m;

    // setup Menu Instance
    public static Menu getInstance() {

        if (m == null) {
            m = new Menu();
        }

        return m;
    }// Method getInstance END

    // reset Menu Instance
    public static void freeInstance() {
        m = null;
    }// Method freeInstance END




    ///////////////////////////////////////////////////////////
    ///////////////////////// Method //////////////////////////
    ///////////////////////////////////////////////////////////
    public void menu(){
        if(loginMenu()){
            mainMenu();
        }
    }






    /////////////////////////// login //////////////////////////
    private boolean loginMenu(){
        Membership m = Membership.getInstance();
        return m.menu();
    }//Method loginMenu END





    /////////////////////////// Main //////////////////////////
    private void mainMenu(){
        mainMenuCommand();
    }//Method mainMenu END

    private void mainMenuCommand(){
        String ans = "";

        for(;;){
            printBuff();
            ans = printMainMenu();

            switch (ans){
                case "1":   //내 일정
                    myToDoMenu(Integer.parseInt(ans));
                    break;
                case "2":   //약속 추가
                    AppointmentController ac = AppointmentController.getInstance();
                    ac.appointment();
                    break;
                case "3":   //사용자 관리
                    userMenu(Integer.parseInt(ans));
                    break;
                case "0":   //종료
                    // printProgramExit();
                    menu();
                    return;
                default:
                    printNumberLimitException();
                    break;
            }
        }
    }//Method mainMenuCommand END


    private String printMainMenu(){
        System.out.print(printMainTUI());
        return printMenu();
    }//Method printMainMenu END

    private String printMainTUI(){
        String str = "";

        str += printMainMenuTUITop();

        LinkedList<String> appointmentList = AppointmentController.getInstance()
            .getAppointmentList();
        if(!appointmentList.isEmpty()) {
            for (int i = 0; i < appointmentList.size(); i++) {
                str += "\t\t" + appointmentList.get(i) + "\n";
            }
        }

        str += printMainMenuTUIBottom();

        return str;
    }//Method printMainTUI END

    private String printMainMenuTUITop(){
        String str = "";

        str +=  printLine() +
                printLine() +
                "                                                                 \n" +
                "                   __  __        __                              \n" +
                "                   \\ \\/ /__ _   / /                              \n" +
                "                    \\  / _ `/  /_/                               \n" +
                "                    /_/\\_,_/  (_)    이날에 먹자!                \n" +
                "                                                                 \n" ;

        return str;
    }//Method printMainMenuTUITop END

    private String printMainMenuTUIBottom(){
        String str = "";

        str +=  "\n" +
                printLine() +
                printLine() +
                "\n";

        return str;
    }//Method printMainMenuTUIBottom END





    ///////////////////////// 1. 내 일정 ////////////////////////
    private void myToDoMenu(int ans){
        printMyToDoMenu(ans);
    }//Method myToDoMenu END

    private void printMyToDoMenu(int ans){
        User user = UserController.getInstance().getUserList().get(Membership.loginUserNo);
        PlanController planController = PlanController.getInstance(user);

        String menuTitle = "내 일정";
        String command;


        while (true) {
            printMyToDoMenuTUI();
            planController.listPlan();

            command = printSubMenu(menuTitle, ans);

            if(command.equals("0")) {
                break;
            }

            planController.plan(command);
        }
    }//Method printMyToDoMenu END

    private void printMyToDoMenuTUI(){
        System.out.print(printLine());
        System.out.println("                                  내 일정");
    }//Method printMyToDoMenuTUI END




    ///////////////////////// 2. 약속 추가 ////////////////////////
    private void appointmentMenu(){

    }


    /////////////////////// 3. 사용자 관리 ////////////////////////
    private void userMenu(int ans){
        printUserMenu(ans);
    }//Method userMenu END

    private void printUserMenu(int ans){
        UserController uc = UserController.getInstance();

        String menuTitle = "사용자관리";
        String command;

        if (!Prompt.input("관리자 비밀번호 : ").equals("0000")) {
            printDisaccordPW();
        } else {
            System.out.println("[사용자관리 화면에 접속합니다.]");
            while (true) {
                printUserMenuTUI();
                uc.listUser();

                command = printSubMenu(menuTitle, ans);

                if (command.equals("0")) {
                    break;
                }

                uc.user(command);
            }
        }
    }//Method printUserMenu END

    private void printUserMenuTUI(){
        System.out.print(printLine());
        System.out.println("                         사용자관리");
    }//Method printUserMenuTUI END

}//Class Main END
