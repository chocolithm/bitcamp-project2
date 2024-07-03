package bitcamp.project2;

import bitcamp.project2.controller.UserController;
import bitcamp.project2.vo.Plan;
import bitcamp.project2.vo.User;
import java.sql.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class DummyData {
    static UserController uc = UserController.getInstance();
    static LinkedList<User> userList = new LinkedList<>();
    static final int sz = 4;

    public static void addDummy(){
        addDummyUser();
        addDummyPlan();
    }


    private static void addDummyUser(){
        Date date = new Date(System.currentTimeMillis());
        int No = 0;
        User[] bufuser = new User[sz];

        for(int i=0;i<sz;i++){
            bufuser[i] = new User();
        }

        bufuser[No].setName("OREO");
        bufuser[No].setPassword("0000");
        bufuser[No].setJoinDate(date);
        bufuser[No].setPlanList(new LinkedList<Plan>());
        userList.add(bufuser[No]);
        No++;


        bufuser[No].setName("선아");
        bufuser[No].setPassword("0000");
        bufuser[No].setJoinDate(date);
        bufuser[No].setPlanList(new LinkedList<Plan>());
        userList.add(bufuser[No]);
        No++;

        bufuser[No].setName("윤상");
        bufuser[No].setPassword("0000");
        bufuser[No].setJoinDate(date);
        bufuser[No].setPlanList(new LinkedList<Plan>());
        userList.add(bufuser[No]);
        No++;


        bufuser[No].setName("root");
        bufuser[No].setPassword("0000");
        bufuser[No].setJoinDate(date);
        bufuser[No].setPlanList(new LinkedList<Plan>());
        userList.add(bufuser[No]);
        No++;

        uc.setUserList(userList);

        addDummyPlan();
    }


//    public Plan(int no, String title, Date startDate, Date endDate, String repeatedDays) {
//        this.no = no;
//        this.title = title;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.repeatedDays = repeatedDays;
//    }

    private static void addDummyPlan(){
        LinkedList<Plan>[] planList = new LinkedList[sz];
        for(int i=0;i<sz;i++){
            planList[i] = new LinkedList<>();
        }


        Iterator<User> iter = userList.iterator();
        User user;

        int no;
        String title;
        Date startDate;
        Date endDate;
        String repeatedDays;

        for(int i=0;iter.hasNext();i++){
            int j=0;
            user = iter.next();

            for(; j<5; j++){
                no = 10*i+j;
                title = "Project"+String.format("%03d",no);
                startDate = Date.valueOf(String.format("2024-%d-%s", 7, (i+j)+1 ));
                endDate = Date.valueOf(String.format("2024-%d-%s", 7, (i+j)+3 ));
                repeatedDays = "";

                planList[i].add(new Plan(no, title, startDate, endDate, repeatedDays));
            }

            no = 10*i+j;
            title = "Project"+String.format("%03d",no);
            startDate = Date.valueOf(String.format("2024-%d-%s", 7, (i+j) ));
            endDate = Date.valueOf(String.format("2024-%d-%s", 7, (i+j)+14 ));
            repeatedDays = "월";

            planList[i].add(new Plan(no, title, startDate, endDate, repeatedDays));

            user.setPlanList(planList[i]);
        }
    }
}
