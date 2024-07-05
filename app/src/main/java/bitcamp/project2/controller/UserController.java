package bitcamp.project2.controller;

import bitcamp.project2.util.Membership;
import bitcamp.project2.util.Prompt;
import bitcamp.project2.vo.User;

import java.sql.Date;
import java.util.LinkedList;
import java.util.Objects;

import static bitcamp.project2.util.Prompt.*;

public class UserController {
    static LinkedList<User> userList = new LinkedList<>();

    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static UserController uc;

    // setup Menu Instance
    public static UserController getInstance() {

        if (uc == null) {
            uc = new UserController();
        }

        return uc;
    }// Method getInstance END

    // reset UserController Instance
    public static void freeInstance() {
        uc = null;
    }// Method freeInstance END







    ///////////////////////////////////////////////////////////
    ///////////////////////// Method //////////////////////////
    ///////////////////////////////////////////////////////////
    public void user(String subMenuNo) {
        switch(subMenuNo) {
            case "1" :
                updateUser();
                break;
            case "2" :
                deleteUser();
                break;
//            case "3" :
//                addTestData();
//                break;
            default:
                printNumberLimitException();
                break;
        }
    }//Method menu END


    public void addUser() {
        User user = new User();

        String name = Prompt.input("ID? ");
        if(getUserByName(name) != null) {
            System.out.println("이미 등록된 ID입니다.");
            loading(1000);
            return;
        }
        user.setName(name);
        user.setPassword(Prompt.input("PW? "));
        user.setJoinDate(new Date(System.currentTimeMillis()));
        user.setPlanList(new LinkedList<>());

        userList.add(user);
        System.out.println("등록되었습니다.");
        loading(1000);
    }//Method addUser END


    public void listUser() {
//        String line = printLine();
        if(!userList.isEmpty()) {
            System.out.print(printLine());
            System.out.println(" No\t\tName");

            for(int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                System.out.printf(" %d.\t\t%s\n", (i + 1), user.getName());
            }

            System.out.print(printLine()+"\n");
        }
    }//Method listUser END

    private int getUserNo(){
        return Prompt.inputInt("사용자 번호?");
    }

    private void updateUser() {
//        if(userList.isEmpty()) {
//            System.out.println("[현재 관리할 사용자가 없습니다.]\n");
//            return;
//        }
        if(isValidateUserList()) {
            int userNo = getUserNo();

            if (isValidateUser(userNo)) {
                User user = userList.get(userNo - 1);

                System.out.print("[1] 이름\t[2] 비밀번호\n");

                int command = Prompt.inputInt("수정할 항목?");
                switch (command){
                    case 1:
                        String oldName = user.getName();
                        String newName = Prompt.input("'%s'님 이름 변경 : ", oldName);
                        user.setName(newName);
                        AppointmentController.getInstance().updateUserName(oldName, newName);
                        if(Membership.getInstance().getName().equals(oldName)) {
                            Membership.getInstance().setName(newName);
                        }
                        System.out.println("수정되었습니다.\n");
                        loading(1000);
                        break;
                    case 2:
                        user.setPassword(Prompt.input("'%s'님 비밀번호 변경 : ", user.getName()));
                        System.out.print("수정되었습니다.\n\n");
                        loading(1000);
                        break;
                    default:
                        System.out.println("잘못된 항목입니다.");
                        loading(1000);
                        break;
                }
//                if (command == 1) {
//                    user.setName(Prompt.input("'%s'님 이름 변경 : ", user.getName()));
//                    System.out.println("수정되었습니다.\n");
//                } else if (command == 2) {
//                    user.setPassword(Prompt.input("'%s'님 비밀번호 변경 : ", user.getName()));
//                    System.out.println("수정되었습니다.\n");
//                } else {
//                    System.out.println("잘못된 항목입니다.");
//                }
            }//if isValidateUser END
        }//if isValidateUserList END

    }//Method updateUser END

    private void deleteUser() {
//        if(userList.isEmpty()) {
//            System.out.println("현재 관리할 사용자가 없습니다.\n");
//            return;
//        }

        if(isValidateUserList()) {
            int userNo = getUserNo();
            if (isValidateUser(userNo)) {
                User user = userList.get(userNo - 1);

                String command = Prompt.input("%s 님을 삭제하시겠습니까?(y/n)", user.getName());
                if (command.equals("Y") || command.equals("y")) {
                    userList.remove(userNo - 1);
                    System.out.printf("[%s 님을 삭제했습니다.]\n\n", user.getName());
                    AppointmentController.getInstance().updateUserName(user.getName(), "");
                    loading(1000);
                }
            }
        }
    }//Method deleteUser END

    private boolean isValidateUserList(){
        if(userList.isEmpty()) {
            System.out.print("[현재 관리할 사용자가 없습니다.]\n");
            loading(1000);
            return false;
        }
        return true;
//        return userList.isEmpty()? false : true;
    }

    private boolean isValidateUser(int userNo) {
        if(userNo < 1 || userNo > userList.size()) {
            System.out.print("[존재하지 않는 사용자입니다.]\n");
            loading(1000);
            return false;
        } else {
            return true;
        }
    }//Method isValidateUser END

    public User getUserByName(String name) {
        for(User user : userList) {
            if(Objects.equals(user.getName(), name)) {
                return user;
            }
        }
        return null;
    }//Method getUserByName END

//    private void addTestData() {
//        Date date = new Date(System.currentTimeMillis());
//        userList.add(new User("윤상", "0000", date, null));
//        userList.add(new User("선아", "1111", date, null));
//        userList.add(new User("무명", "2222", date, null));
//        System.out.println("테스트데이터 등록\n");
//    }//Method addTestData END



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

    public LinkedList<User> getUserList() {
        return userList;
    }

    public void setUserList(LinkedList<User> userList) {
        this.userList = userList;
    }
}//Class UserController END
