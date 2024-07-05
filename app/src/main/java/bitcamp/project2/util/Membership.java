package bitcamp.project2.util;
import bitcamp.project2.controller.UserController;
import bitcamp.project2.vo.User;

import java.util.Iterator;
import java.util.LinkedList;

import static bitcamp.project2.util.Prompt.*;

public class Membership {

    UserController uc = UserController.getInstance();
    LinkedList<User> userList = uc.getUserList();

     String name;
     String password;
     static int loginUserNo = -1;

    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    Membership(){
        this.name = "OREO";       //default
        this.password = "0000";   //default
    }




    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static Membership m;

    // setup Membership Instance
    public static Membership getInstance() {

        if (m == null) {
            m = new Membership();
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
    public boolean menu() {
        return membershipMenu();
    }

    private int getLoginUserNo(){
        Iterator<User> iter = userList.iterator();
        User currentUser = null;
        int userNo = 0;
        while(iter.hasNext()) {
            currentUser = iter.next();

            if (this.name.equals(currentUser.getName()))
                return userNo;
            userNo++;
        }
        return -1;
    }

    private String membershipGuide(){
        String str = "";

        str += printMembershipTUI();
        str += "[1] 로그인       [2] 회원가입        [0] 종료\n";

        return str;
    }

    private String printMembershipTUI(){
        String str = "";

        str+=
                "\n\n"+YAPINK+BOLD +"++-----------------------------------------------------------------------++\n" +
                "++-----------------------------------------------------------------------++\n" +
                "||"+RESET+"                                                                       "+YAPINK+BOLD +"||\n" +
                "||"+RESET+"               ____    ____  ___        __                             "+YAPINK+BOLD +"||\n" +
                "||"+RESET+"               \\   \\  /   / /   \\      |  |                            "+YAPINK+BOLD +"||\n" +
                "||"+RESET+"                \\   \\/   / /  ^  \\     |  |                            "+YAPINK+BOLD +"||\n" +
                "||"+RESET+"                 \\_    _/ /  /_\\  \\    |  |                            "+YAPINK+BOLD +"||\n" +
                "||"+RESET+"                   |  |  /  _____  \\   |__|                            "+YAPINK+BOLD +"||\n" +
                "||"+RESET+"                   |__| /__/     \\__\\  (__)  밥한번 먹자!!             "+YAPINK+BOLD +"||\n" +
                "||"+RESET+"                                                                       "+YAPINK+BOLD +"||\n" +
                "||"+RESET+"                                                                       "+YAPINK+BOLD +"||\n" +
                "||"+RESET+"                                                       강윤상, 이선아  "+YAPINK+BOLD +"||\n" +
                "++-----------------------------------------------------------------------++\n"+
                "++-----------------------------------------------------------------------++"+RESET+"\n\n";

        return str;
    }

    private boolean membershipMenu(){
        String ans = ""; // user answer
        
        for (;;) {
            System.out.print(membershipGuide());
            System.out.print(CYAN+"\n> "+RESET);
            ans = getUserScanner();

            switch (ans){
                case "1": //login
                    if(login()){
                        this.loginUserNo = getLoginUserNo();
                        return true;
                    }else{
                        continue;
                    }
                case "2": //join
                    uc.addUser();
                    continue;
                case "0":
                    printProgramExit();
                    return false;
                default:
                    printNumberLimitException();
            }
        }
    }//Method Menu END


    private boolean login(){
        String id = ""; // user answer id
        String pw = ""; // user answer pw

        System.out.print(CYAN+"ID? : "+RESET);
        id = getUserScanner();
        if(EqualUserID(id)){
            this.name = id;

            System.out.print(CYAN+"PW? : "+RESET);
            pw = getUserScanner();
            if(EqualUserPW(pw)){
                this.password = pw;

                printSuccessLogin();
                return true;
            }
        }

        this.name = "";
        this.password = "";
        printDisaccordLogin();
        return false;
    }//Method login END


    private boolean EqualUserID(String id) {
        Iterator<User> iter = userList.iterator();
        User currentUser = null;
        while(iter.hasNext()) {
            currentUser = iter.next();

            if (id.equals(currentUser.getName()))
                return true;
        }
        return false;
    }//Method EqualUserID END


    private boolean EqualUserPW(String pw){
        Iterator<User> iter = userList.iterator();
        User currentUser = null;
        while(iter.hasNext()) {
            currentUser = iter.next();

            if (pw.equals(currentUser.getPassword()))
                return true;
        }
        return false;
    }
    ///////////////////////////////////////////////////////////
    ///////////////// public getter, setter ///////////////////
    ///////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////
    //////////////////////////// -- ///////////////////////////
    //////////////////////////// -- ///////////////////////////
    //////////////////////////// -- ///////////////////////////
    //////////////////////// ---------- ///////////////////////
    ////////////////////////// ------ /////////////////////////
    //////////////////////////// -- ///////////////////////////
    ///////////////////////////////////////////////////////////


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}//Class Membership END
