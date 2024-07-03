package bitcamp.project2.util;

import bitcamp.project2.controller.AppointmentController;
import bitcamp.project2.controller.PlanController;
import bitcamp.project2.controller.UserController;
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
    }
    /////////////////////////// Main //////////////////////////
    private void mainMenu(){
        mainMenuCommand();
    }

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
                    ac.menu();
                    break;
                case "3":   //사용자 관리
                    userMenu(Integer.parseInt(ans));
                    break;
                case "0":   //종료
                    printProgramExit();
                    return;
                default:
                    printNumberLimitException();
                    break;
            }
        }
    }


    private String printMainMenu(){
        System.out.print(printMainTUI());
        return printMenu();
    }//Method menu END

    private String printMainTUI(){
        String str = "";

//        str += "-----------------------------------------------------------------------\n";

        str += printMainMenuTUITop();

        LinkedList<String> appointmentList = AppointmentController.getInstance()
            .getAppointmentList();
        if(!appointmentList.isEmpty()) {
            for (int i = 0; i < appointmentList.size(); i++) {
                str += "\t\t" + appointmentList.get(i) + "\n";
            }
        }

        str += printMainMenuTUIBottom();


//        str += "print List\n";
//        str += "-----------------------------------------------------------------------\n";

        return str;
    }//Method printTUI END

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
    }

    private String printMainMenuTUIBottom(){
        String str = "";

        str +=  "\n" +
                printLine() +
                printLine() +
                "\n";

        return str;
    }

//    private String printMainMenuList(){
//        String command = printMenu();
////        str += "[1] 내 일정        [2] 약속 추가       [3] 사용자관리       [0] 종료\n";
//
//
//        return str;
//    }//Method printMenu END


    ///////////////////////// 1. 내 일정 ////////////////////////
    private void myToDoMenu(int ans){
        User user = UserController.getInstance().getUserList().get(Membership.loginUserNo);
        PlanController planController = PlanController.getInstance(user);

        String menuTitle = "내 일정";
        System.out.print(printLine());
        System.out.println("                            내 일정");

        while (true) {
            planController.listPlan();

            String command = printSubMenu(menuTitle, ans);
//            String[] menus = subMenus[ans - 1];
//            for (int i = 0; i < menus.length; i++) {
//                System.out.printf("[%d] %s\t", (i + 1), menus[i]);
//            }
//            System.out.println("[0] 이전");
//
//            String command = Prompt.input(String.format("메인/%s>", menuTitle));
            if(command.equals("0")) {
                break;
            }

            planController.executePlanCommand(command);
        }
    }//Method myToDoMenu END



    /////////////////////// 3. 사용자 관리 ////////////////////////
    private void userMenu(int ans){
        UserController userController = new UserController();

        if (!Prompt.input("관리자 비밀번호 : ").equals("0000")) {
            printDisaccordPW();
        } else {
            System.out.println("[사용자관리 화면에 접속합니다.]");
            while (true) {
                userController.listUser();
                String menuTitle = "사용자관리";

                String command = printSubMenu(menuTitle, ans);
//                String[] menus = subMenus[ans - 1];
//                for (int i = 0; i < menus.length; i++) {
//                    System.out.printf("[%d] %s\t", (i + 1), menus[i]);
//                }
//                System.out.println("[0] 이전");
//
//                String command = Prompt.input(String.format("메인/%s>", menuTitle));
                if (command.equals("0")) {
                    break;
                }

                userController.executeUserCommand(command);
            }
        }
    }//Method userMenu END

//    void execute(int ans) {
//        // 내 일정
//        if(ans == 1) {
//            String menuTitle = "내 일정";
//            System.out.println("[내 일정]");
//
//            while (true) {
//                planController.listPlan();
//
//                String[] menus = subMenus[ans - 1];
//                for (int i = 0; i < menus.length; i++) {
//                    System.out.printf("[%d] %s\t", (i + 1), menus[i]);
//                }
//                System.out.println("[0] 이전");
//
//                String command = Prompt.input(String.format("메인/%s>", menuTitle));
//                if(command.equals("0")) {
//                    break;
//                }
//
//                planController.executePlanCommand(command);
//            }
//        }
//
//        // 사용자 관리
//        if(ans == 3) {
//            if (!Prompt.input("관리자 비밀번호 : ").equals("0000")) {
//                MenuController.printDisaccordPW();
//            } else {
//                System.out.println("[사용자관리 화면에 접속합니다.]");
//                while (true) {
//                    userController.listUser();
//                    String menuTitle = "사용자관리";
//
//                    String[] menus = subMenus[ans - 1];
//                    for (int i = 0; i < menus.length; i++) {
//                        System.out.printf("[%d] %s\t", (i + 1), menus[i]);
//                    }
//                    System.out.println("[0] 이전");
//
//                    String command = Prompt.input(String.format("메인/%s>", menuTitle));
//                    if (command.equals("0")) {
//                        break;
//                    }
//
//                    userController.executeUserCommand(command);
//                }
//            }
//        }
//    }

}//Class Main END
