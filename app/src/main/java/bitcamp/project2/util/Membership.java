package bitcamp.project2.util;
import bitcamp.project2.vo.User;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Membership {
    LinkedList<User> bufUserList = new LinkedList<User>();


     Scanner sc = new Scanner(System.in);
     String name;
     String password;

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
    public void menu() {
        /***********************************/
        User bufuser = new User();

        bufuser.setName("OREO");
        bufuser.setPassword("0000");
        this.bufUserList.add(bufuser);
        /***********************************/

        membershipMenu();
    }


    private String membershipGuide(){
        String str = "";

        str += "[야! 먹자]\n";
        str += "[1] 로그인       [2] 회원가입        [0] 종료\n";

        return str;
    }


    private void membershipMenu(){
        String ans = ""; // user answer
        
        for (;;) {
            System.out.print(membershipGuide());
            System.out.print("\n메인 : ");
            ans = sc.nextLine();

            switch (ans){
                case "1": //login
                    if(login()){
                       return;
                    }else{
                        continue;
                    }
                case "2": //join
                    /////////////////////////////////////////////////////////
                    break;
                default:
                    System.out.print("\n[ERROR] 잘못된 입력입니다. 다시 입력해주세요.\n\n");
            }
        }
    }//Method Menu END


    private boolean login(){
        String id = ""; // user answer id
        String pw = ""; // user answer pw

        System.out.print("ID? : ");
        id = sc.nextLine();
        if(EqualUserID(id)){
            this.name = id;

            System.out.print("PW? : ");
            pw = sc.nextLine();
            if(EqualUserPW(pw)){
                this.password = pw;
                System.out.print("로그인 되었습니다.\n\n");
                return true;
            }

        }

        this.name = "";
        this.password = "";
        System.out.print("ID PW를 다시 확인해주세요.\n\n");
        return false;
    }//Method login END


    private boolean EqualUserID(String id) {

        Iterator<User> iter = bufUserList.iterator();
        User currentUser = null;
        for (int userNo = 1; iter.hasNext(); userNo++) {
            currentUser = iter.next();

            if (id.equals(currentUser.getName()))
                return true;
        }
        return false;
    }//Method EqualUserID END


    private boolean EqualUserPW(String pw){
        Iterator<User> iter = bufUserList.iterator();
        User currentUser = null;
        for (int userNo = 1; iter.hasNext(); userNo++) {
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
