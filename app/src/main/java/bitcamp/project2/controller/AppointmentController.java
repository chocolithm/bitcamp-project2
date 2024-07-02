package bitcamp.project2.controller;

import bitcamp.project2.vo.Plan;
import bitcamp.project2.vo.User;

import java.util.Iterator;
import java.util.LinkedList;

import static bitcamp.project2.util.Prompt.*;

public class AppointmentController {
    UserController uc = UserController.getInstance();

    LinkedList<User> userList = uc.getUserList();
    LinkedList<String> memberList = new LinkedList<String>();

//    public class Plan
//        private int no;
//        private String title;
//        private Date startDate;
//        private Date endDate;
//        private String repeatedDays;
//
//
//
//    public class User
//        private String name;
//        private String password;
//        private java.sql.Date joinDate;
//        private LinkedList planList;



    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    AppointmentController(){

    }



    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static AppointmentController m;

    // setup AppointmentController Instance
    public static AppointmentController getInstance() {

        if (m == null) {
            m = new AppointmentController();
        }

        return m;
    }// Method getInstance END

    // reset AppointmentController Instance
    public static void freeInstance() {
        m = null;
    }// Method freeInstance END



    ///////////////////////////////////////////////////////////
    ///////////////////////// Method //////////////////////////
    ///////////////////////////////////////////////////////////
    public void menu(){
        setMember();
        setDate();
    }

    //////////////////////// set Member ////////////////////////
    private void setMember(){
        String ans = "";

        for(;;) {
            System.out.print(printSetMember());
            ans = getUserScanner();

            if(ans.equals("0")){
                return;
            }
            if (isExistMember(ans)) {
                if(!isDuplicateMember(ans)){
                    System.out.printf("'%s'님을 추가합니다. \n", ans);
                    memberList.add(ans);
                    continue;
                }
                System.out.printf("이미 존재하는 멤버입니다. \n");
                continue;
            }
            System.out.printf("존재하지 않는 멤버입니다. \n");
        }

    }

    private String printSetMember(){
        String str = "";

        str += "함께 할 사람(0: 다음) : ";

        return str;
    }

    private boolean isExistMember(String name){
        Iterator<User> iter = userList.iterator();
        User currentUser;
        while(iter.hasNext()) {
            currentUser = iter.next();

            if (name.equals(currentUser.getName()))
                return true;
        }
        return false;
    }

    private boolean isDuplicateMember(String name){
        Iterator<String> iter = memberList.iterator();
        String userName;
        while(iter.hasNext()) {
            userName = iter.next();

            if (name.equals(userName))
                return true;
        }
        return false;
    }

    //////////////////////// set Date ////////////////////////
    private void setDate(){
        if(getMonth()>0){
            searchDate();
        }
    }

    private String printSetDate(){
        String str = "";

        str += "검색할 기간(월) : ";

        return str;
    }

    private int getMonth(){
        System.out.print(printSetDate());
        int month;
        String ans = getUserScanner();
        if(isValidateDate(ans)){
            month = Integer.parseInt(ans);
          return month;
        }
        return -1;
    }

    private boolean isValidateDate(String ans){
        try{
            int month = Integer.parseInt(ans);
            return month>0 && month<=12 ? true : false;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private void searchDate(){

    }

}//Class AppointmentController END
