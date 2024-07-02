package bitcamp.project2.controller;

import bitcamp.project2.vo.Plan;
import bitcamp.project2.vo.User;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlanController {
    LinkedList<Plan> planList = new LinkedList<>();

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
        String line = "--------------------------";

        if(!planList.isEmpty()) {
            System.out.println(line);
            System.out.println("No\t\tTitle\t\t\tDate");
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
                System.out.printf("%d.\t\t%s%s%s\n", (i + 1), plan.getTitle(), getTabByString(plan.getTitle()), date);
            }
            System.out.println(line);
        }
    }

    private void addPlan() {

    }

    private void updatePlan() {

    }

    private void deletePlan() {

    }

    public static String getTabByString(String str) {
        int count = 0;
        int len = str.length();
        Pattern pattern = Pattern.compile("[\uAC00-\uD7A3]");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            count++;
        }
        return "\t".repeat(((12 - count + 3) / 4));
    }

    private void addTestData() {
        planList.add(new Plan(1, "가족모임", Date.valueOf("2024-07-01"), Date.valueOf("2024-07-10")));
        planList.add(new Plan(2, "민지랑 저녁", Date.valueOf("2024-07-11"), Date.valueOf("2024-07-11")));
        planList.add(new Plan(3, "회식", Date.valueOf("2024-07-05"), Date.valueOf("2024-07-06")));
        System.out.println("테스트데이터 등록\n");
    }
}
