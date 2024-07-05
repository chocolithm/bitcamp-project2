package bitcamp.project2;

import bitcamp.project2.controller.AppointmentController;
import bitcamp.project2.controller.UserController;
import bitcamp.project2.vo.Plan;
import bitcamp.project2.vo.User;

import java.sql.Date;
import java.util.Iterator;
import java.util.LinkedList;

import static bitcamp.project2.util.Prompt.dummyFormat;

public class DummyData {
    static UserController uc = UserController.getInstance();
    static LinkedList<User> userList = new LinkedList<>();
    static final int sz = 4;

    public static void addDummy(){
//        System.out.print("가나다라마바사아자차카타파하\n");
//        System.out.print("abcdefghi\n");
//        System.out.print("123456789\n");
//        System.out.print("-----------------------------------------\n");
        addDummyUser();
        addDummyAppoint();
    }//Method addDummy END


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
    }//Method addDummyUser END


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


        Iterator<User> iter = uc.getUserList().iterator();

        int no;
        String title;
        Date startDate;
        Date endDate;
        String repeatedDays;

        for(int userNo=0;iter.hasNext();userNo++){
            int planNo=0;
            User user = iter.next();

            for(; planNo<3; planNo++){
                no = 10*userNo+planNo;
                title = "Project"+ String.format("%03d",no);
                startDate = Date.valueOf(String.format("2024-%d-%s", 7, (userNo+planNo)+1 ));
                endDate = Date.valueOf(String.format("2024-%d-%s", 7, (userNo+planNo)+2 ));
                repeatedDays = "";

                planList[userNo].add(new Plan(no, title, startDate, endDate, repeatedDays));
            }

            no = 10*userNo+planNo;
            title = "Project"+ String.format("%03d",no);
            startDate = Date.valueOf(String.format("2024-%d-%s", 7, (userNo+planNo)+7 ));
            endDate = Date.valueOf(String.format("2024-%d-%s", 7, (userNo+planNo)+14 ));
            repeatedDays = "월";

            planList[userNo].add(new Plan(no, title, startDate, endDate, repeatedDays));
            planList[userNo].add(new Plan(no, "데이트", Date.valueOf("2024-07-30"), Date.valueOf("2024-07-30"), ""));
            planList[userNo].add(new Plan(no, "저녁약속", Date.valueOf("2024-07-25"), Date.valueOf("2024-07-25"), ""));
            planList[userNo].add(new Plan(no, "가족모임", Date.valueOf("2024-07-28"), Date.valueOf("2024-07-29"), ""));


            user.setPlanList(planList[userNo]);
        }

    }//Method addDummyPlan END


    private static void addDummyAppoint(){
        LinkedList<String> appointmentList = AppointmentController.getInstance().getAppointmentList();

        appointmentList.add(dummyFormat("프로젝트","06/24"," ", "", "( 윤상, 선아 )"));
        appointmentList.add(dummyFormat("camp", "08/24", "08/31", "", "( OREO, 윤상, 선아 )"));
        appointmentList.add(dummyFormat("술약속", "07/25", " ", "", "( OREO, root )"));
        appointmentList.add(dummyFormat("술약속", "07/27", " ", " ", "( OREO, 윤상 )"));
        appointmentList.add(dummyFormat("술약속", "07/26", " ", "", "( OREO, 선아 )"));
        appointmentList.add(dummyFormat("회의", "08/05", "08/25", "월수금", "( root, 윤상 )"));
        appointmentList.add(dummyFormat("회의", "08/25", "08/25", "화목", "( root, 선아 )"));


        AppointmentController.getInstance().setAppointmentList(appointmentList);
        AppointmentController.getInstance().sortAppointment();
    }

}//Class DummyData END
