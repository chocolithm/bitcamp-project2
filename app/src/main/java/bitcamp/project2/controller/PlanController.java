package bitcamp.project2.controller;

import bitcamp.project2.util.Menu;
import bitcamp.project2.util.Prompt;
import bitcamp.project2.vo.Plan;
import bitcamp.project2.vo.User;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static bitcamp.project2.util.Prompt.*;

public class PlanController{
    LinkedList<Plan> planList = new LinkedList<>();
//      LinkedList<Plan> planList;

    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static PlanController m;

    // setup Menu Instance
    public static PlanController getInstance(User user) {

        if (m == null) {
            m = new PlanController(user);
        }

        return m;
    }// Method getInstance END

    // reset PlanController Instance
    public static void freeInstance() {
        m = null;
    }// Method freeInstance END


    PlanController(User user){
        this.planList.addAll(user.getPlanList());
    }




    public void executePlanCommand(String subMenuNo) { // 나중에 title로 수정
        switch(subMenuNo) {
            case "1" :
                addPlan();
                break;
            case "2" :
                updatePlan();
                break;
            case "3" :
                deletePlan();
                break;
            case "4" :
                addTestData();
                break;
        }
    }

    public void listPlan() {
//        String line = printLine();

        if(!planList.isEmpty()) {
            System.out.print(printLine());
            System.out.println("No\t\tTitle\t\t\t\tDate");
            for(int i = 0; i < planList.size(); i++) {
                Plan plan = planList.get(i);
                String date = "";
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
                String startDate = formatter.format(plan.getStartDate());
                String endDate = formatter.format(plan.getEndDate());

                if(startDate.equals(endDate)) {
                    date = startDate;
                } else {
                    date = startDate + " ~ " + endDate;
                }
                if(plan.getRepeatedDays() != null) {
                    date = date + " " + plan.getRepeatedDays();
                }
                System.out.printf("%d.\t\t%s%s%s\n", (i + 1), plan.getTitle(), getTabByString(plan.getTitle()), date);
            }
            System.out.print(printLine()+"\n");
        }
    }

    private void addPlan() {
        Plan plan = new Plan();
        plan.setTitle(Prompt.input("제목? "));

        setDates(plan);

        planList.add(plan);
        System.out.println("등록되었습니다.\n");
    }

    private void updatePlan() {
        if(planList.isEmpty()) {
            System.out.println("현재 등록된 일정이 없습니다.\n");
            return;
        }

        int planNo = Prompt.inputInt("수정할 일정?");
        if (isValidatePlan(planNo)) {
            Plan plan = planList.get(planNo - 1);

            System.out.print("[1] 제목\t[2] 기간\n");

            int command = Prompt.inputInt("수정할 항목?");
            if (command == 1) {
                plan.setTitle(Prompt.input("'%s' 이름 변경 : ", plan.getTitle()));
                System.out.println("수정되었습니다.\n");

            } else if (command == 2) {

                setDates(plan);

                System.out.println("수정되었습니다.\n");
            } else {
                System.out.println("잘못된 항목입니다.");
            }
        }
    }

    private void deletePlan() {
        if(planList.isEmpty()) {
            System.out.println("현재 등록된 일정이 없습니다.\n");
            return;
        }

        int planNo = Prompt.inputInt("삭제할 일정?");
        if (isValidatePlan(planNo)) {
            Plan plan = planList.get(planNo - 1);

            String command = Prompt.input("%s 일정을 삭제하시겠습니까?(y/n)", plan.getTitle());
            if(command.equals("Y") || command.equals("y")) {
                planList.remove(planNo - 1);
                System.out.printf("'%s' 일정을 삭제했습니다.\n\n", plan.getTitle());
            }
        }
    }

    private boolean isValidatePlan(int planNo) {
        if(planNo < 1 || planNo > planList.size()) {
            System.out.println("없는 일정입니다.\n");
            return false;
        } else {
            return true;
        }
    }

    public static String getTabByString(String str) {
        int count = 0;
        int len = str.length();
        Pattern pattern = Pattern.compile("[가-힣]");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            count++;
        }
        return "\t".repeat(((20 - count - len + 3) / 4));
    }

    private void setDates(Plan plan) {
        int month = Prompt.inputInt("월? ");
        if(month < 1 || month > 12) {
            System.out.printf("%d월은 없는걸...?\n", month);
            return;
        }
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

        plan.setStartDate(Date.valueOf(startDate));
        plan.setEndDate(Date.valueOf(endDate));
        plan.setRepeatedDays(repeatedDays);
    }

    private void addTestData() {
        planList.add(new Plan(1, "가족모임", Date.valueOf("2024-07-01"), Date.valueOf("2024-07-10"), null));
        planList.add(new Plan(2, "민지랑 저녁식사", Date.valueOf("2024-07-11"), Date.valueOf("2024-07-11"), null));
        planList.add(new Plan(3, "회식", Date.valueOf("2024-07-05"), Date.valueOf("2024-07-06"), null));
        planList.add(new Plan(4, "일", Date.valueOf("2024-07-05"), Date.valueOf("2024-07-06"), "월화"));
        System.out.println("테스트데이터 등록\n");
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


    public LinkedList<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(LinkedList<Plan> planList) {
        this.planList = planList;
    }
}
