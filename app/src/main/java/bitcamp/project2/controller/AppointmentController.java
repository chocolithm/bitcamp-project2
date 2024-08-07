package bitcamp.project2.controller;

import bitcamp.project2.util.Prompt;
import bitcamp.project2.vo.Plan;
import bitcamp.project2.vo.User;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static bitcamp.project2.util.Prompt.*;

public class AppointmentController {
    UserController uc = UserController.getInstance();

    LinkedList<User> userList = uc.getUserList();
    LinkedList<String> memberList = new LinkedList<String>();
    LinkedList<String> appointmentList = new LinkedList<>();
    String appointment = "";

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
    public void appointment(){
        setMember();
        if(!memberList.isEmpty()) {
            setDate();
        } else {
            System.out.println("추가한 멤버가 없습니다.");
            loading(1000);
        }
    }

    //////////////////////// set Member ////////////////////////
    private void setMember(){
        String ans = "";
        memberList = new LinkedList<>();

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
                System.out.print("이미 존재하는 멤버입니다. \n");
                continue;
            }
            System.out.print("존재하지 않는 멤버입니다. \n");
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
        int month = getMonth();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);

        if(month > 0){
            calendar.set(Calendar.MONTH, month - 1);
            int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int[] tempAvailableDates = getTempAvailableDates(month, lastDay);
            LinkedList<Plan> availableDates = getAvailableDates(month, lastDay, tempAvailableDates);

            if (availableDates.isEmpty()) {
                System.out.println("가능한 일정이 없습니다.");
                loading(1000);
                return;
            }

            listAvailableDates(availableDates);

            if (Prompt.input("일정을 등록하시겠습니까?(y/n)").equalsIgnoreCase("y")) {
                addAppointment(month);
            }
        } else {
            System.out.println("월 입력이 잘못되었습니다.");
            loading(1000);
        }
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

    private String printSetDate(){
        String str = "";

        str += "검색할 기간(월) : ";

        return str;
    }

    private boolean isValidateDate(String ans){
        try{
            int month = Integer.parseInt(ans);
            return month > 0 && month <= 12;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public int[] getTempAvailableDates(int month, int lastDay) {
        Calendar calendar = Calendar.getInstance();
        int[] tempAvailableDates = new int[lastDay];

        for (int i = 0; i < memberList.size(); i++) {
            User user = uc.getUserByName(memberList.get(i));
            if(user.getPlanList() != null) {
                for (int j = 0; j < user.getPlanList().size(); j++) {
                    Plan plan = user.getPlanList().get(j);

                    calendar.setTime(plan.getStartDate());
                    if(calendar.get(Calendar.MONTH) == month - 1) {
                        int startDate = calendar.get(Calendar.DAY_OF_MONTH);
                        calendar.setTime(plan.getEndDate());
                        int endDate = calendar.get(Calendar.DAY_OF_MONTH);

                        for(int k = startDate - 1; k < endDate; k++) {
                            tempAvailableDates[k] = -1;
                        }
                    }
                }
            }
        }

        return tempAvailableDates;
    }

    public LinkedList<Plan> getAvailableDates(int month, int lastDay, int[] tempAvailableDates) {
        Date sDate = null, eDate = null;
        Plan plan = new Plan();
        LinkedList<Plan> availableDates = new LinkedList<>();

        for(int i = 0; i < lastDay - 1; i++) {
            if(tempAvailableDates[i] == 0) {
                if(sDate == null) {
                    sDate = Date.valueOf(String.format("2024-%02d-%02d", month, i + 1));
                }
                if(tempAvailableDates[i + 1] == -1) {
                    eDate = Date.valueOf(String.format("2024-%02d-%02d", month, i + 1));
                }
                if(i + 2 == lastDay && tempAvailableDates[i + 1] == 0) {
                    eDate = Date.valueOf(String.format("2024-%02d-%02d", month, i + 2));
                }

                if(sDate != null && eDate != null) {
                    plan.setStartDate(sDate);
                    plan.setEndDate(eDate);
                    availableDates.add(plan);

                    sDate = eDate = null;
                    plan = new Plan();
                }
            }
        }

        return availableDates;
    }

    public void addAppointment(int month) {
        Plan plan = new Plan();
        int titleLen;
        String strLen;

        plan.setTitle(Prompt.input("제목? "));
        titleLen = getlengthWord(plan.getTitle());
        strLen = "%-"+ (15-titleLen/2) +"s ";
        appointment += String.format(strLen,plan.getTitle());

        addDates(plan, month);

        appointment += " ( ";

        for(String str : memberList) {
            appointment += str + ", ";
            User user = uc.getUserByName(str);
            LinkedList<Plan> planList = user.getPlanList();
            planList.add(plan);
            user.setPlanList(planList);
        }

        appointment = appointment.substring(0, appointment.length() - 2);
        appointment += " )";
        appointmentList.add(appointment);
        appointment = "";

        sortAppointment();

        System.out.println("등록되었습니다.\n");
        loading(1000);
    }

    public void addDates(Plan plan, int month) {
        int lastDay = printCalendar(2024, month);
        String days = Prompt.input("일?(1-%d 반복할요일)", lastDay);

        String startDate;
        String endDate;
        String repeatedDays = null;

        if (days.contains(" ")) {
            repeatedDays = days.split(" ")[1];
        }
        if (days.contains("-")) {
            startDate = String.format("2024-%d-%s", month, days.split("-")[0]);
            endDate = String.format("2024-%d-%s", month, days.split("-")[1].split(" ")[0]);
        } else {
            startDate = endDate = String.format("2024-%d-%s", month, days.split(" ")[0]);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");

        plan.setStartDate(Date.valueOf(startDate));
        plan.setEndDate(Date.valueOf(endDate));
        plan.setRepeatedDays(repeatedDays);

        if(startDate.equals(endDate)) {
            appointment += printFormatterDate(formatter.format(plan.getStartDate()));
//            appointment += formatter.format(plan.getStartDate());
        } else {
            appointment += printFormatterDate(formatter.format(plan.getStartDate()), formatter.format(plan.getEndDate()), repeatedDays);
//            appointment += formatter.format(plan.getStartDate()) + " ~ " + formatter.format(plan.getEndDate());
        }

    }

    private String printFormatterDate(String start){
        return String.format("%-5s   %-5s %-14s", start, " "," ");
    }

    private String printFormatterDate(String start, String end, String repeatedDays){
        if(repeatedDays==null){
            repeatedDays = " ";
        }
        int repeatLen = getlengthWord(repeatedDays);
        String str = "%-5s ~ %-5s %-"+(14-repeatLen/2)+"s";
        return String.format(str, start, end, repeatedDays);
    }

    private void listAvailableDates(LinkedList<Plan> availableDates) {
//        String line = "--------------------------------------------------";
        System.out.print(printLine());
        System.out.printf("%3s. %-15s\n", "No", "Date");
        for(int i = 0; i < availableDates.size(); i++) {
            Plan plan = availableDates.get(i);
            String date = "";
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
            String startDate = formatter.format(plan.getStartDate());
            String endDate = formatter.format(plan.getEndDate());

            if(startDate.equals(endDate)) {
                date = startDate;
            } else {
                date = startDate + " ~ " + endDate;
            }

            System.out.printf("%3d. %-15s\n", (i + 1), date);
        }
        System.out.println(printLine());
    }

    private User getUserByName(String name) {
        for (User user : userList) {
            if (Objects.equals(name, user.getName())) {
                return user;
            }
        }

        return null;
    }

    public void updateUserName(String oldName, String newName) {
        for(int i = 0; i < appointmentList.size(); i++) {
            if(appointmentList.get(i).contains(oldName)) {
                appointmentList.set(i, appointmentList.get(i).replace(oldName, newName));
                appointmentList.set(i, appointmentList.get(i).replace(" , ", " "));
                appointmentList.set(i, appointmentList.get(i).replace(",  ", " "));
            }
        }
    }

    public void sortAppointment() {
        for (int i = 0; i < appointmentList.size() - 1; i++) {
            for(int j = i + 1; j < appointmentList.size(); j++) {
                int date1 = Integer.parseInt(appointmentList.get(i).split("\\s+")[1].replace("/", ""));
                int date2 = Integer.parseInt(appointmentList.get(j).split("\\s+")[1].replace("/", ""));
                if(date1 > date2) {
                    String temp = appointmentList.get(i);
                    appointmentList.set(i, appointmentList.get(j));
                    appointmentList.set(j, temp);
                }
            }
        }
    }

    public static String getTabByString(String str, int num) {
        int count = 0;
        int len = str.length();

        Pattern pattern = Pattern.compile("[가-힣]");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            count++;
        }

        return "\t".repeat(((num - count - len + 3) / 4));
    }//Method getTabByString END

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


    public void setAppointmentList(LinkedList<String> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public LinkedList<String> getAppointmentList() {
        return appointmentList;
    }

}//Class AppointmentController END
