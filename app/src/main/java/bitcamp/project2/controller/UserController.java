package bitcamp.project2.controller;

import bitcamp.project2.util.Prompt;
import bitcamp.project2.vo.User;

import java.sql.Date;
import java.util.LinkedList;

public class UserController {
    LinkedList<User> userList = new LinkedList<>();

    public void executeUserCommand(String subMenuNo) { // 나중에 title로 수정
        switch(subMenuNo) {
            case "1" :
                updateUser();
                break;
            case "2" :
                deleteUser();
                break;
            case "3" :
                addTestData();
                break;
        }
    }

    public void listUser() {
        String line = "--------------------------";

        if(!userList.isEmpty()) {
            System.out.println(line);
            System.out.println("No\t\tName");
            for(int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                System.out.printf("%d.\t\t%s\n", (i + 1), user.getName());
            }
            System.out.println(line);
        }
    }

    private void updateUser() {
        if(userList.isEmpty()) {
            System.out.println("현재 관리할 사용자가 없습니다.\n");
            return;
        }

        int userNo = Prompt.inputInt("사용자 번호?");
        if(isValidateUser(userNo)) {
            User user = userList.get(userNo - 1);

            System.out.print("[1] 이름\t[2] 비밀번호\n");

            int command = Prompt.inputInt("수정할 항목?");
            if (command == 1) {
                user.setName(Prompt.input(user.getName() + " 이름 변경 : "));
                System.out.println("수정되었습니다.\n");
            } else if (command == 2) {
                user.setPassword(Prompt.input(user.getName() + " 비밀번호 변경 : "));
                System.out.println("수정되었습니다.\n");
            } else {
                System.out.println("잘못된 항목입니다.");
            }
        }
    }

    private void deleteUser() {
        if(userList.isEmpty()) {
            System.out.println("현재 관리할 사용자가 없습니다.\n");
            return;
        }

        int userNo = Prompt.inputInt("사용자 번호?");
        if(isValidateUser(userNo)) {
            User user = userList.get(userNo - 1);

            String command = Prompt.input("%s 님을 삭제하시겠습니까?(y/n)", user.getName());
            if(command.equals("Y") || command.equals("y")) {
                userList.remove(userNo - 1);
                System.out.printf("%s 님을 삭제했습니다.\n\n", user.getName());
            }
        }
    }

    private boolean isValidateUser(int userNo) {
        if(userNo < 1 || userNo > userList.size()) {
            System.out.println("없는 사용자입니다.\n");
            return false;
        } else {
            return true;
        }
    }

    private void addTestData() {
        Date date = new Date(System.currentTimeMillis());
        userList.add(new User("윤상", "0000", date, null));
        userList.add(new User("선아", "1111", date, null));
        userList.add(new User("무명", "2222", date, null));
        System.out.println("테스트데이터 등록\n");
    }
}
